package controller;

import model.game.User;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

public class StartMenu extends Menu {
    private static StartMenu instance;

    private StartMenu() {

    }

    public static StartMenu getInstance() {
        if (instance == null)
            instance = new StartMenu();
        return instance;
    }

    private static boolean passwordValidation(String password) {
        return false;
    }

    private static void register(String username, String nickname, String password, String confirmPassword, String email) {

    }

    private static void login(String username, String password) {

    }

    private static void forgetPassword(String username) {

    }

    private static void setPassword(String password, User user) {

    }

    @Override
    public void enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.GameMenu) {
            TerminalRun.ChangeCurrentMenu(GameMenu.getInstance());
            Printer.print(MenuMessage.ENTER_GAME_MENU.message());
        } else
            Printer.print(MenuMessage.INVALID_MENU.message());
    }

    @Override
    public void exitMenu() {
        TerminalRun.ChangeCurrentMenu(MainMenu.getInstance());
        Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.START_MENU.message());
    }
}
