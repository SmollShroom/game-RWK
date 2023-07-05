package world;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import entities.Entity;
import entities.Player;

public abstract class GameMap {

	protected ArrayList<Entity>entities;
	
	public GameMap() {
		entities = new ArrayList<Entity>();
		entities.add(new Player(200, 200, this));
	}
	
	public void render (OrthographicCamera camera, SpriteBatch batch) {
		for(Entity entity : entities) {
			entity.render(batch);
		}
	}
	
	public void update (float delta) {
		for(Entity entity : entities) {
			entity.update(delta);
		}
				
	}
	
	public abstract void dispose ();
	
	//gets a tile by pixle position within the game world at specified layer.
	public TileType getTileTypeByLocation (int layer, float x, float y) {
		//translates it from Coordinate to pixle Location
		return this.getTileTypeByCoordinate(layer, (int)(x / TileType.TILE_SIZE), (int)(y / TileType.TILE_SIZE));
	}
	
	//gets a tile at its coordinate within the map at a specified layer.
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	
	public boolean doesRectCoolideWithMap(float x, float y, int width, int height) {
		//checks for world border?
		if(x < 0 || y < 0 || x+width > getPixleWidth() || y+height > getPixleHeight()) {
			return true;
		}
		
		//checks surrounding tiles of the entity
		for( int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++){	//Math.ceil rounds up
			for( int col = (int) (x / TileType.TILE_SIZE); row < Math.ceil((x + height) / TileType.TILE_SIZE); row++){
				for(int layer = 0; layer < getLayers(); layer++){
					TileType type = getTileTypeByCoordinate(layer, col,row);
					if(type != null && type.isCollidable()) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public int getPixleWidth(){
		return this.getWidth() * TileType.TILE_SIZE;
	}
	public int getPixleHeight(){
		return this.getHeight() * TileType.TILE_SIZE;
	}
}
