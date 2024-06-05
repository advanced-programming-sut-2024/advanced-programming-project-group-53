package controller;

import model.game.User;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class ProfileMenu extends Menu {
    private static ProfileMenu instance;

    private ProfileMenu() {
        super.setMenuType(MenuName.ProfileMenu);
    }

    public static ProfileMenu getInstance() {
        if (instance == null)
            instance = new ProfileMenu();
        return instance;
    }

    public void showInformation() {
        Printer.printUserInfo();
    }

    public void showGameHistory(int count) {
        User user = User.getCurrentUser();
        if (user == null) return;
        if (user.isGameHistoryEmpty()) Printer.print(MenuMessage.EMPTY_GAME_HISTORY.message());
        //TODO : fill this in user field for showing game history.
        Printer.temporaryPrinter();
    }

    public boolean checkCountValidation(int count) {
        return count <= 0;
    }

    @Override
    public boolean enterMenu(String name) {
        Printer.print(MenuMessage.INVALID_MENU.message());
        return false;
    }

    @Override
    public void exitMenu() {
        TerminalRun.changeCurrentMenu(MainMenu.getInstance());
        Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.PROFILE_MENU.message());
    }
}
