package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TournamentMenuTest {
    String[] users;
    TournamentMenu tournamentMenu;

    @Before
    public void setUp() {
        users = new String[]{"user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8"};
        tournamentMenu = TournamentMenu.getInstance();
        tournamentMenu.addPlayer("user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8");
    }

    @Test
    public void firstRound() {
        assertEquals("user1 user2 user3 user4 user5 user6 user7 user8", tournamentMenu.firstRound(0));
    }

    @Test
    public void secondRound() {
        assertEquals("user1 user2 user3 user4", tournamentMenu.secondRound(0));
    }

    @Test
    public void thirdRound() {
        assertEquals("user1 user2", tournamentMenu.thirdRound(0));
    }

    @Test
    public void endFirstRound() {
        //assertEquals("",tournamentMenu.endFirstRound(0,"user1", "user2", "user3", "user4"));
    }

    @Test
    public void endSecondRound() {
        //assertEquals("", tournamentMenu.endSecondRound(0,"user1","user2"));
    }
}
