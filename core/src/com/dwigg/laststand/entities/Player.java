package com.dwigg.laststand.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dwigg.laststand.entities.components.*;
import com.dwigg.laststand.utils.Constants;

public class Player extends Entity {

    private TextureAtlas atlas;
    private float positionX;
    private float positionY;

    private AnimationComponent animationComponent;

    private final float BOUNDS_WIDTH = 64;
    private final float BOUNDS_HEIGHT = 128;

    public Player() {
        atlas = new TextureAtlas("tex/rika.pack");
        positionX = (Constants.V_WIDTH / 2) - (128 / 2);
        positionY = 250;

        initializeAnimation();
        addComponents();
    }

    private void initializeAnimation() {
        animationComponent = new AnimationComponent();
        animationComponent.animations.put(
                "IDLE",
                new Animation<TextureRegion>(
                        1/12f,
                        atlas.findRegions("Idle"),
                        Animation.PlayMode.LOOP
                )
        );
        animationComponent.animations.put(
                "SHOOTING",
                new Animation<TextureRegion>(
                        1/8f,
                        atlas.findRegions("Shoot"),
                        Animation.PlayMode.NORMAL
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
    public void addComponents() {
        add(new PlayerComponent());
        add(animationComponent);
        add(new TextureComponent());
        add(new PositionComponent(positionX, positionY));
        add(new StateComponent());
        add(new DirectionComponent());
        add(new BoundsComponent(BOUNDS_WIDTH, BOUNDS_HEIGHT, 32));
        add(new RenderableComponent());
        add(new AliveComponent());
        add(new ScoreComponent());
        add(new DifficultyComponent());
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }
}
