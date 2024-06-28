package model.game;

import model.card.Faction;

import java.util.*;

public class User {
    private final static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String nickname;
    private String email;
    private String password;
    private final String question;
    private final String answer;
    private double maxPoint;
    private int rank;
    private int gameCount;
    private int winCount;
    private int loseCount;
    private int drawCount;
    // this attributes can handle with simple methods.
    private final ArrayList<GameInformation> gameInformations;
    private TreeMap<String, String> securityQuestions;
    private Faction lastFaction;

    public User(String username, String nickname, String email, String password, String question, String answer) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.gameCount = 0;
        this.winCount = 0;
        this.loseCount = 0;
        this.drawCount = 0;
        this.maxPoint = 0;
        this.gameInformations = new ArrayList<>();
        allUsers.add(this);
    }

    public static User findUser(String username) {
        for (User user : allUsers)
            if (user.username().equals(username))
                return user;
        return null;
    }

    public boolean checkSecurityAnswer(String answer) {
        return this.answer.equals(answer);
    }

    public void changeUsername(String newUsername) {
        this.username = newUsername;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void changeNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void changeEmail(String newEmail) {
        this.email = newEmail;
    }

    public boolean isGameHistoryEmpty() {
        return this.gameInformations.isEmpty();
    }

    public String username() {
        return username;
    }

    public String nickname() {
        return nickname;
    }

    public String email() {
        return email;
    }

    public double maxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(double maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int rank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int gameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int winCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int loseCount() {
        return loseCount;
    }

    public void setLoseCount(int loseCount) {
        this.loseCount = loseCount;
    }

    public int drawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public Faction getLastFaction() {
        return lastFaction;
    }

    public void setLastFaction(Faction lastFaction) {
        this.lastFaction = lastFaction;
    }

    public String question() {
        return question;
    }
}