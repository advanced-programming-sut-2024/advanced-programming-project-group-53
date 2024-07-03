package controller;

import model.game.User;
import model.menu.MenuName;
import view.message.MenuMessage;

import java.util.Objects;

public class LoginMenu extends Menu {
    private static LoginMenu instance;

    private LoginMenu() {
        super.setMenuName(MenuName.LoginMenu);
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    public String login(String username, String password) {
        User user = User.findUser(username);
        if (user == null)
            return MenuMessage.NO_USER.message();
        else if (!user.password().equals(password))
            return MenuMessage.INCORRECT_PASSWORD.message();
        return "empty";
    }

    public String userValidation(String username) {
        User user = User.findUser(username);
        if (user == null)
            return MenuMessage.NO_USER.message();
        return "empty";
    }

    public String question(String username) {
        return User.findUser(username).question();
    }

    public String changePassword(String answer,String newPassword,String newPasswordConfirm, String username) {
        User user = User.findUser(username);
        if (!user.checkSecurityAnswer(answer))
            return MenuMessage.WRONG_ANSWER.message();
        if (!newPassword.equals(newPasswordConfirm))
            return MenuMessage.PASSWORD_IS_NOT_THE_SAME.message();
        String result = RegisterMenu.getInstance().passwordValidation(newPassword);
        if (!Objects.equals(result, ""))
            return result;
        else {
            user.setPassword(newPassword);
            return MenuMessage.PASSWORD_CHANGED.message();
        }
    }
}
