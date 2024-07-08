package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.StartMenu;
import game.GWENT;
import model.cards.Deck;
import model.view.Resource;
import network.Instruction;

public class DeckExportView extends View {
    private TextField address;
    private Image save;
    private Image exit;
    private Image start;

    public DeckExportView(GWENT game, String currentUsername, Deck deck) {
        super(game);
        this.currentUsername = currentUsername;
        this.menu = StartMenu.getInstance();
        address = new TextField("", skin);
        address.setMessageText("Address");
        address.setPosition(512 - address.getWidth() / 2, 512 - address.getHeight() / 2);
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(50, 50);
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
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.setPosition(574, 50);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                //TODO: save it.
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_OFF.address())).getDrawable());
            }
        });
        start = new Image(new Texture(Resource.START_GAME_OFF.address()));
        start.setPosition(312, 924);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                start.setDrawable(new Image(new Texture(Resource.START_GAME_CLICKED.address())).getDrawable());
                game.changeScreen(new PregameView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                start.setDrawable(new Image(new Texture(Resource.START_GAME_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                start.setDrawable(new Image(new Texture(Resource.START_GAME_OFF.address())).getDrawable());
            }
        });
        stage.addActor(background);
        stage.addActor(address);
        stage.addActor(exit);
        stage.addActor(start);
        stage.addActor(save);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.START_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}