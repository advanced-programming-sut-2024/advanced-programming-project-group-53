package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class LoginMenuTest {
    private LoginMenu loginMenu;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
        User.resetUsers();
        User userInstance = new User("ValidUsername", "ValidNickname", "Validemail@yahoo.com", "ValidAndStrongPassword12$$");
        //Todo: set some answers for security questions.
    }

    @Test
    public void shouldEnterMainMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertTrue(loginMenu.enterMenu("MainMenu"));
        assertEquals(MenuMessage.ENTER_MAIN_MENU, outContent.toString().trim());
    }

    @Test
    public void shouldShowLoginMenuName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.showMenu();
        assertEquals("LoginMenu", outContent.toString().trim());
    }

    @Test
    public void shouldShowInvalidMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.enterMenu("aMenu"));
        assertEquals(MenuMessage.INVALID_MENU, outContent.toString().trim());
    }

    @Test
    public void shouldLoginValidAccount() {
        assertTrue(loginMenu.login("ValidUsername", "ValidAndStrongPassword12$$"));
    }

    @Test
    public void shouldErrorNoUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.login("ANotExistingUsername", "APassword"));
        assertEquals(MenuMessage.NO_USER, outContent.toString().trim());
    }

    @Test
    public void shouldErrorIncorrectPassword() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.login("ValidUsername", "AWrongPassword"));
        assertEquals(MenuMessage.INCORRECT_PASSWORD, outContent.toString().trim());
    }
}