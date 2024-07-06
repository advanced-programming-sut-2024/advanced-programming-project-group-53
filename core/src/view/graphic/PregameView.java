package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.StartMenu;
import game.GWENT;
import model.view.Resource;
import network.Instruction;

public class PregameView extends View {
    private final Image exit;

    public PregameView(GWENT game, String currentUsername) {
        super(game);
        this.menu = StartMenu.getInstance();
        this.currentUsername = currentUsername;
        Label wait = new Label("Please wait we are looking for an opponent", skin);
        wait.setPosition(512 - wait.getWidth() / 2, 512 - wait.getHeight() / 2);
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(512 - exit.getWidth() / 2, 100);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exit.setDrawable(new Image(new Texture(Resource.EXIT_CLICKED.address())).getDrawable());
                menu.exitGame();
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
        stage.addActor(exit);
        stage.addActor(wait);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.PREGAME_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}
