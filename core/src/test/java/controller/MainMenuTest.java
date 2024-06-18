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
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        mainMenu = MainMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
        User.setCurrentUser(userInstance);
    }

    @Test
    public void shouldShowMainMenu() {
        System.setOut(new PrintStream(outContent));
        mainMenu.showMenu();
        assertEquals(MenuMessage.MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterProfileMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(mainMenu.enterMenu("ProfileMenu"));
        assertEquals(MenuMessage.ENTER_PROFILE_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterGameMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(mainMenu.enterMenu("GameMenu"));
        assertEquals(MenuMessage.ENTER_GAME_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(mainMenu.enterMenu("aMenu"));
        assertEquals(MenuMessage.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldExitToLoginMenu() {
        System.setOut(new PrintStream(outContent));
        mainMenu.exitMenu();
        assertEquals(MenuMessage.ENTER_LOGIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldLogOut() {
        System.setOut(new PrintStream(outContent));
        mainMenu.logout();
        assertEquals(MenuMessage.LOGOUT.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangeUsernameToInvalidUsername() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeUsername("Invalid Username");
        assertNotEquals("Invalid Username",User.getCurrentUser().getUsername());
        assertEquals(MenuMessage.INVALID_USERNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldChangeValidUsername() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeUsername("ValidUsername");
        assertEquals("ValidUsername",User.getCurrentUser().getUsername());
        assertEquals(MenuMessage.CHANGE_USERNAME.message(), outContent.toString().trim());
    }
}