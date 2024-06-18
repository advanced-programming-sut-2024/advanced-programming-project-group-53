package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MainMenuTest {
    private MainMenu mainMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        mainMenu = MainMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
    }

    @Test
    public void shouldShowMainMenu() {
        System.setOut(new PrintStream(outContent));
        mainMenu.showMenu();
        assertEquals(MenuMessage.MAIN_MENU.message(), outContent.toString().trim());
    }
}