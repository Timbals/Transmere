package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class PositionComponent implements Component, Pool.Poolable {

    public float x = 0.0f;
    public float y = 0.0f;

    @Override
    public void reset() {
        x = 0.0f;
        y = 0.0f;
    }

}
