package me.timbals.transmere.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import me.timbals.transmere.Game;

/**
 * Created by Tim Balsfulland on 26.07.2016.
 */
public class MenuScreen implements Screen {

    public static final String TEXT = "Press any key to start!";

    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public MenuScreen() {
    }

    @Override
    public void render(float delta) {
        font.draw(Game.getBatch(),
                TEXT, Game.WIDTH / 2 - glyphLayout.width / 2,
                Game.HEIGHT / 2 - glyphLayout.height / 2);


        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Game.getInstance().setScreen(new GameScreen());
        }
    }

    @Override
    public void show() {
        font = new BitmapFont();
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, TEXT);
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
        font.dispose();
    }

}