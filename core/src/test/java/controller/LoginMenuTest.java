package controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
}