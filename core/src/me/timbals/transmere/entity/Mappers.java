package me.timbals.transmere.entity;

import com.badlogic.ashley.core.ComponentMapper;

import me.timbals.transmere.entity.components.CameraFollowComponent;
import me.timbals.transmere.entity.components.HealthComponent;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;
import me.timbals.transmere.entity.components.VelocityComponent;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class Mappers {

    public static final ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> velocityMapper = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<SizeComponent> sizeMapper = ComponentMapper.getFor(SizeComponent.class);
    public static final ComponentMapper<InputComponent> inputMapper = ComponentMapper.getFor(InputComponent.class);
    public static final ComponentMapper<CameraFollowComponent> cameraFollowMapper = ComponentMapper.getFor(CameraFollowComponent.class);
    public static final ComponentMapper<HealthComponent> healthMapper = ComponentMapper.getFor(HealthComponent.class);

}
