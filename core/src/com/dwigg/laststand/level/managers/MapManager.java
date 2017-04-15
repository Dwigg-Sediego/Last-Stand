package com.dwigg.laststand.level.managers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapManager {

    private OrthographicCamera camera;

    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public MapManager(SpriteBatch spriteBatch, OrthographicCamera camera) {
        this.camera = camera;

        map = new TmxMapLoader().load("map/graveyard.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, spriteBatch);
    }

    public void update() {
        mapRenderer.setView(camera);
    }

    public void draw() {
        mapRenderer.render();
    }

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }
}
