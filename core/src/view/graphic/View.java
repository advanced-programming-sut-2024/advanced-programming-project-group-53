package view.graphic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import controller.Menu;
import game.GWENT;
import network.Instruction;
import view.Resource;

import static com.badlogic.gdx.Gdx.input;

public abstract class View implements Screen {
    protected GWENT game;
    protected Stage stage;
    protected Skin skin;
    protected Image background;
    protected Menu menu;
    protected String currentUsername;

    public View(GWENT game) {
        this.game = game;
        skinLoader();
        backgroundLoader();
        stage = new Stage();
        input.setInputProcessor(stage);
    }

    protected void skinLoader() {
        AssetManager assetManager = new AssetManager();
        assetManager.load(Resource.SKIN.address(), Skin.class);
        assetManager.finishLoading();
        skin = assetManager.get(Resource.SKIN.address(), Skin.class);
    }

    protected abstract void backgroundLoader();

    protected abstract void perform(Instruction instruction);

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
