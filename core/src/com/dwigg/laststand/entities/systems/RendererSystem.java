package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dwigg.laststand.entities.components.PositionComponent;
import com.dwigg.laststand.entities.components.RenderableComponent;
import com.dwigg.laststand.entities.components.TextureComponent;

public class RendererSystem extends EntitySystem {

    private SpriteBatch spriteBatch;

    private ImmutableArray<Entity> entities;

    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<PositionComponent> positionMapper;

    public RendererSystem(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;

        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        RenderableComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            TextureComponent texture = textureMapper.get(entity);
            PositionComponent position = positionMapper.get(entity);

            spriteBatch.begin();
            spriteBatch.draw(
                    texture.region,
                    position.x,
                    position.y
            );
            spriteBatch.end();
        }
    }
}
