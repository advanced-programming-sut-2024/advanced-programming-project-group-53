package view.single;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.GameMenu;
import game.GWENT;
import model.card.*;
import model.game.Player;
import model.game.Table;
import view.components.ImageWrapper;
import model.view.Resource;

import java.util.ArrayList;
import java.util.Objects;

import static model.card.SpecialInformation.*;

public class GameView extends View {
    private Commander player1Commander;
    private Image player1CommanderImage;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player1Siege;
    private ArrayList<Card> player1Ranged;
    private ArrayList<Card> player1Closed;
    private HorizontalGroup player1HandGroup;
    private HorizontalGroup player1SiegeGroup;
    private HorizontalGroup player1RangedGroup;
    private HorizontalGroup player1ClosedGroup;
    private ScrollPane scrollPaneHand;
    private Image player1SiegeHead;
    private Image player1RangedHead;
    private Image player1ClosedHead;

    private Commander player2Commander;
    private Image player2CommanderImage;
    private ArrayList<Card> player2Siege;
    private ArrayList<Card> player2Ranged;
    private ArrayList<Card> player2Closed;
    private HorizontalGroup player2SiegeGroup;
    private HorizontalGroup player2RangedGroup;
    private HorizontalGroup player2ClosedGroup;
    private Image player2SiegeHead;
    private Image player2RangedHead;
    private Image player2ClosedHead;

    private Table gameTable;

    private Image discardPile;
    private HorizontalGroup discardPileGroup;
    private ScrollPane discardPileScrollPane;
    private ArrayList<Card> discards;

    private Card clicked;

    private Card decoyAbility;

    //commander attributes
    private boolean P = false;
    private double PTime = 0;
    private boolean O = false;
    private double OTime = 0;
    private boolean I = false;
    private double ITime = 0;
    private boolean N = false;
    private double NTime = 0;
    private boolean S = false;
    private double STime = 0;
    private boolean R = false;
    private double RTime = 0;
    private boolean C = false;
    private double CTime = 0;
    private boolean E = false;
    private double ETime = 0;
    private boolean W = false;
    private double WTime = 0;
    private boolean V = false;
    private double VTime = 0;
    //cheat codes key.
    private boolean ONE = false;
    private double ONETime = 0;
    private boolean TWO = false;
    private double TWOTime = 0;
    private boolean THREE = false;
    private double THREETime = 0;
    private boolean FOUR = false;
    private double FOURTime = 0;
    private boolean FIVE = false;
    private double FIVETime = 0;
    private boolean SIX = false;
    private double SIXTime = 0;
    private boolean SEVEN = false;
    private double SEVENTime = 0;
    private boolean EIGHT = false;
    private double EIGHTTime = 0;
    private boolean NINE = false;
    private double NINETime = 0;
    private boolean ZERO = false;
    private double ZEROTime = 0;
    private boolean U = false;
    private double UTime = 0;

    private final double time = 0.5;

    private final HorizontalGroup commanderGroup;
    private final ScrollPane commanderScrollPane;
    private final com.badlogic.gdx.scenes.scene2d.ui.Table commanderTable;
    private final Image ok;
    private final Image save;
    private Card commanderAbility;

    //life
    private Image life11;
    private Image life12;
    private Image life21;
    private Image life22;

    private ScrollPane middle;
    private HorizontalGroup middleGroup;
    private ArrayList<Special> middles;

    private Label label11;
    private Label label12;
    private Label label13;
    private Label label14;
    private Label label21;
    private Label label22;
    private Label label23;
    private Label label24;

