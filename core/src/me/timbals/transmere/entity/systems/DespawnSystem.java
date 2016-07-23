package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import me.timbals.transmere.Game;
import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.DespawnComponent;

/**
 * Created by Tim Balsfulland on 23.07.2016.
 */
public class DespawnSystem extends IteratingSystem {

    public DespawnSystem() {
        super(Family.all(DespawnComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DespawnComponent despawnComponent = Mappers.despawnMapper.get(entity);

        despawnComponent.duration -= deltaTime;

        if(despawnComponent.duration <= 0) {
            Game.getEntityEngine().removeEntity(entity);
        }
    }

}
