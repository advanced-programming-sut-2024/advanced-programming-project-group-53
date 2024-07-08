package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import model.view.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProfileMenuTest {
    private ProfileMenu profileMenu;

    @Before
    public void setUp() {
        profileMenu = ProfileMenu.getInstance();
    }

    @Test
    public void shouldShowUserInfo() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        user.setDrawCount(12);
        user.setGameCount(15);
        user.setLoseCount(1);
        user.setWinCount(2);
        assertEquals("Username Nickname 0.0 0 15 12 2 1 ",profileMenu.getInformation("Username"));
    }

    @Test
    public void shouldShowEmptyGameHistory() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.EMPTY_GAME_HISTORY.message(),profileMenu.showGameHistory("Username","",""));
    }

    @Test
    public void shouldChangeValidUsernameAndErrorInvalid() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INVALID_USERNAME.message(),profileMenu.changeUsername("Invalid username","Username"));
        assertEquals("empty",profileMenu.changeUsername("NewUsername","Username"));
        assertEquals("NewUsername",user.username());
    }

    @Test
    public void shouldErrorWrongOldPassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INCORRECT_PASSWORD.message(),profileMenu.changePassword("WrongPassword","newPassword","newPassword","Username"));
    }

    @Test
    public void shouldErrorInvalidNewPasswordAndNotSame() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.PASSWORD_IS_NOT_THE_SAME.message(),profileMenu.changePassword("Password123#","newPassword","new_Password","Username"));
        assertEquals(Message.WEAK_PASSWORD.message(),profileMenu.changePassword("Password123#","newPassword","newPassword","Username"));
    }

    @Test
    public void shouldChangePassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals("empty",profileMenu.changePassword("Password123#","newPassword123#","newPassword123#","Username"));
        assertEquals("newPassword123#",user.password());
    }

    @Test
    public void shouldChangeValidNicknameAndErrorInvalid() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INVALID_NICKNAME.message(),profileMenu.changeNickname("Invalid nickname","Username"));
        assertEquals("empty",profileMenu.changeNickname("NewNickname","Username"));
        assertEquals("NewNickname",user.nickname());
    }

    @Test
    public void shouldChangeValidEmailAndErrorInvalid() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INVALID_EMAIL.message(),profileMenu.changeEmail("Invalid email","Username"));
        assertEquals("empty",profileMenu.changeEmail("newEmail@outlook.com","Username"));
        assertEquals("newEmail@outlook.com",user.email());
    }
    @Test
    public void ranking() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(profileMenu.showRanking(), User.ranking().toString());
    }
}