package view.single;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.GameMenu;
import game.GWENT;
import model.card.*;
import model.game.Player;
import model.game.Table;
import view.components.ImageWrapper;
import view.Resource;

import java.util.ArrayList;

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

    private Card clicked;

    //commander attributes
    private boolean P = false;
    private double PTime = 0;
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
    private final double time = 0.5;

    private final HorizontalGroup commanderGroup;
    private final ScrollPane commanderScrollPane;
    private final com.badlogic.gdx.scenes.scene2d.ui.Table commanderTable;
    private final Image ok;
    private final Image save;
    private Card commanderAbility;

    //life
    private int life1;
    private int life2;
    private Image life11;
    private Image life12;
    private Image life21;
    private Image life22;

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
        player2SiegeGroup.setBounds(400, 590, 380, 65);
        player2RangedGroup = new HorizontalGroup();
        player2RangedGroup.setBounds(400, 662, 380, 65);
        player2ClosedGroup = new HorizontalGroup();
        player2ClosedGroup.setBounds(400, 732, 380, 65);
        player2SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
        player2SiegeHead.setBounds(315, 590, 50, 65);
        player2RangedHead = new Image(new Texture(Resource.EMPTY.address()));
        player2RangedHead.setBounds(315, 662, 50, 65);
        player2ClosedHead = new Image(new Texture(Resource.EMPTY.address()));
        player2ClosedHead.setBounds(315, 732, 50, 65);
        stage.addListener(new ClickListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.P)
                    P = true;
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
                return true;
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.P)
                    P = false;
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
                }
                return true;
            }
        });
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
        if (P && PTime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!clicked.isSpecial())
                    ((GameMenu) menu).placeCardInRow(clicked, GameMenu.getCardPositionToPlay(clicked), 0);
                updateAll();
            }
        }
        if (N && NTime > time) {

        }
        if (S && STime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!clicked.isSpecial())
                    ((GameMenu) menu).placeCardInRow(clicked, 0, 0);
                else gameTable.getPlayGround().placeSpecialCard(0, clicked);
                updateAll();
            }
        }
        if (R && RTime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!clicked.isSpecial())
                    ((GameMenu) menu).placeCardInRow(clicked, 1, 0);
                else gameTable.getPlayGround().placeSpecialCard(1, clicked);
                updateAll();
            }
        }
        if (C && CTime > time) {
            if (clicked != null) {
                System.out.println("play card " + clicked);
                if (!clicked.isSpecial())
                    ((GameMenu) menu).placeCardInRow(clicked, 2, 0);
                else gameTable.getPlayGround().placeSpecialCard(2, clicked);
                updateAll();
            }
        }
        if (E && ETime > time) {
            System.out.println("execute commander");
            ((GameMenu) menu).commanderExecution();
            updateAll();
        }

    }

    private void siege1() {
        player1SiegeGroup.clear();
        player1Siege = gameTable.getPlayGround().getUnitCardsInRow(0);
        for (int i = 0; i < player1Siege.size(); i++) {
            Card card = player1Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1SiegeGroup.addActor(imageWrapper);
        }
        player1SiegeHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(0) != null)
            player1SiegeHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(0).address()));
        else
            player1SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void ranged1() {
        player1RangedGroup.clear();
        player1Ranged = gameTable.getPlayGround().getUnitCardsInRow(1);
        for (int i = 0; i < player1Ranged.size(); i++) {
            Card card = player1Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1RangedGroup.addActor(imageWrapper);
        }
        player1RangedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(1) != null)
            player1RangedHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(1).address()));
        else
            player1RangedHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void closed1() {
        player1ClosedGroup.clear();
        player1Closed = gameTable.getPlayGround().getUnitCardsInRow(2);
        for (int i = 0; i < player1Closed.size(); i++) {
            Card card = player1Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1ClosedGroup.addActor(imageWrapper);
        }
        player1ClosedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(2) != null)
            player1ClosedHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(2).address()));
        else
            player1ClosedHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void siege2() {
        player2SiegeGroup.clear();
        player2Siege = gameTable.getPlayGround().getUnitCardsInRow(5);
        for (int i = 0; i < player2Siege.size(); i++) {
            Card card = player2Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2SiegeGroup.addActor(imageWrapper);
        }
        player2SiegeHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(5) != null)
            player2SiegeHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(5).address()));
        else
            player2SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void ranged2() {
        player2RangedGroup.clear();
        player2Ranged = gameTable.getPlayGround().getUnitCardsInRow(4);
        for (int i = 0; i < player2Ranged.size(); i++) {
            Card card = player2Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2RangedGroup.addActor(imageWrapper);
        }
        player2RangedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(4) != null)
            player2RangedHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(4).address()));
        else
            player2RangedHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void closed2() {
        player2ClosedGroup.clear();
        player2Closed = gameTable.getPlayGround().getUnitCardsInRow(3);
        for (int i = 0; i < player2Closed.size(); i++) {
            Card card = player2Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2ClosedGroup.addActor(imageWrapper);
        }
        player2ClosedHead.clear();
        if (gameTable.getPlayGround().getSpecialInRow(3) != null)
            player2ClosedHead = new Image(new Texture(gameTable.getPlayGround().getSpecialInRow(3).address()));
        else
            player2ClosedHead = new Image(new Texture(Resource.EMPTY.address()));
    }

    private void handUpdater() {
        player1Hand = gameTable.getPlayers(0).getHand().getCards();
        for (int i = 0; i < player1Hand.size(); i++) {
            Card card = player1Hand.get(i);
            ImageWrapper image = new ImageWrapper(card.address(), 50, 65);
            image.setPosition(i * 55, 0);
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

    private void updateAll() {
        siege1();
        siege2();
        closed1();
        closed2();
        ranged1();
        ranged2();
        handUpdater();
    }
}
