package com.dwigg.laststand.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dwigg.laststand.entities.components.*;
import com.dwigg.laststand.utils.Constants;

public class Zombie extends Entity {

    private TextureAtlas atlas;
    private float positionX;
    private float positionY;
    private float velocityX;
    private DirectionComponent.Direction direction;

    private AnimationComponent animationComponent;

    private final float BOUNDS_WIDTH = 64;
    private final float BOUNDS_HEIGHT = 128;

    public Zombie(TextureAtlas atlas, int origin, float velocityX) {
        this.atlas = atlas;

        positionY = 250;
        setOriginOfDirection(origin);

        setVelocityX(origin, velocityX);

        initializeAnimation();
        addComponents();
    }

    private void setVelocityX(int origin, float velocityX) {
        if (origin == 0) {
            this.velocityX = velocityX;
        } else if (origin == 1) {
            this.velocityX = -velocityX;
        }
    }

    private void setOriginOfDirection(int origin) {
        if (origin == 0) { // From left
            positionX = -128;
            direction = DirectionComponent.Direction.RIGHT;
        } else if (origin == 1) { // From right
            positionX = (Constants.V_WIDTH + 128);
            direction = DirectionComponent.Direction.LEFT;
        }
    }

    private void initializeAnimation() {
        animationComponent = new AnimationComponent();
        animationComponent.animations.put(
                "WALKING",
                new Animation<TextureRegion>(
                        1/12f,
                        atlas.findRegions("Walk"),
                        Animation.PlayMode.LOOP
                )
        );
        animationComponent.animations.put(
                "ATTACKING",
                new Animation<TextureRegion>(
                        1/8f,
                        atlas.findRegions("Attack"),
                        Animation.PlayMode.LOOP
                )
        );
        animationComponent.animations.put(
                "DEAD",
                new Animation<TextureRegion>(
                        1/16f,
                        atlas.findRegions("Dead"),
                        Animation.PlayMode.NORMAL
                )
        );
    }

    @Override
    void addComponents() {
        StateComponent stateComponent = new StateComponent();
        stateComponent.set("WALKING");
        DirectionComponent directionComponent = new DirectionComponent();
        if (direction == DirectionComponent.Direction.RIGHT) {
            directionComponent.right();
        } else if (direction == DirectionComponent.Direction.LEFT) {
            directionComponent.left();
        }

        add(new ZombieComponent());
        add(animationComponent);
        add(new TextureComponent());
        add(new PositionComponent(positionX, positionY));
        add(new VelocityComponent(velocityX, 0));
        add(stateComponent);
        add(directionComponent);
        add(new BoundsComponent(BOUNDS_WIDTH, BOUNDS_HEIGHT, 16));
        add(new RenderableComponent());
        add(new AliveComponent());
        add(new DecayComponent());
    }

    @Override
    public void dispose() {

    }
}
