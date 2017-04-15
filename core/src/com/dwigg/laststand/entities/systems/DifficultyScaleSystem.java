package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.dwigg.laststand.entities.components.DifficultyComponent;
import com.dwigg.laststand.entities.components.PlayerComponent;
import com.dwigg.laststand.entities.components.ScoreComponent;

public class DifficultyScaleSystem extends EntitySystem {

    private Entity player;

    private ComponentMapper<ScoreComponent> scoreMapper;
    private ComponentMapper<DifficultyComponent> difficultyMapper;

    public DifficultyScaleSystem() {
        scoreMapper = ComponentMapper.getFor(ScoreComponent.class);
        difficultyMapper = ComponentMapper.getFor(DifficultyComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        ScoreComponent score = scoreMapper.get(player);
        DifficultyComponent difficulty = difficultyMapper.get(player);

        difficulty.timer = 1.1f - (score.kills * 0.001f);

        System.out.println(difficulty.timer + " " + score.kills);
    }
}
