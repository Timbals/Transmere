package me.timbals.transmere;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.entity.systems.InputSystem;
import me.timbals.transmere.entity.systems.MovementSystem;
import me.timbals.transmere.entity.systems.RenderSystem;

public class Game extends ApplicationAdapter {

	public static final int HEIGHT = 720;
	public static final int WIDTH = HEIGHT / 9 * 16;
	public static int SCREEN_HEIGHT = HEIGHT;
	public static int SCREEN_WIDTH = WIDTH;

	public static final float TARGET_FPS = 60;

	public static PooledEngine entityEngine;

	public static SpriteBatch batch;
	public static OrthographicCamera camera;
	public static Viewport viewport;
	
	@Override
	public void create () {
		entityEngine = new PooledEngine();

		batch = new SpriteBatch();
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		viewport = new ExtendViewport(WIDTH, HEIGHT, camera);

		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new RenderSystem());
		entityEngine.addSystem(new InputSystem());

		Entity entity = entityEngine.createEntity();
		entity.add(new PositionComponent(20, 20));
		entity.add(new VelocityComponent(24, 24));
		entity.add(new TextureComponent(new Texture("badlogic.jpg")));
		entity.add(new SizeComponent(64, 64));
		entity.add(new InputComponent());
		entityEngine.addEntity(entity);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		SCREEN_WIDTH = width;
		SCREEN_HEIGHT = height;
	}

	@Override
	public void render () {
		float delta = Gdx.graphics.getDeltaTime() / (1f / TARGET_FPS); // this will make delta 1 if target fps is exactly reached

		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		entityEngine.update(delta);
	}
	
	@Override
	public void dispose () {
	}
}
