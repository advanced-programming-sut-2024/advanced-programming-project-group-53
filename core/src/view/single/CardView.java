package view.single;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.Menu;
import controller.StartMenu;
import game.GWENT;
import model.card.*;
import model.game.Player;
import model.game.User;
import view.Resource;

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
        menu = StartMenu.setInstance(currentUsername, faction, new Commander(commander));
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
                        boolean added = ((StartMenu)menu).addSpecialToDeck(special);
                        if (!added) return; //TODO : show message to show that card number is more than max number
                        Image image1 = new Image(new Texture(special.address()));
                        image1.setWidth(200);
                        image1.setHeight(200 * 1.9f);
                        deckSpecialImages.add(image1);
                        image1.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                deckSpecialImages.remove(image1);
                                deckSpecial.remove(special);
                                ((StartMenu)menu).getDeck().removeCard(special.name());
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
                        boolean added = ((StartMenu)menu).addUnitToDeck(unit);
                        if (!added) return; // TODO : message to show that card number is more that secure state.
                        Image image1 = new Image(new Texture(unit.address()));
                        image1.setWidth(200);
                        image1.setHeight(200 * 1.9f);
                        deckUnitImages.add(image1);
                        image1.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                deckUnitImages.remove(image1);
                                deckUnit.remove(unit);
                                ((StartMenu)menu).getDeck().removeCard(unit.name());
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
                //TODO: save deck!
                boolean isOKToSaveDeck = ((StartMenu)menu).availableToSave();
                if (!isOKToSaveDeck) return; //TODO : show message for that.
                Player currentPlayer = new Player(User.findUser(currentUsername), ((StartMenu) menu).getDeck(),
                        faction, ((StartMenu)menu).getCommanderUser());
                System.out.println(currentPlayer.getCommander().getCommanderInformation().name());
                game.changeScreen(new LoginView(game, currentUsername, currentPlayer));
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
        ScrollPane cardScroll = new ScrollPane(card, skin);
        cardScroll.setWidth(450);
        cardScroll.setHeight(800);
        cardScroll.setPosition(50, 212);
        cardScroll.setFlickScroll(true);
        cardScroll.setFadeScrollBars(false);
        ScrollPane deckScroll = new ScrollPane(deck, skin);
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

    public CardView(GWENT game, Faction faction, CommanderInformation commander, String currentUsername, String username1, Player player) {
        super(game);
        this.username1 = username1;
        this.currentUsername = currentUsername;
        menu = StartMenu.setInstance(currentUsername, faction, new Commander(commander));
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
                        boolean added = ((StartMenu)menu).addSpecialToDeck(special);
                        if (!added) return; // TODO : message to show that card number is more that secure state.
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
                        boolean added = ((StartMenu)menu).addUnitToDeck(unit);
                        if (!added) return; // TODO : message to show that card number is more that secure state.
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
                //TODO: save deck!
                boolean isOKToSaveDeck = ((StartMenu)menu).availableToSave();
                if (!isOKToSaveDeck) return; //TODO : show message for that.
                Player currentPlayer = new Player(User.findUser(currentUsername), ((StartMenu) menu).getDeck(),
                        faction, ((StartMenu)menu).getCommanderUser());
                System.out.println(currentPlayer.getCommander().getCommanderInformation());
                game.changeScreen(new GameView(game, username1, currentUsername, player, currentPlayer));
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
        ScrollPane cardScroll = new ScrollPane(card, skin);
        cardScroll.setWidth(450);
        cardScroll.setHeight(800);
        cardScroll.setPosition(50, 212);
        cardScroll.setFlickScroll(true);
        cardScroll.setFadeScrollBars(false);
        ScrollPane deckScroll = new ScrollPane(deck, skin);
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
