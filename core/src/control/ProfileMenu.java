package control;

public class ProfileMenu extends Menu {
    private static ProfileMenu instance;

    private ProfileMenu() {

    }

    public static ProfileMenu getInstance() {
        if (instance == null)
            instance = new ProfileMenu();
        return instance;
    }

    private static void changeUsername(String newUsername) {

    }

    private static void changePassword(String newPassword) {

    }

    private static void changeNickname(String newNickname) {

    }

    private static void changeEmail(String newEmail) {

    }

    private static void showInformation() {

    }

    private static void showGameHistory(int count) {

    }
}
