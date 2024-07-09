package model.game;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class GameInformation {
    private Date date;
    private final ArrayList<Round> rounds;
    private final String player1Username;
    private final String player2Username;
    private final String winnerName;
    private final String loserName;
    private final boolean draw;
    public GameInformation(Player player1, Player player2, Player winner, Player loser, ArrayList<Round> rounds) {
        // before this store it in user probably will handle by team.
        player1Username = player1.getUser().username();
        player2Username = player2.getUser().username();
        this.winnerName = winner.getUser().username();
        this.loserName = loser.getUser().username();
        draw = Objects.equals(winnerName, loserName);
        this.rounds = rounds;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public String getLoserName() {
        return loserName;
    }

    public boolean containsPlayer(String username) {
        return this.winnerName.equals(username) || this.loserName.equals(username);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("GAME INFO:\n");
        if (draw) {
            result.append("DRAW\n");
        } else {
            result.append("Winner : ").append(winnerName).append("\n");
            result.append("Loser : ").append(loserName).append("\n");
        }
        result.append("ROUNDS:\n");
        int counter = 1;
        for (Round round : rounds) {
            result.append("Round ").append(counter).append("\n");
            result.append(round);
            counter++;
        }
        return result.toString();
    }

    public String getPlayer2Username() {
        return player2Username;
    }

    public String getPlayer1Username() {
        return player1Username;
    }
}
