package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import model.view.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginMenuTest {
    private LoginMenu loginMenu;

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
    }

    @Test
    public void shouldErrorNoUser() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        assertEquals(Message.NO_USER.message(),loginMenu.userValidation("Username"));
        assertEquals(Message.NO_USER.message(),loginMenu.login("Username","Password"));
    }

    @Test
    public void shouldReturnValidUser() {
        if(User.findUser("Username") != null)
            assertEquals("Username",loginMenu.userValidation("Username"));
        else{
            User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
            assertEquals("empty",loginMenu.userValidation("Username"));
        }
    }

    @Test
    public void shouldErrorWrongPassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INCORRECT_PASSWORD.message(),loginMenu.login("Username","WrongPassword123"));
    }

    @Test
    public void shouldLoginToUser() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals("empty",loginMenu.login("Username","Password123#"));
    }

    @Test
    public void shouldReturnQuestion() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals("Question?",loginMenu.question("Username"));
    }

    @Test
    public void shouldErrorWrongAnswer() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.WRONG_ANSWER.message(),loginMenu.changePassword("WrongAnswer","NewPassword123#","NewPassword123#","Username"));
    }

    @Test
    public void shouldErrorNotSameConfirmPassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.PASSWORD_IS_NOT_THE_SAME.message(),loginMenu.changePassword("Answer","NewPassword123#","NewPassword13#","Username"));
    }

    @Test
    public void shouldErrorWeakNewPass() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.WEAK_PASSWORD.message(),loginMenu.changePassword("Answer","NewPassword","NewPassword","Username"));
    }

    @Test
    public void shouldChangePassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.PASSWORD_CHANGED.message(),loginMenu.changePassword("Answer","NewPassword123#","NewPassword123#","Username"));
        assertEquals("NewPassword123#",user.password());
    }
}