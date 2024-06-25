package controller;

import model.card.Commander;
import model.card.CommanderInformation;
import model.card.Faction;
import model.cards.Deck;
import model.game.Player;
import model.game.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import view.terminal.Message.MenuMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StartMenuTest {
    private StartMenu startMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        //User.reestUsers(allUsersTemp);
        User userInstance1 = new User("aValidUsername-1", "aValid-1Nickname", "valid1mail@gmail.com", "Valid#Strong1password");
        User userInstance2 = new User("aValidUsername-2", "aValid-2Nickname", "valid2mail@gmail.com", "Valid#Strong2password");
        Deck deckinstance1 = new Deck();
        Deck deckinstance2 = new Deck();
        Commander commander1 = new Commander(CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders);
        Commander commander2 = new Commander(CommanderInformation.Foltest_LordCommanderOfTheNorth);
        Player playerInstance1 = new Player(userInstance1,deckinstance1,Faction.Monsters, commander1);
        Player playerInstance2 = new Player(userInstance2,deckinstance2,Faction.NorthernRealms,commander2);
        User.setCurrentUser(userInstance1);
        userInstance1.setOpponent(userInstance2);
        StartMenu.setInstance();
        startMenu = StartMenu.getInstance();
    }


    @Test
    public void shouldShowStartMenu() {
        System.setOut(new PrintStream(outContent));
        startMenu.showMenu();
        assertEquals(MenuMessage.START_MENU.message(),outContent.toString().trim());
    }

    @Test
    public void shouldExitMenu() {
        System.setOut(new PrintStream(outContent));
        startMenu.exitMenu();
        assertEquals(MenuMessage.ENTER_MAIN_MENU.message(),outContent.toString().trim());
    }

    @Test
    public void shouldEnterGameMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(startMenu.enterMenu("GameMenu"));
        assertEquals(MenuMessage.ENTER_GAME_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(startMenu.enterMenu("aMenu"));
        assertEquals(MenuMessage.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidUsername() {
        System.setOut(new PrintStream(outContent));
        assertFalse(StartMenu.createGame("invalidUsername12#"));
        assertEquals(MenuMessage.NO_USER.message(), outContent.toString().trim());
    }

    @Test
    public void shouldStartGame() {
        System.setOut(new PrintStream(outContent));
        assertTrue(StartMenu.createGame("aValidUsername-2"));
        assertEquals(MenuMessage.GAME_CREATED_SUCCESSFULLY.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.showFactions();
        assertEquals("FACTIONS:\r\n" +
                "Monsters\r\n" +
                "NilfgaardianEmpire\r\n" +
                "NorthernRealms\r\n" +
                "Scoiatael\r\n" +
                "Skellige\r\n" +
                "Neutral\r\n" +
                "All", outContent.toString().trim());
    }
}