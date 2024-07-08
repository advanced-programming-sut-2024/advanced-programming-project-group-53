package controller;

import model.game.User;
import model.view.Message;
import network.MyJWT;

import java.util.Objects;

public class LoginMenu extends Menu {
    private static LoginMenu instance;

    private LoginMenu() {
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    public String login(String username, String password) {
        User user = User.findUser(username);
        if (user == null)
            return Message.NO_USER.message();
        else if (!user.password().equals(password))
            return Message.INCORRECT_PASSWORD.message();
        else {
            user.setToken(new MyJWT(username).generateToken("expireAfter15min"));
            return "empty";
        }
    }

    public String userValidation(String username) {
        User user = User.findUser(username);
        if (user == null)
            return Message.NO_USER.message();
        return "empty";
    }

    public String question(String username) {
        return User.findUser(username).question();
    }

    public String changePassword(String answer,String newPassword,String newPasswordConfirm, String username) {
        User user = User.findUser(username);
        if (!user.checkSecurityAnswer(answer))
            return Message.WRONG_ANSWER.message();
        if (!newPassword.equals(newPasswordConfirm))
            return Message.PASSWORD_IS_NOT_THE_SAME.message();
        String result = RegisterMenu.getInstance().passwordValidation(newPassword);
        if (!Objects.equals(result, ""))
            return result;
        else {
            user.setPassword(newPassword);
            return Message.PASSWORD_CHANGED.message();
        }
    }
}
