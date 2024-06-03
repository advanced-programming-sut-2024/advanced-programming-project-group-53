package control;

import model.game.User;

public class StartMenu extends Menu {
    private static StartMenu instance;

    private StartMenu() {

    }

    public static StartMenu getInstance() {
        if (instance == null)
            instance = new StartMenu();
        return instance;
    }

    private static boolean passwordValidation(String password) {
        return false;
    }

    private static void register(String username, String nickname, String password, String confirmPassword, String email) {

    }

    private static void login(String username, String password) {

    }

    private static void forgetPassword(String username) {

    }

    private static void setPassword(String password, User user) {

    }

}
