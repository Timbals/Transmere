package me.timbals.transmere.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by Tim Balsfulland on 19.07.2016.
 */
public class TextureComponent implements Component, Pool.Poolable {

    public Texture texture;

    public TextureComponent(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void reset() {
        texture = null;
    }
}
