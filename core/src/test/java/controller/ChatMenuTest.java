package controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChatMenuTest {
    private ChatMenu chatMenu;

    @Before
    public void setUp() {
        chatMenu = ChatMenu.getInstance();
    }

    @Test
    public void userValidation() {
        assertEquals("empty", chatMenu.userValidation("lo okjo"));
    }

    @Test
    public void setMessage() {
        assertEquals("empty", chatMenu.setMessage("1234", "a", "g"));
    }

    @Test
    public void getMessage() {
        assertEquals("empty", chatMenu.getMessage("a", "g"));
    }
}
