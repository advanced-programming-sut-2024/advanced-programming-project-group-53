package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LoginMenuTest {
    /*private LoginMenu loginMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();

    @Before
    public void setUp() {
        loginMenu = LoginMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("ValidUsername", "ValidNickname", "Validemail@yahoo.com", "ValidAndStrongPassword12$$");
        //Todo: set some answers for security questions.
    }

    @Test
    public void shouldEnterMainMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertTrue(loginMenu.enterMenu("MainMenu"));
        assertEquals(Message.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowLoginMenuName() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.showMenu();
        assertEquals(Message.LOGIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowInvalidMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.enterMenu("aMenu"));
        assertEquals(Message.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldLoginValidAccount() {
        assertTrue(loginMenu.login("ValidUsername", "ValidAndStrongPassword12$$"));
    }

    @Test
    public void shouldErrorNoUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.login("ANotExistingUsername", "APassword"));
        assertEquals(Message.NO_USER.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorIncorrectPassword() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertFalse(loginMenu.login("ValidUsername", "AWrongPassword"));
        assertEquals(Message.INCORRECT_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void shouldValidateUsernameForgottenPassword() {
        assertTrue(loginMenu.forgetPasswordUserValidation("ValidUsername"));
    }

    @Test
    public void shouldErrorInvalidUsernameForgottenPassword() {
        assertFalse(loginMenu.forgetPasswordUserValidation("AnInvalidUsername"));
    }

    @Test
    public void shouldChangePasswordWithStrongPassword() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.setPassword("AnotherValidAndStrongPassword12$$", "ValidUsername");
        assertEquals(Message.PASSWORD_CHANGED.message(), outContent.toString().trim());
        //Todo: check that the password has actually changed or not
    }

    @Test
    public void shouldErrorWeakPassword() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        loginMenu.setPassword("WeakPass", "ValidUsername");
        assertEquals(Message.WEAK_PASSWORD.message(), outContent.toString().trim());
        //Todo: check that the password hasn't changed
    }
    //todo: tests for security question

    @After
    public void loadUsers() {
        User.loadUsers(allUsersTemp);
    }*/

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
            assertEquals("Username",loginMenu.userValidation("Username"));
            User.deleteAccount(user);
        }
    }

    @Test
    public void shouldErrorWrongPassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.INCORRECT_PASSWORD.message(),loginMenu.login("Username","WrongPassword123"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldLoginToUser() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals("",loginMenu.login("Username","Password123#"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldReturnQuestion() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals("Question?",loginMenu.question("Username"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldErrorWrongAnswer() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.WRONG_ANSWER.message(),loginMenu.changePassword("WrongAnswer","NewPassword123#","NewPassword123#","Username"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldErrorNotSameConfirmPassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.PASSWORD_IS_NOT_THE_SAME.message(),loginMenu.changePassword("Answer","NewPassword123#","NewPassword13#","Username"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldErrorWeakNewPass() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.WEAK_PASSWORD.message()+"\n",loginMenu.changePassword("Answer","NewPassword","NewPassword","Username"));
        User.deleteAccount(user);
    }

    @Test
    public void shouldChangePassword() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.PASSWORD_CHANGED.message(),loginMenu.changePassword("Answer","NewPassword123#","NewPassword123#","Username"));
        assertEquals("NewPassword123#",user.password());
        User.deleteAccount(user);
    }
}