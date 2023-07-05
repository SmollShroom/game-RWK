package entities;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import world.GameMap;

public class EntityLoader {

	private static Json json = new Json();
	
	public static ArrayList<Entity> loadEntities (String id, GameMap map, ArrayList<Entity> currentEntities){
		Gdx.files.local("data/").file().mkdirs();
		FileHandle file = Gdx.files.local("data/"+id+".entities");
		
		if(file.exists()) {
			EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
			ArrayList<Entity> entities = new ArrayList <Entity>();
			for(EntitySnapshot snapshot : snapshots) {
				entities.add(EntityType.createEntityUsingSnapshot(snapshot, map));
			}
			return entities;
		}else {
//			Gdx.app.error("EntityLoader", "Could not load entities.");
			saveEntities(id, currentEntities);
			return null;
		}
	}
	
	public static void saveEntities(String id, ArrayList<Entity> entities) {
		ArrayList<EntitySnapshot> snapshots = new ArrayList<EntitySnapshot>();
		for(Entity entity : entities) {
			snapshots.add(entity.getSaveSnapshot());
		}
		Gdx.files.local("data/").file().mkdirs();
		FileHandle file = Gdx.files.local("data/" + id + ".entities");
		file.writeString(json.prettyPrint(snapshots), false);
	}
	
}
