package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 22.07.2016.
 */
public class HealthComponent implements Component, Pool.Poolable {

    public int maxHealth = 5;
    public int health = maxHealth;

    @Override
    public void reset() {
        maxHealth = 5;
        health = maxHealth;
    }

}
