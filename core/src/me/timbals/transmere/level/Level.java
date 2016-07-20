package me.timbals.transmere.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import me.timbals.transmere.Game;

/**
 * Created by Tim Balsfulland on 20.07.2016.
 */
public class Level {

    private static TiledMap tiledMap;
    private static MapRenderer mapRenderer;

    private static OrthographicCamera camera;

    public static void loadMap(String map) {
        Game.assetManager.load(map, TiledMap.class);
        Game.assetManager.finishLoadingAsset(map);
        tiledMap = Game.assetManager.get(map, TiledMap.class);

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 2f);
        if(camera != null)
            mapRenderer.setView(camera);
    }

    public static void setView(OrthographicCamera camera) {
        Level.camera = camera;
        if(mapRenderer != null)
            mapRenderer.setView(camera);
    }

    public static void render() {
        if(mapRenderer != null)
            mapRenderer.render();
    }

}
