package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginMenuTest {
    private LoginMenu loginMenu;
    private User mockUser;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
        mockUser = mock(User.class);
    }

    @Test
    public void shouldEnterMainMenu() {
        assertTrue(loginMenu.enterMenu("MainMenu"));
    }

    @Test
    public void shouldShowLoginMenuName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.showMenu();
        assertEquals("LoginMenu", outContent.toString().trim());
    }


}