package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Menu {
    EnterMenu("^\\s*enter\\s+menu\\s+(?<MenuName>\\S+)\\s*$"),
    ExitMenu("^\\s*exit\\s+menu\\s+$"),
    ShowCurrentMenu("^\\s*show\\s+menu\\s+$");

    final String regex;
    Menu(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
