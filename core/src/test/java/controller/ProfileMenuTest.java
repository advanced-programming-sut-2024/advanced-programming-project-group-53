package controller;

import model.game.User;
import org.junit.Before;
import org.junit.Test;
import view.Message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProfileMenuTest {
   /* private ProfileMenu profileMenu;
    private static final ArrayList<User> allUsersTemp = new ArrayList<>();
    private static final User currentUserTemp = User.getCurrentUser();
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        profileMenu = ProfileMenu.getInstance();
        User.resetUsers(allUsersTemp);
        User userInstance = new User("aValidUsername-1", "aValid-1Nickname", "valid2mail@gmail.com", "Valid#Strong45password");
        User.setCurrentUser(userInstance);
    }

    @Test
    public void shouldShowProfileMenu() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showMenu();
        assertEquals(Message.PROFILE_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldExitToMainMenu() {
        System.setOut(new PrintStream(outContent));
        profileMenu.exitMenu();
        assertEquals(Message.ENTER_MAIN_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldNotEnterAnyMenu() {
        System.setOut(new PrintStream(outContent));
        assertFalse(profileMenu.enterMenu("aMenu"));
        assertEquals(Message.INVALID_MENU.message(), outContent.toString().trim());
    }

    @Test
    public void shouldShowUsersInformation() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showInformation();
        assertEquals("Username:   aValidUsername-1\r\nNickname:   aValid-1Nickname\r\nMaxXP:      0.0\r\nRank:       0\r\nGame Count: 0\r\nDraw Count: 0\r\nWin Count:  0\r\nLose Count: 0", outContent.toString().trim());
    }

    @Test
    public void shouldShowEmptyGameHistory() {
        System.setOut(new PrintStream(outContent));
        profileMenu.showGameHistory(2);//just a useless number to give to method
        assertEquals(Message.EMPTY_GAME_HISTORY.message(), outContent.toString().trim());
        //test failure because of temporary print
    }

    @After
    public void loadUsers() {
        User.setCurrentUser(currentUserTemp);
        User.loadUsers(allUsersTemp);
    }*/

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
        User.deleteAccount(user);
    }

    @Test
    public void shouldShowEmptyGameHistory() {
        if(User.findUser("Username") != null)
            fail("test is invalid");
        User user = new User("Username","Nickname","mail@gmail.com","Password123#","Question?","Answer");
        assertEquals(Message.EMPTY_GAME_HISTORY.message(),profileMenu.showGameHistory("Username","",""));
        User.deleteAccount(user);
    }
}