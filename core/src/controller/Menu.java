package controller;

public abstract class Menu {
    public abstract void enterMenu(String name);

    public abstract void exitMenu();

    public abstract void showMenu();

    public void exitGame() {
        System.exit(0);//TODO: Save sth.
    }
}
