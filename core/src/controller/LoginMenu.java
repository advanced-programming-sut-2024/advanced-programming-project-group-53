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

    public boolean login(String username, String password) {
        User user = User.findUser(username);
        if (user == null) {
            Printer.print(MenuMessage.NO_USER.message());
            return false;
        }
        if (!user.getPassword().equals(password)) {
            Printer.print(MenuMessage.INCORRECT_PASSWORD.message());
            return false;
        }
        User.setCurrentUser(user);
        TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
        return true;
    }
    public boolean forgetPasswordUserValidation(String username) {
        return User.findUser(username) != null;
    }
}
