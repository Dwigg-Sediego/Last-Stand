package com.dwigg.laststand.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dwigg.laststand.entities.components.*;

public class Bullet extends Entity {

    private TextureRegion region;
    private float posX;
    private float posY;
    private float velX;

    private final float BOUNDS_WIDTH = 39;
    private final float BOUNDS_HEIGHT = 9;

    public static final float BULLET_SPEED = 10;

    public Bullet(TextureRegion region, float posX, float posY, float velX) {
        this.region = region;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;

        addComponents();
    }

    @Override
    void addComponents() {
        TextureComponent texture = new TextureComponent();
        texture.region = region;

        add(texture);
        add(new PositionComponent(posX, posY));
        add(new VelocityComponent(velX, 0));
        add(new BoundsComponent(BOUNDS_WIDTH, BOUNDS_HEIGHT, 0));
        add(new RenderableComponent());
        add(new BulletComponent());
    }

    @Override
    public void dispose() {

    }
}
