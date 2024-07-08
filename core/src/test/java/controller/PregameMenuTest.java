package controller;

import game.GWENT;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PregameMenuTest {
    PregameMenu pregameMenu;

    @Before
    public void setUp() {
        pregameMenu = PregameMenu.getInstance();
    }

    @Test
    public void addToWaiting() {
        //assertEquals(null,pregameMenu.addToWaiting("a",new GWENT()));
    }
}
