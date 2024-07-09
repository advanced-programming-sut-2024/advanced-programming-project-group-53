package controller;

import model.card.*;
import model.game.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StartMenuTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("Username", "Nickname", "mail@gmail.com", "Pass123#", "Question?", "answer");
        StartMenu.setInstance("Username", Faction.NorthernRealms, new Commander(CommanderInformation.TWO_COMMANDER_SKELLIGE_CrachAnCraite));
    }

    @Test
    public void shouldAddSpecialEnough() {
        for (int i = 0; i < 3; i++) { //3 is the max number of used card in the test
            assertTrue(StartMenu.getInstance().addSpecialToDeck(SpecialInformation.BitingFrost));
        }
        assertNotNull(StartMenu.getInstance().getDeck());
        assertFalse(StartMenu.getInstance().addSpecialToDeck(SpecialInformation.BitingFrost));
    }

    @Test
    public void shouldAddUnitEnough() {
        assertTrue(StartMenu.getInstance().addUnitToDeck(UnitInformation.Albrich));
        assertNotNull(StartMenu.getInstance().getDeck());
        assertFalse(StartMenu.getInstance().addUnitToDeck(UnitInformation.Albrich));
    }

}