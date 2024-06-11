package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

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
        TerminalRun mockTerminalRun = mock(TerminalRun.class);
        Printer mockPrinter = mock(Printer.class);

        assertTrue(loginMenu.enterMenu("MainMenu"));
        verify(mockPrinter);
        Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
    }

    @Test
    public void shouldShowLoginMenuName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.showMenu();
        assertEquals("LoginMenu", outContent.toString().trim());
    }


}