package view.terminal;

import controller.LoginMenu;
import controller.Menu;
import view.terminal.regex.MenuRegex;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TerminalRun {
    private static Menu currentMenu = LoginMenu.getInstance();

    public static void run(Scanner scanner) {
        String line = scanner.nextLine();
        Matcher matcher = MenuRegex.enterMenu.getMatcher(line);
        if (matcher.matches())
            currentMenu.enterMenu(matcher.group("menuName"));
    }

    public static void ChangeCurrentMenu(Menu menu) {
        currentMenu = menu;
    }
}
