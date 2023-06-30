package me.noni.rework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {
	
	final GameRWK game;

	OrthographicCamera camera;
	private ScreenViewport screenViewport;
	
	Texture exitButtonActive, exitButtonInactive;
	Texture playButtonActive, playButtonInactive;
	Texture background, titleTexture, buttonTexture;
//	Texture exitButtonActive, exitButtonInactive;
	private static final int NORM_BUTTON_WIDTH = 260;
	private static final int NORM_BUTTON_HEIGHT = 100;
	
	
	
	public MainMenuScreen(final GameRWK game) {
			this.game = game;
			screenViewport = new ScreenViewport();

			camera = new OrthographicCamera();
			camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
			
			exitButtonActive = new Texture(Gdx.files.internal("data/otherSprites/exitButton.png"));
			exitButtonInactive = new Texture(Gdx.files.internal("data/otherSprites/exitButton02.png"));
			playButtonActive = new Texture(Gdx.files.internal("data/otherSprites/startButton.png"));
			playButtonInactive = new Texture(Gdx.files.internal("data/otherSprites/startButton02.png"));

			background = new Texture(Gdx.files.internal("data/otherSprites/TitleBackground.png"));
			titleTexture = new Texture(Gdx.files.internal("data/otherSprites/TitleName.png"));
			buttonTexture = new Texture (Gdx.files.internal("data/otherSprites/Button01.png"));
	}
		
	@Override
	public void render(float delta) {
		ScreenUtils.clear(0.1f, 0f, 0.3f, 1);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();/////////////////////////////////////	begin a new batch and draw the bucket and all drops
		
		game.batch.draw(background, 0, 0, game.WIDTH, game.HEIGHT);
		game.batch.draw(buttonTexture , 110, 630,480,330);
		game.batch.draw(titleTexture, 190, 680, 336, 231);


		int x = 945;
		int y = 600;
		if(getMouseInBounds(Gdx.input.getX(),Gdx.input.getY(), x, y, NORM_BUTTON_WIDTH, NORM_BUTTON_HEIGHT)) {
			game.batch.draw(playButtonActive, x,y, NORM_BUTTON_WIDTH,NORM_BUTTON_HEIGHT);
				if(Gdx.input.isTouched()) {
					game.setScreen(new GameScreen(game));
					dispose(); 
				}
		}else {
			game.batch.draw(playButtonInactive, x,y, NORM_BUTTON_WIDTH,NORM_BUTTON_HEIGHT);
		}
		
		y -= 160;
		if(getMouseInBounds(Gdx.input.getX(),Gdx.input.getY(), x, y, NORM_BUTTON_WIDTH, NORM_BUTTON_HEIGHT)) {
			game.batch.draw(exitButtonActive, x,y, NORM_BUTTON_WIDTH,NORM_BUTTON_HEIGHT);
				if(Gdx.input.isTouched()) {
					Gdx.app.exit();
					dispose(); 
				}
		}else {
			game.batch.draw(exitButtonInactive, x,y, NORM_BUTTON_WIDTH,NORM_BUTTON_HEIGHT);
		}
		
		game.batch.end();/////////////////////////////////////		will submit all drawing requests we made at once
		
	}
	

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	
	public boolean getMouseInBounds(int xPos, int yPos, int x, int y, int width, int height) {
		boolean bool = false;
		
		if(xPos >= x && xPos <= x + width && game.HEIGHT -yPos >= y && game.HEIGHT -yPos <= y + height) {	//for some reason have to subtract the screen HEIGHT form y(mby cause ViewPort?)
				bool = true;
		}
		
		return bool;
	}

}
