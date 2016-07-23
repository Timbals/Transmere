package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.RotationComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class InputSystem extends IteratingSystem {

    private static final float speed = 6f;
    private static final float diagonalSpeed = 4f;

    private int[] ticksPressed = new int[4];

    public InputSystem() {
        super(Family.all(VelocityComponent.class, InputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);
        RotationComponent rotationComponent = Mappers.rotationMapper.get(entity);

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocityComponent.y = speed;
            ticksPressed[0]++;
            ticksPressed[2] = 0;
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocityComponent.y = -speed;
            ticksPressed[2]++;
            ticksPressed[0] = 0;
        } else {
            ticksPressed[0] = 0;
            ticksPressed[2] = 0;
            velocityComponent.y = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocityComponent.x = speed;
            ticksPressed[3]++;
            ticksPressed[1] = 0;
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocityComponent.x = -speed;
            ticksPressed[1]++;
            ticksPressed[3] = 0;
        } else {
            ticksPressed[1] = 0;
            ticksPressed[3] = 0;
            velocityComponent.x = 0;
        }

        if(velocityComponent.x == speed && velocityComponent.y == speed) {
            velocityComponent.x = diagonalSpeed;
            velocityComponent.y = diagonalSpeed;
        }

        if(rotationComponent != null)
            calculateRotation(rotationComponent);
    }

    public void calculateRotation(RotationComponent rotationComponent) {
        int highest = 0;
        for(int i = 0; i < ticksPressed.length; i++) {
            if(ticksPressed[i] > highest) {
                highest = ticksPressed[i];
                rotationComponent.rotation = i;
            }
        }
    }

}
