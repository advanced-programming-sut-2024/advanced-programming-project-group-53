package controller;

import com.google.gson.Gson;
import model.game.User;
import model.view.ChatContainer;
import model.view.Message;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class ChatMenu extends Menu {
    private static ChatMenu instance;
    private final ArrayList<ChatContainer> messages = new ArrayList<>();

    {
        DatabaseHandler.addAllChats(messages);
    }
    private ChatMenu() {
    }

    public static ChatMenu getInstance() {
        if (instance == null)
            instance = new ChatMenu();
        return instance;
    }

    public String userValidation(String username) {
        User user = User.findUser(username);
        if (user == null)
            return Message.NO_USER.message();
        else
            return "empty";
    }

    public String setMessage(String message, String sender, String receiver) {
        if (message.contains("\\") || message.contains("/"))
            return Message.ILLEGAL_CHARACTER.message();
        else {
            messages.add(new ChatContainer(message, sender, receiver));
            return "empty";
        }
    }

    public String getMessage(String username1, String username2) {
        ArrayList<ChatContainer> forMe = new ArrayList<>();
        for (ChatContainer chatContainer : messages)
            if ((Objects.equals(chatContainer.receiver(), username1) &&
                    Objects.equals(chatContainer.sender(), username2)) ||
                    (Objects.equals(chatContainer.receiver(), username2) &&
                            Objects.equals(chatContainer.sender(), username1)))
                forMe.add(chatContainer);
        StringBuilder builder = new StringBuilder();
        for (ChatContainer chatContainer : forMe)
            builder.append(chatContainer.toString()).append("\\");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public static class DatabaseHandler {
        public static void createDataBaseChatTable() {
            String url = "jdbc:sqlite:chats.db";
            String sql = "CREATE TABLE IF NOT EXISTS chats (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " data TEXT NOT NULL\n"
                    + ");";

            try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("Chats table created successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void selectAllChats() {
            String url = "jdbc:sqlite:chats.db";
            String sql = "SELECT data FROM chats";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    ChatContainer chatContainer = gson.fromJson(jsonData, ChatContainer.class);
                    System.out.println(chatContainer.sender() + "------>" + chatContainer.receiver());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void resetDatabase() {
            File dbFile = new File("chats.db");
            if (dbFile.exists()) {
                if (dbFile.delete()) {
                    System.out.println("Old database file deleted successfully.");
                } else {
                    System.out.println("Failed to delete old database file.");
                }
            }

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:chats.db")) {
                if (conn != null) {
                    System.out.println("A new database has been created.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void insertChat(ChatContainer chatContainer) {
            String url = "jdbc:sqlite:chats.db";
            String sql = "INSERT INTO chats(data) VALUES(?)";
            Gson gson = new Gson();
            String json = gson.toJson(chatContainer);

            try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, json);
                preparedStatement.executeUpdate();
                System.out.println("Chat inserted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void deleteChatByJson(String json) {
            String sql = "DELETE FROM chats WHERE data = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:chats.db");
                PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, json);
                preparedStatement.executeUpdate();
                System.out.println("Chat(s) deleted successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void addAllChats(ArrayList<ChatContainer> allChats) {
            String url = "jdbc:sqlite:chats.db";
            String sql = "SELECT data FROM chats";
            Gson gson = new Gson();
            try (Connection conn = DriverManager.getConnection(url);
                 Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String jsonData = resultSet.getString("data");
                    ChatContainer chat = gson.fromJson(jsonData, ChatContainer.class);
                    allChats.add(chat);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void updateChatInformationByJson(String previousData, String updatedData) {
            String spl = "UPDATE chats SET data = ? WHERE data = ?";

            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:chats.db");
                PreparedStatement preparedStatement = conn.prepareStatement(spl)) {
                preparedStatement.setString(1, updatedData);
                preparedStatement.setString(2, previousData);
                preparedStatement.executeUpdate();
                System.out.println("Chats data updated successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void main(String[] args) {
            resetDatabase();
            createDataBaseChatTable();
        }
    }
}
