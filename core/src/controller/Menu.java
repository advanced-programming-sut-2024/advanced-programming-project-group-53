package controller;

import model.menu.MenuName;

public abstract class Menu {
    private MenuName menuName;

    public void exitGame() {
        System.exit(0);
        //TODO: Save sth.
    }

    public void setMenuName(MenuName menuName) {
        this.menuName = menuName;
    }

    public MenuName menuName() {
        return this.menuName;
    }
}
