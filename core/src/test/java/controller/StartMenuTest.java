package controller;

import model.card.Commander;
import model.card.CommanderInformation;
import model.card.Faction;
import model.cards.Deck;
import model.game.Player;
import model.game.User;
import org.junit.Before;
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
        Player playerInstance1 = new Player(userInstance1, deckinstance1, Faction.Monsters, commander1);
        Player playerInstance2 = new Player(userInstance2, deckinstance2, Faction.NorthernRealms, commander2);
        User.setCurrentUser(userInstance1);
        userInstance1.setOpponent(userInstance2);
        StartMenu.setInstance();
        startMenu = StartMenu.getInstance();
    }


    @Test
    public void shouldShowStartMenu() {
        System.setOut(new PrintStream(outContent));
        startMenu.showMenu();
        assertEquals(MenuMessage.START_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldExitMenu() {
        System.setOut(new PrintStream(outContent));
        startMenu.exitMenu();
        assertEquals(MenuMessage.ENTER_MAIN_MENU.message(), outContent.toString().trim());
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

    @Test
    public void shouldSelectMonstersFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("Monsters");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Monsters", startMenu.getUserFaction().toString());
    }

    @Test
    public void shouldSelectNilfgaardianEmpireFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("NilfgaardianEmpire");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Nilfgaardian Empire", startMenu.getUserFaction().toString());
    }

    @Test
    public void shouldSelectNorthernRealmsFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("NorthernRealms");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Northern Realms", startMenu.getUserFaction().toString());
    }

    @Test
    public void shouldSelectScoiataelFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("Scoiatael");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Scoia'tael", startMenu.getUserFaction().toString());
    }

    @Test
    public void shouldSelectSkelligeFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("Skellige");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Skellige", startMenu.getUserFaction().toString());
    }

    @Test
    public void shouldSelectNeutralFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("Neutral");
        assertEquals(MenuMessage.FACTION_SELECTED.message(), outContent.toString().trim());
        assertEquals("Neutral", startMenu.getUserFaction().toString());
    }

    //todo: check if All(in factions) need a selection test.(maybe even a test to be unselectable.

    @Test
    public void shouldErrorInvalidFaction() {
        System.setOut(new PrintStream(outContent));
        StartMenu.selectFaction("aFaction");
        assertEquals(MenuMessage.INVALID_FACTION.message(), outContent.toString().trim());
    }

    @Test
    public void shouldReturnAccurateCardCode() {
        assertEquals(0,StartMenu.nameValidation("InvalidCard"));
        assertEquals(1,StartMenu.nameValidation("Albrich"));
        assertEquals(2,StartMenu.nameValidation("SkelligeStorm"));
    }

    @Test
    public void ShouldErrorInvalidCardAndCount() {
        System.setOut(new PrintStream(outContent));
        assertEquals(0,StartMenu.nameAndCountValidation("InvalidName","1"));
        assertEquals(MenuMessage.THERE_IS_NO_CARD_WITH_THIS_NAME.message(), outContent.toString().trim());
    }

    @Test
    public void ShouldErrorInvalidCountString() {
        System.setOut(new PrintStream(outContent));
        assertEquals(0,StartMenu.nameAndCountValidation("Albrich","sd1"));
        assertEquals(MenuMessage.WRONG_NUMBER_FORMAT.message(), outContent.toString().trim());
    }

    @Test
    public void ShouldErrorOutOfRangeCountStringUpperBound() {
        System.setOut(new PrintStream(outContent));
        assertEquals(0,StartMenu.nameAndCountValidation("Albrich","10"));
        assertEquals(MenuMessage.COUNT_OUT_OF_RANGE.message(), outContent.toString().trim());
    }

    @Test
    public void ShouldErrorOutOfRangeCountStringLowerBound() {
        System.setOut(new PrintStream(outContent));
        assertEquals(0,StartMenu.nameAndCountValidation("Albrich","0"));
        assertEquals(MenuMessage.COUNT_OUT_OF_RANGE.message(), outContent.toString().trim());
    }

    @Test
    public void ShouldReturnValidState() {
        assertEquals(1,StartMenu.nameAndCountValidation("Albrich","3"));
        assertEquals(2,StartMenu.nameAndCountValidation("SkelligeStorm","3"));
    }

    @Test
    public void shouldNotAddInvalidCardToDeck() {
        assertFalse(StartMenu.addToDeck("InvalidCard","2"));
    }

    @Test
    public void shouldErrorMoreThanAvailableUnit() {
        System.setOut(new PrintStream(outContent));
        assertFalse(StartMenu.addToDeck("Albrich","8"));
        assertEquals(MenuMessage.MORE_THAT_AVAILABILITY.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorMoreThanAvailableSpecial() {
        System.setOut(new PrintStream(outContent));
        assertFalse(StartMenu.addToDeck("SkelligeStorm","8"));
        assertEquals(MenuMessage.MORE_THAT_AVAILABILITY.message(), outContent.toString().trim());
    }

    @Test
    public void shouldAddValidAvailableUnitCard() {
        System.setOut(new PrintStream(outContent));
        assertTrue(StartMenu.addToDeck("Albrich","1"));
        assertEquals(MenuMessage.ADD_CARD.message(), outContent.toString().trim());
    }

    @Test
    public void shouldAddValidAvailableSpecialCard() {
        System.setOut(new PrintStream(outContent));
        assertTrue(StartMenu.addToDeck("SkelligeStorm","2"));
        assertEquals(MenuMessage.ADD_CARD.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowAccurateDeck() {
        System.setOut(new PrintStream(outContent));
        assertTrue(StartMenu.addToDeck("SkelligeStorm","2"));
        assertTrue(StartMenu.addToDeck("Albrich","1"));
        StartMenu.showDeck();
        assertEquals("Card(s) added successfully.\r\n" +
                "Card(s) added successfully.\r\n" +
                "CURRENT DECK:\r\n" +
                "1. SkelligeStorm Weather-null\r\n" +
                "2. SkelligeStorm Weather-null\r\n" +
                "3. Albrich RangedCombat-Nilfgaardian Empire", outContent.toString().trim());
    }

    @Test
    public void shouldShowUsersInfo() {
        System.setOut(new PrintStream(outContent));
        assertTrue(StartMenu.addToDeck("SkelligeStorm","2"));
        assertTrue(StartMenu.addToDeck("Albrich","1"));
        StartMenu.showCurrentUserInformation();
        assertEquals("Card(s) added successfully.\r\n" +
                "Card(s) added successfully.\r\n" +
                "aValidUsername-1\r\n" +
                "Current Deck Size: 3\r\n" +
                "Unit Cards Count: 1\r\n" +
                "Special Cards Count: 2\r\n" +
                "Hero Cards Count: 0\r\n" +
                "Current Deck Cards Ability: \r\n" +
                "1. SkelligeStorm ---> None\r\n" +
                "2. SkelligeStorm ---> None\r\n" +
                "3. Albrich ---> None", outContent.toString().trim());
    }
}