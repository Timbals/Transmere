package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 24.07.2016.
 */
public class RandomMovementComponent implements Component, Pool.Poolable {

    public float timer = 0;

    @Override
    public void reset() {
        timer = 0;
    }

}
