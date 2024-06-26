package controller;

import model.game.User;
import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
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

    public void logout() {
        User.setCurrentUser(null);
        TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
        Printer.print(MenuMessage.LOGOUT.message());
    }

    public void changeUsername(String newUsername) {
        boolean changed = User.getCurrentUser().changeUsername(newUsername);
        if (changed) Printer.print(MenuMessage.CHANGE_USERNAME.message());
        else Printer.print(MenuMessage.INVALID_USERNAME.message());
    }

    public void changePassword(String newPassword, String oldPassword) {
        User user = User.getCurrentUser();
        if (!user.getPassword().equals(oldPassword)) {
            Printer.print(MenuMessage.INCORRECT_PASSWORD.message());
            return;
        }
        int errorState = user.passwordCheck(newPassword);
        if (errorState == 1) {
            Printer.print(MenuMessage.PASSWORD_LENGTH_ERROR.message());
        } else if (errorState == 2) {
            Printer.print(MenuMessage.PASSWORD_INVALID_CHARACTERS_ERROR.message());
        } else {
            user.changePassword(newPassword);
            Printer.print(MenuMessage.CHANGE_PASSWORD.message());
        }
    }

    public void changeNickname(String newNickname) {
        boolean changed = User.getCurrentUser().changeNickname(newNickname);
        if (changed) Printer.print(MenuMessage.CHANGE_NICKNAME.message());
        else Printer.print(MenuMessage.INVALID_NICKNAME.message());
    }

    public void changeEmail(String newEmail) {
        boolean changed = User.getCurrentUser().changeEmail(newEmail);
        if (changed) Printer.print(MenuMessage.CHANGE_EMAIL.message());
        else Printer.print(MenuMessage.INVALID_EMAIL.message());
    }

    @Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.StartMenu) {
            TerminalRun.changeCurrentMenu(StartMenu.getInstance());
            Printer.print(MenuMessage.ENTER_START_MENU.message());
            return true;
        } else if (MenuName.getMenu(name) == MenuName.ProfileMenu) {
            TerminalRun.changeCurrentMenu(ProfileMenu.getInstance());
            Printer.print(MenuMessage.ENTER_PROFILE_MENU.message());
            return true;
        } else {
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
