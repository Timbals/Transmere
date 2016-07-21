package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector3;

import me.timbals.transmere.Game;
import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.CameraFollowComponent;
import me.timbals.transmere.entity.components.PositionComponent;

/**
 * Created by Tim Balsfulland on 20.07.2016.
 */
public class CameraFollowSystem extends IteratingSystem {

    public CameraFollowSystem() {
        super(Family.all(PositionComponent.class, CameraFollowComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        CameraFollowComponent cameraFollowComponent = Mappers.cameraFollowMapper.get(entity);

        Vector3 cameraPosition = Game.getCamera().position;
        cameraPosition.x += (positionComponent.x - cameraPosition.x) * cameraFollowComponent.tween;
        cameraPosition.y += (positionComponent.y - cameraPosition.y) * cameraFollowComponent.tween;
    }

}
