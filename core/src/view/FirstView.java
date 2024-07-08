package view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.GWENT;
import model.view.Resource;

import static com.badlogic.gdx.Gdx.input;

public class FirstView implements Screen {
    private final Stage stage;
    private final Image single;
    private final Image multi;
    private final Image exit;

    public FirstView(GWENT game) {
        stage = new Stage();
        input.setInputProcessor(stage);
        Image background = new Image(new Texture(Resource.FIRST_BACKGROUND.address()));
        single = new Image(new Texture(Resource.SINGLE_OFF.address()));
        single.setPosition(524, 512);
        single.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                single.setDrawable(new Image(new Texture(Resource.SINGLE_CLICKED.address())).getDrawable());
                game.changeScreen(new view.single.LoginView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                single.setDrawable(new Image(new Texture(Resource.SINGLE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                single.setDrawable(new Image(new Texture(Resource.SINGLE_OFF.address())).getDrawable());
            }
        });
        multi = new Image(new Texture(Resource.MULTI_OFF.address()));
        multi.setPosition(100, 512);
        multi.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                multi.setDrawable(new Image(new Texture(Resource.MULTI_ON.address())).getDrawable());
                game.changeScreen(new view.graphic.TournamentView(game,"a"));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                multi.setDrawable(new Image(new Texture(Resource.MULTI_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                multi.setDrawable(new Image(new Texture(Resource.MULTI_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(312, 450);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_CLICKED.address())).getDrawable());
                System.exit(0);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_OFF.address())).getDrawable());
            }
        });
        stage.addActor(background);
        stage.addActor(single);
        stage.addActor(multi);
        stage.addActor(exit);
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
    public void dispose() {
        stage.dispose();
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
    public void show() {
    }
}