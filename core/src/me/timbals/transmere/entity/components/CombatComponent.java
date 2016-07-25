package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 25.07.2016.
 */
public class CombatComponent implements Component, Pool.Poolable {

    public int touchDamage = 1;

    @Override
    public void reset() {
    }

}
