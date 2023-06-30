package me.noni.rework;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import world.GameMap;
import world.TiledGameMap;

public class GameRWK extends Game{

	public SpriteBatch batch;
	public BitmapFont font;
	
	public static final int WIDTH = 1800;
	public static final int HEIGHT = 1013;
	public static final int TILE_SIZE = 64;
	
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font
		this.setScreen(new MainMenuScreen(this));
		
	}

	public void render() {
		super.render(); // important!

	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
