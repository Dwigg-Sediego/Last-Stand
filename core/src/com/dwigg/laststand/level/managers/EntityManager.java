package com.dwigg.laststand.level.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dwigg.laststand.MainGame;
import com.dwigg.laststand.entities.Player;
import com.dwigg.laststand.entities.systems.*;
import com.dwigg.laststand.utils.InputHelper;

public class EntityManager {

    private MainGame game;

    private SpriteBatch spriteBatch;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private Engine engine;

    private Player player;

    private TextureAtlas objectAtlas;

    private TextureAtlas zombieFemaleAtlas;
    private TextureAtlas zombieMaleAtlas;

    private Sound zombie1 = Gdx.audio.newSound(Gdx.files.internal("sfx/zombie_1.wav"));
    private Sound zombie2 = Gdx.audio.newSound(Gdx.files.internal("sfx/zombie_2.wav"));
    private Sound zombie3 = Gdx.audio.newSound(Gdx.files.internal("sfx/zombie_3.wav"));
    private Sound gun = Gdx.audio.newSound(Gdx.files.internal("sfx/bang.wav"));

    public EntityManager(MainGame game,
                         SpriteBatch spriteBatch,
                         ShapeRenderer shapeRenderer,
                         OrthographicCamera camera) {
        this.game = game;
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;
        this.camera = camera;

        engine = new Engine();

        objectAtlas = new TextureAtlas("tex/objects.pack");

        zombieFemaleAtlas = new TextureAtlas("tex/zombief.pack");
        zombieMaleAtlas = new TextureAtlas("tex/zombiem.pack");

        addEntitiesToEngine();
        addSystemToEngine();
    }

    private void addSystemToEngine() {
        InputSystem inputSystem = new InputSystem(objectAtlas, gun);

        engine.addSystem(inputSystem);
        engine.addSystem(new AnimationSystem());
        engine.addSystem(new RendererSystem(spriteBatch));
        engine.addSystem(new MovementSystem());
        engine.addSystem(new ZombieSpawningSystem(zombieFemaleAtlas, zombieMaleAtlas, zombie1, zombie2));
        engine.addSystem(new BulletRemovalSystem());
        engine.addSystem(new BulletCollisionSystem(zombie3));
        engine.addSystem(new ZombieRemovalSystem());
        engine.addSystem(new ZombieCollisionSystem());
        engine.addSystem(new DifficultyScaleSystem());
        engine.addSystem(new GameOverSystem(game));

        //engine.addSystem(new BoundsRendererSystem(shapeRenderer));

        Gdx.input.setInputProcessor(new InputHelper(inputSystem, camera));
    }

    private void addEntitiesToEngine() {
        player = new Player();
        engine.addEntity(player);
    }

    public void update(float delta) {
        engine.update(delta);
        System.out.println(engine.getEntities().size());
    }

    public void dispose() {
        player.dispose();
        objectAtlas.dispose();
        zombie1.dispose();
        zombie2.dispose();
        zombie3.dispose();
        gun.dispose();
    }
}
