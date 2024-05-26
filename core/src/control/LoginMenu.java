package control;

public class LoginMenu extends Menu {
    private static LoginMenu instance;

    private LoginMenu() {

    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }
}
