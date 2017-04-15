package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.dwigg.laststand.entities.Bullet;
import com.dwigg.laststand.entities.Zombie;
import com.dwigg.laststand.entities.components.AnimationComponent;
import com.dwigg.laststand.entities.components.DirectionComponent;
import com.dwigg.laststand.entities.components.PlayerComponent;
import com.dwigg.laststand.entities.components.StateComponent;
import com.dwigg.laststand.utils.Constants;
import com.dwigg.laststand.utils.InputListener;

public class InputSystem extends EntitySystem implements InputListener {

    private ComponentMapper<AnimationComponent> animationMapper;
    private ComponentMapper<StateComponent> stateMapper;
    private ComponentMapper<DirectionComponent> directionMapper;

    private AnimationComponent animation;
    private DirectionComponent direction;
    private StateComponent state;

    private TextureAtlas objectAtlas;

    private Sound gun;

    public InputSystem(TextureAtlas objectAtlas, Sound gun) {
        this.objectAtlas = objectAtlas;
        this.gun = gun;

        animationMapper = ComponentMapper.getFor(AnimationComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        Entity entity = engine.getEntitiesFor(
                Family.all(
                        PlayerComponent.class
                ).get()
        ).first();

        animation = animationMapper.get(entity);
        direction = directionMapper.get(entity);
        state = stateMapper.get(entity);
    }

    @Override
    public void update(float deltaTime) {
        if (!state.get().equals("DEAD")) {
            if (animation.animations.get(state.get()).isAnimationFinished(state.time)) {
                state.set("IDLE");
            }
        }
    }

    @Override
    public void right() {
        if (!state.get().equals("DEAD")) {
            if (state.get().equals("IDLE")) {
                state.set("SHOOTING");
                direction.right();
                shoot((Constants.V_WIDTH / 2) + 32,
                        Bullet.BULLET_SPEED);
            }
        }
    }

    @Override
    public void left() {
        if (!state.get().equals("DEAD")) {
            if (state.get().equals("IDLE")) {
                state.set("SHOOTING");
                direction.left();
                shoot((Constants.V_WIDTH / 2) - 48,
                        -Bullet.BULLET_SPEED);
            }
        }
    }

    private void shoot(float x, float velX) {
        gun.play();
        Bullet bullet = new Bullet(
                objectAtlas.findRegion("bullet"),
                x,
                300,
                velX);
        getEngine().addEntity(bullet);
    }
}
