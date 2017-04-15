package com.dwigg.laststand.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.level.managers.BackgroundManager;
import com.dwigg.laststand.utils.Constants;

public class GameOverScreen implements Screen {

    private MainGame game;

    private BackgroundManager background;
    private Sprite gameOverSprite;

    public GameOverScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new BackgroundManager();
        gameOverSprite = new Sprite(new Texture("img/died_last_stand.png"));
        gameOverSprite.setPosition(
                (Constants.V_WIDTH / 2) - (gameOverSprite.getWidth() / 2),
                400);
    }

    @Override
    public void render(float delta) {
        draw();

        if (!game.isPaused()) {
            update(delta);
        }
    }

    private void update(float delta) {
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
        }
    }

    private void draw() {
        background.draw(game.getSpriteBatch());
        game.getSpriteBatch().begin();
        gameOverSprite.draw(game.getSpriteBatch());
        game.getSpriteBatch().end();
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
        background.dispose();
        gameOverSprite.getTexture().dispose();

    }
}
