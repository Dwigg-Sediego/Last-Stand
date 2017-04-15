package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.dwigg.laststand.entities.components.AliveComponent;
import com.dwigg.laststand.entities.components.DecayComponent;

public class ZombieRemovalSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<AliveComponent> aliveMapper;
    private ComponentMapper<DecayComponent> decayMapper;

    private float timeToDecay = 3f;

    public ZombieRemovalSystem() {
        aliveMapper = ComponentMapper.getFor(AliveComponent.class);
        decayMapper = ComponentMapper.getFor(DecayComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(DecayComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            AliveComponent alive = aliveMapper.get(entity);
            DecayComponent decay = decayMapper.get(entity);

            if (!alive.get()) {
                decay.timer += deltaTime;
            }

            if (decay.timer > timeToDecay) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
