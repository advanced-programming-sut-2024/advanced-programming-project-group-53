package Control.View.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GeneralRegex {
    EnterMenu("^\\s*enter\\s+menu\\s+(?<MenuName>\\S+)\\s*$"),
    ExitMenu("^\\s*exit\\s+menu\\s+$"),
    ShowCurrentMenu("^\\s*show\\s+menu\\s+$");

    final String regex;
    GeneralRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
