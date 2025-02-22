package entities;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

import Monster.Slime_Green;
import world.GameMap;

@SuppressWarnings("rawtypes")
public enum EntityType {

	PLAYER("Player", Player.class, 64, 64),
	SMILE_GREEN("slime green", Slime_Green.class, 64, 64);
	
	private String id;
	private Class loaderClass;
	private int width, height;
	
	private EntityType(String id, Class loaderClass, int width, int height) {
		this.id = id;
		this.loaderClass = loaderClass;
		this.width = width;
		this.height = height;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public static Entity createEntityUsingSnapshot(EntitySnapshot entitySnapshot, GameMap map) {
		EntityType type = entityTypes.get(entitySnapshot.type);
		try {
			@SuppressWarnings("unchecked")
			Entity entity = ClassReflection.newInstance(type.loaderClass);
			entity.create(entitySnapshot, type, map);
			return entity;
		} catch (ReflectionException e) {
			Gdx.app.error("Entity Loader", "Could not load entity of type" + type.id);
			return null;
		}
	}
	
	private static HashMap<String,EntityType> entityTypes;
	
	static {
		entityTypes = new HashMap<String , EntityType>();
		for(EntityType type : EntityType.values()){
			entityTypes.put(type.id, type);
		}
	}
	
}
