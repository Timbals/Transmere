package me.timbals.transmere;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
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
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.entity.systems.CameraFollowSystem;
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

	public static PooledEngine entityEngine;

	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static Viewport viewport;

	public static AssetManager assetManager;
	
	@Override
	public void create () {
		entityEngine = new PooledEngine();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new RenderSystem());
		entityEngine.addSystem(new InputSystem());
		entityEngine.addSystem(new CameraFollowSystem());

		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

		Entity entity = entityEngine.createEntity();
		PositionComponent positionComponent = entityEngine.createComponent(PositionComponent.class);
		positionComponent.x = WIDTH / 2;
		positionComponent.y = HEIGHT / 2;
		entity.add(positionComponent);
		entity.add(entityEngine.createComponent(VelocityComponent.class));
		TextureComponent textureComponent = entityEngine.createComponent(TextureComponent.class);
		textureComponent.texture = new Texture("badlogic.jpg");
		entity.add(textureComponent);
		SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
		sizeComponent.width = 64;
		sizeComponent.height = 64;
		entity.add(sizeComponent);
		entity.add(entityEngine.createComponent(InputComponent.class));
		entity.add(entityEngine.createComponent(CameraFollowComponent.class));
		entityEngine.addEntity(entity);

		Level.loadMap("grassview.tmx");
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

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Level.render();

		entityEngine.update(delta);
	}
	
	@Override
	public void dispose () {
	}
}
