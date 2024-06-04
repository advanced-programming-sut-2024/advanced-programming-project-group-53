package controller;

import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu() {
        super.setMenuType(MenuName.MainMenu);
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
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.GameMenu) {
            TerminalRun.changeCurrentMenu(GameMenu.getInstance());
            Printer.print(MenuMessage.ENTER_GAME_MENU.message());
            return true;
        }
        else if (MenuName.getMenu(name) == MenuName.ProfileMenu) {
            TerminalRun.changeCurrentMenu(ProfileMenu.getInstance());
            Printer.print(MenuMessage.ENTER_PROFILE_MENU.message());
            return true;
        }
        else {
            Printer.print(MenuMessage.INVALID_MENU.message());
            return false;
        }
    }

    @Override
    public void exitMenu() {
        TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
        Printer.print(MenuMessage.ENTER_LOGIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.MAIN_MENU.message());
    }
}
