package view.terminal;

import controller.*;
import model.menu.MenuName;
import view.terminal.Message.MenuMessage;
import view.terminal.regex.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class TerminalRun {
    private static Menu currentMenu = LoginMenu.getInstance();

    public static void loginMenuRun(Scanner scanner) {
        LoginMenu exactlyCurrentMenu = (LoginMenu)TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = LoginMenuRegex.skip.getMatcher(line);
            if (matcher.find()) {
                currentMenu.enterMenu("RegisterMenu");
                runSpecificMenu(MenuName.RegisterMenu, scanner);
                break;
            }
            matcher = LoginMenuRegex.login.getMatcher(line);
            if (matcher.find()) {
                boolean validLogin = exactlyCurrentMenu.login(matcher.group("username"),
                        matcher.group("password"));
                if (validLogin) runSpecificMenu(MenuName.MainMenu, scanner);
                continue;
            }
            matcher = LoginMenuRegex.forgetPassword.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this field with 2 regexes (answer question and set password) and
                // confirm it in controller.
                if (exactlyCurrentMenu.forgetPasswordUserValidation(matcher.group("username"))) {
                    String questionLine = scanner.nextLine();
                    matcher = LoginMenuRegex.answerQuestion.getMatcher(questionLine);
                } else Printer.print(MenuMessage.NO_USER.message());
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
    }


    public static void registerMenuRun(Scanner scanner) {
        RegisterMenu exactlyCurrentMenu = (RegisterMenu) TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = RegisterMenuRegex.register.getMatcher(line);
            if (matcher.find()) {
                String username = matcher.group("username");
                String nickname = matcher.group("nickname");
                String email = matcher.group("email");
                String password = matcher.group("password");
                boolean validate = exactlyCurrentMenu.registerValidate(username, nickname, email, password);
                if (validate) {
                    while (true) {
                        String pickQuestionLine = scanner.nextLine();
                        matcher = RegisterMenuRegex.pickQuestion.getMatcher(pickQuestionLine);
                        int number = Integer.parseInt(matcher.group("questionNumber"));
                        boolean questionValidation = exactlyCurrentMenu.pickQuestion(number);
                        if (questionValidation) {
                            exactlyCurrentMenu.register(username, nickname, email, password, number);
                            TerminalRun.runSpecificMenu(MenuName.LoginMenu,scanner);
                            break;
                        } else Printer.print(MenuMessage.TRY_AGAIN.message());
                    }
                    break;
                } else Printer.print(MenuMessage.TRY_AGAIN.message());
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
    }

    public static void mainMenuRun(Scanner scanner) {
        MainMenu exactlyCurrentMenu = (MainMenu) TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = MainMenuRegex.logout.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.logout();
                runSpecificMenu(MenuName.LoginMenu,scanner);
                break;
            }
            matcher = MainMenuRegex.changeUsername.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.changeUsername(matcher.group("newUsername"));
                continue;
            }
            matcher = MainMenuRegex.changePassword.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.changePassword(matcher.group("newPassword"),
                        matcher.group("oldPassword"));
                continue;
            }
            matcher = MainMenuRegex.changeNickname.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.changeNickname(matcher.group("newNickname"));
                continue;
            }
            matcher = MainMenuRegex.changeEmail.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.changeEmail(matcher.group("newEmail"));
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
    }

    public static void profileMenuRun(Scanner scanner) {
        //TODO : complete show game history in User class.
        ProfileMenu exactlyCurrentMenu = (ProfileMenu) TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = ProfileMenuRegex.enterUserInfo.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.showInformation();
                continue;
            }
            matcher = ProfileMenuRegex.gameHistoryNormal.getMatcher(line);
            if (matcher.find()) {
                exactlyCurrentMenu.showGameHistory(5);
                continue;
            }
            matcher = ProfileMenuRegex.gameHistorySpecified.getMatcher(line);
            if (matcher.find()) {
                int count = Integer.parseInt(matcher.group("number"));
                if (exactlyCurrentMenu.checkCountValidation(count)) {
                    Printer.print(MenuMessage.INVALID_NUMBER.message());
                    continue;
                }
                exactlyCurrentMenu.showGameHistory(count);
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
    }

    public static void startMenuRun(Scanner scanner) {
        StartMenu exactlyCurrentMenu = (StartMenu) TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = StartMenuRegex.createGame.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.showFactions.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.selectFaction.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.showCards.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.showDeck.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.showCurrentUserInformation.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.showLeaders.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.startGame.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.changeTurn.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.saveDeckAddress.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.saveDeckName.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.loadDeckAddress.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.loadDeckName.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.selectLeader.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.addToDeck.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = StartMenuRegex.deleteFromDeck.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }

    }

    public static void gameMenuRun(Scanner scanner) {
        GameMenu exactlyCurrentMenu = (GameMenu) TerminalRun.getMenuInstance();
        while (true) {
            String line = scanner.nextLine();
            checkGeneralCommands(line, scanner);
            Matcher matcher = GameMenuRegex.veto.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.inHandDeck.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.remainingCards.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.outOfPlayCards.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.cardsInRow.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.spellsInPlay.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.placeCard.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showCommander.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.commanderPowerPlay.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showPlayersInfo.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showNumberOfCardsInHand.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showPlayerLives.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showTurnInfo.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showTotalScore.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.passRound.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.passRound.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            }
            matcher = GameMenuRegex.showTotalScoreOfRow.getMatcher(line);
            if (matcher.find()) {
                //TODO : fill this with controller.
            } else Printer.print(MenuMessage.INVALID_COMMAND.message());
        }
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

    public static Menu getMenuInstance() {
        return TerminalRun.currentMenu;
    }
}
