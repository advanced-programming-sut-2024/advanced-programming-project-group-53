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


}