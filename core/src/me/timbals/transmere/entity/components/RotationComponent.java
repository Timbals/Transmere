package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 23.07.2016.
 */
public class RotationComponent implements Component, Pool.Poolable {

    public int rotation = 0; // north(0), west(1), south(2), east(3)

    @Override
    public void reset() {
        rotation = 0;
    }

}
