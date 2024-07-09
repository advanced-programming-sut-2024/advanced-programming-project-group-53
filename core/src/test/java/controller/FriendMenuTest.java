package controller;

import model.game.User;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class FriendMenuTest {

    private FriendMenu friendMenu;
    private User user1;
    private User user2;

    @Before
    public void setUp() {
        friendMenu = FriendMenu.getInstance();
        user1 = new User("Username1", "Nickname1", "mail1@gmail.com", "Pass123#1", "Question", "answer");
        user2 = new User("Username2", "Nickname2", "mail2@gmail.com", "Pass123#2", "Question", "answer");
    }

    @After
    public void end() {
        user1 = null;
        user2 = null;
    }


}