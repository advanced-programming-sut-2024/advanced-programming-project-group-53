package controller;

import model.game.User;
import model.view.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void shouldBeSingleton() {
        FriendMenu instance1 = FriendMenu.getInstance();
        assertNotNull(instance1);//checking the instantiation
        FriendMenu instance2 = FriendMenu.getInstance();
        assertEquals(instance2,instance1);
    }

    @After
    public void end() {
        user1 = null;
        user2 = null;
    }

    @Test
    public void shouldShowRequests() {
        friendMenu.sendFriendRequest("Username1", "Username2");
        assertEquals("Username1", friendMenu.requestsIn("Username2"));
        assertEquals("Username2", friendMenu.requestsOut("Username1"));
    }

    @Test
    public void shouldShowAcceptingRequest() {
        friendMenu.sendFriendRequest("Username1", "Username2");
        friendMenu.acceptFriendRequest("Username1", "Username2");
        assertTrue(user1.friends().contains("Username2"));
        assertTrue(user2.friends().contains("Username1"));
    }

    @Test
    public void shouldShowRejectingRequest() {
        friendMenu.sendFriendRequest("Username1", "Username2");
        friendMenu.rejectFriendRequest("Username1", "Username2");
        assertFalse(user1.friends().contains("Username2"));
        assertFalse(user2.friends().contains("Username1"));
    }

    @Test
    public void shouldAddFriend() {
        user1.addFriend("Username2");
        assertTrue(user1.friends().contains("Username2"));
    }

    @Test
    public void shouldShowEmptyRespond() {
        assertEquals("empty", friendMenu.sendFriendRequest("Username1", "Username2"));
    }

    @Test
    public void shouldErrorNonExistingUser() {
        if (User.findUser("NonExistingUser") != null)
            fail("Invalid Test");
        assertEquals(Message.NO_USER.message(), friendMenu.sendFriendRequest("Username1", "NonExistingUser"));
    }

    @Test
    public void shouldAlertOldFriend() {
        user1.addFriend("Username2");
        assertEquals(Message.OLD_FRIEND.message(), friendMenu.sendFriendRequest("Username1", "Username2"));
    }
}