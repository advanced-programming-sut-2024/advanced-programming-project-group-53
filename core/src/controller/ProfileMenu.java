package controller;

import model.game.User;
import model.view.Message;

import java.util.ArrayList;
import java.util.Objects;

public class ProfileMenu extends Menu {
    private static ProfileMenu instance;

    private ProfileMenu() {
    }

    public static ProfileMenu getInstance() {
        if (instance == null)
            instance = new ProfileMenu();
        return instance;
    }

    public String getInformation(String username) {
        StringBuilder information = new StringBuilder();
        User user = User.findUser(username);
        information.append(user.username()).append(" ");
        information.append(user.nickname()).append(" ");
        information.append(user.maxPoint()).append(" ");
        information.append(user.rank()).append(" ");
        information.append(user.gameCount()).append(" ");
        information.append(user.drawCount()).append(" ");
        information.append(user.winCount()).append(" ");
        information.append(user.loseCount()).append(" ");
        return information.toString();
    }

    public String showGameHistory(String username, String normal, String numberString) {
        User user = User.findUser(username);
        int number;
        boolean isNormal = Boolean.parseBoolean(normal);
        if (Objects.equals(numberString, ""))
            number = 5;
        else
            number = Integer.parseInt(numberString);
        if (user.isGameHistoryEmpty())
            return Message.EMPTY_GAME_HISTORY.message();
        else
            return user.gameInformation().toString();
    }

    public String showRanking() {
        ArrayList<User> users = User.ranking();
        StringBuilder ranking = new StringBuilder();
        for (User user : users)
            ranking.append(user.username()).append(" ");
        return ranking.toString();
    }

    public String changeUsername(String newUsername, String oldUsername) {
        String result = RegisterMenu.getInstance().usernameValidation(newUsername);
        if (Objects.equals(result, "")) {
            User.findUser(oldUsername).changeUsername(newUsername);
            return "empty";
        }
        return result;
    }

    public String changePassword(String oldPassword, String newPassword, String newPasswordConfirm, String username) {
        User user = User.findUser(username);
        if (!user.password().equals(oldPassword))
            return Message.INCORRECT_PASSWORD.message();
        if (!newPassword.equals(newPasswordConfirm))
            return Message.PASSWORD_IS_NOT_THE_SAME.message();
        String result = RegisterMenu.getInstance().passwordValidation(newPassword);
        if (!Objects.equals(result, ""))
            return result;
        else {
            user.setPassword(newPassword);
            return "empty";
        }
    }

    public String changeNickname(String newNickname, String username) {
        User user = User.findUser(username);
        String result = RegisterMenu.getInstance().nicknameValidation(newNickname);
        if (!Objects.equals(result, ""))
            return result;
        else {
            user.changeNickname(newNickname);
            return "empty";
        }
    }

    public String changeEmail(String newEmail, String username) {
        User user = User.findUser(username);
        String result = RegisterMenu.getInstance().emailValidation(newEmail);
        if (!Objects.equals(result, ""))
            return result;
        else {
            user.changeEmail(newEmail);
            return "empty";
        }
    }
}
