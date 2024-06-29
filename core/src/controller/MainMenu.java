package controller;

import model.game.User;
import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
import view.terminal.TerminalRun;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu() {
        super.setMenuName(MenuName.MainMenu);
    }

    public static MainMenu getInstance() {
        if (instance == null)
            instance = new MainMenu();
        return instance;
    }
}
