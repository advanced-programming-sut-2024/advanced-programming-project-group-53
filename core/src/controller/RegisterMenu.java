package controller;

import model.game.User;
import model.game.ValidationRegex;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.Printer;
import view.terminal.TerminalRun;

import java.util.regex.Matcher;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance;

    private RegisterMenu() {
        super.setMenuType(MenuName.RegisterMenu);
    }

    public static RegisterMenu getInstance() {
        if (instance == null)
            instance = new RegisterMenu();
        return instance;
    }

    public boolean registerValidate(String username, String nickname, String email, String password) {
        Matcher matcher = ValidationRegex.Username.getMatcher(username);
        if (!matcher.find()) {
            Printer.print(MenuMessage.INVALID_USERNAME.message());
            return false;
        }
        matcher = ValidationRegex.Nickname.getMatcher(nickname);
        if (!matcher.find()) {
            Printer.print(MenuMessage.INVALID_NICKNAME.message());
            return false;
        }
        matcher = ValidationRegex.Email.getMatcher(email);
        if (!matcher.find()) {
            Printer.print(MenuMessage.INVALID_EMAIL.message());
            return false;
        }
        matcher = ValidationRegex.Password.getMatcher(password);
        if (!matcher.find()) {
            Printer.print(MenuMessage.WEAK_PASSWORD.message());
            return false;
        }
        User.printAllSecurityQuestions();
        return true;
    }

    public void register(String username, String nickname, String email, String password, int number) {
        User user = new User(username, nickname, email, password);
        User.setCurrentUser(user);
        User.putQuestion(number);
        TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
    }

    public boolean pickQuestion(int number) {
        if (!User.checkQuestionNumberValidation(number)) {
            Printer.print(MenuMessage.INVALID_NUMBER.message());
            return false;
        }
        return true;
    }
    @Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.MainMenu) {
            TerminalRun.changeCurrentMenu(MainMenu.getInstance());
            Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
            return true;
        } else if (MenuName.getMenu(name) == MenuName.LoginMenu) {
            TerminalRun.changeCurrentMenu(LoginMenu.getInstance());
            Printer.print(MenuMessage.ENTER_REGISTER_MENU.message());
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
}
