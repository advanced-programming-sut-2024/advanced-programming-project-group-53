package control;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu() {

    }

    public static MainMenu getInstance() {
        if (instance == null)
            instance = new MainMenu();
        return instance;
    }

    private static void logout() {

    }

    private static void goToGameMenu() {

    }

    private static void goToProfileMenu() {

    }
}
