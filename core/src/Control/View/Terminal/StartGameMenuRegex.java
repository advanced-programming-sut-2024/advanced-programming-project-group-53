package Control.View.Terminal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartGameMenuRegex {
    ShowFactions("^\\s*show\\s+factions\\s*$"),
    SelectFaction("^\\s*select\\s+faction\\s+-f\\s+(?<FactionName>.+)\\s*$"),
    ShowCards("^\\s*show\\s+cards\\s*$"),
    ShowDeck("^\\s*show\\s+deck\\s*$"),
    //a little different from project Doc
    ShowCurrentUserInformation("^\\s*show\\s+current\\s+user\\s+information\\s*$"),
    ShowLeaders("^\\s*show\\s+leaders\\s*$"),
    StartGame("^\\s*start\\s+game\\s*$"),
    ChangeTurn("^\\s*change\\s+turn\\s*$"),
    SaveDeckAddress("^\\s*save\\s+deck\\s+-f\\s+(?<Path>.+)\\s*$"),
    SaveDeckName("^\\s*save\\s+deck\\s+-n\\s+(?<Name>.+)\\s*$"),
    LoadDeckAddress("^\\s*load\\s+deck\\s+-f\\s+(?<Path>.+)\\s*$"),
    LoadDeckName("^\\s*load\\s+deck\\s+-n\\s+(?<Name>.+)\\s*$"),
    SelectLeader("^\\s*select\\s+leader\\s+-l\\s+(?<LeaderNumber>\\d+)\\s*$"),
    AddToDeck("^\\s*add\\s+to\\s+deck\\s+-n\\s+(?<CardName>\\S+)\\s+-c\\s+(?<Count>\\d+)\\s*$"),
    DeleteFromDeck("^\\s*delete\\s+from\\s+deck\\s+-n\\s+(?<CardName>\\S+)\\s+-c\\s+(?<Count>\\d+)\\s*$")
    ;
    final String regex;
    StartGameMenuRegex(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(regex).matcher(input);
    }
}
