package controller;

import game.GWENT;
import model.card.*;
import model.cards.Deck;
import model.game.Player;
import model.game.Table;
import model.game.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameMenuTest {
    private GameMenu gameMenu;

    @Before
    public void setUp() {
        gameMenu = GameMenu.getInstance();
    }

    @Test
    public void vetoCards() {
        assertEquals(true, GameMenu.vetoCards(1));
    }

    @Test
    public void placeCardInRow() {
        assertEquals(1, gameMenu.placeCardInRow(new Unit(UnitInformation.Arachas), 0, 0));
    }

    @Test
    public void getCardPositionToPlay() {
        assertEquals(1, gameMenu.getCardPositionToPlay(new Unit(UnitInformation.Arachas)));
    }

    @Test
    public void commanderExecution() {
        assertEquals(new ArrayList<Card>(), gameMenu.commanderExecution());
    }

    @Test
    public void changeTurn() {
        //assertEquals(null,gameMenu.changeTurn());
    }

    @Test
    public void getTable() {
        assertEquals(new Table(
                        new Player(User.findUser("a"),
                                new Deck(),
                                Faction.Skellige,
                                new Commander(CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders)),
                        new Player(User.findUser("a"),
                                new Deck(),
                                Faction.Skellige,
                                new Commander(CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders))),
                gameMenu.getTable());
    }
}
