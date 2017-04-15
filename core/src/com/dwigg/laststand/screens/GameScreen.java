package com.dwigg.laststand.screens;

import com.badlogic.gdx.Screen;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.level.Level;

public class GameScreen implements Screen {

    private MainGame game;

    private Level level;

    public GameScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        level = new Level(
                game,
                game.getSpriteBatch(),
                game.getShapeRenderer(),
                game.getCamera()
        );
    }

    @Override
    public void render(float delta) {
        draw();

        if (!game.isPaused()) {
            update(delta);
        }
    }

    private void update(float delta) {
        level.update(delta);
    }

    private void draw() {
        level.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        level.dispose();
    }
}
