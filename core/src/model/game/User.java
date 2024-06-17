package model.game;

import model.card.Faction;
import view.terminal.Printer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private final static ArrayList<User> allUsers = new ArrayList<>();
    private static TreeMap<String, String> allSecurityQuestionsAndAnswers;
    private static int questionsCount;
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
    private final ArrayList<GameInformation> gameInformations;
    private TreeMap<String, String> securityQuestions;
    private Faction lastFaction;

    static {
        //Initialize all security questions part in static block. at least 5 questions.
        allSecurityQuestionsAndAnswers = new TreeMap<>();
        allSecurityQuestionsAndAnswers.put("What year Hitler was born?", "1889");
        allSecurityQuestionsAndAnswers.put("What year Hiroshima happened?", "1945");
        allSecurityQuestionsAndAnswers.put("How many ribs a ordinary human has?", "24");
        allSecurityQuestionsAndAnswers.put("What is the capital city of Iran?", "Tehran");
        allSecurityQuestionsAndAnswers.put("How old was Henry Kissinger when he died?","100");
        questionsCount = 5;
    }
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
        this.gameInformations = new ArrayList<>();
        allUsers.add(this);//Farbod: couldn't find this anywhere (when a new User is initialized we need to add it to allUsers)
        //TODO: decide a default faction for the new players!
    }

    public static void resetUsers () {
        //used in testing process
        allUsers.clear();
    }
    public static User findUser(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void printAllSecurityQuestions() {
        //probably in future I will make this a method with int return for have current number of questions .
        int counter = 1;
        for (Map.Entry<String, String> aQuestion : allSecurityQuestionsAndAnswers.entrySet()) {
            Printer.print(counter + ". " + aQuestion.getKey() + " " + aQuestion.getValue());
            counter++;
        }
    }

    public static boolean checkQuestionNumberValidation(int number) {
        return number >= 1 && number <= 5;
    }
    public static void putQuestion(int questionNumber) {
        //TODO : check this part for bugs because it is not working for sure and intelliJ gives warning.
        Set<Map.Entry<String, String>> set = allSecurityQuestionsAndAnswers.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        Map.Entry<String, String> questionAnswer = (Map.Entry<String, String>)iterator;
        for (int i = 1; i < questionNumber; i++) {
            questionAnswer = (Map.Entry<String, String>)iterator.next();
        }
        currentUser.putSecurityQuestion(questionAnswer.getKey(), questionAnswer.getValue());
    }
    public int passwordCheck(String password) {
        if (password.length() < 8) return 1;
        Matcher matcher = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]+$").matcher(password);
        if (!matcher.find()) return 2;
        return 0;
    }

    public int checkSecurityQuestionAnswer(int number, String answer){
        Set<Map.Entry<String, String>> set = securityQuestions.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        Map.Entry<String, String> questionAnswer = (Map.Entry<String, String>)iterator;
        int counter = 1;
        boolean flag = true;
        while (iterator.hasNext()) {
            counter++;
            questionAnswer = iterator.next();
            if (counter == number) {
                flag = false;
                break;
            }
        }
        if (flag) return 1;
        if (!questionAnswer.getValue().equals(answer)) return 2;
        return 0;
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


    public boolean changeUsername(String newUsername) {
        if (ValidationRegex.Username.getMatcher(newUsername).find()) {
            this.setUsername(newUsername);
            return true;
        }
        return false;
    }

    public void changePassword(String newPassword) {
        if (passwordCheck(newPassword) == 0) {
            this.setPassword(newPassword);
        }
    }

    public boolean changeNickname(String newNickname) {
        if (ValidationRegex.Nickname.getMatcher(newNickname).find()) {
            this.setNickname(newNickname);
            return true;
        }
        return false;
    }

    public boolean changeEmail(String newEmail) {
        if (ValidationRegex.Email.getMatcher(newEmail).find()) {
            this.setEmail(newEmail);
            return true;
        }
        return false;
    }

    public void showGameHistory(int count) {
        //TODO : fill this after completing game model.
    }

    public boolean isGameHistoryEmpty() {
        return this.gameInformations.isEmpty();
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
