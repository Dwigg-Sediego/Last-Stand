package com.dwigg.laststand.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.level.managers.BackgroundManager;
import com.dwigg.laststand.level.managers.EntityManager;
import com.dwigg.laststand.level.managers.MapManager;

public class Level {

    private SpriteBatch spriteBatch;

    private BackgroundManager background;
    private MapManager map;
    private EntityManager entities;

    public Level(MainGame game, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, OrthographicCamera camera) {
        this.spriteBatch = spriteBatch;

        background = new BackgroundManager();
        map = new MapManager(spriteBatch, camera);
        entities = new EntityManager(game, spriteBatch, shapeRenderer, camera);
    }

    public void update(float delta) {
        map.update();
        entities.update(delta);
    }

    public void draw() {
        background.draw(spriteBatch);
        map.draw();
    }

    public void dispose() {
        background.dispose();
        map.dispose();
        entities.dispose();
    }
}
