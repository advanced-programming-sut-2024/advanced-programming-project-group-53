package view.graphic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;
import controller.Menu;
import game.GWENT;

import static com.badlogic.gdx.Gdx.input;

public abstract class MenuView implements Screen {
    protected GWENT game;
    protected Stage stage;
    protected Skin checkBox;
    protected Skin label;
    protected Skin scrollPane;
    protected Skin textField;
    protected Skin textButton;
    protected Skin window;
    protected Image background;
    protected Menu menu;
    public MenuView(GWENT game) {
        this.game = game;
        skinLoader();
        backgroundLoader();
        stage = new Stage();
        input.setInputProcessor(stage);
        stage.setViewport(new FillViewport(background.getWidth(), background.getHeight()));
    }
    protected void skinLoader() {
        AssetManager assetManager = new AssetManager();
        assetManager.load("skin/checkBox/checkBox.json", Skin.class);
        assetManager.load("skin/label/label.json", Skin.class);
        assetManager.load("skin/scrollPane/scrollPane.json", Skin.class);
        assetManager.load("skin/textField/textField.json", Skin.class);
        assetManager.load("skin/window/window.json", Skin.class);
        assetManager.finishLoading();
        checkBox = assetManager.get("skin/checkBox/checkBox.json", Skin.class);
        label = assetManager.get("skin/label/label.json", Skin.class);
        scrollPane = assetManager.get("skin/scrollPane/scrollPane.json", Skin.class);
        textField = assetManager.get("skin/textField/textField.json", Skin.class);
        window = assetManager.get("skin/window/window.json", Skin.class);
    }
    protected abstract void backgroundLoader();
    public void settingUpdater() {
        //TODO: fill it.
    }
    @Override
    public void show() {

    }

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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
