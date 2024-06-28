package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.bullet.collision._btMprSimplex_t;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.StartMenu;
import game.GWENT;
import model.card.CommanderInformation;
import model.card.Faction;
import network.Instruction;

import java.util.HashMap;

public class StartViewCommander extends View {
    private final HashMap<Image, CommanderInformation> commanderImages;
    private final ScrollPane commanderScroll;
    private final VerticalGroup commanderVerticalGroup;
    private CommanderInformation commander;
    private Image currentCommander;
    private final Image save;
    private final Image changeFaction;
    private final Image exit;


    public StartViewCommander(GWENT game, Faction faction) {
        super(game);
        menu = StartMenu.getInstance();
        commanderImages = new HashMap<>();
        commanderVerticalGroup = new VerticalGroup();
        commanderVerticalGroup.space(10);
        commanderScroll = new ScrollPane(commanderVerticalGroup, scrollPane);
        for (CommanderInformation commanderInformation : CommanderInformation.values()) {
            if (commanderInformation.faction() == faction) {
                Image image = new Image(new Texture(commanderInformation.address()));
                image.setWidth(400);
                image.setHeight(400 * 1.9f);
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (image != currentCommander) {
                            currentCommander.setDrawable(new Image(new Texture(commanderImages.get(image).address())).getDrawable());
                            commander = commanderImages.get(image);
                        }
                    }

                    @Override
                    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                        image.setScale(1.01f);
                    }

                    @Override
                    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                        image.setScale(1);
                    }
                });
                commanderImages.put(image, commanderInformation);
                commanderVerticalGroup.addActor(image);
                if (currentCommander == null)
                    currentCommander = new Image(new Texture(commanderImages.get(image).address()));
            }
        }
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.setPosition(574, 120);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                game.changeScreen(new StartViewCard(game, faction, commander));
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
        changeFaction = new Image(new Texture(Resource.CHANGE_FACTION_OFF.address()));
        changeFaction.setPosition(50, 120);
        changeFaction.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeFaction.setDrawable(new Image(new Texture(Resource.CHANGE_FACTION_CLICKED.address())).getDrawable());
                game.changeScreen(new StartViewFaction(game));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                changeFaction.setDrawable(new Image(new Texture(Resource.CHANGE_FACTION_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                changeFaction.setDrawable(new Image(new Texture(Resource.CHANGE_FACTION_OFF.address())).getDrawable());
            }
        });
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
        currentCommander.setPosition(600, 612 - 200 * 1.9f);
        commanderScroll.setWidth(450);
        commanderScroll.setHeight(800);
        commanderScroll.setPosition(50, 212);
        commanderScroll.setFlickScroll(true);
        commanderScroll.setFadeScrollBars(false);
        stage.addActor(background);
        stage.addActor(currentCommander);
        stage.addActor(commanderScroll);
        stage.addActor(exit);
        stage.addActor(changeFaction);
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
