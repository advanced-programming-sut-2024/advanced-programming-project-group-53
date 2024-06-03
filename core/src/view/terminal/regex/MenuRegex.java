package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuRegex {
    enterMenu(Pattern.compile("^\\s*enter\\s+menu\\s+(?<menuName>\\S+)\\s*$")),
    exitMenu(Pattern.compile("^\\s*exit\\s+menu\\s*$")),
    showCurrentMenu(Pattern.compile("^\\s*show\\s+menu\\s*$")),
    exitGame(Pattern.compile("^\\s*exit\\s+game\\s*$")),;

    private final Pattern pattern;

    MenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
