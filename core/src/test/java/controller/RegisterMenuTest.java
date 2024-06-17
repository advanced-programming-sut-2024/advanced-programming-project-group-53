package controller;

import junit.framework.TestCase;
import model.game.User;
import org.junit.Before;

import java.util.ArrayList;

public class RegisterMenuTest {
    private RegisterMenu registerMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();

    @Before
    public void setUp() {
        registerMenu = RegisterMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1","aValid-1Nickname","valid2mail@gmail.com","Valid#Strong45password");
    }

}