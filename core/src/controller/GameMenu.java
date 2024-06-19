package controller;

import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
import view.terminal.TerminalRun;

public class GameMenu extends Menu {
    private static GameMenu instance;

    private GameMenu() {
        super.setMenuType(MenuName.GameMenu);
    }

    public static GameMenu getInstance() {
        if (instance == null)
            instance = new GameMenu();
        return instance;
    }

    private static void createGame(String player) {

    }

    private static void showFaction() {

    }

    private static void setFaction() {

    }

    private static void showCardsOfFaction() {

    }

    private static void showDeck() {

    }

    private static void showInformation() {

    }

    private static void deleteFromDeck(String cardName, int count) {

    }

    private static void addToDeck(String cardName, int count) {

    }

    private static void saveDeck(String address) {

    }

    private static void loadDeck(String address) {

    }

    private static void showCommander() {

    }

    private static void selectCommander(int commanderNumber) {

    }

    private static void startGame() {

    }

    private static void passTurn() {

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
