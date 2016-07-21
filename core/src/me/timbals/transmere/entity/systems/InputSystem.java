package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class InputSystem extends IteratingSystem{

    private static final float speed = 6f;

    public InputSystem() {
        super(Family.all(VelocityComponent.class, InputComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        VelocityComponent velocityComponent = Mappers.velocityMapper.get(entity);

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocityComponent.y = speed;
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocityComponent.y = -speed;
        } else {
            velocityComponent.y = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocityComponent.x = speed;
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocityComponent.x = -speed;
        } else {
            velocityComponent.x = 0;
        }
    }
}
