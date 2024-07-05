package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.Message;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RegisterMenuTest {
    /*private RegisterMenu registerMenu;
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
        assertEquals(Message.REGISTER_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterMainMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(registerMenu.enterMenu("MainMenu"));
        assertEquals(Message.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterLoginMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(registerMenu.enterMenu("LoginMenu"));
        assertEquals(Message.ENTER_REGISTER_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowInvalidMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(registerMenu.enterMenu("aMenu"));
        assertEquals(Message.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidUsername() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("invalid  username", "aNickname", "email@yahoo.com", "aPassword"));
        assertEquals(Message.INVALID_USERNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidNickname() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "an invalid Nickname", "email@yahoo.com", "aPassword"));
        assertEquals(Message.INVALID_NICKNAME.message(), outContent.toString().trim());
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
        assertEquals(Message.INVALID_EMAIL.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoCapitalLettersShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "password1233#"));
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoOrdinaryLetterShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "PASSWORD1233#"));
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
    }
    @Test
    public void passwordWithNoNumbersShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password#"));
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithNoSpecialCharacterShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password1233"));
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void passwordWithAnyWhiteSpaceShouldErrorWeakPassword() {
        System.setOut((new PrintStream(outContent)));
        assertFalse(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password   1233#"));
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void validInputsShouldPassValidation() {
        assertTrue(registerMenu.registerValidate("aUsername", "aNickname", "email@yahoo.com", "Password1233#"));
    }

    @After
    public void loadUsers() {
        User.loadUsers(allUsersTemp);
    }*/

    private RegisterMenu registerMenu;

    @Before
    public void setUp() {
        registerMenu = RegisterMenu.getInstance();
    }

    @Test
    public void shouldRegisterValidNotExistingUser() {
        if(User.findUser("validUsername") != null)
            fail("test is invalid.");
        assertEquals("",registerMenu.registerValidate("validUsername","validNickname","valid_email@yahoo.com","ValidPass123##"));
    }

    @Test
    public void shouldErrorExistingUsername() {
        //adding a user
        User user = new User("ExistingUser","validNickname","validMail@yahoo.com","validPass123#","Question?","answer");
        //todo: change the expected message when the message is actually existing :)
        assertEquals("",registerMenu.registerValidate("ExistingUser","validNickname","valid_email@yahoo.com","ValidPass123##"));
        User.deleteAccount(user);
        assertNull(User.findUser("ExistingUser"));
    }

    @Test
    public void shouldErrorInvalidUsername() {
        assertEquals(Message.INVALID_USERNAME.message()+"\n",registerMenu.usernameValidation("Invalid username"));
    }

    @Test
    public void shouldErrorInvalidNickname() {
        assertEquals(Message.INVALID_NICKNAME.message()+"\n",registerMenu.nicknameValidation("Invalid nickname"));
    }

    @Test
    public void shouldErrorInvalidEmail() {
        assertEquals(Message.INVALID_EMAIL.message()+"\n",registerMenu.emailValidation("InvalidEmail @gmail.com"));
    }

    @Test
    public void shouldErrorWeakPassword() {
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("password_without_capital1"));
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("PASSWORD_WITHOUT_ORDINARY1"));
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("Password_Without_Number"));
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("PasswordWithoutSpecialChar12"));
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("Short#1"));
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",registerMenu.passwordValidation("Password with space 123#"));
        //todo: check suggestion pass later
    }

    @Test
    public void shouldErrorNotSamePassword() {
        assertEquals(Message.PASSWORD_IS_NOT_THE_SAME.message(),registerMenu.register("ExistingUser","validNickname","validMail@yahoo.com","validPass123#","notSamePass123#","Question?","answer"));
    }

    @Test
    public void shouldHaveMultipleErrors() {
        assertEquals(Message.INVALID_USERNAME.message()+"\n"+Message.INVALID_NICKNAME.message()+"\n"+Message.INVALID_EMAIL.message()+"\n"+Message.WEAK_PASSWORD.message()+"\n",registerMenu.registerValidate("invalid username","invalid nickname","invalid mail@yahoo.com","weakPassword"));
    }
}