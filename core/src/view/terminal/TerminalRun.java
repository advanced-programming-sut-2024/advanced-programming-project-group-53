package view.terminal;

import controller.LoginMenu;
import controller.Menu;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.regex.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class TerminalRun {
    private static Menu currentMenu = LoginMenu.getInstance();

    public static void loginMenuRun(Scanner scanner) {
        while(true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = LoginMenuRegex.skip.getMatcher(line);
            if (matcher.find()) {
                currentMenu.enterMenu("RegisterMenu");
                runSpecificMenu(MenuName.RegisterMenu, scanner);
            }
            matcher = LoginMenuRegex.login.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with with controller.
            }
            matcher = LoginMenuRegex.forgetPassword.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this field with 2 regexes (answer question and set password) and
                // confirm it in controller.
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
    }


    public static void registerMenuRun(Scanner scanner) {
        String line = scanner.nextLine();
        checkGeneralCommands(line, scanner);
        Matcher matcher = RegisterMenuRegex.register.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = RegisterMenuRegex.pickQuestion.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        } else Printer.print(MenuMessage.INVALID_COMMAND.message());
    }

    public static void mainMenuRun(Scanner scanner) {
        String line = scanner.nextLine();
        checkGeneralCommands(line, scanner);
        Matcher matcher = MainMenuRegex.logout.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = MainMenuRegex.changeUsername.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = MainMenuRegex.changePassword.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = MainMenuRegex.changeNickname.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = MainMenuRegex.changeEmail.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        } else Printer.print(MenuMessage.INVALID_COMMAND.message());
    }

    public static void profileMenuRun(Scanner scanner) {
        String line = scanner.nextLine();
        checkGeneralCommands(line, scanner);
        Matcher matcher = ProfileMenuRegex.enterUserInfo.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = ProfileMenuRegex.gameHistoryNormal.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        }
        matcher = ProfileMenuRegex.gameHistorySpecified.getMatcher(line);
        if (matcher.find()) {
            //TODO : fill this with controller.
        } else Printer.print(MenuMessage.INVALID_COMMAND.message());
    }

    public static void startMenuRun(Scanner scanner) {

    }

    public static void gameMenuRun(Scanner scanner) {

    }
    public static void changeCurrentMenu(Menu menu) {
        currentMenu = menu;
    }

    public static void checkGeneralCommands(String input, Scanner scanner) {
        Matcher matcher = MenuRegex.enterMenu.getMatcher(input);
        if (matcher.find()) {
            boolean entered = currentMenu.enterMenu(matcher.group("menuName"));
            if (entered) {
                MenuName menuName = MenuName.getMenu(matcher.group("menuName"));
                assert menuName != null;
                runSpecificMenu(menuName, scanner);
            }
        }
        matcher = MenuRegex.exitMenu.getMatcher(input);
        if (matcher.find()) {
            currentMenu.exitMenu();
        }
        matcher = MenuRegex.exitGame.getMatcher(input);
        if (matcher.find()) {
            currentMenu.exitGame();
        }
        matcher = MenuRegex.showCurrentMenu.getMatcher(input);
        if (matcher.find()) {
            currentMenu.showMenu();
        }
    }

    public static void runSpecificMenu(MenuName menuName, Scanner scanner) {
        switch (menuName) {
            case LoginMenu:
                loginMenuRun(scanner);
            case RegisterMenu:
                registerMenuRun(scanner);
            case MainMenu:
                mainMenuRun(scanner);
            case ProfileMenu:
                profileMenuRun(scanner);
            case StartMenu:
                startMenuRun(scanner);
            case GameMenu:
                gameMenuRun(scanner);
            default:
                return;
        }
    }
}
