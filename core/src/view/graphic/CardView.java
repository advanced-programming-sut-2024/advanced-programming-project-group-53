package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.StartMenu;
import game.GWENT;
import model.card.*;
import network.Command;
import network.Connector;
import network.Instruction;
import model.view.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class CardView extends View {
    private final VerticalGroup card;
    private final VerticalGroup deck;
    private final HashMap<Image, UnitInformation> unitImages;
    private final ArrayList<UnitInformation> deckUnit;
    private final ArrayList<Image> deckUnitImages;
    private final HashMap<Image, SpecialInformation> specialImages;
    private final ArrayList<SpecialInformation> deckSpecial;
    private final ArrayList<Image> deckSpecialImages;
    private final ScrollPane cardScroll;
    private final ScrollPane deckScroll;
    private final Image save;
    private final Image changeFaction;
    private final Image changeCommander;
    private final Image exit;

    public CardView(GWENT game, Faction faction, CommanderInformation commander, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = StartMenu.getInstance();
        card = new VerticalGroup();
        card.space(10);
        deck = new VerticalGroup();
        deck.space(10);
        unitImages = new HashMap<>();
        deckUnit = new ArrayList<>();
        deckUnitImages = new ArrayList<>();
        specialImages = new HashMap<>();
        deckSpecial = new ArrayList<>();
        deckSpecialImages = new ArrayList<>();
        for (SpecialInformation special : SpecialInformation.values()) {
            if (special.faction() == faction || special.faction() == Faction.Neutral) {
                Image image = new Image(new Texture(special.address()));
                image.setWidth(200);
                image.setHeight(200 * 1.9f);
                image.addListener(new ClickListener() {//22 10
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Image image1 = new Image(new Texture(special.address()));
                        image1.setWidth(200);
                        image1.setHeight(200 * 1.9f);
                        deckSpecialImages.add(image1);
                        image1.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                deckSpecialImages.remove(image1);
                                deckSpecial.remove(special);
                                deckUpdater();
                            }

                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                image1.setScale(1.01f);
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                image1.setScale(1);
                            }
                        });
                        deckSpecial.add(special);
                        deckUpdater();
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
                specialImages.put(image, special);
                card.addActor(image);
            }
        }
        for (UnitInformation unit : UnitInformation.values()) {
            if (unit.faction() == faction || unit.faction() == Faction.Neutral) {
                Image image = new Image(new Texture(unit.address()));
                image.setWidth(200);
                image.setHeight(200 * 1.9f);
                image.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        Image image1 = new Image(new Texture(unit.address()));
                        image1.setWidth(200);
                        image1.setHeight(200 * 1.9f);
                        deckUnitImages.add(image1);
                        image1.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                deckUnitImages.remove(image1);
                                deckUnit.remove(unit);
                                deckUpdater();
                            }

                            @Override
                            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                                image1.setScale(1.01f);
                            }

                            @Override
                            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                                image1.setScale(1);
                            }
                        });
                        deckUnit.add(unit);
                        deckUpdater();
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
                unitImages.put(image, unit);
                card.addActor(image);
            }
        }
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.setPosition(574, 120);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                String[] deck = new String[deckUnit.size() + deckSpecial.size() + 1];
                deck[0] = commander.toString();
                for (int i = 1; i < deck.length; i++)

                new Connector().perform(new Instruction(Command.SAVE_DECK));
               // game.changeScreen(new CardView(game, faction, commander));
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
        changeCommander = new Image(new Texture(Resource.CHANGE_COMMANDER_OFF.address()));
        changeCommander.setPosition(50, 120);
        changeCommander.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeCommander.setDrawable(new Image(new Texture(Resource.CHANGE_COMMANDER_CLICKED.address())).getDrawable());
            //    game.changeScreen(new CommanderView(game, faction));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                changeCommander.setDrawable(new Image(new Texture(Resource.CHANGE_COMMANDER_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                changeCommander.setDrawable(new Image(new Texture(Resource.CHANGE_COMMANDER_OFF.address())).getDrawable());
            }
        });
        changeFaction = new Image(new Texture(Resource.CHANGE_FACTION_OFF.address()));
        changeFaction.setPosition(50, 50);
        changeFaction.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeFaction.setDrawable(new Image(new Texture(Resource.CHANGE_FACTION_CLICKED.address())).getDrawable());
                //game.changeScreen(new FactionView(game));
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
        exit.setPosition(574, 50);
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
        deckUpdater();
        cardScroll = new ScrollPane(card, skin);
        cardScroll.setWidth(450);
        cardScroll.setHeight(800);
        cardScroll.setPosition(50, 212);
        cardScroll.setFlickScroll(true);
        cardScroll.setFadeScrollBars(false);
        deckScroll = new ScrollPane(deck, skin);
        deckScroll.setWidth(450);
        deckScroll.setHeight(800);
        deckScroll.setPosition(524, 212);
        deckScroll.setFlickScroll(true);
        deckScroll.setFadeScrollBars(false);
        stage.addActor(background);
        stage.addActor(cardScroll);
        stage.addActor(deckScroll);
        stage.addActor(save);
        stage.addActor(exit);
        stage.addActor(changeCommander);
        stage.addActor(changeFaction);
    }

    private void deckUpdater() {
        deck.clear();
        for (Image image : deckSpecialImages)
            deck.addActor(image);
        for (Image image : deckUnitImages)
            deck.addActor(image);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.START_BACKGROUND.address()));
    }

    @Override
    protected void perform(Instruction instruction) {

    }
}
