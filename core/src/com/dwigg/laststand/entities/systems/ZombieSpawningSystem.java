package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.dwigg.laststand.entities.Zombie;
import com.dwigg.laststand.entities.components.DifficultyComponent;
import com.dwigg.laststand.entities.components.PlayerComponent;

public class ZombieSpawningSystem extends EntitySystem {

    private Entity player;

    private TextureAtlas zombieFemaleAtlas;
    private TextureAtlas zombieMaleAtlas;

    private ComponentMapper<DifficultyComponent> difficultyMapper;

    private float spawnTimer = 0;
    private float timeToSpawn = 1;

    private Sound zombie1;
    private Sound zombie2;

    public ZombieSpawningSystem(TextureAtlas zombieFemaleAtlas, TextureAtlas zombieMaleAtlas,
                                Sound zombie1, Sound zombie2) {
        this.zombieFemaleAtlas = zombieFemaleAtlas;
        this.zombieMaleAtlas = zombieMaleAtlas;
        this.zombie1 = zombie1;
        this.zombie2 = zombie2;

        difficultyMapper = ComponentMapper.getFor(DifficultyComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        DifficultyComponent difficulty = difficultyMapper.get(player);

        spawnTimer += deltaTime;

        if (spawnTimer > difficulty.timer) {
            int zombieType = MathUtils.random(1);
            int origin = MathUtils.random(1);
            float velX = MathUtils.random(1f, 3f);

            Zombie zombie;

            if (zombieType == 0) {
                zombie = new Zombie(zombieMaleAtlas, origin, velX);
                zombie1.play();
            } else {
                zombie = new Zombie(zombieFemaleAtlas, origin, velX);
                zombie2.play();
            }

            getEngine().addEntity(zombie);

            spawnTimer = 0;
        }
    }
}
