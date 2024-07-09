package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.Message;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RegisterMenuTest {
    private RegisterMenu registerMenu;

    @Before
    public void setUp() {
        registerMenu = RegisterMenu.getInstance();
    }

    @Test
    public void shouldBeSingleton() {
        RegisterMenu instance1 = RegisterMenu.getInstance();
        assertNotNull(instance1);//checking the instantiation
        RegisterMenu instance2 = RegisterMenu.getInstance();
        assertEquals(instance2,instance1);
    }

    @Test
    public void shouldRegisterValidNotExistingUser() {
        if(User.findUser("validUsername") != null)
            fail("test is invalid.");
        assertEquals("empty",registerMenu.registerValidate("validUsername","validNickname","valid_email@yahoo.com","ValidPass123##"));
    }

    @Test
    public void shouldErrorExistingUsername() {
        //adding a user
        User user = new User("ExistingUser","validNickname","validMail@yahoo.com","validPass123#","Question?","answer");
        //todo: change the expected message when the message is actually existing :)
        assertEquals("empty",registerMenu.registerValidate("ExistingUser","validNickname","valid_email@yahoo.com","ValidPass123##"));
        User.deleteAccount(user);
        assertNull(User.findUser("ExistingUser"));
    }

    @Test
    public void shouldErrorInvalidUsername() {
        assertEquals(Message.INVALID_USERNAME.message(),registerMenu.usernameValidation("Invalid username"));
    }

    @Test
    public void shouldErrorInvalidNickname() {
        assertEquals(Message.INVALID_NICKNAME.message(),registerMenu.nicknameValidation("Invalid nickname"));
    }

    @Test
    public void shouldErrorInvalidEmail() {
        assertEquals(Message.INVALID_EMAIL.message(),registerMenu.emailValidation("InvalidEmail @gmail.com"));
    }

    @Test
    public void shouldErrorWeakPassword() {
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("password_without_capital1"));
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("PASSWORD_WITHOUT_ORDINARY1"));
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("Password_Without_Number"));
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("PasswordWithoutSpecialChar12"));
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("Short#1"));
        assertEquals(Message.WEAK_PASSWORD.message(),registerMenu.passwordValidation("Password with space 123#"));
        //todo: check suggestion pass later
    }

    @Test
    public void shouldErrorNotSamePassword() {
        assertEquals(Message.PASSWORD_IS_NOT_THE_SAME.message(),registerMenu.register("ExistingUser","validNickname","validMail@yahoo.com","validPass123#","notSamePass123#","Question?","answer"));
    }

    @Test
    public void shouldHaveMultipleErrors() {
        assertEquals(Message.INVALID_USERNAME.message()+Message.INVALID_NICKNAME.message()+Message.INVALID_EMAIL.message()+Message.WEAK_PASSWORD.message(),registerMenu.registerValidate("invalid username","invalid nickname","invalid mail@yahoo.com","weakPassword"));
    }
}