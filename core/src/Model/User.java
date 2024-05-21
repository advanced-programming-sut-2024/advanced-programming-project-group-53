package Model;

import Model.Card.Faction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private static Matcher getmatcher(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
    public static User findUser(String username) {
        for (User user : allUsers) {
            if (user.username.equals(username))
                return user;
        }
        return null;
    }

    public void changeUsername(String newUsername) {
        if (getmatcher(newUsername, String.valueOf(ValidationRegexes.Username)) != null) {
            this.setUsername(newUsername);
        }
    }

    public void changePassword(String newPassword) {
        if (getmatcher(newPassword, String.valueOf(ValidationRegexes.Password)) != null) {
            this.setPassword(newPassword);
        }
    }

    public void changeNickname(String newNickname) {
        if (getmatcher(newNickname, String.valueOf(ValidationRegexes.Username)) != null) {
            this.setNickname(newNickname);
        }
    }

    public void changeEmail(String newEmail) {
        if (getmatcher(newEmail, String.valueOf(ValidationRegexes.Email)) != null) {
            this.setEmail(newEmail);
        }
    }

    public boolean passwordCheck(String password) throws Exception{
        //Farbod: checking the password acceptability(not its accuracy)
        if (password.length() < 8) {
            throw new Exception("not enough length");
        } else if (getmatcher(password, "[a-z]") != null) {
            throw new Exception("missing lowercase letter");
        } else if (getmatcher(password, "[A-Z]") != null) {
            throw new Exception("missing uppercase letter");
        } else if (getmatcher(password, "[0-9]") != null) {
            throw new Exception("missing number");
        } else if (getmatcher(password, "[!@#$%^&*]") != null) {
            throw new Exception("missing special character");
        }
        return true;
    }

    public boolean checkSecurity(String answer, String question) {
        return false;//check inputs with team
    }

    public void startGame(String playerName) {

    }

}
