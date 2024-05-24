package Control.View.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserInfoMenuRegex {
    EnterUserInfo("^\\s*enter\\s+user\\s+info\\s+menu\\s*$"),
    GameHistorySpecified("^\\s*game\\s+history\\s+-n\\s+(?<Number>\\d+)\\s*$"),
    GameHistoryNormal("^\\s*game\\s+history\\s*$");

    final String regex;
    UserInfoMenuRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(regex).matcher(input);
    }
}
