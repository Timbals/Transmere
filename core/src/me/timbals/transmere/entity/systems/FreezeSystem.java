package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.FreezeComponent;

/**
 * Created by Tim Balsfulland on 23.07.2016.
 */
public class FreezeSystem extends IteratingSystem {

    public FreezeSystem() {
        super(Family.all(FreezeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        FreezeComponent freezeComponent = Mappers.freezeMapper.get(entity);

        freezeComponent.duration -= deltaTime;

        if(freezeComponent.duration <= 0) {
            entity.remove(FreezeComponent.class);
        }
    }

}
