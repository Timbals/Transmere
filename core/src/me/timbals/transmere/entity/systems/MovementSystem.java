package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
        priority = 10;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        positionComponent.x += velocityComponent.x * deltaTime;
        positionComponent.y += velocityComponent.y * deltaTime;
    }
}
