package me.noni.rework;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import entities.Entity;
import world.GameMap;
import world.TiledGameMap;

public class GameScreen implements Screen {
	
	final GameRWK game;
	GameMap gameMap;
	
	private FitViewport gameViewport;
	
	Texture dummyImage;
	OrthographicCamera camera;
	
	

	public GameScreen(final GameRWK game) {
		this.game = game;
		
		gameMap = new TiledGameMap();
		
		
        gameViewport = new FitViewport(game.WIDTH, game.HEIGHT);

//		//for minimap:
//		mapViewport = new FitViewport(800,800);
//		mapViewport.getCamera().position.set(400, 400, 00);
//		mapViewport.setScreenBounds(Gdx.graphics.getWidth() - 120, 20, 100, 100);

		dummyImage = new Texture(Gdx.files.internal("data/dummy.png"));

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


//		// create a Rectangle to logically represent the bucket
//		player = new Rectangle();
//		player.x = playerScreenX;
//		player.y = playerScreenY;
//		player.width = game.TILE_SIZE;
//		player.height = game.TILE_SIZE;

		
//		stateTime = 0f;	//reset

	}

	@Override
	public void render(float delta) {

		if(Gdx.input.isTouched()) {				//dragging the camera
			camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			
			// tell the camera to update its matrices.
			camera.update();
		}
//		if(Gdx.input.justTouched()) {			//get the clicked tile infos
//			Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));	//location in the gameWorld
//			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
//			if(type != null) {
//				System.out.println("Clicked on Tile: "+type.getId()+" "+ type.getName()+" "+type.isCollidable()+" "+type.getDamage());
//			}
//		}
		ScreenUtils.clear(0.1f, 0f, 0.3f, 1); 	// clear the screen with a color

		
        gameViewport.getCamera().position.set(0, 0, 0);

		    gameViewport.apply();
			game.batch.setProjectionMatrix(camera.combined);	// tell the SpriteBatch to render in the coordinate system specified by the camera.
			
			
			
				game.batch.begin();		///////////////////////////////////////////				
				
				game.font.draw(game.batch, "Just some Text on the top-left", 2, Gdx.graphics.getHeight()-2);
	//			System.out.println(Gdx.graphics.getFramesPerSecond());
				
				game.batch.end();		///////////////////////////////////////////

				gameMap.update(delta);
				gameMap.render(camera, game.batch);
				

	}

	@Override
	public void resize(int width, int height) {
        gameViewport.update(width, height);
	}

	@Override
	public void show() {
		
		// start the playback of the background music  when the screen is shown
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		dummyImage.dispose();
		gameMap.dispose();
//		playerWalkSheet.dispose();
		//dispose "PlayerAnimations"??
	}
	
	
	
}
