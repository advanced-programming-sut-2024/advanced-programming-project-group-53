package view.terminal.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenu {
    Veto("^\\s*veto\\s+card\\s+-n\\s+(?<CardNumber>\\d+)\\s*$"),
    InHandDeck("^\\s*in\\s+hand\\s+deck\\s+-option\\s+(?<CardNumber>\\d+)\\s*$"),
    RemainingCards("^\\s*remaining\\s+cards\\s+to\\s+play\\s*$"),
    OutOfPlayCards("^\\s*out\\s+of\\s+play\\s+Cards\\s*$"),
    CardsInRow("^\\s*cards\\s+in\\s+row\\s+(?<RowNumber>.+)\\s*$"),
    SpellsInPlay("^\\s*spell\\s+in\\s+play\\s*$"),
    PlaceCard("^\\s*place\\s+card\\s+in\\s+(?<CardNumber\\d+)\\s+row\\s+(?<RowNumber>\\d+)\\s*$"),
    ShowCommander("^\\s*show\\s+commander\\s*$"),
    CommanderPowerPlay("^\\s*commander\\s+power\\s+play\\s*$"),
    ShowPlayersInfo("^\\s*show\\s+players\\s+info\\s*$"),
    ShowNumberOfCardsInHand("^\\s*show\\s+numbers\\s+of\\s+card\\s+in\\s+hand\\s*$"),
    ShowPlayerLives("^\\s*show\\s+lives\\s+play\\s*$"),
    ShowTurnInfo("^\\s*show\\s+turn\\s+info\\s*$"),
    ShowTotalScore("^\\s*show\\s+total\\s+score\\s*$"),
    PassRound("^\\s*pass\\s+round\\s*$"),
    ShowTotalScoreOfRow("^\\s*show\\s+total\\s+score\\s+of\\s+row\\s+(?<RowNumber>\\d+)\\s*$");
    final String regex;
    GameMenu(String regex) {
        this.regex = regex;
    }
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.regex).matcher(input);
    }
}
