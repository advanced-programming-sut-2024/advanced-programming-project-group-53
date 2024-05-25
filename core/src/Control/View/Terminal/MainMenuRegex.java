package Control.View.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuRegex {
    Logout("^\\s*user\\s+logout\\s*$"),
    ChangeUsername("^\\s*change\\s+username\\s+-u\\s+(?<NewUsername>.+)\\s*$"),
    ChangeNickname("^\\s*change\\s+nickname\\s+-n\\s+(?<NewNickname>.+)\\s*$"),
    ChangeEmail("^\\s*change\\s+email\\s+-e\\s+(?<NewEmail>.+)\\s*$"),
    ChangePassword("^\\s*change\\s+password\\s+-p\\s+(?<NewPass>\\S+)\\s+-o\\s+(?<OldPass>\\S+)\\s*$"),
    //this one was on the startGame menu ,
    // but I transformed it here because probably we will use this command in main menu
    CreateGame("^\\s*create\\s+game\\s+-p2\\s+(?<Player2Username>.+)\\s*$");

    final String regex;
    MainMenuRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(regex).matcher(input);
    }
}
