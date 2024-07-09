package controller;

import game.GWENT;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PregameMenuTest {

    private PregameMenu pregameMenu;
    private GWENT game;


    @Before
    public void setUp() {
        pregameMenu = PregameMenu.getInstance();
    }

    @Test
    public void shouldAddOneUserToWaiting() {
        pregameMenu.addToWaiting("Username1", game);
        assertEquals(1, pregameMenu.getWaiting().size());
        assertEquals("Username1", pregameMenu.getWaiting().get(0));
    }

    @Test
    public void shouldAddTwoUsersToWaiting() {
        pregameMenu.addToWaiting("Username1", game);
        pregameMenu.addToWaiting("Username2", game);
        //after adding two players the waiting list should empty
        assertEquals(0, pregameMenu.getWaiting().size());
    }

    @After
    public void end() {
        //to clear waiting list after each test
        pregameMenu.getWaiting().clear();
    }
}