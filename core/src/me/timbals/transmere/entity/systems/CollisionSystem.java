package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Iterator;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.CollisionComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.SpriteComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.level.Level;

/**
 * Created by Tim Balsfulland on 22.07.2016.
 */
public class CollisionSystem extends IteratingSystem {

    public CollisionSystem() {
        super(Family
                .all(PositionComponent.class, CollisionComponent.class)
                .one(SizeComponent.class, SpriteComponent.class)
                .get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);
        SpriteComponent spriteComponent = Mappers.spriteMapper.get(entity);
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        int width = sizeComponent != null ? sizeComponent.width : (int) spriteComponent.sprite.getWidth();
        int height = sizeComponent != null ? sizeComponent.height : (int) spriteComponent.sprite.getHeight();

        if(velocityComponent != null) {
            if(checkTileCollision(new Rectangle(
                    positionComponent.x + velocityComponent.x * deltaTime,
                    positionComponent.y,
                    width, height))) {
                velocityComponent.x = 0;
            }

            if(checkTileCollision(new Rectangle(
                    positionComponent.x,
                    positionComponent.y + velocityComponent.y * deltaTime,
                    width, height))) {
                velocityComponent.y = 0;
            }
        }
    }

    public boolean checkTileCollision(Rectangle rect) {
        Iterator<MapLayer> iterator = Level.getTiledMap().getLayers().iterator();
        while(iterator.hasNext()) {
            TiledMapTileLayer layer = (TiledMapTileLayer) iterator.next();
            if(layer.getProperties().get("blocked", "false", String.class).equals("true")) {
                outer : for(int y = 0; y < layer.getHeight(); y++) {
                    for(int x = 0; x < layer.getWidth(); x++) {
                        if(layer.getCell(x, y) != null) {
                            if(rect.overlaps(new Rectangle(
                                    x * layer.getTileWidth() * 2,
                                    y * layer.getTileHeight() * 2,
                                    layer.getTileWidth() * 2,
                                    layer.getTileHeight() * 2))) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

}
