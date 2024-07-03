package view.single;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import controller.Menu;
import game.GWENT;
import view.graphic.Resource;

import static com.badlogic.gdx.Gdx.input;

public abstract class View implements Screen {
    protected GWENT game;
    protected Stage stage;
    protected Skin label;
    protected Skin scrollPane;
    protected Skin textField;
    protected Image background;
    protected Menu menu;
    protected String currentUsername;
    protected String username1;
    protected String username2;

    public View(GWENT game) {
        this.game = game;
        skinLoader();
        backgroundLoader();
        stage = new Stage();
        input.setInputProcessor(stage);
    }

    protected void skinLoader() {
        AssetManager assetManager = new AssetManager();
        assetManager.load(Resource.LABEL.address(), Skin.class);
        assetManager.load(Resource.SCROLL_PANE.address(), Skin.class);
        assetManager.load(Resource.TEXT_FIELD.address(), Skin.class);
        assetManager.finishLoading();
        label = assetManager.get(Resource.LABEL.address(), Skin.class);
        scrollPane = assetManager.get(Resource.SCROLL_PANE.address(), Skin.class);
        textField = assetManager.get(Resource.TEXT_FIELD.address(), Skin.class);
    }

    protected abstract void backgroundLoader();

    @Override
    public void render(float deltaTime) {
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}
