package controller;

import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance;

    private RegisterMenu() {
        super.setMenuType(MenuName.RegisterMenu);
    }

    public static RegisterMenu getInstance() {
        if (instance == null)
            instance = new RegisterMenu();
        return instance;
    }

    @Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.MainMenu) {
            TerminalRun.changeCurrentMenu(MainMenu.getInstance());
            Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
            return true;
        } else if (MenuName.getMenu(name) == MenuName.LoginMenu) {
            TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
            Printer.print(MenuMessage.ENTER_REGISTER_MENU.message());
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
