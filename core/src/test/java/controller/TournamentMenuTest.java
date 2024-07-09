package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TournamentMenuTest {

    private TournamentMenu tournamentMenu;

    @Before
    public void setUp() {
        tournamentMenu = TournamentMenu.getInstance();
    }

    @Test
    public void shouldAddPlayers() {
        tournamentMenu.addPlayer("player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8");
        assertEquals(1, tournamentMenu.getGames().size());
        assertArrayEquals(new String[]{"player1", "player2", "player3", "player4", "player5", "player6", "player7", "player8"}, tournamentMenu.getGames().get(0));
    }
}