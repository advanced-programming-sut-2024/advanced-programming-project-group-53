package model.game;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ValidationRegex {
    Email(Pattern.compile("^(?<email>.+)@(yahoo|gmail|outlook|hotmail)\\.(com)$")),
    Username(Pattern.compile("^(?<username>([a-z]|[A-Z]|[0-9]|-)+)$")),
    Nickname(Pattern.compile("^(?<username>([a-z]|[A-Z]|[0-9]|-)+)$")),
    Password(Pattern.compile("^(?<password>([a-z]|[A-Z]|[0-9]|[!@#$%^&*])+)$"));
    private String value;

    private final Pattern pattern;
    ValidationRegex(Pattern pattern) {
        this.pattern = pattern;
    }
    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
