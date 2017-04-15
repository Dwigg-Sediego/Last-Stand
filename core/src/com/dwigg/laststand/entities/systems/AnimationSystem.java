package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dwigg.laststand.entities.components.*;

public class AnimationSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<AnimationComponent> animationMapper;
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<StateComponent> stateMapper;
    private ComponentMapper<DirectionComponent> directionMapper;

    public AnimationSystem() {
        animationMapper = ComponentMapper.getFor(AnimationComponent.class);
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
        directionMapper = ComponentMapper.getFor(DirectionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        AnimationComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            AnimationComponent animation = animationMapper.get(entity);
            TextureComponent texture = textureMapper.get(entity);
            StateComponent state = stateMapper.get(entity);
            DirectionComponent direction = directionMapper.get(entity);


            texture.region = animation.animations.get(state.get()).getKeyFrame(state.time);


            if (direction.get().equals(DirectionComponent.Direction.RIGHT) &&
                    texture.region.isFlipX()) {
                texture.region.flip(true, false);
            } else if (direction.get().equals(DirectionComponent.Direction.LEFT) &&
                    !texture.region.isFlipX()) {
                texture.region.flip(true, false);
            }

            state.time += deltaTime;
        }
    }
}
