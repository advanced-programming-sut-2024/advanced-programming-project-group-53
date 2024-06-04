package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartMenuRegex {
    createGame(Pattern.compile("^\\s*create\\s+game\\s+-p2\\s+(?<player2Username>\\S+)\\s*$")),
    showFactions(Pattern.compile("^\\s*show\\s+factions\\s*$")),
    selectFaction(Pattern.compile("^\\s*select\\s+faction\\s+-f\\s+(?<factionName>\\S+)\\s*$")),
    showCards(Pattern.compile("^\\s*show\\s+cards\\s*$")),
    showDeck(Pattern.compile("^\\s*show\\s+deck\\s*$")),
    showCurrentUserInformation(Pattern.compile("^\\s*show\\s+current\\s+user\\s+information\\s*$")),
    showLeaders(Pattern.compile("^\\s*show\\s+leaders\\s*$")),
    changeTurn(Pattern.compile("^\\s*change\\s+turn\\s*$")),
    saveDeckAddress(Pattern.compile("^\\s*save\\s+deck\\s+-f\\s+(?<path>\\S+)\\s*$")),
    saveDeckName(Pattern.compile("^\\s*save\\s+deck\\s+-n\\s+(?<name>\\S+)\\s*$")),
    loadDeckAddress(Pattern.compile("^\\s*load\\s+deck\\s+-f\\s+(?<path>\\S+)\\s*$")),
    loadDeckName(Pattern.compile("^\\s*load\\s+deck\\s+-n\\s+(?<name>\\S+)\\s*$")),
    selectLeader(Pattern.compile("^\\s*select\\s+leader\\s+-l\\s+(?<leaderNumber>\\d+)\\s*$")),
    addToDeck(Pattern.compile("^\\s*add\\s+to\\s+deck\\s+-n\\s+(?<cardName>\\S+)\\s+-c\\s+(?<count>\\d+)\\s*$")),
    deleteFromDeck(Pattern.compile("^\\s*delete\\s+from\\s+deck\\s+-n\\s+(?<cardName>\\S+)\\s+-c\\s+(?<count>\\d+)\\s*$")),
    startGame(Pattern.compile("^\\s*start\\s+game\\s*$"));
    private final Pattern pattern;

    StartMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
