package me.timbals.transmere.entity;

import com.badlogic.ashley.core.ComponentMapper;

import me.timbals.transmere.entity.components.CameraFollowComponent;
import me.timbals.transmere.entity.components.DespawnComponent;
import me.timbals.transmere.entity.components.FreezeComponent;
import me.timbals.transmere.entity.components.HealthComponent;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.RandomMovementComponent;
import me.timbals.transmere.entity.components.RotationComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.SpriteComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class Mappers {

    public static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<SizeComponent> sizeMapper = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
    public static final ComponentMapper<CameraFollowComponent> cameraFollowMapper = ComponentMapper.getFor(CameraFollowComponent.class);
    public static final ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);
    public static final ComponentMapper<RotationComponent> rotationMapper = ComponentMapper.getFor(RotationComponent.class);
    public static final ComponentMapper<FreezeComponent> freezeMapper = ComponentMapper.getFor(FreezeComponent.class);
    public static final ComponentMapper<DespawnComponent> despawnMapper = ComponentMapper.getFor(DespawnComponent.class);
    public static final ComponentMapper<RandomMovementComponent> randomMovementMapper = ComponentMapper.getFor(RandomMovementComponent.class);

}
