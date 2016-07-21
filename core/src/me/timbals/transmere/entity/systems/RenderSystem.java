package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.timbals.transmere.Game;
import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> entities;

    public RenderSystem() {
        super();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = Game.getEntityEngine().getEntitiesFor(Family.all(PositionComponent.class, TextureComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        SpriteBatch batch = Game.getBatch();

        batch.begin();

        for(Entity entity : entities) {
            PositionComponent positionComponent = Mappers.positionMapper.get(entity);
            TextureComponent textureComponent = Mappers.textureMapper.get(entity);
            SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);

            if(sizeComponent != null) {
                batch.draw(
                        textureComponent.texture,
                        positionComponent.x,
                        positionComponent.y,
                        sizeComponent.width,
                        sizeComponent.height);
            } else {
                batch.draw(
                        textureComponent.texture,
                        positionComponent.x,
                        positionComponent.y);
            }
        }

        batch.end();
    }

}
