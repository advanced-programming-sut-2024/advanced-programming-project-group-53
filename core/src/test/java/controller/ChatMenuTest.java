package controller;

import model.view.ChatContainer;
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


}