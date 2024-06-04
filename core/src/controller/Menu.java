package controller;

import model.menu.MenuName;

public abstract class Menu {
    private MenuName menuName;

    public abstract boolean enterMenu(String name);

    public abstract void exitMenu();

    public abstract void showMenu();

    public void exitGame() {
        System.exit(0);//TODO: Save sth.
    }

    public void setMenuType(MenuName menuName) {
        this.menuName = menuName;
    }

    public MenuName getMenuType() {
        return this.menuName;
    }
}
