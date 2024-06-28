package model.game;

import java.util.ArrayList;
import java.util.Date;

public class GameInformation {
    private Date date;
    private final ArrayList<Round> rounds;
    private final String winnerName;
    private final String loserName;
    public GameInformation(Player winner, Player loser, ArrayList<Round> rounds) {
        // before this store it in user probably will handle by team.
        this.winnerName = winner.getUser().username();
        this.loserName = loser.getUser().username();
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
}
