package controller;

import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class LoginMenu extends Menu {
    private static LoginMenu instance;

    private LoginMenu() {

    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    @Override
    public void enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.MainMenu) {
            TerminalRun.ChangeCurrentMenu(MainMenu.getInstance());
            Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
        }
        else
            Printer.print(MenuMessage.INVALID_MENU.message());
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
