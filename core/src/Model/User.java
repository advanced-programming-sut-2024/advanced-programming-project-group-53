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
    private HashMap<String,String> securityQuestions;
    private Faction lastFaction;
    public static User findUser(String username){
        return null;
    }
    public void changeUsername(String newUsername){

    }
    public void changePassword(String newPassword){

    }
    public void changeNickname(String newNickname){

    }
    public void changeEmail(String newEmail){

    }
    public boolean passwordCheck(String password){
        return false;
    }
    public boolean checkSecurity(String answer,String question){
        return false;//check inputs with team
    }
    public void startGame(String playerName){

    }

}
