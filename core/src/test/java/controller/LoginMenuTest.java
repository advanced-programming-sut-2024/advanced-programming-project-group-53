package controller;

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
}