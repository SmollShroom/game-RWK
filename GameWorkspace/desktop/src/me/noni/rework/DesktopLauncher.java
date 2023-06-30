package me.noni.rework;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.noni.rework.GameRWK;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("ReworkPR");
	    config.setWindowedMode(GameRWK.WIDTH, GameRWK.HEIGHT);
	    config.setResizable(false);

		new Lwjgl3Application(new GameRWK(), config);
	}
}
