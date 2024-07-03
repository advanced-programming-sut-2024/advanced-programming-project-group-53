package view.graphic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import controller.GameMenu;
import game.GWENT;
import model.card.Card;
import model.card.Commander;
import network.Instruction;

import java.util.ArrayList;

public class GameView extends View {
    private String username1;
    private String username2;
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player2Deck;
    private Commander player1Commander;
    private Commander player2Commander;
    private HorizontalGroup player1HandGroup;
    private HorizontalGroup player1Siege;
    private Image player1SiegeHead;
    private HorizontalGroup player2Siege;
    private Image player2SiegeHead;
    private HorizontalGroup player1Ranged;
    private Image player1RangedHead;
    private HorizontalGroup player2Ranged;
    private Image player2RangedHead;
    private HorizontalGroup player1Closed;
    private Image player1ClosedHead;
    private HorizontalGroup player2Closed;
    private Image player2ClosedHead;
    private Image player1CommanderImage;
    private Image player2CommanderImage;
    private Image clicked;

    public GameView(GWENT game, String username1, String username2) {
        super(game);
        this.username1 = username1;
        this.username2 = username2;
        menu = GameMenu.getInstance();
        stage.addActor(background);
        //TODO: make a update cards.
        player1HandGroup = new HorizontalGroup();
        player1Siege = new HorizontalGroup();
        player2Siege = new HorizontalGroup();
        player1Ranged = new HorizontalGroup();
        player2Ranged = new HorizontalGroup();
        player1Closed = new HorizontalGroup();
        player2Closed = new HorizontalGroup();
        player1CommanderImage = new Image(new Texture(player1Commander.getCommanderInformation().address()));
        player2CommanderImage = new Image(new Texture(player2Commander.getCommanderInformation().address()));
        for (Card card : player1Deck)
            switch (card.getType()) {
                case Siege:
                    player1Siege.addActor(new Image(new Texture(card.address())));
                    break;
                case RangedCombat:
                    player1Ranged.addActor(new Image(new Texture(card.address())));
                    break;
                case CloseCombat:
                    player1Closed.addActor(new Image(new Texture(card.address())));
                    break;
                case Spell:
                case Weather:
                    if (player1SiegeHead == null)
                        player1SiegeHead = new Image(new Texture(card.address()));
                    else if (player1RangedHead == null)
                        player1RangedHead = new Image(new Texture(card.address()));
                    else
                        player1ClosedHead = new Image(new Texture(card.address()));
            }
        for (Card card : player2Deck)
            switch (card.getType()) {
                case Siege:
                    player2Siege.addActor(new Image(new Texture(card.address())));
                    break;
                case RangedCombat:
                    player2Ranged.addActor(new Image(new Texture(card.address())));
                    break;
                case CloseCombat:
                    player2Closed.addActor(new Image(new Texture(card.address())));
                    break;
                case Spell:
                case Weather:
                    if (player2SiegeHead == null)
                        player2SiegeHead = new Image(new Texture(card.address()));
                    else if (player2RangedHead == null)
                        player2RangedHead = new Image(new Texture(card.address()));
                    else
                        player2ClosedHead = new Image(new Texture(card.address()));
            }
    }

    @Override
    protected void backgroundLoader() {
        background = new Image(new Texture(Resource.GAME_BACKGROUND.address()));
        background.setPosition(0, 512 - background.getHeight() / 2);
    }

    @Override
    protected void perform(Instruction instruction) {
        switch (instruction.command()) {
            case DECK_MESSAGE:
                //TODO:!
            case HAND_MESSAGE:
                //TODO:!
            case DISCARD_PILE_MESSAGE:
                //TODO:!
        }
    }
}
