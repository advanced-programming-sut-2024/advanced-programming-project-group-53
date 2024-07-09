package model.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class LoginContainer {
    private String username;
    private String password;

    public LoginContainer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static void saveStayLoggedIn(LoginContainer loginContainer) throws IOException {
        File dir = new File(System.getProperty("user.home") + "/gwentInformation/");
        if (!dir.exists()) dir.mkdirs();
        File loginState = new File(dir, "login.json");
        if (!loginState.exists()) loginState.createNewFile();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(loginState);
        gson.toJson(loginContainer, writer);
        writer.close();
    }

    public static void deleteLoginState() {
        File file = new File(System.getProperty("user.home") + "/gwentInformation/login.json");
        if (!file.exists()) return;
        file.delete();
    }

    public static LoginContainer getLastLogin() throws FileNotFoundException {
        File file = new File(System.getProperty("user.home") + "/gwentInformation/login.json");
        if (!file.exists()) return null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = new FileReader(file);
        LoginContainer loginContainer = gson.fromJson(reader, LoginContainer.class);
        deleteLoginState();
        return loginContainer;
    }


    public static void main(String[] args) throws IOException {
//        LoginContainer loginState = LoginContainer.getLastLogin();
//        LoginContainer.saveStayLoggedIn(loginState);
//        assert loginState != null;
//        System.out.println(loginState.getUsername());
//        System.out.println(loginState.getPassword());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
