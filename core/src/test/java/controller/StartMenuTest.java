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
    public void shouldBeSingleton() {
        StartMenu instance1 = StartMenu.getInstance();
        assertNotNull(instance1);//checking the instantiation
        StartMenu instance2 = StartMenu.getInstance();
        assertEquals(instance2,instance1);
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

    @Test
    public void shouldBeAvailableToSave() {
        StartMenu startMenu = StartMenu.getInstance();
        assertFalse(startMenu.availableToSave());
        //making a deck with less than 10 special cards and at least 22 unit cards
        for (int i = 0; i < 3; i++) {
            //note that all cards of this scope have a max availability of 3
            //instances of special cards
            startMenu.addSpecialToDeck(SpecialInformation.ClearWeather);
            //instances of unit cards
            startMenu.addUnitToDeck(UnitInformation.Arachas);
            startMenu.addUnitToDeck(UnitInformation.BlueStripesCommando);
            startMenu.addUnitToDeck(UnitInformation.ClanAnCraite);
            startMenu.addUnitToDeck(UnitInformation.ClanBrokvarArcher);
            startMenu.addUnitToDeck(UnitInformation.ClanDrummondShieldmaiden);
            startMenu.addUnitToDeck(UnitInformation.DolBlathannaScout);
            startMenu.addUnitToDeck(UnitInformation.DragonHunter);
        }
        startMenu.addUnitToDeck(UnitInformation.Albrich);
        startMenu.addUnitToDeck(UnitInformation.DonarAnHindar);
        assertEquals(26, startMenu.getDeck().size());
        assertTrue(startMenu.availableToSave());
    }
}