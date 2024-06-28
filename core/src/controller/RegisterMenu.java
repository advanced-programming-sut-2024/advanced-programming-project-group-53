package controller;

import model.game.User;
import model.game.ValidationRegex;
import model.menu.MenuName;
import view.message.MenuMessage;

import java.util.regex.Matcher;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance;

    private RegisterMenu() {
        super.setMenuName(MenuName.RegisterMenu);
    }

    public static RegisterMenu getInstance() {
        if (instance == null)
            instance = new RegisterMenu();
        return instance;
    }

    public String registerValidate(String username, String nickname, String email, String password) {
        return usernameValidation(username) +
                nicknameValidation(nickname) +
                emailValidation(email) +
                passwordValidation(password);
    }

    public String register(String username, String nickname, String email, String password, String question, String answer) {
        String result = registerValidate(username, nickname, email, password);
        if (result.isEmpty())
            new User(username, nickname, email, password, question, answer);
        return result;
    }

    public String usernameValidation(String username) {
        Matcher matcher = ValidationRegex.Username.getMatcher(username);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(MenuMessage.INVALID_USERNAME.message()).append("\n");
        return result.toString();
    }

    public String nicknameValidation(String nickname) {
        Matcher matcher = ValidationRegex.Nickname.getMatcher(nickname);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(MenuMessage.INVALID_NICKNAME.message()).append("\n");
        return result.toString();
    }

    public String emailValidation(String email) {
        Matcher matcher = ValidationRegex.Email.getMatcher(email);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(MenuMessage.INVALID_EMAIL.message()).append("\n");
        return result.toString();
    }

    public String passwordValidation(String password) {
        Matcher matcher = ValidationRegex.Password.getMatcher(password);
        StringBuilder result = new StringBuilder();
        if (!matcher.find()) {
            result.append(MenuMessage.WEAK_PASSWORD.message()).append("\n");
            //TODO: suggest a password.
        }
        return result.toString();
    }
}
