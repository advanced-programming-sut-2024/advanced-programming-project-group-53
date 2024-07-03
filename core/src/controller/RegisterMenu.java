package controller;

import model.game.User;
import model.game.ValidationRegex;
import view.Message;

import java.util.regex.Matcher;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance;

    private RegisterMenu() {
    }

    public static RegisterMenu getInstance() {
        if (instance == null)
            instance = new RegisterMenu();
        return instance;
    }

    public String registerValidate(String username, String nickname, String email, String password) {
        String result = usernameValidation(username) +
                nicknameValidation(nickname) +
                emailValidation(email) +
                passwordValidation(password);
        if (result.isEmpty())
            return "empty";
        else
            return result;
    }

    public String register(String username, String nickname, String email, String password, String confirmPassword,String question, String answer) {
        String result = "";
        if (!password.equals(confirmPassword)) {
            result += Message.PASSWORD_IS_NOT_THE_SAME.message();
            return result;
        }
        result +=  registerValidate(username, nickname, email, password);
        if (result.equals("empty"))
            new User(username, nickname, email, password, question, answer);
        return result;
    }

    public String usernameValidation(String username) {
        Matcher matcher = ValidationRegex.Username.getMatcher(username);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_USERNAME.message());
        return result.toString();
    }

    public String nicknameValidation(String nickname) {
        Matcher matcher = ValidationRegex.Nickname.getMatcher(nickname);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_NICKNAME.message());
        return result.toString();
    }

    public String emailValidation(String email) {
        Matcher matcher = ValidationRegex.Email.getMatcher(email);
        StringBuilder result = new StringBuilder();
        if (!matcher.find())
            result.append(Message.INVALID_EMAIL.message());
        return result.toString();
    }

    public String passwordValidation(String password) {
        Matcher matcher = ValidationRegex.Password.getMatcher(password);
        StringBuilder result = new StringBuilder();
        if (!matcher.find()) {
            result.append(Message.WEAK_PASSWORD.message());
            //TODO: suggest a password.
        }
        return result.toString();
    }
}
