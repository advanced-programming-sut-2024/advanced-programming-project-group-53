package controller;

import model.menu.MenuName;

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
