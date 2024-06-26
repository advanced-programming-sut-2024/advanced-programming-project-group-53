package controller;

import model.game.GameInformation;
import model.game.Player;
import model.game.Table;
import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
import view.terminal.TerminalRun;

public class GameMenu extends Menu {
    private static GameMenu instance;
    private final Table table;
    private GameMenu(Player player1, Player player2) {
        super.setMenuType(MenuName.GameMenu);
        this.table = new Table(player1, player2);
    }

    public static GameMenu getInstance(Player player1, Player player2) {
        if (instance == null)
            setInstance(player1, player2);
        return instance;
    }

    public static void setInstance(Player player1, Player player2) {
        instance = new GameMenu(player1, player2);
    }

    @Override
    public boolean enterMenu(String name) {
        Printer.print(MenuMessage.INVALID_MENU.message());
        return false;
    }

    @Override
    public void exitMenu() {
        TerminalRun.changeCurrentMenu(StartMenu.getInstance());
        Printer.print(MenuMessage.ENTER_START_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.GAME_MENU.message());
    }
}
