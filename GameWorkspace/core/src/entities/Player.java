package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import world.GameMap;

public class Player extends Entity{

	float stateTime;
	private static final int SPEED = 160;
	
	public static final float PLAYER_ANIMATION_SPEED = 0.5f;
	public static final int PLAYER_SIZE_PIXLE = 32;
	Texture playerWalkSheet;
	Animation<TextureRegion>[] PlayerAnimations;
	int currentAnim;
	
	Texture img;	////
	
	
	
	public Player(float x, float y, GameMap map) {
		
		playerWalkSheet = new Texture(Gdx.files.internal("data/player/player_walking_Sheet.png"));
		PlayerAnimations = new Animation[5];
		TextureRegion[][] AnimSpriteSheet = TextureRegion.split(playerWalkSheet, PLAYER_SIZE_PIXLE, PLAYER_SIZE_PIXLE);
		
		currentAnim = 1;
		PlayerAnimations[0] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[0]);	// walking UP
		PlayerAnimations[1] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[1]);	// walking DOWN
		PlayerAnimations[2] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[2]);	// walking LEFT
		PlayerAnimations[3] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[3]);	// walking RIGHT
		PlayerAnimations[4] = new Animation<TextureRegion>(PLAYER_ANIMATION_SPEED, AnimSpriteSheet[4]);	// DYING
		
		img = new Texture(Gdx.files.internal("data/dummy.png"));	////
	}
	
	public void create(EntitySnapshot snapshot, EntityType type, GameMap map){
		super.create(snapshot, type, map);
		
	}
	
	@Override
	public void update(float deltaTime) {
		
		super.update(deltaTime);
		

		if (Gdx.input.isKeyPressed(Keys.W)) {
			moveY(SPEED * deltaTime);
//			System.out.println("W");
			
			currentAnim = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			moveY(-SPEED * deltaTime);
//			System.out.println("S");
			
			currentAnim = 1;

		}
		if (Gdx.input.isKeyPressed(Keys.A)) {
			moveX(-SPEED * deltaTime);
//			System.out.println("A");
			
			currentAnim = 2;

		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			moveX(SPEED * deltaTime);
//			System.out.println("D");

			currentAnim = 3;
		}
		
//		map.camera.position.x = this.pos.x;
		
		stateTime += deltaTime;
		
	}
	
	@Override
	public void render(SpriteBatch batch) {
		
		batch.draw(PlayerAnimations[currentAnim].getKeyFrame(stateTime,true), pos.x, pos.y, getWidth(), getHeight());
//		batch.draw(img, pos.x, pos.y, getWidth(), getHeight());

		
	}
	
	

}
