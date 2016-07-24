package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.timbals.transmere.Game;
import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.RandomMovementComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class MovementSystem extends IteratingSystem {

    public static float TIME_BETWEEN_MOVEMENTS = 5f;
    public static float SPEED = 1f;

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
        priority = 10;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if(Mappers.freezeMapper.has(entity)) {
            return;
        }

        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        if(Mappers.randomMovementMapper.has(entity)) {
            randomMovement(velocityComponent, entity, deltaTime);
        }

        positionComponent.x += velocityComponent.x * deltaTime;
        positionComponent.y += velocityComponent.y * deltaTime;
    }

    public void randomMovement(VelocityComponent velocityComponent, Entity entity, float deltaTime) {
        RandomMovementComponent randomMovementComponent = Mappers.randomMovementMapper.get(entity);

        if(randomMovementComponent.timer <= 0 || (velocityComponent.x == 0 && velocityComponent.y == 0)) {
            randomMovementComponent.timer = TIME_BETWEEN_MOVEMENTS * Game.TARGET_FPS;

            velocityComponent.x = 0;
            velocityComponent.y = 0;

            int direction = Game.RANDOM.nextInt(4);
            switch(direction) {
                case 0:
                    velocityComponent.y = SPEED;
                    break;
                case 1:
                    velocityComponent.x = SPEED;
                    break;
                case 2:
                    velocityComponent.y = -SPEED;
                    break;
                case 3:
                    velocityComponent.x = -SPEED;
                    break;
                default:
                    break;
            }
        } else {
            randomMovementComponent.timer -= deltaTime;
        }
    }

}
