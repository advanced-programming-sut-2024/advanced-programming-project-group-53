package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.google.gson.Gson;
import controller.StartMenu;
import game.GWENT;
import model.card.Card;
import model.cards.Deck;
import model.cards.DeckContainer;
import model.game.Player;
import model.game.User;
import model.view.Resource;
import view.components.ImageWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DeckOldView extends View {
    private ScrollPane scrollPane;
    private VerticalGroup verticalGroup;
    private ArrayList<DeckContainer> allUserDeckContainers;
    private Image save;
    private Image exit;
    private DeckContainer select = null;
    private Image start;


    public DeckOldView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        this.menu = StartMenu.getInstance();
        verticalGroup = new VerticalGroup();
        File dir = new File(System.getProperty("user.home") + "/gwentInformation/" + currentUsername);
        for (File userFile : Objects.requireNonNull(dir.listFiles(File::isFile))) {
            DeckContainer userDeck;
            Gson gson = new Gson();
            try {
                FileReader reader = new FileReader(userFile);
                userDeck = gson.fromJson(reader, DeckContainer.class);
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            allUserDeckContainers.add(userDeck);
        }
        verticalGroup.space(10);
        scrollPane = new ScrollPane(verticalGroup, skin);
        scrollPane.setBounds(100, 250, 824, 650);
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
        exit.setPosition(312, 50);
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
        save.setPosition(312, 150);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
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
                if (select == null) return;
                game.changeScreen(new LoginView(game, currentUsername,new Player(User.findUser(currentUsername),
                        select.getDeck(), select.getFaction(),select.getCommander())));//TODO:NULLLLLLL!!!!!!
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
        stage.addActor(scrollPane);
        stage.addActor(save);
        stage.addActor(exit);
        stage.addActor(start);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.START_BACKGROUND.address()));
    }

    private void update() {
        verticalGroup.clear();
        //TODO : show all deck containers with name , faction and commander in a vertical group in this menu.
        for (DeckContainer deckContainer : allUserDeckContainers) {
            HorizontalGroup horizontalGroup = new HorizontalGroup();
            horizontalGroup.space(10);
            horizontalGroup.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    select = deckContainer;
                }
            });
            verticalGroup.addActor(horizontalGroup);
        }
    }
}
