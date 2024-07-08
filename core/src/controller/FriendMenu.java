package controller;

import com.google.gson.Gson;
import model.game.User;
import model.view.FriendRequest;
import model.view.Message;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class FriendMenu extends Menu {
    private static FriendMenu instance;
    private final ArrayList<FriendRequest> friendRequests = new ArrayList<>();

    {
        DataBaseHandler.addAllRequests(friendRequests);
    }
    private FriendMenu() {
    }

    public static FriendMenu getInstance() {
        if (instance == null)
            instance = new FriendMenu();
        return instance;
    }

    public String requestsIn(String username) {
        StringBuilder builder = new StringBuilder();
        for (FriendRequest friendRequest : friendRequests)
            if (Objects.equals(friendRequest.receiver(), username))
                builder.append(friendRequest.sender()).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String requestsOut(String username) {
        StringBuilder builder = new StringBuilder();
        for (FriendRequest friendRequest : friendRequests)
            if (Objects.equals(friendRequest.sender(), username))
                builder.append(friendRequest.receiver()).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String acceptFriendRequest(String sender, String receiver) {
        for (FriendRequest friendRequest : friendRequests)
            if (sender.equals(friendRequest.sender()) &&
                    receiver.equals(friendRequest.receiver())) {
                friendRequests.remove(friendRequest);
                User.findUser(sender).addFriend(receiver);
                User.findUser(receiver).addFriend(sender);
                break;
            }
        return "empty";
    }

    public String rejectFriendRequest(String sender, String receiver) {
        for (FriendRequest friendRequest : friendRequests)
            if (sender.equals(friendRequest.sender()) &&
                    receiver.equals(friendRequest.receiver())) {
                friendRequests.remove(friendRequest);
                User.findUser(sender).removeFriend(receiver);
                User.findUser(receiver).removeFriend(sender);
                break;
            }
        return "empty";
    }

    public String friends(String username) {
        StringBuilder builder = new StringBuilder();
        for (String friend : User.findUser(username).friends())
            builder.append(friend).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String sendFriendRequest(String sender, String receiver) {
        if (User.findUser(receiver) == null)
            return Message.NO_USER.message();
        else {
            if (User.findUser(sender).friends().contains(receiver))
                return Message.OLD_FRIEND.message();
            else {
                friendRequests.add(new FriendRequest(sender, receiver));
                return "empty";
            }
        }
    }

    public static class DataBaseHandler {
        public static void createDataBaseRequestTable() {
            String url = "jdbc:sqlite:requests.db";
            String sql = "CREATE TABLE IF NOT EXISTS requests (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " data TEXT NOT NULL\n"
                    + ");";

            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Table created successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void selectAllRequests() {
            String url = "jdbc:sqlite:requests.db";
            String sql = "SELECT data FROM requests";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    FriendRequest request = gson.fromJson(jsonData, FriendRequest.class);
                    System.out.println(request.sender() + "\t" + request.receiver());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void resetDatabase() {
            File dbFile = new File("requests.db");
            if (dbFile.exists()) {
                if (dbFile.delete()) {
                    System.out.println("Old database file deleted successfully.");
                } else {
                    System.out.println("Failed to delete old database file.");
                }
            }

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:requests.db")) {
                if (conn != null) {
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void insertRequest(FriendRequest request) {
            String url = "jdbc:sqlite:requests.db";
            String sql = "INSERT INTO requests(data) VALUES(?)";
            Gson gson = new Gson();
            String json = gson.toJson(request);

            try (Connection conn = DriverManager.getConnection(url);
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, json);
                preparedStatement.executeUpdate();
                System.out.println("FriendRequest inserted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void deleteRequestByJson(String json) {
            String sql = "DELETE FROM requests WHERE data = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:requests.db");
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, json);
                preparedStatement.executeUpdate();
                System.out.println("FriendRequests(s) deleted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void addAllRequests(ArrayList<FriendRequest> allRequests) {
            String url = "jdbc:sqlite:requests.db";
            String sql = "SELECT data FROM requests";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    FriendRequest request = gson.fromJson(jsonData, FriendRequest.class);
                    allRequests.add(request);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void updateRequestInformationByUsername(String previousData, String updatedData) {
            String spl = "UPDATE requests SET data = ? WHERE data = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:requests.db");
                 PreparedStatement preparedStatement = conn.prepareStatement(spl)) {
                preparedStatement.setString(1, updatedData);
                preparedStatement.setString(2, previousData);
                preparedStatement.executeUpdate();
                System.out.println("FriendRequest data updated successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
