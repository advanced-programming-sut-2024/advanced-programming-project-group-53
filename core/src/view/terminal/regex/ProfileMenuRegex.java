package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuRegex {
    enterUserInfo(Pattern.compile("^\\s*enter\\s+user\\s+info\\s+menu\\s*$")),
    gameHistorySpecified(Pattern.compile("^\\s*game\\s+history\\s+-n\\s+(?<number>\\d+)\\s*$")),
    gameHistoryNormal(Pattern.compile("^\\s*game\\s+history\\s*$"));

    private final Pattern pattern;
    ProfileMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
