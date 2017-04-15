package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.dwigg.laststand.entities.components.*;

public class ZombieCollisionSystem extends EntitySystem {

    private Entity player;
    private ImmutableArray<Entity> zombies;

    private ComponentMapper<BoundsComponent> boundsMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<StateComponent> stateMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<AliveComponent> aliveMapper;

    public ZombieCollisionSystem() {
        boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        aliveMapper = ComponentMapper.getFor(AliveComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(
                Family.all(
                        PlayerComponent.class
                ).get()
        ).first();

        zombies = engine.getEntitiesFor(
                Family.all(
                        ZombieComponent.class,
                        BoundsComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        BoundsComponent playerBounds = boundsMapper.get(player);
        PositionComponent playerPosition = positionMapper.get(player);
        playerBounds.rectangle.x = playerPosition.x;
        playerBounds.rectangle.y = playerPosition.y;
        StateComponent playerState = stateMapper.get(player);
        AliveComponent playerAlive = aliveMapper.get(player);

        for (Entity zombie : zombies) {
            StateComponent zombieState = stateMapper.get(zombie);
            VelocityComponent zombieVelocity = velocityMapper.get(zombie);
            BoundsComponent zombieBounds = boundsMapper.get(zombie);
            PositionComponent zombiePosition = positionMapper.get(zombie);
            zombieBounds.rectangle.x = zombiePosition.x;
            zombieBounds.rectangle.y = zombiePosition.y;

            if (playerBounds.rectangle.overlaps(zombieBounds.rectangle)) {
                zombieVelocity.x = 0;
                zombieState.set("ATTACKING");
                zombie.remove(BoundsComponent.class);

                if (playerAlive.get()) {
                    playerState.set("DEAD");
                    playerAlive.set(false);
                }
            }
        }
    }
}
