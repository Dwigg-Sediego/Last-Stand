package com.dwigg.laststand;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dwigg.laststand.screens.MainMenuScreen;
import com.dwigg.laststand.utils.Constants;

public class MainGame extends Game {

	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private OrthographicCamera camera;
	private Viewport viewport;
	private boolean paused;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);
		paused = false;

		camera.position.set(
				viewport.getWorldWidth() / 2,
				viewport.getWorldHeight() / 2,
				0
		);

		//bgm.play();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void render() {
		clearScreen();
		updateCamera();
		super.render();
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void updateCamera() {
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	@Override
	public void dispose() {
		super.dispose();
		spriteBatch.dispose();
		shapeRenderer.dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public boolean isPaused() {
		return paused;
	}
}
