package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 20.07.2016.
 */
public class CameraFollowComponent implements Component, Pool.Poolable {

    public float tween = 0.12f;

    @Override
    public void reset() {
        tween = 0.12f;
    }

}
