package model.game;

import com.google.gson.Gson;
import model.card.Faction;

import java.io.File;
import java.sql.*;
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
    private final ArrayList<String> friends = new ArrayList<>();
    // this attributes can handle with simple methods.
    private final ArrayList<GameInformation> gameInformations;
    private TreeMap<String, String> securityQuestions;
    private Faction lastFaction;

    static {
        DataBaseHandler.addAllUsers(allUsers);
    }

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
        saveUser();
    }

    public void saveUser() {
        //This part is to save and specify a place for saving deck and user json in file system.
        DataBaseHandler.insertUser(this);
        Path gwentInformation = Paths.get("~/gwentInformation");
        if (Files.exists(gwentInformation)) {
            try {
                Files.createDirectories(gwentInformation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path userDecksPath = Paths.get(gwentInformation.toUri() + "/" + username);
        if (Files.exists(gwentInformation)) {
            try {
                Files.createDirectories(userDecksPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUserInformation() {
        Gson gson = new Gson();
        String jsonString = gson.toJson(this);
        DataBaseHandler.updateUserInformationByUsername(this.username, jsonString);
    }

    public static User findUser(String username) {
        DataBaseHandler.addAllUsers(allUsers);
        for (User user : allUsers) {
            if (user.username().equals(username)) {
                return user;
            }
        }
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

    public String password() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String question() {
        return question;
    }

    public static ArrayList<User> ranking() {
        allUsers.sort(Comparator.comparing(User::winCount));
        return allUsers;
    }

    public void addFriend(String friendUsername) {
        this.friends.add(friendUsername);
    }

    public void removeFriend(String friendUsername) {
        this.friends.remove(friendUsername);
    }

    public ArrayList<String> friends() {
        return friends;
    }

    public void addGameInformation(GameInformation gameInformation) {
        gameInformations.add(gameInformation);
        if (gameInformation.getWinnerName().equalsIgnoreCase(username) &&
                !Objects.equals(gameInformation.getWinnerName(),gameInformation.getLoserName()))
            winCount++;
        else if (Objects.equals(gameInformation.getWinnerName(),gameInformation.getLoserName())) {
            drawCount++;
        } else loseCount++;
    }

    public ArrayList<GameInformation> gameInformation() {
        return gameInformations;
    }

    static class DataBaseHandler {
        public static void createDataBaseUserTable() {
            String url = "jdbc:sqlite:users.db";
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " data TEXT NOT NULL\n,"
                    + " name TEXT NOT NULL\n"
                    + ");";

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Table created successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void selectAllUsers() {
            String url = "jdbc:sqlite:users.db";
            String sql = "SELECT data FROM users";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    User user = gson.fromJson(jsonData, User.class);
                    System.out.println(user.username + "\t" + user.password);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void resetDatabase() {
            File dbFile = new File("users.db");
            if (dbFile.exists()) {
                if (dbFile.delete()) {
                    System.out.println("Old database file deleted successfully.");
                } else {
                    System.out.println("Failed to delete old database file.");
                }
            }

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db")) {
                if (conn != null) {
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void insertUser(User user) {
            String url = "jdbc:sqlite:users.db";
            String sql = "INSERT INTO users(data,name) VALUES(?,?)";
            Gson gson = new Gson();
            String json = gson.toJson(user);

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, json);
                preparedStatement.setString(2, user.username);
                preparedStatement.executeUpdate();
                System.out.println("User " + user.username + " inserted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void deleteUserByUsername(String name) {
            String sql = "DELETE FROM users WHERE name = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db");
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                System.out.println("User(s) deleted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void addAllUsers(ArrayList<User> allUsers) {
            String url = "jdbc:sqlite:users.db";
            String sql = "SELECT data FROM users";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    User user = gson.fromJson(jsonData, User.class);
                    allUsers.add(user);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void updateUserInformationByUsername(String username, String updatedData) {
            String spl = "UPDATE users SET data = ? WHERE name = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:users.db");
                PreparedStatement preparedStatement = conn.prepareStatement(spl)) {
                preparedStatement.setString(1, updatedData);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
                System.out.println("User data updated successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void main(String[] args) {
            //Just for testing purposes!
            resetDatabase();
            createDataBaseUserTable();
        }

    }
}