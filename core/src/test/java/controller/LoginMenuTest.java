package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginMenuTest {
    private LoginMenu loginMenu;
    private User mockUser;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
    }

    @Test
    public void shouldEnterMainMenu() {
        assertTrue(loginMenu.enterMenu("MainMenu"));
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.enterMenu("MainMenu");
        assertEquals(MenuMessage.ENTER_MAIN_MENU, outContent.toString().trim());
    }

    @Test
    public void shouldShowLoginMenuName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.showMenu();
        assertEquals("LoginMenu", outContent.toString().trim());
    }


}