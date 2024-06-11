package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginMenuTest {
    private LoginMenu loginMenu;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
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
        assertEquals("LoginMenu",outContent.toString().trim());
    }
}