package view.graphic;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.GameMenu;
import game.GWENT;
import model.card.*;
import network.Instruction;
import view.components.ImageWrapper;
import view.Resource;

import java.util.ArrayList;

public class GameView extends View {
    private String username1;
    private String username2;
    private Commander player1Commander;
    private Image player1CommanderImage;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player1Siege;
    private ArrayList<Card> player1Ranged;
    private ArrayList<Card> player1Closed;
    private ScrollPane scrollPaneHand;
    private HorizontalGroup player1HandGroup;
    private HorizontalGroup player1SiegeGroup;
    private HorizontalGroup player1RangedGroup;
    private HorizontalGroup player1ClosedGroup;
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

    private boolean P = false;
    private boolean C = false;
    private boolean N = false;
    private boolean S = false;
    private boolean R = false;

    private final HorizontalGroup commanderGroup;
    private final ScrollPane commanderScrollPane;
    private final Table commanderTable;
    private final Image ok;
    private final Image save;
    private Card commanderAbility;

    private Card clicked;
    private int life1;
    private int life2;
    private Image life11;
    private Image life12;
    private Image life21;
    private Image life22;

    public GameView(GWENT game, String username1, String username2) {
        super(game);
        this.currentUsername = username1;
        this.username1 = username1;
        this.username2 = username2;
        menu = GameMenu.getInstance();
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
        commanderTable = new Table();
        commanderTable.setBounds(50, 20, 924, 188);
        commanderTable.add(commanderScrollPane);
        commanderTable.row();
        commanderTable.add(ok);
        commanderTable.add(save);
        //commanderTable.setVisible(false);
        scrollPaneHand = new ScrollPane(player1HandGroup, skin);
        scrollPaneHand.setFlickScroll(true);
        scrollPaneHand.setFadeScrollBars(false);
        player1CommanderImage = new Image(new Texture(player1Commander.getCommanderInformation().address()));
        player1CommanderImage.setWidth(50);
        player1CommanderImage.setHeight(70);
        player1CommanderImage.setPosition(75, 285);
        life11 = new Image(new Texture(Resource.LIFE_ON.address()));
        life11.setBounds(75, 360, 20, 20);
        life12 = new Image(new Texture(Resource.LIFE_ON.address()));
        life12.setBounds(100, 360, 20, 20);
//      life11.setDrawable(new Image(new Texture(Resource.LIFE_OFF.address())).getDrawable());
        player1HandGroup = new HorizontalGroup();
        player1HandGroup.setBounds(310, 285, 495, 65);
        handUpdater();
        player1SiegeGroup = new HorizontalGroup();
        player1SiegeGroup.setBounds(400, 365, 380, 65);
        player1SiegeGroup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && clicked.getType() == Type.Siege) {
                    player1Siege.add(clicked);
                    siege1();
                    player1Hand.remove(clicked);
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
                    handUpdater();
                    clicked = null;
                }
            }
        });
        ImageWrapper imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player1SiegeGroup.addActor(imageWrapper);//TODO: delete
        imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player1RangedGroup.addActor(imageWrapper);//TODO: delete
        imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player1ClosedGroup.addActor(imageWrapper);//TODO: delete
        player1SiegeHead = new Image(new Texture(Resource.EMPTY.address()));
        player1SiegeHead.setBounds(315, 365, 50, 65);
        player1SiegeHead.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clicked != null && (clicked.getType() == Type.Spell || clicked.getType() == Type.Weather)) {
                    player1SiegeHead.setDrawable(new Image(new Texture(clicked.address())).getDrawable());
                    player1Hand.remove(clicked);
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
                    handUpdater();
                    clicked = null;
                }
            }
        });

        player2CommanderImage = new Image(new Texture(player2Commander.getCommanderInformation().address()));
        player2CommanderImage.setWidth(50);
        player2CommanderImage.setHeight(70);
        player2CommanderImage.setPosition(75, 685);
        life21 = new Image(new Texture(Resource.LIFE_ON.address()));
        life21.setBounds(75, 610, 20, 20);
        life22 = new Image(new Texture(Resource.LIFE_ON.address()));
        life22.setBounds(100, 610, 20, 20);
        player2SiegeGroup = new HorizontalGroup();
        player2SiegeGroup.setBounds(400, 590, 380, 65);
        player2RangedGroup = new HorizontalGroup();
        player2RangedGroup.setBounds(400, 662, 380, 65);
        player2ClosedGroup = new HorizontalGroup();
        player2ClosedGroup.setBounds(400, 732, 380, 65);
        imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player2SiegeGroup.addActor(imageWrapper);//TODO: delete
        imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player2RangedGroup.addActor(imageWrapper);//TODO: delete
        imageWrapper = new ImageWrapper(player1Deck.get(2).address(), 50, 65);//TODO: delete
        player2ClosedGroup.addActor(imageWrapper);//TODO: delete
        player2SiegeHead = new Image(new Texture(player1Deck.get(0).address()));
        player2SiegeHead.setBounds(315, 590, 50, 65);
        player2RangedHead = new Image(new Texture(player1Deck.get(0).address()));
        player2RangedHead.setBounds(315, 662, 50, 65);
        player2ClosedHead = new Image(new Texture(player1Deck.get(0).address()));
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
                return true;
            }
        });
        stage.addActor(background);
        stage.addActor(player1CommanderImage);
        stage.addActor(scrollPaneHand);
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
        stage.addActor(commanderTable);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (P) {

        }
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.GAME_BACKGROUND.address()));
        background.setPosition(0, 512 - background.getHeight() / 2);
    }

    @Override
    protected void perform(Instruction instruction) {

    }

    private void siege1() {
        player1SiegeGroup.clear();
        for (int i = 0; i < player1Siege.size(); i++) {
            Card card = player1Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1SiegeGroup.addActor(imageWrapper);
        }
    }

    private void ranged1() {
        player1RangedGroup.clear();
        for (int i = 0; i < player1Ranged.size(); i++) {
            Card card = player1Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1RangedGroup.addActor(imageWrapper);
        }
    }

    private void closed1() {
        player1ClosedGroup.clear();
        for (int i = 0; i < player1Closed.size(); i++) {
            Card card = player1Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player1ClosedGroup.addActor(imageWrapper);
        }
    }

    private void siege2() {
        player2SiegeGroup.clear();
        for (int i = 0; i < player2Siege.size(); i++) {
            Card card = player2Siege.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2SiegeGroup.addActor(imageWrapper);
        }
    }

    private void ranged2() {
        player2RangedGroup.clear();
        for (int i = 0; i < player2Ranged.size(); i++) {
            Card card = player2Ranged.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2RangedGroup.addActor(imageWrapper);
        }
    }

    private void closed2() {
        player2ClosedGroup.clear();
        for (int i = 0; i < player2Closed.size(); i++) {
            Card card = player2Closed.get(i);
            ImageWrapper imageWrapper = new ImageWrapper(card.address(), 50, 65);
            imageWrapper.setPosition(i * 55, 0);
            player2ClosedGroup.addActor(imageWrapper);
        }
    }

    private void handUpdater() {
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
}