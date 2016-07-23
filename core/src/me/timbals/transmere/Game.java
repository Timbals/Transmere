package me.timbals.transmere;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.timbals.transmere.entity.components.CameraFollowComponent;
import me.timbals.transmere.entity.components.CollisionComponent;
import me.timbals.transmere.entity.components.HealthComponent;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.entity.systems.CameraFollowSystem;
import me.timbals.transmere.entity.systems.CollisionSystem;
import me.timbals.transmere.entity.systems.InputSystem;
import me.timbals.transmere.entity.systems.MovementSystem;
import me.timbals.transmere.entity.systems.RenderSystem;
import me.timbals.transmere.level.Level;

public class Game extends ApplicationAdapter {

	public static final int WIDTH = 1280; // virtual resolution for pixel coordinates
	public static final int HEIGHT = 720;
	public static int SCREEN_WIDTH = WIDTH;
	public static int SCREEN_HEIGHT = HEIGHT;

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

		Gdx.gl.glClearColor(1, 1, 1, 0);

		entityEngine = new PooledEngine();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new RenderSystem());
		entityEngine.addSystem(new InputSystem());
		entityEngine.addSystem(new CameraFollowSystem());
		entityEngine.addSystem(new CollisionSystem());

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		Level.loadMap("dev.tmx");

		addEnemy();
		addPlayer();
	}

	// temporary
	public void addPlayer() {
		Entity entity = entityEngine.createEntity();

		PositionComponent positionComponent = entityEngine.createComponent(PositionComponent.class);
		positionComponent.x = WIDTH / 2;
		positionComponent.y = 85 * 64;
		entity.add(positionComponent);

		entity.add(entityEngine.createComponent(VelocityComponent.class));

		TextureComponent textureComponent = entityEngine.createComponent(TextureComponent.class);
		textureComponent.texture = new Texture("badlogic.jpg");
		entity.add(textureComponent);

		SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
		sizeComponent.width = 48;
		sizeComponent.height = 48;
		entity.add(sizeComponent);

		entity.add(entityEngine.createComponent(InputComponent.class));

		entity.add(entityEngine.createComponent(CameraFollowComponent.class));

		entity.add(entityEngine.createComponent(HealthComponent.class));

		entity.add(entityEngine.createComponent(CollisionComponent.class));

		entityEngine.addEntity(entity);
	}

	// temporary
	public void addEnemy() {
		Entity entity = entityEngine.createEntity();

		PositionComponent positionComponent = entityEngine.createComponent(PositionComponent.class);
		positionComponent.x = WIDTH / 2;
		positionComponent.y = 96 * 64;
		entity.add(positionComponent);

		SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
		sizeComponent.width = 128;
		sizeComponent.height = 128;

		TextureComponent textureComponent = entityEngine.createComponent(TextureComponent.class);
		textureComponent.texture = new Texture("badlogic.jpg");
		entity.add(textureComponent);

		entity.add(entityEngine.createComponent(HealthComponent.class));

		entityEngine.addEntity(entity);
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

		Level.render();

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
	}

}
