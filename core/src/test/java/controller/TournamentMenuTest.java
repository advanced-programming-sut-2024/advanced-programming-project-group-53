package controller;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TournamentMenuTest {

    private TournamentMenu tournamentMenu;

    @Before
    public void setUp() {
        tournamentMenu = TournamentMenu.getInstance();
        tournamentMenu.getGames().clear();//to clear its memory
    }

    @Test
    public void shouldBeSingleton() {
        TournamentMenu instance1 = TournamentMenu.getInstance();
        assertNotNull(instance1);//checking the instantiation
        TournamentMenu instance2 = TournamentMenu.getInstance();
        assertEquals(instance2,instance1);
    }

    @Test
    public void shouldAddPlayers() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        assertEquals(1, tournamentMenu.getGames().size());
        assertArrayEquals(new String[]{"player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8"}, tournamentMenu.getGames().get(0));
    }

    @Test
    public void testFirstRound() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        assertNotNull(tournamentMenu.firstRound(0));
        assertEquals(56, tournamentMenu.firstRound(0).length()); // 8 players * 7 characters for each player
    }

    @Test
    public void testSecondRound() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        assertNotNull(tournamentMenu.secondRound(0));
        assertEquals(28, tournamentMenu.secondRound(0).length()); // 4 players * 7 characters for each player
    }

    @Test
    public void testThirdRound() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        assertNotNull(tournamentMenu.secondRound(0));
        assertEquals(14, tournamentMenu.secondRound(0).length()); // 2 players * 7 characters for each player
    }

    @Test
    public void testEndFirstRound() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        tournamentMenu.endFirstRound(0, "player1", "player2", "player3", "player4");
        assertEquals(1, tournamentMenu.getGames().size());
        assertArrayEquals(new String[]{"player1", "player2", "player3", "player4"}, tournamentMenu.getGames().get(0));
    }

    @Test
    public void testEndSecondRound() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        tournamentMenu.endSecondRound(0, "player1", "player2");
        assertEquals(1, tournamentMenu.getGames().size());
        assertArrayEquals(new String[]{"player1", "player2"}, tournamentMenu.getGames().get(0));
    }

}