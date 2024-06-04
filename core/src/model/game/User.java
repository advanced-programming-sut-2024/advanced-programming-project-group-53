package model.game;

import model.card.Faction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final static ArrayList<User> allUsers = new ArrayList<>();
    private static User currentUser;
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
    // this attributes can handle with simple methods.
    private ArrayList<GameInformation> gameInformation;
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
        //TODO: decide a default faction for the new players!
    }

    public static User findUser(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean passwordCheck(String password) {
        //TODO : probably delete it because we have password regex in terminal package .
        return false;
    }

    public boolean checkSecurity(String answer, String question) {
        for (Map.Entry<String, String> aQuestion : securityQuestions.entrySet()) {
            if (aQuestion.toString().equals(question) && aQuestion.getKey().equals(answer))
                return true;
        }
        return false;
    }

    public void putSecurityQuestion(String question, String answer) {
        securityQuestions.put(question, answer);
    }

    public void startGame(String playerName) {
        //TODO : probably this method will delete in future .
    }


    public void changeUsername(String newUsername) {
        if (ValidationRegex.Username.getMatcher(newUsername).find()) {
            this.setUsername(newUsername);
        }
    }

    public void changePassword(String newPassword) {
        if (ValidationRegex.Password.getMatcher(newPassword).find()) {
            this.setPassword(newPassword);
        }
    }

    public void changeNickname(String newNickname) {
        if (ValidationRegex.Nickname.getMatcher(newNickname).find()) {
            this.setNickname(newNickname);
        }
    }

    public void changeEmail(String newEmail) {
        if (ValidationRegex.Email.getMatcher(newEmail).find()) {
            this.setEmail(newEmail);
        }
    }
    /*
     * getters and setters part
     */
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
    /*
     * getters and setters part
     */
}
