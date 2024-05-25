package Model;

import java.util.Date;
import java.util.ArrayList;

public class Game {
    private final Date date = new Date();
    private final User user1, user2;
    private int user1FinalPoints, user2FinalPoints;
    private ArrayList<GameLog> rounds;
    private User winner;
    public Game(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
        this.rounds = new ArrayList<>();
    }

    public Date getDate() {
        return date;
    }
    public User getUsers(int which) {
        if (which == 1) return user1;
        return user2;
    }
    public void setFinalPoints(int points1, int points2) {
        user1FinalPoints = points1;
        user2FinalPoints = points2;
    }
    public void addRound(GameLog round) {
        rounds.add(round);
    }
}
