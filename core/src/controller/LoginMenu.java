package controller;

import model.game.User;
import model.game.ValidationRegex;
import model.menu.MenuName;
import view.message.MenuMessage;
import view.message.Printer;
import view.terminal.TerminalRun;

import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    /*private static LoginMenu instance;

    private LoginMenu() {
        super.setMenuName(MenuName.LoginMenu);
    }

    public static LoginMenu getInstance() {
        if (instance == null)
            instance = new LoginMenu();
        return instance;
    }

    @Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.MainMenu) {
            TerminalRun.changeCurrentMenu(MainMenu.getInstance());
            Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
            return true;
        } else {
            Printer.print(MenuMessage.INVALID_MENU.message());
            return false;
        }
    }

    @Override
    public void exitMenu() {
        exitGame();
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.LOGIN_MENU.message());
    }

    public boolean login(String username, String password) {
        User user = User.findUser(username);
        if (user == null) {
            Printer.print(MenuMessage.NO_USER.message());
            return false;
        }
        if (!user.getPassword().equals(password)) {
            Printer.print(MenuMessage.INCORRECT_PASSWORD.message());
            return false;
        }
        User.setCurrentUser(user);
        TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
        return true;
    }
    public boolean forgetPasswordUserValidation(String username) {
        return User.findUser(username) != null;
    }

    public boolean forgetPasswordAnswerQuestion(int number, String answer, String username) {
        User user = User.findUser(username);
        assert user != null;
        int state = user.checkSecurityQuestionAnswer(number, answer);
        if (state == 1) {
            Printer.print(MenuMessage.INVALID_NUMBER.message());
            return false;
        }
        else if (state == 2) {
            Printer.print(MenuMessage.WRONG_ANSWER.message());
            return false;
        }
        return true;
    }

    public void setPassword(String newPassword, String username) {
        User user = User.findUser(username);
        Matcher matcher = ValidationRegex.Password.getMatcher(newPassword);
        if (!matcher.find()) {
            Printer.print(MenuMessage.WEAK_PASSWORD.message());
            return;
        }
        assert user != null;
        user.setPassword(newPassword);
        Printer.print(MenuMessage.PASSWORD_CHANGED.message());
    }*/
}
