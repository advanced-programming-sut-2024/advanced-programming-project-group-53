package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProfileMenuTest {
    private ProfileMenu profileMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        profileMenu = ProfileMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
        User.setCurrentUser(userInstance);
    }

    @Test
    public void shouldShowProfileMenu() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showMenu();
        assertEquals(MenuMessage.PROFILE_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldExitToMainMenu() {
        System.setOut(new PrintStream(outContent));
        profileMenu.exitMenu();
        assertEquals(MenuMessage.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotEnterAnyMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(profileMenu.enterMenu("aMenu"));
        assertEquals(MenuMessage.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowUsersInformation() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showInformation();
        assertEquals("Username:   aValidUsername-1\r\nNickname:   aValid-1Nickname\r\nMaxXP:      0.0\r\nRank:       0\r\nGame Count: 0\r\nDraw Count: 0\r\nWin Count:  0\r\nLose Count: 0", outContent.toString().trim());
    }

    @Test
    public void shouldShowEmptyGameHistory() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showGameHistory(2);//just a useless number to give to method
        assertEquals(MenuMessage.EMPTY_GAME_HISTORY.message(), outContent.toString().trim());
    }
}