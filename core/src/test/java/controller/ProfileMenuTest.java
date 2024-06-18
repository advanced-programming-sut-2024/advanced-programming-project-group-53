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
}