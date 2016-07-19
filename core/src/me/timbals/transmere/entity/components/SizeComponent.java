package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class SizeComponent implements Component, Pool.Poolable {

    public int width = 0;
    public int height = 0;

    public SizeComponent() {
    }

    public SizeComponent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void reset() {
        width = 0;
        height = 0;
    }
}
