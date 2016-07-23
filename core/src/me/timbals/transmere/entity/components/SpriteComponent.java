package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class SpriteComponent implements Component, Pool.Poolable {

    public Sprite sprite = new Sprite();

    @Override
    public void reset() {
        sprite = new Sprite();
    }

}
