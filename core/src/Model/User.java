package Model;

import Model.Card.Faction;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private String username;
    private String nickname;
    private String email;
    private String password;
    private double maxPoint;
    private int rank;
    private int gameCount;
    private int winCount;
    private int loseCount;
    private int drawCount;
    private ArrayList<Model.Game> game;
    private static ArrayList<User> allUsers;
    private static User currentUser;
    private HashMap<String, String> securityQuestions;
    private Faction lastFaction;

    public User(String username, String nickname, String email, String password) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.gameCount = 0;
        this.winCount = 0;
        this.loseCount = 0;
        this.drawCount = 0;
        this.maxPoint = 0;
        //decide a default faction for the new players!
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(double maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public int getWinCount() {
        return winCount;
    }

    public void setWinCount(int winCount) {
        this.winCount = winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(int loseCount) {
        this.loseCount = loseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int drawCount) {
        this.drawCount = drawCount;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public Faction getLastFaction() {
        return lastFaction;
    }

    public void setLastFaction(Faction lastFaction) {
        this.lastFaction = lastFaction;
    }

    public static User findUser(String username) {
        for (User user : allUsers) {
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    public void changeUsername(String newUsername) {

    }

    public void changePassword(String newPassword) {

    }

    public void changeNickname(String newNickname) {

    }

    public void changeEmail(String newEmail) {

    }

    public boolean passwordCheck(String password) {
        return false;
    }

    public boolean checkSecurity(String answer, String question) {
        return false;//check inputs with team
    }

    public void startGame(String playerName) {

    }

}
