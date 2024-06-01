package control;

public class GameMenu extends Menu {
    private static GameMenu instance;

    private GameMenu() {

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
}
