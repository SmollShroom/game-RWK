package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import world.GameMap;

public abstract class Entity {

	protected int id;
	protected Vector2 pos;
	protected EntityType type;
	protected GameMap map;
	
	public void create(EntitySnapshot snapshot, EntityType type, GameMap map) {
		this.pos = new Vector2(snapshot.getX(),snapshot.getY());
		this.type = type;
		this.map = map;
	}
	
	public void update(float deltaTime) {
		
	}
	
	public abstract void render(SpriteBatch batch);

	protected void moveX(float amount) {
		float newX = this.pos.x + amount;
		if(!map.doesRectCoolideWithMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
			System.out.println("moveX " + newX);
		}
	}
	protected void moveY(float amount) {
		float newY = this.pos.y + amount;
		if(!map.doesRectCoolideWithMap(pos.x, newY, getWidth(), getHeight())) {
			this.pos.y = newY;
			System.out.println("moveY " + newY);
		}
	}
	
	public EntitySnapshot getSaveSnapshot() {
		return new EntitySnapshot(type.getId(), pos.x, pos.y);
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
		
	}
	
	public float getY() {
		return pos.y;
	}

	public EntityType getType() {
		return type;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	
	public int getHeight() {
		return type.getHeight();
	}
	
}
