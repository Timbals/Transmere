package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 23.07.2016.
 */
public class FreezeComponent implements Component, Pool.Poolable {

    public float duration = 0;

    @Override
    public void reset() {
        duration = 0;
    }

}
