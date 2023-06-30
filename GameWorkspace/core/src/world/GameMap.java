package world;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {

	public abstract void render (OrthographicCamera camera);
	public abstract void update (float delta);
	public abstract void dispose ();
	
	//gets a tile by pixle position within the game world at specified layer.
	public TileType getTileTypeByLocation (int layer, float x, float y) {
		//translates it from Coordinate to pixle Location
		return this.getTileTypeByCoordinate(layer, (int)(x / TileType.TILE_SIZE), (int)(y / TileType.TILE_SIZE));
	}
	
	//gets a tile at its coordinate within the map at a specified layer.
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
}