    public GameView(GWENT game, String username1, String username2, Player player1, Player player2) {
        super(game);
        menu = GameMenu.setInstance(player1, player2);
        this.gameTable = ((GameMenu) menu).getTable();
        this.currentUsername = gameTable.getPlayers(0).getUser().username();
        this.username1 = gameTable.getPlayers(0).getUser().username();
        this.username2 = gameTable.getPlayers(1).getUser().username();
        player1Commander = gameTable.getPlayers(0).getCommander();
        player2Commander = gameTable.getPlayers(1).getCommander();
        player1Hand = gameTable.getPlayers(0).getHand().getCards();
        player1Deck = gameTable.getPlayers(0).getDeck().getCards();
        player1CommanderImage = new Image(new Texture(player1Commander.getCommanderInformation().address()));
        System.out.println(player1Commander.getCommanderInformation().name());
        player1CommanderImage.setWidth(50);
        player1CommanderImage.setHeight(70);
        player1CommanderImage.setPosition(75, 285);
        player1HandGroup = new HorizontalGroup();
        player1HandGroup.setBounds(310, 285, 495, 65);
        handUpdater();
        label11 = new Label("0", skin);
        label11.setBounds(280, 380, 15, label11.getHeight());
        label12 = new Label("0", skin);
        label12.setBounds(280, 455, 15, label11.getHeight());
        label13 = new Label("0", skin);
        label13.setBounds(280, 525, 15, label11.getHeight());
        label14 = new Label("0", skin);
        label14.setBounds(235, 395, 15, label11.getHeight());
        label21 = new Label("0", skin);
        label21.setBounds(280, 605, 15, label11.getHeight());
        label22 = new Label("0", skin);
        label22.setBounds(280, 675, 15, label11.getHeight());
        label23 = new Label("0", skin);
        label23.setBounds(280, 745, 15, label11.getHeight());
        label24 = new Label("0", skin);
        label24.setBounds(235, 610, 15, label11.getHeight());
        //handle life part of game
        life11 = new Image(new Texture(Resource.LIFE_ON.address()));
        life11.setBounds(75, 360, 20, 20);
        life11.setPosition(80, 385);
        life12 = new Image(new Texture(Resource.LIFE_ON.address()));
        life12.setBounds(100, 360, 20, 20);
        life12.setPosition(105, 385);
        life21 = new Image(new Texture(Resource.LIFE_ON.address()));
        life21.setBounds(75, 360, 20, 20);
        life21.setPosition(80, 635);
        life22 = new Image(new Texture(Resource.LIFE_ON.address()));
        life22.setBounds(100, 360, 20, 20);
        life22.setPosition(105, 635);
        //code for make life off
//      life11.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());

        //commander part
        commanderGroup = new HorizontalGroup();
        commanderGroup.space(10);
        commanderScrollPane = new ScrollPane(commanderGroup, skin);
        commanderScrollPane.setFlickScroll(true);
        commanderScrollPane.setFadeScrollBars(false);
        ok = new Image(new Texture(Resource.OK_OFF.address()));
        ok.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ok.setDrawable(new Image(new Texture(Resource.OK_CLICKED.address())).getDrawable());
                //TODO:do sth.
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ok.setDrawable(new Image(new Texture(Resource.OK_ON.address())).getDrawable());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                ok.setDrawable(new Image(new Texture(Resource.OK_OFF.address())).getDrawable());
            }
        });
        save = new Image(new Texture(Resource.SAVE_OFF.address()));
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                save.setDrawable(new Image(new Texture(Resource.SAVE_CLICKED.address())).getDrawable());
                //TODO:do sth.
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
        commanderTable = new com.badlogic.gdx.scenes.scene2d.ui.Table();
        commanderTable.setBounds(50, 20, 924, 188);
        commanderTable.add(commanderScrollPane);
        commanderTable.row();
        commanderTable.add(ok);
        commanderTable.add(save);
        //commanderTable.setVisible(false);

        //scroll pane for hand
        scrollPaneHand = new ScrollPane(player1HandGroup, skin);
        scrollPaneHand.setFlickScroll(true);
        scrollPaneHand.setFadeScrollBars(false);
        player1SiegeGroup = new HorizontalGroup();
        player1SiegeGroup.setBounds(400, 365, 380, 65);
        player1SiegeGroup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && clicked.getType() == Type.Siege) {
                    player1Siege.add(clicked);
                    siege1();
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });

        player1RangedGroup = new HorizontalGroup();
        player1RangedGroup.setBounds(400, 440, 380, 65);
        player1RangedGroup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && clicked.getType() == Type.RangedCombat) {
                    player1Ranged.add(clicked);
                    ranged1();
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });
        player1ClosedGroup = new HorizontalGroup();
        player1ClosedGroup.setBounds(400, 510, 380, 65);
        player1ClosedGroup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && clicked.getType() == Type.CloseCombat) {
                    player1Closed.add(clicked);
                    closed1();
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });
        player1SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
        player1SiegeHead.setBounds(315, 365, 50, 65);
        player1SiegeHead.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && (clicked.getType() == Type.Spell || clicked.getType() == Type.Weather)) {
                    player1SiegeHead.setDrawable(new Image(new Texture(clicked.address())).getDrawable());
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });
        player1RangedHead = new Image(new Texture(Resource.EMPTY.address()));
        player1RangedHead.setBounds(315, 440, 50, 65);
        player1RangedHead.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && (clicked.getType() == Type.Spell || clicked.getType() == Type.Weather)) {
                    player1RangedHead.setDrawable(new Image(new Texture(clicked.address())).getDrawable());
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });
        player1ClosedHead = new Image(new Texture(Resource.EMPTY.address()));
        player1ClosedHead.setBounds(315, 510, 50, 65);
        player1ClosedHead.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && (clicked.getType() == Type.Spell || clicked.getType() == Type.Weather)) {
                    player1ClosedHead.setDrawable(new Image(new Texture(clicked.address())).getDrawable());
                    player1Hand.remove(clicked);
                    gameTable.getPlayers(0).getHand().removeCard(clicked.getName());
                    handUpdater();
                    clicked = null;
                }
            }
        });
        System.out.println(player2Commander.getCommanderInformation().name());
        player2CommanderImage = new Image(new Texture(player2Commander.getCommanderInformation().address()));
        player2CommanderImage.setWidth(50);
        player2CommanderImage.setHeight(70);
        player2CommanderImage.setPosition(75, 685);
        player2SiegeGroup = new HorizontalGroup();
        player2SiegeGroup.setBounds(400, 732, 380, 65);
        player2RangedGroup = new HorizontalGroup();
        player2RangedGroup.setBounds(400, 662, 380, 65);
        player2ClosedGroup = new HorizontalGroup();
        player2ClosedGroup.setBounds(400, 590, 380, 65);
        player2SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
        player2SiegeHead.setBounds(315, 732, 50, 65);
        player2RangedHead = new Image(new Texture(Resource.EMPTY.address()));
        player2RangedHead.setBounds(315, 662, 50, 65);
        player2ClosedHead = new Image(new Texture(Resource.EMPTY.address()));
        player2ClosedHead.setBounds(315, 590, 50, 65);
        stage.addListener(new ClickListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.P)
                    P = true;
                else if (keycode == Input.Keys.O)
                    O = true;
                else if (keycode == Input.Keys.I)
                    I = true;
                else if (keycode == Input.Keys.C)
                    C = true;
                else if (keycode == Input.Keys.N)
                    N = true;
                else if (keycode == Input.Keys.S)
                    S = true;
                else if (keycode == Input.Keys.R)
                    R = true;
                else if (keycode == Input.Keys.E)
                    E = true;
                else if (keycode == Input.Keys.W) {
                    W = true;
                } else if (keycode == Input.Keys.V) {
                    V = true;
                } else if (keycode == Input.Keys.NUM_1) {
                    ONE = true;
                } else if (keycode == Input.Keys.NUM_2) {
                    TWO = true;
                } else if (keycode == Input.Keys.NUM_3) {
                    THREE = true;
                } else if (keycode == Input.Keys.NUM_4) {
                    FOUR = true;
                } else if (keycode == Input.Keys.NUM_5) {
                    FIVE = true;
                } else if (keycode == Input.Keys.NUM_6) {
                    SIX = true;
                } else if (keycode == Input.Keys.NUM_7) {
                    SEVEN = true;
                } else if (keycode == Input.Keys.NUM_8) {
                    EIGHT = true;
                } else if (keycode == Input.Keys.NUM_9) {
                    NINE = true;
                } else if (keycode == Input.Keys.NUM_0) {
                    ZERO = true;
                } else if (keycode == Input.Keys.U) {
                    U = true;
                }
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.P)
                    P = false;
                else if (keycode == Input.Keys.O)
                    O = false;
                else if (keycode == Input.Keys.I)
                    I = false;
                else if (keycode == Input.Keys.C)
                    C = false;
                else if (keycode == Input.Keys.N)
                    N = false;
                else if (keycode == Input.Keys.S)
                    S = false;
                else if (keycode == Input.Keys.R)
                    R = false;
                else if (keycode == Input.Keys.E) {
                    E = false;
                } else if (keycode == Input.Keys.W) {
                    W = false;
                } else if (keycode == Input.Keys.V) {
                    V = false;
                } else if (keycode == Input.Keys.NUM_1) {
                    ONE = false;
                } else if (keycode == Input.Keys.NUM_2) {
                    TWO = false;
                } else if (keycode == Input.Keys.NUM_3) {
                    THREE = false;
                } else if (keycode == Input.Keys.NUM_4) {
                    FOUR = false;
                } else if (keycode == Input.Keys.NUM_5) {
                    FIVE = false;
                } else if (keycode == Input.Keys.NUM_6) {
                    SIX = false;
                } else if (keycode == Input.Keys.NUM_7) {
                    SEVEN = false;
                } else if (keycode == Input.Keys.NUM_8) {
                    EIGHT = false;
                } else if (keycode == Input.Keys.NUM_9) {
                    NINE = false;
                } else if (keycode == Input.Keys.NUM_0) {
                    ZERO = false;
                } else if (keycode == Input.Keys.U) {
                    U = false;
                }
                return true;
            }
        });
        middleGroup = new HorizontalGroup();
        middleGroup.space(10);
        middle = new ScrollPane(middleGroup, skin);
        middle.setBounds(75, 485, 125, 70);
        middle.setPosition(50, 485);

        discardPile = new Image(new Texture(Resource.EMPTY.address()));
        discardPile.setBounds(820, 285, 55, 70);
        discardPile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                discardPileScrollPane.setVisible(true);
            }
        });
        discardPileGroup = new HorizontalGroup();
        discardPileGroup.space(10);
        discardPileScrollPane = new ScrollPane(discardPileGroup, skin);
        discardPileScrollPane.setBounds(200, 900, 500, 100);
        discardPileScrollPane.setVisible(false);

        stage.addActor(background);
        stage.addActor(player1CommanderImage);
        stage.addActor(player1HandGroup);
        stage.addActor(player1SiegeGroup);
        stage.addActor(player1RangedGroup);
        stage.addActor(player1ClosedGroup);
        stage.addActor(player1SiegeHead);
        stage.addActor(player1RangedHead);
        stage.addActor(player1ClosedHead);
        stage.addActor(player2CommanderImage);
        stage.addActor(player2SiegeGroup);
        stage.addActor(player2RangedGroup);
        stage.addActor(player2ClosedGroup);
        stage.addActor(player2SiegeHead);
        stage.addActor(player2RangedHead);
        stage.addActor(player2ClosedHead);
        stage.addActor(life11);
        stage.addActor(life12);
        stage.addActor(life21);
        stage.addActor(life22);
        stage.addActor(middle);
        stage.addActor(discardPile);
        stage.addActor(discardPileScrollPane);
        stage.addActor(label11);
        stage.addActor(label12);
        stage.addActor(label13);
        stage.addActor(label14);
        stage.addActor(label21);
        stage.addActor(label22);
        stage.addActor(label23);
        stage.addActor(label24);
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.GAME_BACKGROUND.address()));
        background.setPosition(0, 512 - background.getHeight() / 2);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        PTime += delta;
        NTime += delta;
        STime += delta;
        RTime += delta;
        CTime += delta;
        ETime += delta;
        WTime += delta;
        VTime += delta;
        ONETime += delta;
        TWOTime += delta;
        THREETime += delta;
        FOURTime += delta;
        FIVETime += delta;
        SIXTime += delta;
        SEVENTime += delta;
        EIGHTTime += delta;
        NINETime += delta;
        ZEROTime += delta;
        UTime += delta;
        if (P && PTime > time) {
            if (clicked != null) {
                if (Objects.equals(clicked.getAbility(), Ability.Spy.name())) {
                    gameTable.getPlayGround().placeSpyUnitCard(5, clicked, gameTable.getPlayers(0));
                    System.out.println("spy is about to place");
                }
                updateAll();
            }
        }
        if (O && OTime > time) {
            if (clicked != null) {
                if (Objects.equals(clicked.getAbility(), Ability.Spy.name())) {
                    gameTable.getPlayGround().placeSpyUnitCard(4, clicked, gameTable.getPlayers(0));
                    System.out.println("spy is about to place");
                }
                updateAll();
            }
        }
        if (I && ITime > time) {
            if (clicked != null) {
                if (Objects.equals(clicked.getAbility(), Ability.Spy.name())) {
                    gameTable.getPlayGround().placeSpyUnitCard(3, clicked, gameTable.getPlayers(0));
                    System.out.println("spy is about to place");
                }
                updateAll();
            }
        }
        if (N && NTime > time) {
            boolean ended = gameTable.changeTurn();
            gameTable.getPlayGround().printPlayGround();
            gameTable.printCurrentPlayerHand();
            if (ended) {
                game.changeScreen(new EndView(game, gameTable.getGameInformation().getWinnerName(),
                        gameTable.getGameInformation().getLoserName(), gameTable.getPlayers(0).getUser().username(), gameTable.getGameInformation().getDraw()));
                System.out.println("YOU DIED");
            }
            updateAll();
        }
        if (S && STime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!Objects.equals(clicked.getName(), Decoy.name())) {
                    int state = -3;
                    state = ((GameMenu) menu).placeCardInRow(clicked, 0, 0);
                    System.out.println("STATE = " + state);
                    gameTable.getPlayGround().printPlayGround();
                    gameTable.printCurrentPlayerHand();
                } else if (Objects.equals(clicked.getName(), Decoy.name())) {
                    if (decoyAbility != null)
                        gameTable.placeDecoy(decoyAbility, 0);
                }
                updateAll();
            }
        }
        if (R && RTime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!Objects.equals(clicked.getName(), Decoy.name())) {
                    int state = -3;
                    state = ((GameMenu) menu).placeCardInRow(clicked, 1, 0);
                    System.out.println("STATE = " + state);
                    gameTable.printCurrentPlayerHand();
                } else if (Objects.equals(clicked.getName(), Decoy.name())) {
                    if (decoyAbility != null)
                        gameTable.placeDecoy(decoyAbility, 1);
                }
                updateAll();
            }
        }
        if (C && CTime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!Objects.equals(clicked.getName(), Decoy.name())) {
                    int state = -3;
                    state = ((GameMenu) menu).placeCardInRow(clicked, 2, 0);
                    System.out.println("STATE = " + state);
                    gameTable.printCurrentPlayerHand();
                } else if (Objects.equals(clicked.getName(), Decoy.name())) {
                    if (decoyAbility != null)
                        gameTable.placeDecoy(decoyAbility, 2);
                }
                updateAll();
            }
        }
        if (E && ETime > time) {
            System.out.println("execute commander");
            ((GameMenu) menu).commanderExecution();
            updateAll();
        }
        if (V && VTime > time) {
            if (clicked != null) {
                gameTable.veto(clicked);
                gameTable.printCurrentPlayerHand();
                updateAll();
            }
        }
        if (W && WTime > time) {
            if (clicked != null) {
                if (clicked.isSpecial()) {
                    gameTable.getPlayGround().placeSpellCard(clicked, gameTable.getPlayers(0),
                            gameTable.getPlayers(1));
                    gameTable.printCurrentPlayerHand();
                    updateAll();
                }
            }
        }

        if (ONE && ONETime > time) {
            gameTable.cheatCode1RecoverCrystals();
            updateAll();
        }

        if (TWO && TWOTime > time) {
            gameTable.cheatCode2addCardToHand();
            updateAll();
        }

        if (THREE && THREETime > time) {
            gameTable.cheatCode3Win();
            game.changeScreen(new EndView(game, gameTable.getGameInformation().getWinnerName(),
                    gameTable.getGameInformation().getLoserName(), gameTable.getPlayers(0).getUser().username(),  gameTable.getGameInformation().getDraw()));
        }

        if (FOUR && FOURTime > time) {
            gameTable.cheatCode4Lose();
            game.changeScreen(new EndView(game, gameTable.getGameInformation().getWinnerName(),
                    gameTable.getGameInformation().getLoserName(), gameTable.getPlayers(0).getUser().username(), gameTable.getGameInformation().getDraw()));
        }

        if (FIVE && FIVETime > time) {
            gameTable.cheatCode5Draw();
            game.changeScreen(new EndView(game, gameTable.getGameInformation().getWinnerName(),
                    gameTable.getGameInformation().getLoserName(), gameTable.getPlayers(0).getUser().username(), gameTable.getGameInformation().getDraw()));
        }

        if (SIX && SIXTime > time) {
            gameTable.cheatCode6Mardroeme();
            updateAll();
        }

        if (SEVEN && SEVENTime > time) {
            gameTable.cheatCode7Berserker();
            updateAll();
        }

        if (EIGHT && EIGHTTime > time) {
            gameTable.cheatCode8CommanderHorn();
            updateAll();
        }

        if (NINE && NINETime > time) {
            gameTable.cheatCode9YoungBerserker();
            updateAll();
        }

        if (ZERO && ZEROTime > time) {
            gameTable.cheatCode10Spy();
            updateAll();
        }

        if (U && UTime > time) {
            gameTable.cheatCode11Cow();
            updateAll();
        }
    }

    private void siege1() {
        player1SiegeGroup.clear();
        player1Siege = gameTable.getPlayGround().getUnitCardsInRow(0);
        player1Siege.removeIf(Objects::isNull);
        for (int i = 0; i < player1Siege.size(); i++) {
            Card card = player1Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            imageWrapper.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (decoyAbility == card)
                        decoyAbility = null;
                    else
                        decoyAbility = card;
                    System.out.println("Card For Decoy: " + decoyAbility);
                }
            });
            player1SiegeGroup.addActor(imageWrapper);
        }
        player1SiegeHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(0) != null)
            player1SiegeHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(0).address())).getDrawable());
        else
            player1SiegeHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void ranged1() {
        player1RangedGroup.clear();
        player1Ranged = gameTable.getPlayGround().getUnitCardsInRow(1);
        player1Ranged.removeIf(Objects::isNull);
        for (int i = 0; i < player1Ranged.size(); i++) {
            Card card = player1Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            imageWrapper.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (decoyAbility == card)
                        decoyAbility = null;
                    else
                        decoyAbility = card;
                    System.out.println("Card For Decoy: " + decoyAbility);
                }
            });
            player1RangedGroup.addActor(imageWrapper);
        }
        player1RangedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(1) != null)
            player1RangedHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(1).address())).getDrawable());
        else
            player1RangedHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void closed1() {
        player1ClosedGroup.clear();
        player1Closed = gameTable.getPlayGround().getUnitCardsInRow(2);
        player1Closed.removeIf(Objects::isNull);
        for (int i = 0; i < player1Closed.size(); i++) {
            Card card = player1Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            imageWrapper.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (decoyAbility == card)
                        decoyAbility = null;
                    else
                        decoyAbility = card;
                    System.out.println("Card For Decoy: " + decoyAbility);
                }
            });
            player1ClosedGroup.addActor(imageWrapper);
        }
        player1ClosedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(2) != null)
            player1ClosedHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(2).address())).getDrawable());
        else
            player1ClosedHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void siege2() {
        player2SiegeGroup.clear();
        player2Siege = gameTable.getPlayGround().getUnitCardsInRow(5);
        player1Siege.removeIf(Objects::isNull);
        for (int i = 0; i < player2Siege.size(); i++) {
            Card card = player2Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2SiegeGroup.addActor(imageWrapper);
        }
        player2SiegeHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(5) != null)
            player2SiegeHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(5).address())).getDrawable());
        else
            player2SiegeHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void ranged2() {
        player2RangedGroup.clear();
        player2Ranged = gameTable.getPlayGround().getUnitCardsInRow(4);
        player2Ranged.removeIf(Objects::isNull);
        for (int i = 0; i < player2Ranged.size(); i++) {
            Card card = player2Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2RangedGroup.addActor(imageWrapper);
        }
        player2RangedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(4) != null)
            player2RangedHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(4).address())).getDrawable());
        else
            player2RangedHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void closed2() {
        player2ClosedGroup.clear();
        player2Closed = gameTable.getPlayGround().getUnitCardsInRow(3);
        player2Closed.removeIf(Objects::isNull);
        for (int i = 0; i < player2Closed.size(); i++) {
            Card card = player2Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2ClosedGroup.addActor(imageWrapper);
        }
        player2ClosedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(3) != null)
            player2ClosedHead.setDrawable(new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(3).address())).getDrawable());
        else
            player2ClosedHead.setDrawable(new Image(new Texture(Resource.EMPTY.address())).getDrawable());
    }

    private void handUpdater() {
        player1HandGroup.clear();
        player1Hand = gameTable.getPlayers(0).getHand().getCards();
        player1Hand.removeIf(Objects::isNull);
        for (int i = 0; i < player1Hand.size(); i++) {
            Card card = player1Hand.get(i);
            ImageWrapper image = new ImageWrapper(card.address(), 40, 52);
            image.setPosition(i * 45, 10);
            image.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (clicked == card)
                        clicked = null;
                    else
                        clicked = card;
                    System.out.println(clicked);
                }
            });
            player1HandGroup.addActor(image);
        }
    }

    private void lifeUpdater() {
        life11.setDrawable(new Image(new Texture(Resource.LIFE_ON.address())).getDrawable());
        life12.setDrawable(new Image(new Texture(Resource.LIFE_ON.address())).getDrawable());
        life21.setDrawable(new Image(new Texture(Resource.LIFE_ON.address())).getDrawable());
        life22.setDrawable(new Image(new Texture(Resource.LIFE_ON.address())).getDrawable());
        if (gameTable.getPlayers(0).hp() == 1)
            life12.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
        if (gameTable.getPlayers(1).hp() == 1)
            life22.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
        if (gameTable.getPlayers(0).hp() <= 0) {
            life12.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
            life11.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
        }
        if (gameTable.getPlayers(1).hp() <= 0) {
            life22.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
            life21.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
        }
    }

    private void commanderUpdater() {
        player1Commander = gameTable.getPlayers(0).getCommander();
        player2Commander = gameTable.getPlayers(1).getCommander();
        player1CommanderImage = new Image(new Texture(player1Commander.getCommanderInformation().address()));
        player2CommanderImage = new Image(new Texture(player1Commander.getCommanderInformation().address()));
    }

    private void weather() {
        middleGroup.clear();
        middles = gameTable.getPlayGround().getSpells();
        for (int i = 0; i < middles.size(); i++) {
            Card card = middles.get(i);
            ImageWrapper image = new ImageWrapper(card.address(), 50, 65);
            image.setPosition(i * 55, 0);
            middleGroup.addActor(image);
        }
    }

    private void discardPile() {
        discardPileGroup.clear();
        discards = gameTable.getPlayers(0).getDiscardPiles().getCards();
        discards.removeIf(Objects::isNull);
        for (int i = 0; i < discards.size(); i++) {
            Card card = discards.get(i);
            ImageWrapper image = new ImageWrapper(card.address(), 50, 65);
            image.setPosition(i * 55, 0);
            discardPileGroup.addActor(image);
        }
    }

    public void scoreUpdated() {
        label11.setText(gameTable.getPlayGround().playerPointInRow(0));
        label12.setText(gameTable.getPlayGround().playerPointInRow(1));
        label13.setText(gameTable.getPlayGround().playerPointInRow(2));
        label14.setText(gameTable.getPlayGround().playersPoint(0));
        label21.setText(gameTable.getPlayGround().playerPointInRow(3));
        label22.setText(gameTable.getPlayGround().playerPointInRow(4));
        label23.setText(gameTable.getPlayGround().playerPointInRow(5));
        label24.setText(gameTable.getPlayGround().playersPoint(1));
    }

    private void updateAll() {
        lifeUpdater();
        commanderUpdater();
        siege1();
        siege2();
        closed1();
        closed2();
        ranged1();
        ranged2();
        weather();
        handUpdater();
        discardPile();
        scoreUpdated();
    }
}
