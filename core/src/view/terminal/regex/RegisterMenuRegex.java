package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuRegex {
    register(Pattern.compile("^\\s*register(?=.*\\s+-u\\s+(?<username>\\S+)\\s*)(?=.*\\s+-p\\s+(?<passSection>\\S+)\\s*)" +
            "(?=.*\\s+-n\\s+(?<nickname>.+)\\s*)(?=.*\\s+-e\\s+(?<email>.+)\\s*)(\\s+-[eunp]\\s+(.+)\\s*){4}\\s*$")),
    pickQuestion(Pattern.compile("^\\s*pick\\s+question(?=.*\\s+-q\\s+(?<questionNumber>\\d+)\\s*)" +
            "(?=.*\\s+-a\\s+(?<answer>\\.+)\\s*)(?=.*\\s+-c\\s+(?<confirmAnswer>\\d+)\\s*)" +
            "(\\s+-[qac]\\s+(\\.+)\\s*){3}\\s*$"));

    private final Pattern pattern;

    RegisterMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
