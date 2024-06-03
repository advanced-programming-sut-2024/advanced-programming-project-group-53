package model.game;

import model.card.Faction;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private final static ArrayList<User> allUsers = new ArrayList<>();
    private static User currentUser;
    private  String username;
    private String nickname;
    private String email;
    private String password;
    private double maxPoint;
    private int rank;
    //TODO : make this attributes method in future .
    private int gameCount;
    private int winCount;
    private int loseCount;
    private int drawCount;
    // this attributes can handle with simple methods.
    private ArrayList<GameInformation> gameInformation;
    private HashMap<String, String> securityQuestions;
    private Faction lastFaction;

    public static User findUser(String username) {
        for (User user: allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void changeUsername(String newUsername) {
        this.username = username;
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

    public boolean passwordCheck(String password) {
        //TODO : probably delete it because we have password regex in terminal package .
        return false;
    }

    public boolean checkSecurity(String answer, String question) {
        //TODO : generate at least 5 Q&A .
        return false;//check inputs with team
    }

    public void startGame(String playerName) {
        //TODO : probably this method will delete in future .
    }

    public String getUsername() {
        return this.username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
