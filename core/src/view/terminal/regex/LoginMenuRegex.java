package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegex {
    register(Pattern.compile("^\\s*register(?=.*\\s+-u\\s+(?<username>\\S+)\\s*)(?=.*\\s+-p\\s+(?<passSection>\\S+)\\s*)" +
            "(?=.*\\s+-n\\s+(?<nickname>.+)\\s*)(?=.*\\s+-e\\s+(?<email>.+)\\s*)(\\s+-[eunp]\\s+(.+)\\s*){4}\\s*$")),
    pickQuestion(Pattern.compile("^\\s*pick\\s+question(?=.*\\s+-q\\s+(?<questionNumber>\\d+)\\s*)" +
            "(?=.*\\s+-a\\s+(?<answer>\\.+)\\s*)(?=.*\\s+-c\\s+(?<confirmAnswer>\\d+)\\s*)" +
            "(\\s+-[qac]\\s+(\\.+)\\s*){3}\\s*$")),
    login(Pattern.compile("^\\s*login(?=.*\\s+-a\\s+(?<username>\\S+)\\s*)(?=.*\\s+-a\\s+(?<password>\\S+)\\s*)" +
            "(\\s+-[up]\\s+(\\.+)\\s*){2}\\s+" +
            "-stay-logged-in\\s*$")),
    forgetPassword(Pattern.compile("^\\s*forget\\s+password\\s+-u\\s+(?<username>\\S+)\\s*$")),
    answerQuestion(Pattern.compile("^\\s*answer\\s+-q\\s+(?<questionNumber>\\d+)\\s*$")),
    setPassword(Pattern.compile("^\\s*set\\s+password\\s+-p\\s+(?<password>\\S+)\\s*$")),
    skip(Pattern.compile("^\\s*menu\\s+enter\\s+register\\s+menu\\s*$"));

    private final Pattern pattern;

    LoginMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
