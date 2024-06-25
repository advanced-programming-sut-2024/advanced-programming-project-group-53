package controller;

import model.game.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class StartMenuTest {
    private StartMenu startMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUp() {
        User.reestUsers(allUsersTemp);
        User userInstance1 = new User("aValidUsername-1", "aValid-1Nickname", "valid1mail@gmail.com", "Valid#Strong1password");
        User userInstance2 = new User("aValidUsername-2", "aValid-2Nickname", "valid2mail@gmail.com", "Valid#Strong2password");
        User.setCurrentUser(userInstance1);
    }

    @Before
    public void getMenuInstance() {
        startMenu = StartMenu.getInstance();
    }

}