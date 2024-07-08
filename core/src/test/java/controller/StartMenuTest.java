package controller;

import model.card.*;
import model.cards.Deck;
import model.game.Player;
import model.game.User;
import model.view.Message;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StartMenuTest {
    private StartMenu startMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        User userInstance1 = new User("aValidUsername-1", "aValid-1Nickname", "valid1mail@gmail.com", "Valid#Strong1password", "Q", "A");
        User userInstance2 = new User("aValidUsername-2", "aValid-2Nickname", "valid2mail@gmail.com", "Valid#Strong2password", "Q", "A");
        Deck deckinstance1 = new Deck();
        Deck deckinstance2 = new Deck();
        Commander commander1 = new Commander(CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders);
        Commander commander2 = new Commander(CommanderInformation.Foltest_LordCommanderOfTheNorth);
        Player playerInstance1 = new Player(userInstance1, deckinstance1, Faction.Monsters, commander1);
        Player playerInstance2 = new Player(userInstance2, deckinstance2, Faction.NorthernRealms, commander2);
        startMenu = StartMenu.getInstance();
    }

    @Test
    public void shouldExitMenu() {
        System.setOut(new PrintStream(outContent));
        startMenu.exitGame();
        assertEquals(Message.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void setDeck() {
        Deck deck = new Deck();
        StartMenu.setDeck(deck);
        assertEquals(deck, startMenu.getDeck());
    }

    @Test
    public void addSpecialToDeck() {
        assertEquals(true, startMenu.addSpecialToDeck(SpecialInformation.BitingFrost));
    }

    @Test
    public void addUnitToDeck() {
        assertEquals(true, startMenu.addUnitToDeck(UnitInformation.Albrich));
    }

    @Test
    public void availableToSave() {
        assertEquals(true, startMenu.availableToSave());
    }

    @Test
    public void getCommanderUser() {
        Commander commander = new Commander(CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders);
        startMenu.setCommanderUser(commander);
        assertEquals(commander,startMenu.getCommanderUser());
    }

    @Test
    public void getUserFaction() {
        Faction faction = Faction.Monsters;
        startMenu.setUserFaction(faction);
        assertEquals(faction,startMenu.getUserFaction());
    }

    @Test
    public void getUser() {
        assertEquals(new User("aValidUsername-1",
                "aValid-1Nickname",
                "valid1mail@gmail.com",
                "Valid#Strong1password",
                "Q",
                "A"),
                startMenu.getUser());
    }
}