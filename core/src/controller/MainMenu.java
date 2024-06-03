package controller;

import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

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

    @Override
    public void enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.GameMenu) {
            TerminalRun.ChangeCurrentMenu(GameMenu.getInstance());
            Printer.print(MenuMessage.ENTER_GAME_MENU.message());
        }
        else if (MenuName.getMenu(name) == MenuName.ProfileMenu) {
            TerminalRun.ChangeCurrentMenu(ProfileMenu.getInstance());
            Printer.print(MenuMessage.ENTER_PROFILE_MENU.message());
        }
        else
            Printer.print(MenuMessage.INVALID_MENU.message());
    }

    @Override
    public void exitMenu() {
        TerminalRun.ChangeCurrentMenu(LoginMenu.getInstance());
        Printer.print(MenuMessage.ENTER_LOGIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.MAIN_MENU.message());
    }
}
