package me.timbals.transmere;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.TextureComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.entity.systems.MovementSystem;
import me.timbals.transmere.entity.systems.RenderSystem;

public class Game extends ApplicationAdapter {

	public static PooledEngine entityEngine;
	
	@Override
	public void create () {
		entityEngine = new PooledEngine();

		entityEngine.addSystem(new MovementSystem());
		entityEngine.addSystem(new RenderSystem());

		Entity entity = entityEngine.createEntity();
		entity.add(new PositionComponent(20, 20));
		entity.add(new VelocityComponent(24, 24));
		entity.add(new TextureComponent(new Texture("badlogic.jpg")));
		entity.add(new SizeComponent(64, 64));
		entityEngine.addEntity(entity);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		entityEngine.update(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
	}
}
