package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.audio.Sound;
import com.dwigg.laststand.entities.components.*;

public class BulletCollisionSystem extends EntitySystem {

    private Entity player;
    private ImmutableArray<Entity> bullets;
    private ImmutableArray<Entity> zombies;

    private ComponentMapper<BoundsComponent> boundsMapper;
    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<StateComponent> stateMapper;
    private ComponentMapper<VelocityComponent> velocityMapper;
    private ComponentMapper<AliveComponent> aliveMapper;
    private ComponentMapper<ScoreComponent> scoreMapper;

    private Sound zombie3;

    public BulletCollisionSystem(Sound zombie3) {
        this.zombie3 = zombie3;

        boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        stateMapper = ComponentMapper.getFor(StateComponent.class);
        velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
        aliveMapper = ComponentMapper.getFor(AliveComponent.class);
        scoreMapper = ComponentMapper.getFor(ScoreComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
        bullets = engine.getEntitiesFor(Family.all(BulletComponent.class).get());
        zombies = engine.getEntitiesFor(Family.all(ZombieComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        checkForBulletsCollision();
    }

    private void checkForBulletsCollision() {
        ScoreComponent score = scoreMapper.get(player);

        for (Entity bullet : bullets) {
            BoundsComponent bulletBounds = boundsMapper.get(bullet);
            PositionComponent bulletPosition = positionMapper.get(bullet);
            bulletBounds.rectangle.x = bulletPosition.x;
            bulletBounds.rectangle.y = bulletPosition.y;

            for (Entity zombie : zombies) {
                StateComponent zombieState = stateMapper.get(zombie);
                VelocityComponent zombieVelocity = velocityMapper.get(zombie);
                BoundsComponent zombieBounds = boundsMapper.get(zombie);
                PositionComponent zombiePosition = positionMapper.get(zombie);
                zombieBounds.rectangle.x = zombiePosition.x;
                zombieBounds.rectangle.y = zombiePosition.y;
                AliveComponent alive = aliveMapper.get(zombie);

                if (bulletBounds.rectangle.overlaps(zombieBounds.rectangle)) {
                    if (alive.get()) {
                        getEngine().removeEntity(bullet);
                        zombieVelocity.x = 0;
                        zombieState.set("DEAD");
                        alive.set(false);
                        score.kills += 10;
                        zombie3.play();
                    }
                }
            }
        }
    }
}
