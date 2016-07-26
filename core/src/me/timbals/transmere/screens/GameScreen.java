package me.timbals.transmere.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import me.timbals.transmere.Game;
import me.timbals.transmere.entity.components.CameraFollowComponent;
import me.timbals.transmere.entity.components.CollisionComponent;
import me.timbals.transmere.entity.components.HealthComponent;
import me.timbals.transmere.entity.components.InputComponent;
import me.timbals.transmere.entity.components.PositionComponent;
import me.timbals.transmere.entity.components.RandomMovementComponent;
import me.timbals.transmere.entity.components.RotationComponent;
import me.timbals.transmere.entity.components.SizeComponent;
import me.timbals.transmere.entity.components.SpriteComponent;
import me.timbals.transmere.entity.components.VelocityComponent;
import me.timbals.transmere.level.Level;

/**
 * Created by Tim Balsfulland on 26.07.2016.
 */
public class GameScreen implements Screen {

    public PooledEngine entityEngine;

    public GameScreen() {
    }

    @Override
    public void render(float delta) {
        Level.render();
    }

    @Override
    public void show() {
        entityEngine = Game.getEntityEngine();

        Level.loadMap("dev.tmx");

        addEnemy(5, 5);
        addEnemy(11, 8);
        addEnemy(21, 4);
        addEnemy(30, 4);
        addEnemy(25, 15);
        addPlayer();
    }

    // temporary
    public void addPlayer() {
        Entity entity = entityEngine.createEntity();

        PositionComponent positionComponent = entityEngine.createComponent(PositionComponent.class);
        positionComponent.x = Game.WIDTH / 2;
        positionComponent.y = 85 * 64;
        entity.add(positionComponent);

        entity.add(entityEngine.createComponent(VelocityComponent.class));

        SpriteComponent spriteComponent = entityEngine.createComponent(SpriteComponent.class);
        spriteComponent.sprite.setTexture(new Texture("badlogic.jpg"));
        spriteComponent.sprite.setRegion(spriteComponent.sprite.getTexture());
        entity.add(spriteComponent);

        SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
        sizeComponent.width = 48;
        sizeComponent.height = 48;
        entity.add(sizeComponent);

        entity.add(entityEngine.createComponent(InputComponent.class));

        entity.add(entityEngine.createComponent(CameraFollowComponent.class));

        entity.add(entityEngine.createComponent(HealthComponent.class));

        entity.add(entityEngine.createComponent(CollisionComponent.class));

        entity.add(entityEngine.createComponent(RotationComponent.class));

        entityEngine.addEntity(entity);
    }

    // temporary
    public void addEnemy(int x, int y) {
        Entity entity = entityEngine.createEntity();

        PositionComponent positionComponent = entityEngine.createComponent(PositionComponent.class);
        positionComponent.x = x * 64;
        positionComponent.y = (99 - y) * 64;
        entity.add(positionComponent);

        SizeComponent sizeComponent = entityEngine.createComponent(SizeComponent.class);
        sizeComponent.width = 48;
        sizeComponent.height = 48;
        entity.add(sizeComponent);

        SpriteComponent spriteComponent = entityEngine.createComponent(SpriteComponent.class);
        spriteComponent.sprite.setTexture(new Texture("badlogic.jpg"));
        spriteComponent.sprite.setRegion(spriteComponent.sprite.getTexture());
        entity.add(spriteComponent);

        entity.add(entityEngine.createComponent(HealthComponent.class));

        entity.add(entityEngine.createComponent(VelocityComponent.class));

        entity.add(entityEngine.createComponent(RandomMovementComponent.class));

        entity.add(entityEngine.createComponent(CollisionComponent.class));

        entityEngine.addEntity(entity);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

}
