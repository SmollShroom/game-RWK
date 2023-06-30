package me.noni.rework;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class GameScreen implements Screen {
	
	final GameRWK game;
	GameMap gameMap;
	
	private FitViewport gameViewport;
	
	Texture dummyImage;
	OrthographicCamera camera;
	Rectangle player;
		
	float playerScreenX;
	float playerScreenY;
	float playerSpeed;
	
	public static final float PLAYER_ANIMATION_SPEED = 0.5f;
	public static final int PLAYER_SIZE_PIXLE = 32;
	Texture playerWalkSheet;
	Animation<TextureRegion>[] PlayerAnimations;
	int currentAnim;
	
	float stateTime;

	

	public GameScreen(final GameRWK game) {
		this.game = game;
		
		gameMap = new TiledGameMap();
				
		playerScreenX = (game.WIDTH/2) -  game.TILE_SIZE;
		playerScreenY = (game.HEIGHT/2) -  game.TILE_SIZE;
		playerSpeed = 300;	//??add: * Gdx.graphics.getDeltaTime();??
		
		playerWalkSheet = new Texture(Gdx.files.internal("data/player/player_walking_Sheet.png"));
		PlayerAnimations = new Animation[5];
		TextureRegion[][] AnimSpriteSheet = TextureRegion.split(playerWalkSheet, PLAYER_SIZE_PIXLE, PLAYER_SIZE_PIXLE);
		
		currentAnim = 1;
		PlayerAnimations[0] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[0]);	// walking UP
		PlayerAnimations[1] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[1]);	// walking DOWN
		PlayerAnimations[2] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[2]);	// walking LEFT
		PlayerAnimations[3] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[3]);	// walking RIGHT
		PlayerAnimations[4] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[4]);	// DYING
		
        gameViewport = new FitViewport(game.WIDTH, game.HEIGHT);

//		//for minimap:
//		mapViewport = new FitViewport(800,800);
//		mapViewport.getCamera().position.set(400, 400, 00);
//		mapViewport.setScreenBounds(Gdx.graphics.getWidth() - 120, 20, 100, 100);

		dummyImage = new Texture(Gdx.files.internal("data/dummy.png"));

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// create a Rectangle to logically represent the bucket
		player = new Rectangle();
		player.x = playerScreenX;
		player.y = playerScreenY;
		player.width = game.TILE_SIZE;
		player.height = game.TILE_SIZE;

		
		stateTime = 0f;	//reset

	}

	@Override
	public void render(float delta) {
		//mby update another way for performance?
		player.x = playerScreenX; 
		player.y = playerScreenY; 
		

		if(Gdx.input.isTouched()) {				//dragging the camera
			camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			
			// tell the camera to update its matrices.
			camera.update();
		}
		if(Gdx.input.justTouched()) {			//get the clicked tile infos
			Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));	//location in the gameWorld
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
			if(type != null) {
				System.out.println("Clicked on Tile: "+type.getId()+" "+ type.getName()+" "+type.isCollidable()+" "+type.getDamage());
			}
		}
		
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			playerScreenY += playerSpeed * Gdx.graphics.getDeltaTime();
			
			if (playerScreenY > game.HEIGHT - game.TILE_SIZE)
				playerScreenY = game.HEIGHT - game.TILE_SIZE;
			
			currentAnim = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			playerScreenY -= playerSpeed * Gdx.graphics.getDeltaTime();
			
			if (playerScreenY < 0)
				playerScreenY = 0;
			
			currentAnim = 1;

		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			playerScreenX -= playerSpeed * Gdx.graphics.getDeltaTime();

			if (playerScreenX < 0)
				playerScreenX = 0;
			
			currentAnim = 2;

		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			playerScreenX += playerSpeed * Gdx.graphics.getDeltaTime();

			if (playerScreenX > game.WIDTH - game.TILE_SIZE)
				playerScreenX = game.WIDTH - game.TILE_SIZE;
			
			currentAnim = 3;

		}

		
		stateTime += delta;
		
				


		
        gameViewport.getCamera().position.set(playerScreenX, playerScreenY, 0);

			ScreenUtils.clear(0.1f, 0f, 0.3f, 1); 	// clear the screen with a color
		    gameViewport.apply();
			game.batch.setProjectionMatrix(camera.combined);	// tell the SpriteBatch to render in the coordinate system specified by the camera.
			
			gameMap.render(camera);
			
				game.batch.begin();		///////////////////////////////////////////
			
				game.batch.draw(PlayerAnimations[currentAnim].getKeyFrame(stateTime,true), playerScreenX, playerScreenY, game.TILE_SIZE, game.TILE_SIZE);
				
				
				game.font.draw(game.batch, "Just some Text on the top-left", 2, Gdx.graphics.getHeight()-2);
	//			System.out.println(Gdx.graphics.getFramesPerSecond());
				
				game.batch.end();		///////////////////////////////////////////

		

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
		playerWalkSheet.dispose();
		//dispose "PlayerAnimations"??
	}
	
	
	
}
