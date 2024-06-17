package controller;

import junit.framework.TestCase;
import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RegisterMenuTest {
    private RegisterMenu registerMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUp() {
        registerMenu = RegisterMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1","aValid-1Nickname","valid2mail@gmail.com","Valid#Strong45password");
    }

    @Test
    public void shouldShowRegisterMenu() {
        System.setOut(new PrintStream(outContent));
        registerMenu.showMenu();
        assertEquals(MenuMessage.REGISTER_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterMainMenu() {
        System.setOut(new PrintStream(outContent));
        registerMenu.enterMenu("MainMenu");
        assertEquals(MenuMessage.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }
}