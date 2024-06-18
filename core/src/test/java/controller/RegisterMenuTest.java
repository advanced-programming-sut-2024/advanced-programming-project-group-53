package controller;

import junit.framework.TestCase;
import model.game.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.terminal.Message.MenuMessage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegisterMenuTest {
    private RegisterMenu registerMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        registerMenu = RegisterMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
    }

    @Test
    public void shouldShowRegisterMenu() {
        System.setOut(new PrintStream(outContent));
        registerMenu.showMenu();
        assertEquals(MenuMessage.REGISTER_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterMainMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(registerMenu.enterMenu("MainMenu"));
        assertEquals(MenuMessage.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterLoginMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(registerMenu.enterMenu("LoginMenu"));
        assertEquals(MenuMessage.ENTER_REGISTER_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowInvalidMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(registerMenu.enterMenu("aMenu"));
        assertEquals(MenuMessage.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidUsername() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("invalid  username", "aNickname", "email@yahoo.com", "aPassword"));
        assertEquals(MenuMessage.INVALID_USERNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidNickname() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "an invalid Nickname", "email@yahoo.com", "aPassword"));
        assertEquals(MenuMessage.INVALID_NICKNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidEmail() {
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "invalid email@yahoo.com", "aPassword"));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yhoo.com", "aPassword"));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "@yahoo.com", "aPassword"));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "emailyahoo.com", "aPassword"));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoocom", "aPassword"));
    }

    @Test
    public void shouldInformInvalidEmail() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "invalid email@yahoo.com", "aPassword"));
        assertEquals(MenuMessage.INVALID_EMAIL.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoCapitalLettersShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "password1233#"));
        assertEquals(MenuMessage.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoOrdinaryLetterShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "PASSWORD1233#"));
        assertEquals(MenuMessage.WEAK_PASSWORD.message(), outContent.toString().trim());
    }
    @Test
    public void passwordWithNoNumbersShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password#"));
        assertEquals(MenuMessage.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoSpecialCharacterShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password1233"));
        assertEquals(MenuMessage.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithAnyWhiteSpaceShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password   1233#"));
        assertEquals(MenuMessage.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void validInputsShouldPassValidation() {
        assertTrue(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password1233#"));
    }

    @After
    public void loadUsers() {
        User.loadUsers(allUsersTemp);
    }
}