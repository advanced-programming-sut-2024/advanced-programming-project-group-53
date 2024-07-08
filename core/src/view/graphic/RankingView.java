package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.MainMenu;
import game.GWENT;
import network.Command;
import network.Connector;
import network.Instruction;
import model.view.Resource;

import java.util.ArrayList;

public class RankingView extends View {
    private final VerticalGroup lines;
    private ArrayList<Label> players = new ArrayList<>();
    private final Image mainMenu;
    private final Image exit;
    private final ScrollPane ranking;

    public RankingView(GWENT game, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = MainMenu.getInstance();
        lines = new VerticalGroup();
        lines.space(10);
        perform(new Connector().perform(new Instruction(Command.RANKING)));
        mainMenu = new Image(new Texture(Resource.MAIN_MENU_OFF.address()));
        mainMenu.setPosition(574, 50);
        mainMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenu.setDrawable(new Image(new Texture(Resource.MAIN_MENU_CLICKED.address())).getDrawable());
                game.changeScreen(new MainView(game, currentUsername));
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
        ranking = new ScrollPane(lines, skin);
        ranking.setBounds(650 - 200, 650 - 200, 400, 400);
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
        String[] users = instruction.arguments()[0].split(" ");
        for (String user : users)
            players.add(new Label(user, skin));
    }
}
