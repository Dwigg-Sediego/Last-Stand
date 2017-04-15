package com.dwigg.laststand.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Constants.TITLE + " " + Constants.VERSION;
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;

		new LwjglApplication(new MainGame(), config);
	}
}
