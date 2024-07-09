package controller;

import model.game.User;
import model.view.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatMenuTest {
    private ChatMenu chatMenu;

    @Before
    public void setUp() {
        chatMenu = ChatMenu.getInstance();
        //todo: clear messages if needed(create a method)
    }

    @Test
    public void shouldValidateExistingUsers() {
        if(User.findUser("Username") != null) {
            assertEquals("empty", chatMenu.userValidation("Username"));
        } else {
            assertEquals(Message.NO_USER.message(), chatMenu.userValidation("Username"));
            User user = new User("Username","Nickname", "mail@yahoo.com", "Password123#", "Question?", "answer");
            assertEquals("empty", chatMenu.userValidation("Username"));//todo: why a new user doesn't appear to be in the database
        }
    }

    @Test
    public void shouldSetMessage() {
        assertEquals("empty", chatMenu.setMessage("Hello, how are you?", "senderUser", "receiverUser"));
        assertEquals(1, chatMenu.getMessages().size());
        assertEquals(Message.ILLEGAL_CHARACTER.message(),chatMenu.setMessage("Hello \\ how are you?", "senderUser", "receiverUser"));
    }

    @Test
    public void shouldGetMessage() {
        chatMenu.setMessage("Hello", "user1", "user2");
        chatMenu.setMessage("Hi", "user2", "user1");
        assertTrue(chatMenu.getMessage("user1", "user2").contains("Hello"));
        assertTrue(chatMenu.getMessage("user1", "user2").contains("Hi"));
    }


}