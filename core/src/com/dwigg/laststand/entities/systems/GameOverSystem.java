package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.entities.components.AliveComponent;
import com.dwigg.laststand.entities.components.PlayerComponent;
import com.dwigg.laststand.screens.GameOverScreen;

public class GameOverSystem extends EntitySystem {

    private MainGame game;

    private Entity player;

    private ComponentMapper<AliveComponent> aliveMapper;

    private float timer = 0;
    private float timeToGameOver = 3;

    public GameOverSystem(MainGame game) {
        this.game = game;

        aliveMapper = ComponentMapper.getFor(AliveComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        player = engine.getEntitiesFor(Family.all(PlayerComponent.class).get()).first();
    }

    @Override
    public void update(float deltaTime) {
        AliveComponent alive = aliveMapper.get(player);

        if (!alive.get()) {
            timer += deltaTime;
        }

        if (timer > timeToGameOver) {
            game.setScreen(new GameOverScreen(game));
        }
    }
}
