package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuRegex {
    veto(Pattern.compile("^\\s*veto\\s+card\\s+-n\\s+(?<cardNumber>\\d+)\\s*$")),
    inHandDeck(Pattern.compile("^\\s*in\\s+hand\\s+deck\\s+-option\\s+(?<cardNumber>\\d+)\\s*$")),
    remainingCards(Pattern.compile("^\\s*remaining\\s+cards\\s+to\\s+play\\s*$")),
    outOfPlayCards(Pattern.compile("^\\s*out\\s+of\\s+play\\s+Cards\\s*$")),
    cardsInRow(Pattern.compile("^\\s*cards\\s+in\\s+row\\s+(?<rowNumber>.+)\\s*$")),
    spellsInPlay(Pattern.compile("^\\s*spell\\s+in\\s+play\\s*$")),
    placeCard(Pattern.compile("^\\s*place\\s+card\\s+in\\s+(?<cardNumber>\\d+)\\s+row\\s+(?<rowNumber>\\d+)\\s*$")),
    showCommander(Pattern.compile("^\\s*show\\s+commander\\s*$")),
    commanderPowerPlay(Pattern.compile("^\\s*commander\\s+power\\s+play\\s*$")),
    showPlayersInfo(Pattern.compile("^\\s*show\\s+players\\s+info\\s*$")),
    showNumberOfCardsInHand(Pattern.compile("^\\s*show\\s+numbers\\s+of\\s+card\\s+in\\s+hand\\s*$")),
    showPlayerLives(Pattern.compile("^\\s*show\\s+lives\\s+play\\s*$")),
    showTurnInfo(Pattern.compile("^\\s*show\\s+turn\\s+info\\s*$")),
    showTotalScore(Pattern.compile("^\\s*show\\s+total\\s+score\\s*$")),
    passRound(Pattern.compile("^\\s*pass\\s+round\\s*$")),
    showTotalScoreOfRow(Pattern.compile("^\\s*show\\s+total\\s+score\\s+of\\s+row\\s+(?<rowNumber>\\d+)\\s*$"));
    private final Pattern pattern;

    GameMenuRegex(Pattern pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}
