package Control.View.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuRegex {
    Register("^\\s*register(?=.*\\s+-u\\s+(?<Username>.+)\\s*)(?=.*\\s+-p\\s+(?<PassSection>.+)\\s*)" +
            "(?=.*\\s+-n\\s+(?<Nickname>.+)\\s*)(?=.*\\s+-e\\s+(?<Email>.+)\\s*)(\\s+-[eunp]\\s+(.+)\\s*){4}\\s*$"),
    PickQuestion("^\\s*pick\\s+question(?=.*\\s+-q\\s+(?<QuestionNumber>\\.+)\\s*)" +
            "(?=.*\\s+-a\\s+(?<Answer>\\.+)\\s*)(?=.*\\s+-c\\s+(?<ConfirmAnswer>\\.+)\\s*)" +
            "(\\s+-[qac]\\s+(\\.+)\\s*){3}\\s*$"),
    Login("^\\s*login(?=.*\\s+-a\\s+(?<Username>.+)\\s*)(?=.*\\s+-a\\s+(?<Password>.+)\\s*)" +
            "(\\s+-[up]\\s+(\\.+)\\s*){2}\\s+" +
            "-stay-logged-in\\s*$"),
    ForgetPassword("^\\s*forget\\s+password\\s+-u\\s+(?<Username>.+)\\s*$"),
    AnswerQuestion("^\\s*answer\\s+-q\\s+(?<QuestionNumber>\\d+)\\s*$"),
    SetPassword("^\\s*set\\s+password\\s+-p\\s+(?<Password>.+)\\s*$"),
    Skip("^\\s*menu\\s+enter\\s+register\\s+menu\\s*$");

    final String regex;
    LoginMenuRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
