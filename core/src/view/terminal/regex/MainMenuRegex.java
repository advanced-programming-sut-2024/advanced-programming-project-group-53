package view.terminal.regex;

import jdk.jfr.internal.consumer.StringParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegex {
    logout(Pattern.compile("^\\s*user\\s+logout\\s*$")),
    changeUsername(Pattern.compile("^\\s*change\\s+username\\s+-u\\s+(?<newUsername>\\S+)\\s*$")),
    changeNickname(Pattern.compile("^\\s*change\\s+nickname\\s+-n\\s+(?<newNickname>\\S+)\\s*$")),
    changeEmail(Pattern.compile("^\\s*change\\s+email\\s+-e\\s+(?<newEmail>\\S+)\\s*$")),
    changePassword(Pattern.compile("^\\s*change\\s+password\\s+-p\\s+(?<newPassword>\\S+)\\s+-o\\s+(?<oldPassword>\\S+)\\s*$"));

    private final Pattern pattern;

    MainMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
