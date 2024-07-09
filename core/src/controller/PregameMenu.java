package controller;

import game.GWENT;
import view.graphic.GameView;

import java.util.ArrayList;

public class PregameMenu extends Menu {
    private static PregameMenu instance;
    private ArrayList<String> waiting;

    private PregameMenu() {
        waiting = new ArrayList<>();
    }

    public static PregameMenu getInstance() {
        if (instance == null)
            instance = new PregameMenu();
        return instance;
    }

    public void addToWaiting(String username, GWENT game) {
        waiting.add(username);
        if (waiting.size() % 2 == 0) {
            String username1 = waiting.get(0);
            String username2 = waiting.get(1);
            game.changeScreen(new GameView(game, username1, username2));
            waiting.remove(username1);
            waiting.remove(username2);
        }
    }

    public ArrayList<String> getWaiting() {
        //used for tests.
        return waiting;
    }
}
