package model.game;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class Table {
    private final ArrayList<Player> players;
    private final Player player1, player2;
    private Player currentPlayer;
    private Player winner;
    private Player loser;
    private ArrayList<Round> rounds;
    private GameInformation gameInformation;
    private Playground playGround;
    private int roundNumber;

    public Table(Player player1, Player player2) {
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = new ArrayList<>();
        this.currentPlayer = player1;
        this.roundNumber = 1;
        preGameConfigs();
    }

    public void preGameConfigs() {
        if (player1.getFaction() == Faction.Scoiatael)
            if (player2.getFaction() == Faction.Scoiatael) {
                boolean choice = (new Random()).nextBoolean();
                if (choice)
                    changeCurrentPlayer();
            }
        else if (player2.getFaction() == Faction.Scoiatael)
            changeCurrentPlayer();
        handCardRandomSelection();
    }

    public void handCardRandomSelection() {
        players.get(0).initialHandSelection();
        players.get(1).initialHandSelection();
    }
    public void changeCurrentPlayer() {
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public boolean putCard(Card card, int row) {
        //TODO : fill this one.
        ArrayList<Card> unitCardsInRow = playGround.getUnitCardsInRow(row);
        if (unitCardsInRow.size() == 9)
            return false;
        this.playGround.placeNoneSpyUnit(card, row, getPlayers(0), getPlayers(1));
        return true;
    }

    public void changeTurn() {
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public GameInformation saveGame() {
        //for end of the game. probably with Gson and saving with random naming .
        //TODO : specify attributes that are needed for save a game correctly .
        this.gameInformation = new GameInformation(winner, loser, this.rounds);
        return null;
    }

    public GameInformation getGame() {
        return gameInformation;
    }

    public Player getPlayers(int which) {
        return players.get(which - 1);
    }

    public void newRound(Round round) {
        // TODO : make this and round better for store with Gson and preventing circular references.
        this.rounds.add(new Round(this.currentPlayer.getUser(), this.players.get(0).getPoint(),
                this.players.get(1).getPoint()));
    }

    public Playground getPlayGround() {
        return playGround;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }


    //leaders Abilities
    public ArrayList<Card> executeNorthernLeader(Commander commander) {
        Player currentPlayer = getPlayers(0);
        Player opponent = getPlayers(1);
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_TheSiegeMaster) {
            if (currentPlayer.getDeck().impenetrableFogGetter() == null) {
                if (currentPlayer.getHand().impenetrableFogGetter() != null)
                    playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                    ImpenetrableFog.name())), currentPlayer,
                                    opponent);
            } else
                playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                ImpenetrableFog.name())), currentPlayer,
                                opponent);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_TheSteelForged) {
            playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                            ClearWeather.name())),
                            currentPlayer, opponent);
            playGround.setWeatherConditionAt(false, 0);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_KingOfTemeria) {
            if (playGround.haveCommandersHornAt(2)) return null;
            playGround.commandersHornAbility(2);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_LordCommanderOfTheNorth) {
            playGround.removeCardsWithMaxPowerScorch(3, currentPlayer, opponent);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_SonOfMedell)
            playGround.removeCardsWithMaxPowerScorch(3, currentPlayer, opponent);
        return null;
    }

    public ArrayList<Card> executeNilfgaardian(Commander commander) {
        Player currentPlayer = getPlayers(0);
        Player opponent = getPlayers(1);
        if (commander.getCommanderInformation() == CommanderInformation.EmhyrVarEmreis_TheWhiteFlame) {
            if (currentPlayer.getDeck().torrentialRainGetter() == null) {
                if (currentPlayer.getHand().torrentialRainGetter() != null)
                    playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                    TorrentialRain.name())), currentPlayer,
                                    opponent);
            } else
                playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                TorrentialRain.name())), currentPlayer,
                                opponent);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.EmhyrVarEmreis_HisImperialMajesty) {
            opponent.getHand().shuffle();
            ArrayList<Card> cardsToShow = new ArrayList<>();
            cardsToShow.add(opponent.getHand().cardAt(0));
            cardsToShow.add(opponent.getHand().cardAt(1));
            cardsToShow.add(opponent.getHand().cardAt(2));
            opponent.getHand().shuffle();
            return cardsToShow;
        }
        if (commander.getCommanderInformation() == CommanderInformation.EmhyrVarEmreis_EmperorOfNilfgaard) {
            //TODO : this commander cancel the opponent commander ability and I should hard code for this.
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.EmhyrVarEmreis_TheRelentless)
            return opponent.getDiscardPiles().getCards();
        if (commander.getCommanderInformation() == CommanderInformation.EmhyrVarEmreis_InvaderOfTheNorth) {
            currentPlayer.invaderAbility();
            opponent.invaderAbility();
            return null;
        }
        return null;
    }

    public ArrayList<Card> executeMonsters(Commander commander) {
        Player currentPlayer = getPlayers(0);
        Player opponent = getPlayers(1);
        if (commander.getCommanderInformation() == CommanderInformation.EredinBreaccGlas_BringerOfDeath) {
            if (playGround.haveCommandersHornAt(0)) return null;
            playGround.commandersHornAbility(0);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.Foltest_KingOfTemeria)
            return currentPlayer.getDiscardPiles().getCards();
        if (commander.getCommanderInformation() == CommanderInformation.EredinBreaccGlas_DestroyerOfWorlds) {
            ArrayList<Card> cardsToShow = new ArrayList<>();
            cardsToShow.addAll(playGround.getUnitCardsInRow(0));
            cardsToShow.addAll(playGround.getUnitCardsInRow(1));
            cardsToShow.addAll(playGround.getUnitCardsInRow(2));
            return cardsToShow;
        }
        if (commander.getCommanderInformation() == CommanderInformation.EredinBreaccGlas_CommanderOfTheRedRiders)
            return currentPlayer.getDeck().weatherCards();
        if (commander.getCommanderInformation() == CommanderInformation.EredinBreaccGlas_TheTreacherous) {
            playGround.spyCardBuff();
            return null;
        }
        return null;
    }

    public ArrayList<Card> executeScoiatael(Commander commander) {
        Player currentPlayer = getPlayers(0);
        Player opponent = getPlayers(1);
        if (commander.getCommanderInformation() == CommanderInformation.FrancescaFindabair_QueenOfDolBlathanna) {
            if (playGround.suitableToScorch(5))
                playGround.simpleRemoveCardWithMaxPower(6, currentPlayer, opponent);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.FrancescaFindabair_TheBeautiful) {
            if (playGround.haveCommandersHornAt(1)) return null;
            playGround.commandersHornAbility(1);
        }
        //this commander ability won't execute, but in the first of the match.
        if (commander.getCommanderInformation() == CommanderInformation.FrancescaFindabair_DaisyOfTheValley)
            return null;
        if (commander.getCommanderInformation() == CommanderInformation.FrancescaFindabair_PureBloodElf) {
            if (currentPlayer.getDeck().bitingFrostGetter() == null) {
                if (currentPlayer.getHand().bitingFrostGetter() != null)
                    playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                    BitingFrost.name())), currentPlayer,
                                    opponent);
            } else
                playGround.placeSpellCard(Objects.requireNonNull(Special.getInstanceByName(SpecialInformation.
                                BitingFrost.name())), currentPlayer,
                                opponent);
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.FrancescaFindabair_HopeOfTheAenSeidhe) {
            //TODO : fill this for this commander about agile unit.
            return null;
        }
        return null;
    }
}
