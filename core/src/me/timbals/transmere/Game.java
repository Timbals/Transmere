package me.timbals.transmere;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import me.timbals.transmere.entity.systems.CameraFollowSystem;
import me.timbals.transmere.entity.systems.CollisionSystem;
import me.timbals.transmere.entity.systems.DamageSystem;
import me.timbals.transmere.entity.systems.DespawnSystem;
import me.timbals.transmere.entity.systems.FreezeSystem;
import me.timbals.transmere.entity.systems.InputSystem;
import me.timbals.transmere.entity.systems.MovementSystem;
import me.timbals.transmere.entity.systems.RenderSystem;
import me.timbals.transmere.level.Level;
import me.timbals.transmere.screens.MenuScreen;

public class Game extends com.badlogic.gdx.Game {

	public static final int WIDTH = 1280; // virtual resolution for pixel coordinates
	public static final int HEIGHT = 720;
	public static int SCREEN_WIDTH = WIDTH;
	public static int SCREEN_HEIGHT = HEIGHT;

	public static final Random RANDOM = new Random();

	public static final float TARGET_FPS = 60;

	private static Game instance;

	public PooledEngine entityEngine;

	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Viewport viewport;

	public AssetManager assetManager;

	private Game() {
	}

	@Override
	public void create () {
		Gdx.app.setLogLevel(Debug.log ? Application.LOG_DEBUG : Application.LOG_INFO);

		Gdx.gl.glClearColor(0, 0, 0, 0);

		entityEngine = new PooledEngine();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new RenderSystem());
		entityEngine.addSystem(new InputSystem());
		entityEngine.addSystem(new CameraFollowSystem());
		entityEngine.addSystem(new CollisionSystem());
		entityEngine.addSystem(new FreezeSystem());
		entityEngine.addSystem(new DespawnSystem());
		entityEngine.addSystem(new DamageSystem());

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		setScreen(new MenuScreen());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		camera.position.set(Game.WIDTH / 2, Game.HEIGHT / 2, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		Level.setView(camera);

		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime() / (1f / TARGET_FPS); // this will make delta 1 if target fps is exactly reached

		camera.update();
		Level.setView(camera);
		batch.setProjectionMatrix(camera.combined);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		super.render();
		batch.end();

		entityEngine.update(delta);
	}

	public static PooledEngine getEntityEngine() {
		return getInstance().entityEngine;
	}

	public static SpriteBatch getBatch() {
		return getInstance().batch;
	}

	public static OrthographicCamera getCamera() {
		return getInstance().camera;
	}

	public static Viewport getViewport() {
		return getInstance().viewport;
	}

	public static AssetManager getAssetManager() {
		return getInstance().assetManager;
	}

	public static synchronized Game getInstance() {
		if(instance == null)
			instance = new Game();
		return instance;
	}

	@Override
	public void dispose () {
		assetManager.dispose();
	}

}
