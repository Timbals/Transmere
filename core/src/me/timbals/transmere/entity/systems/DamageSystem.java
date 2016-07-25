package me.timbals.transmere.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;

import me.timbals.transmere.Game;
import me.timbals.transmere.Utility;
import me.timbals.transmere.entity.Mappers;
import me.timbals.transmere.entity.components.CombatComponent;
import me.timbals.transmere.entity.components.HealthComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.SpriteComponent;

/**
 * Created by Tim Balsfulland on 25.07.2016.
 */
public class DamageSystem extends IteratingSystem {

    public DamageSystem(){
        super(Family.all(PositionComponent.class, CombatComponent.class)
                .one(SpriteComponent.class, SizeComponent.class)
                .get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent positionComponent = Mappers.positionMapper.get(entity);
        CombatComponent combatComponent = Mappers.comaptMapper.get(entity);

        Rectangle hitbox = Utility.calculateHitbox(entity);

        for(Entity e : Game.getEntityEngine().getEntitiesFor(Family.all(PositionComponent.class, HealthComponent.class)
                .one(SpriteComponent.class, SizeComponent.class)
                .get())) {
            if(hitbox.overlaps(Utility.calculateHitbox(e))) {
                HealthComponent healthComponent = Mappers.healthMapper.get(e);
                healthComponent.health -= combatComponent.touchDamage;
                if(healthComponent.health <= 0) {
                    Game.getEntityEngine().removeEntity(e);
                }
            }
        }
    }

}
