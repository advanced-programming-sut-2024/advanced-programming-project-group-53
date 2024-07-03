package view.single;

import com.badlogic.gdx.graphics.Texture;
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
import model.card.SpecialInformation;
import model.card.UnitInformation;
import view.graphic.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class CardView extends View {
    private final VerticalGroup deck;
    private final ArrayList<UnitInformation> deckUnit;
    private final ArrayList<Image> deckUnitImages;
    private final ArrayList<SpecialInformation> deckSpecial;
    private final ArrayList<Image> deckSpecialImages;
    private final Image save;

    public CardView(GWENT game, Faction faction, CommanderInformation commander, String currentUsername) {
        super(game);
        this.currentUsername = currentUsername;
        menu = StartMenu.getInstance();
        VerticalGroup card = new VerticalGroup();
        card.space(10);
        deck = new VerticalGroup();
        deck.space(10);
        HashMap<Image, UnitInformation> unitImages = new HashMap<>();
        deckUnit = new ArrayList<>();
        deckUnitImages = new ArrayList<>();
        HashMap<Image, SpecialInformation> specialImages = new HashMap<>();
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
                /*String[] deck = new String[deckUnit.size() + deckSpecial.size() + 1];
                deck[0] = commander.toString();
                for (int i = 0; i < deckUnit.size(); i++)
                    deck[i + 1] = deckUnit.get(i).toString();
                for (int i = 0; i < deckSpecial.size(); i++)
                    deck[i + 1 + deckSpecial.size()] = deckSpecial.get(i).toString();*/
                //TODO: save deck!
                game.changeScreen(new LoginView(game, currentUsername));
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
        Image changeCommander = new Image(new Texture(Resource.CHANGE_COMMANDER_OFF.address()));
        changeCommander.setPosition(50, 120);
        changeCommander.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeCommander.setDrawable(new Image(new Texture(Resource.CHANGE_COMMANDER_CLICKED.address())).getDrawable());
                game.changeScreen(new CommanderView(game, faction, currentUsername));
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
        Image changeFaction = new Image(new Texture(Resource.CHANGE_FACTION_OFF.address()));
        changeFaction.setPosition(50, 50);
        changeFaction.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                changeFaction.setDrawable(new Image(new Texture(Resource.CHANGE_FACTION_CLICKED.address())).getDrawable());
                game.changeScreen(new FactionView(game, currentUsername));
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
        Image exit = new Image(new Texture(Resource.EXIT_OFF.address()));
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
        ScrollPane cardScroll = new ScrollPane(card, scrollPane);
        cardScroll.setWidth(450);
        cardScroll.setHeight(800);
        cardScroll.setPosition(50, 212);
        cardScroll.setFlickScroll(true);
        cardScroll.setFadeScrollBars(false);
        ScrollPane deckScroll = new ScrollPane(deck, scrollPane);
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

    public CardView(GWENT game, Faction faction, CommanderInformation commander, String currentUsername, String username1) {
        super(game);
        this.username1 = username1;
        this.currentUsername = currentUsername;
        menu = StartMenu.getInstance();
        VerticalGroup card = new VerticalGroup();
        card.space(10);
        deck = new VerticalGroup();
        deck.space(10);
        HashMap<Image, UnitInformation> unitImages = new HashMap<>();
        deckUnit = new ArrayList<>();
        deckUnitImages = new ArrayList<>();
        HashMap<Image, SpecialInformation> specialImages = new HashMap<>();
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
                /*String[] deck = new String[deckUnit.size() + deckSpecial.size() + 1];
                deck[0] = commander.toString();
                for (int i = 0; i < deckUnit.size(); i++)
                    deck[i + 1] = deckUnit.get(i).toString();
                for (int i = 0; i < deckSpecial.size(); i++)
                    deck[i + 1 + deckSpecial.size()] = deckSpecial.get(i).toString();*/
                //TODO: save deck!
                game.changeScreen(new GameView(game, username1, currentUsername));
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
        deckUpdater();
        ScrollPane cardScroll = new ScrollPane(card, scrollPane);
        cardScroll.setWidth(450);
        cardScroll.setHeight(800);
        cardScroll.setPosition(50, 212);
        cardScroll.setFlickScroll(true);
        cardScroll.setFadeScrollBars(false);
        ScrollPane deckScroll = new ScrollPane(deck, scrollPane);
        deckScroll.setWidth(450);
        deckScroll.setHeight(800);
        deckScroll.setPosition(524, 212);
        deckScroll.setFlickScroll(true);
        deckScroll.setFadeScrollBars(false);
        stage.addActor(background);
        stage.addActor(cardScroll);
        stage.addActor(deckScroll);
        stage.addActor(save);
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
}
