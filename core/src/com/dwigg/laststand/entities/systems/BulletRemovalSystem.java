package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.dwigg.laststand.entities.components.BulletComponent;
import com.dwigg.laststand.entities.components.PositionComponent;
import com.dwigg.laststand.utils.Constants;

public class BulletRemovalSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper;

    public BulletRemovalSystem() {
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        BulletComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            PositionComponent position = positionMapper.get(entity);

            if (position.x > Constants.V_WIDTH ||
                    position.x < 0) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
