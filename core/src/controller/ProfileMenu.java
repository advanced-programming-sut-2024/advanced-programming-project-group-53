package controller;

import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

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

    @Override
    public void enterMenu(String name) {
        Printer.print(MenuMessage.INVALID_MENU.message());
    }

    @Override
    public void exitMenu() {
        TerminalRun.ChangeCurrentMenu(MainMenu.getInstance());
        Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.PROFILE_MENU.message());
    }
}
