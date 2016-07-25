package me.timbals.transmere;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.SpriteComponent;

/**
 * Created by Tim Balsfulland on 25.07.2016.
 */
public class Utility {

    public static Rectangle calculateHitbox(Entity entity) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        SpriteComponent spriteComponent = Mappers.spriteMapper.get(entity);
        SizeComponent sizeComponent = Mappers.sizeMapper.get(entity);

        if(positionComponent != null) {
            if(spriteComponent != null) {
                return new Rectangle(positionComponent.x,
                        positionComponent.y,
                        spriteComponent.sprite.getWidth(),
                        spriteComponent.sprite.getHeight());
            } else if(sizeComponent != null) {
                return new Rectangle(positionComponent.x,
                        positionComponent.y,
                        sizeComponent.width,
                        sizeComponent.height);
            } else {
                return new Rectangle(positionComponent.x, positionComponent.y, 1, 1);
            }
        }
        return new Rectangle(0, 0, 0, 0);
    }

}
