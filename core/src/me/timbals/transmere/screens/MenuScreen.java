package me.timbals.transmere.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import me.timbals.transmere.Game;

/**
 * Created by Tim Balsfulland on 26.07.2016.
 */
public class MenuScreen implements Screen {

    public static final String TEXT = "Press any key to start!";

    private BitmapFont titleFont;
    private BitmapFont font;
    private GlyphLayout glyphLayout;

    public MenuScreen() {
    }

    @Override
    public void render(float delta) {
        glyphLayout.setText(titleFont, Game.NAME);
        titleFont.draw(Game.getBatch(),
                Game.NAME,
                Game.WIDTH / 2 - glyphLayout.width / 2,
                Game.HEIGHT - Game.HEIGHT / 8 - glyphLayout.height / 2);

        glyphLayout.setText(font, TEXT);
        font.draw(Game.getBatch(),
                TEXT,
                Game.WIDTH / 2 - glyphLayout.width / 2,
                Game.HEIGHT / 2 - glyphLayout.height / 2);


        if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            Game.getInstance().setScreen(new GameScreen());
        }
    }

    @Override
    public void show() {
        FreetypeFontLoader.FreeTypeFontLoaderParameter size1Params = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        size1Params.fontFileName = "Roboto-Regular.ttf";
        size1Params.fontParameters.size = 100;
        Game.getAssetManager().load("size100.ttf", BitmapFont.class, size1Params);
        Game.getAssetManager().finishLoadingAsset("size100.ttf");

        font = new BitmapFont();
        glyphLayout = new GlyphLayout();
        titleFont = Game.getAssetManager().get("size100.ttf", BitmapFont.class);
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