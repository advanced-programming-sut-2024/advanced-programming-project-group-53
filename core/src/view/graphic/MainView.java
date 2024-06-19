package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import controller.LoginMenu;
import game.GWENT;

public class MainView extends View {
    private final Table mainTable;
    private final Table logoutTable;
    private final Image gameMenu;
    private final Image profileMenu;
    private final Image logout;
    private final Image exitGame;

    public MainView(GWENT game) {
        super(game);
        menu = LoginMenu.getInstance();
        mainTable = new Table();
        mainTable.setBounds(50, 50, 400, 400 / 1.97f);
        mainTable.align(Align.center);
        logoutTable = new Table();
        logoutTable.setBounds(574, 50, 400, 400 / 5.92f);
        logoutTable.align(Align.center);
        gameMenu = new Image(new Texture(Resource.GAME_MENU_OFF.address()));
        gameMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameMenu.setDrawable(new Image(new Texture(Resource.GAME_MENU_CLICKED.address())).getDrawable());
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                gameMenu.setDrawable(new Image(new Texture(Resource.GAME_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                gameMenu.setDrawable(new Image(new Texture(Resource.GAME_MENU_OFF.address())).getDrawable());
            }
        });
        profileMenu = new Image(new Texture(Resource.PROFILE_MENU_OFF.address()));
        profileMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profileMenu.setDrawable(new Image(new Texture(Resource.PROFILE_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new ProfileView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                profileMenu.setDrawable(new Image(new Texture(Resource.PROFILE_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                profileMenu.setDrawable(new Image(new Texture(Resource.PROFILE_MENU_OFF.address())).getDrawable());
            }
        });
        logout = new Image(new Texture(Resource.LOGOUT_OFF.address()));
        logout.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                logout.setDrawable(new Image(new Texture(Resource.LOGOUT_CLICKED.address())).getDrawable());
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
        exitGame = new Image(new Texture(Resource.EXIT_GAME_OFF.address()));
        exitGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_CLICKED.address())).getDrawable());
                menu.exitGame();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitGame.setDrawable(new Image(new Texture(Resource.EXIT_GAME_OFF.address())).getDrawable());
            }
        });
        mainTable.add(gameMenu);
        mainTable.row();
        mainTable.add(profileMenu);
        mainTable.row();
        mainTable.add(exitGame);
        logoutTable.add(logout);
        stage.addActor(background);
        stage.addActor(mainTable);
        stage.addActor(logoutTable);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.MAIN_BACKGROUND.address()));
    }
}
