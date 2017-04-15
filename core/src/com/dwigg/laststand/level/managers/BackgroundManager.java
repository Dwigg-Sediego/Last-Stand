package com.dwigg.laststand.level.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dwigg.laststand.utils.Constants;

public class BackgroundManager {

    private Sprite backgroundSprite;

    public BackgroundManager() {
        backgroundSprite = new Sprite(new Texture("img/background.png"));
        backgroundSprite.setSize(Constants.V_WIDTH, Constants.V_HEIGHT);
        backgroundSprite.setPosition(0, 0);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        backgroundSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public void dispose() {
        backgroundSprite.getTexture().dispose();
    }
}
