package com.dwigg.laststand.entities.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dwigg.laststand.entities.components.BoundsComponent;
import com.dwigg.laststand.entities.components.PositionComponent;

public class BoundsRendererSystem extends EntitySystem {

    private ShapeRenderer shapeRenderer;

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<BoundsComponent> boundsMapper;

    public BoundsRendererSystem(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;

        positionMapper = ComponentMapper.getFor(PositionComponent.class);
        boundsMapper = ComponentMapper.getFor(BoundsComponent.class);
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(
                Family.all(
                        BoundsComponent.class
                ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < entities.size(); ++i) {
            Entity entity = entities.get(i);

            PositionComponent position = positionMapper.get(entity);
            BoundsComponent bounds = boundsMapper.get(entity);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(
                    position.x + bounds.offsetX,
                    position.y,
                    bounds.rectangle.width,
                    bounds.rectangle.height
            );
            shapeRenderer.end();
        }
    }
}
