package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.MainMenu;
import game.GWENT;
import view.Resource;

public class MainView extends View {
    private final Image chooseDeck;
    private final Image profile;
    private final Image ranking;
    private final Image logout;
    private final Image exit;

    public MainView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = MainMenu.getInstance();
        Table mainTable = new Table();
        mainTable.setBounds(50, 50, 400, (float) (400 * 0.1458 * 4));
        mainTable.align(Align.center);
        chooseDeck = new Image(new Texture(Resource.CHOOSE_DECK_OFF.address()));
        chooseDeck.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chooseDeck.setDrawable(new Image(new Texture(Resource.CHOOSE_DECK_CLICKED.address())).getDrawable());
                game.changeScreen(new FactionView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                chooseDeck.setDrawable(new Image(new Texture(Resource.CHOOSE_DECK_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                chooseDeck.setDrawable(new Image(new Texture(Resource.CHOOSE_DECK_OFF.address())).getDrawable());
            }
        });
        profile = new Image(new Texture(Resource.PROFILE_OFF.address()));
        profile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_CLICKED.address())).getDrawable());
                game.changeScreen(new ProfileView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                profile.setDrawable(new Image(new Texture(Resource.PROFILE_OFF.address())).getDrawable());
            }
        });
        ranking = new Image(new Texture(Resource.RANKING_OFF.address()));
        ranking.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ranking.setDrawable(new Image(new Texture(Resource.RANKING_CLICKED.address())).getDrawable());
                game.changeScreen(new RankingView(game, currentUsername));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ranking.setDrawable(new Image(new Texture(Resource.RANKING_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                ranking.setDrawable(new Image(new Texture(Resource.RANKING_OFF.address())).getDrawable());
            }
        });
        logout = new Image(new Texture(Resource.LOGOUT_OFF.address()));
        logout.setPosition(574, 50);
        logout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logout.setDrawable(new Image(new Texture(Resource.LOGOUT_CLICKED.address())).getDrawable());
                game.changeScreen(new LoginView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                logout.setDrawable(new Image(new Texture(Resource.LOGOUT_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                logout.setDrawable(new Image(new Texture(Resource.LOGOUT_OFF.address())).getDrawable());
            }
        });
        exit = new Image(new Texture(Resource.EXIT_OFF.address()));
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
        mainTable.add(chooseDeck);
        mainTable.row();
        mainTable.add(profile);
        mainTable.row();
        mainTable.add(ranking);
        mainTable.row();
        mainTable.add(exit);
        stage.addActor(background);
        stage.addActor(mainTable);
        stage.addActor(logout);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.MAIN_BACKGROUND.address()));
    }
}
