package controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainMenuTest {

    @Test
    public void shouldBeSingleton() {
        MainMenu instance1 = MainMenu.getInstance();
        assertNotNull(instance1);//checking the instantiation
        MainMenu instance2 = MainMenu.getInstance();
        assertEquals(instance2,instance1);
    }
    /*private MainMenu mainMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        mainMenu = MainMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
        User.setCurrentUser(userInstance);
    }

    @Test
    public void shouldShowMainMenu() {
        System.setOut(new PrintStream(outContent));
        mainMenu.showMenu();
        assertEquals(Message.MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterProfileMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(mainMenu.enterMenu("ProfileMenu"));
        assertEquals(Message.ENTER_PROFILE_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldEnterGameMenu() {
        System.setOut(new PrintStream(outContent));
        assertTrue(mainMenu.enterMenu("GameMenu"));
        assertEquals(Message.ENTER_GAME_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldErrorInvalidMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(mainMenu.enterMenu("aMenu"));
        assertEquals(Message.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldExitToLoginMenu() {
        System.setOut(new PrintStream(outContent));
        mainMenu.exitMenu();
        assertEquals(Message.ENTER_LOGIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldLogOut() {
        System.setOut(new PrintStream(outContent));
        mainMenu.logout();
        assertEquals(Message.LOGOUT.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangeUsernameToInvalidUsername() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeUsername("Invalid Username");
        assertNotEquals("Invalid Username",User.getCurrentUser().username());
        assertEquals(Message.INVALID_USERNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldChangeValidUsername() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeUsername("ValidUsername");
        assertEquals("ValidUsername",User.getCurrentUser().username());
        assertEquals(Message.CHANGE_USERNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangeNicknameToInvalidNickname() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeNickname("Invalid Nickname");
        assertNotEquals("Invalid Nickname",User.getCurrentUser().nickname());
        assertEquals(Message.INVALID_NICKNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldChangeValidNickname() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeNickname("ValidNickname");
        assertEquals("ValidNickname",User.getCurrentUser().nickname());
        assertEquals(Message.CHANGE_NICKNAME.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangeEmailToInvalidEmail() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeEmail("Invalid email@yahoo");
        assertNotEquals("Invalid email@yahoo",User.getCurrentUser().getEmail());
        assertEquals(Message.INVALID_EMAIL.message(), outContent.toString().trim());
    }

    @Test
    public void shouldChangeValidEmail() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changeEmail("validmail@gmail.com");
        assertEquals("validmail@gmail.com",User.getCurrentUser().getEmail());
        assertEquals(Message.CHANGE_EMAIL.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordInCaseOfIncorrectOldPassword() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("newPassword","ValidStrong45password");
        assertNotEquals("newPassword",User.getCurrentUser().getPassword());
        assertEquals(Message.INCORRECT_PASSWORD.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordToShortPassword() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("newPwd","Valid#Strong45password");
        assertNotEquals("newPwd",User.getCurrentUser().getPassword());
        assertEquals(Message.PASSWORD_LENGTH_ERROR.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordToWeakPasswordWithoutCapitalLetter() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("password12#","Valid#Strong45password");
        assertNotEquals("password12#",User.getCurrentUser().getPassword());
        assertEquals(Message.PASSWORD_INVALID_CHARACTERS_ERROR.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordToWeakPasswordWithoutOrdinaryLetter() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("PASSWORD12#","Valid#Strong45password");
        assertNotEquals("PASSWORD12#",User.getCurrentUser().getPassword());
        assertEquals(Message.PASSWORD_INVALID_CHARACTERS_ERROR.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordToWeakPasswordWithoutNumbers() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("Password#","Valid#Strong45password");
        assertNotEquals("Password#",User.getCurrentUser().getPassword());
        assertEquals(Message.PASSWORD_INVALID_CHARACTERS_ERROR.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotChangePasswordToWeakPasswordWithoutSpecialCharacters() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("Password123","Valid#Strong45password");
        assertNotEquals("Password123",User.getCurrentUser().getPassword());
        assertEquals(Message.PASSWORD_INVALID_CHARACTERS_ERROR.message(), outContent.toString().trim());
    }

    @Test
    public void shouldChangePasswordToStrongPassword() {
        System.setOut(new PrintStream(outContent));
        mainMenu.changePassword("Password123@#","Valid#Strong45password");
        assertEquals("Password123@#",User.getCurrentUser().getPassword());
        assertEquals(Message.CHANGE_PASSWORD.message(), outContent.toString().trim());
    }

    @After
    public void loadUsers() {
        User.setCurrentUser(currentUserTemp);
        User.loadUsers(allUsersTemp);
    }*/
}