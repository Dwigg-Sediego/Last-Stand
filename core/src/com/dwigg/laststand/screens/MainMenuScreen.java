package com.dwigg.laststand.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.level.managers.BackgroundManager;
import com.dwigg.laststand.utils.Constants;

public class MainMenuScreen implements Screen {

    private MainGame game;

    private BackgroundManager background;
    private Sprite titleSprite;

    public MainMenuScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        background = new BackgroundManager();
        titleSprite = new Sprite(new Texture("img/title_last_stand.png"));
        titleSprite.setPosition(
                (Constants.V_WIDTH / 2) - (titleSprite.getWidth() / 2),
                60);
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
        titleSprite.draw(game.getSpriteBatch());
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
        titleSprite.getTexture().dispose();

    }
}
