package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.MainMenu;
import game.GWENT;
import network.Instruction;

import java.util.ArrayList;

public class RankingView extends View {
    private final VerticalGroup lines;
    private final ArrayList<Label> players;
    private final Image mainMenu;
    private final Image exit;
    private final ScrollPane ranking;

    public RankingView(GWENT game) {
        super(game);
        menu = MainMenu.getInstance();
        lines = new VerticalGroup();
        lines.space(10);
        players = new ArrayList<>();//TODO: give it the players!
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        players.add(new Label("ali", label));
        mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.setPosition(574, 50);
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new MainView(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_OFF.address())).getDrawable());
            }
        });
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
        for (Label player : players)
            lines.addActor(player);
        ranking = new ScrollPane(lines, scrollPane);
        ranking.setPosition(512 - ranking.getWidth() / 2, 800 - ranking.getHeight() / 2);
        ranking.setFlickScroll(true);
        ranking.setFadeScrollBars(false);
        stage.addActor(background);
        stage.addActor(ranking);
        stage.addActor(exit);
        stage.addActor(mainMenu);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.RANKING_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}
