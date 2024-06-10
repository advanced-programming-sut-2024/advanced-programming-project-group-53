package controller;

import jdk.jpackage.internal.Log;
import org.junit.Before;

import static org.junit.jupiter.api.Assertions.*;

class LoginMenuTest {
    private LoginMenu loginMenu;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
    }

}