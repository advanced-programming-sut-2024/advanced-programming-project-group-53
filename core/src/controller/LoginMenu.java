package controller;

import model.game.User;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class LoginMenu extends Menu {
    private static LoginMenu instance;

    private LoginMenu() {
        super.setMenuType(MenuName.LoginMenu);
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    @Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.MainMenu) {
            TerminalRun.changeCurrentMenu(MainMenu.getInstance());
            Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
            return true;
        } else {
            Printer.print(MenuMessage.INVALID_MENU.message());
            return false;
        }
    }

    @Override
    public void exitMenu() {
        exitGame();
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.LOGIN_MENU.message());
    }
}
