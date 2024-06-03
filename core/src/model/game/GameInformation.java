package model.game;

import model.Round;

import java.util.ArrayList;
import java.util.Date;

public class GameInformation {
    private Date date;
    private ArrayList<Round> rounds;
    private User winner;
    private final ArrayList<Player> players;
    public GameInformation(Player player1, Player player2) {
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
    }
}
