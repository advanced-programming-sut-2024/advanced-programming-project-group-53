package controller;

import java.util.ArrayList;
import java.util.Random;

public class TournamentMenu extends Menu {
    private static TournamentMenu instance;
    private ArrayList<String[]> games;

    private TournamentMenu() {
        games = new ArrayList<>();
    }

    public static TournamentMenu getInstance() {
        if (instance == null)
            instance = new TournamentMenu();
        return instance;
    }

    public void addPlayer(String username1,
                          String username2,
                          String username3,
                          String username4,
                          String username5,
                          String username6,
                          String username7,
                          String username8) {
        String[] game = {username1, username2, username3, username4, username5, username6, username7, username8};
        games.add(game);
    }

    public String firstRound(int index) {
        String[] game = games.get(index);
        ArrayList<Integer> used = new ArrayList<>();
        Random random = new Random();
        while (used.size() != 8) {
            int generated = random.nextInt(7) + 1;
            if (used.contains(generated))
                continue;
            used.add(generated);
        }
        StringBuilder builder = new StringBuilder();
        for (int order : used)
            builder.append(game[order]);
        return builder.toString();
    }

    public String secondRound(int index) {
        String[] game = games.get(index);
        ArrayList<Integer> used = new ArrayList<>();
        Random random = new Random();
        while (used.size() != 4) {
            int generated = random.nextInt(3) + 1;
            if (used.contains(generated))
                continue;
            used.add(generated);
        }
        StringBuilder builder = new StringBuilder();
        for (int order : used)
            builder.append(game[order]);
        return builder.toString();
    }

    public String thirdRound(int index) {
        String[] game = games.get(index);
        StringBuilder builder = new StringBuilder();
        for (String name : game)
            builder.append(name);
        return builder.toString();
    }

    public void endFirstRound(int index,
                              String username1,
                              String username2,
                              String username3,
                              String username4) {
        games.remove(index);
        games.add(index, new String[]{username1, username2, username3, username4});
    }

    public void endSecondRound(int index,
                               String username1,
                               String username2) {
        games.remove(index);
        games.add(index, new String[]{username1, username2});
    }

    public ArrayList<String[]> getGames() {
        //added for test purposes
        return games;
    }
}
