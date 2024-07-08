package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FriendMenuTest {
    FriendMenu friendMenu;

    @Before
    public void setUp() {
        friendMenu = FriendMenu.getInstance();
    }

    @Test
    public void requestsIn() {
        assertEquals("empty", friendMenu.requestsIn("a"));
    }

    @Test
    public void requestsOut() {
        assertEquals("empty", friendMenu.requestsOut("a"));
    }

    @Test
    public void acceptFriendRequest() {
        assertEquals("empty", friendMenu.acceptFriendRequest("a", "g"));
    }

    @Test
    public void rejectFriendRequest() {
        assertEquals("empty", friendMenu.rejectFriendRequest("a", "g"));
    }

    @Test
    public void friends() {
        assertEquals("empty", friendMenu.friends("a"));
    }

    @Test
    public void sendFriendRequest() {
        assertEquals("empty", friendMenu.sendFriendRequest("a", "g"));
    }
}
