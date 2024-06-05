package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegex {
    login(Pattern.compile("^\\s*login(?=.*\\s+-u\\s+(?<username>\\S+)\\s*)" +
            "(?=.*\\s+-p\\s+(?<password>\\S+)\\s*)(\\s+-[up]\\s+(\\S+)\\s*){2}\\s+-stay-logged-in\\s*$")),
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
