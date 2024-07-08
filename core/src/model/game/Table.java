package model.game;

import model.card.*;

import javax.sound.midi.Soundbank;
import java.util.*;

public class Table {
    private final ArrayList<Player> players;
    private final Player player1, player2;
    private Player currentPlayer;
    private Player winner;
    private Player loser;
    private ArrayList<Round> rounds;
    private GameInformation gameInformation;
    private final Playground playGround;
    private int roundNumber;
    private int turnNumber;

    public Table(Player player1, Player player2) {
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = new ArrayList<>();
        this.currentPlayer = player1;
        this.roundNumber = 1;
        this.turnNumber = 1;
        this.playGround = new Playground();
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

    public void swapCards() {
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
        playGround.changeTurn();
    }

    public GameInformation saveGame() {
        this.gameInformation = new GameInformation(player1, player2 ,winner, loser, this.rounds);
        return gameInformation;
    }

    public GameInformation getGame() {
        return gameInformation;
    }

    public Player getPlayers(int which) {
        return players.get(which);
    }

    public void newRound() {
        // TODO : make this and round better for store with Gson and preventing circular references.
        this.rounds.add(new Round(this.currentPlayer.getUser(), this.players.get(0).getPoint(),
                this.players.get(0).getPoint()));
    }

    public boolean changeTurn() {
        if (turnNumber % 2 == 0) {
            changeMonster();
            if (roundNumber == 2) changeSkellige();
            newRound();
            if (playerPoint(0) > playerPoint(1)) {
                players.get(1).decreaseHP();
            } else if (playerPoint(0) < playerPoint(1)) {
                players.get(0).decreaseHP();
            } else {
                if (players.get(0).getFaction() == Faction.NilfgaardianEmpire &&
                        players.get(1).getFaction() != Faction.NilfgaardianEmpire) {
                    players.get(1).decreaseHP();
                } else if (players.get(1).getFaction() == Faction.NilfgaardianEmpire) {
                    players.get(0).decreaseHP();
                } else {
                    players.get(0).decreaseHP();
                    players.get(1).decreaseHP();
                }
            }
            if (players.get(0).hp() == 0) {
                if (players.get(1).hp() == 0) {
                    winner = players.get(0);
                } else {
                    winner = players.get(1);
                }
                loser = players.get(0);
                saveGame();
                players.get(0).getUser().addGameInformation(gameInformation);
                players.get(1).getUser().addGameInformation(gameInformation);
                return true;
            } else if (players.get(1).hp() == 0) {
                winner = players.get(0);
                loser = players.get(1);
                saveGame();
                players.get(0).getUser().addGameInformation(gameInformation);
                players.get(1).getUser().addGameInformation(gameInformation);
                return true;
            } else {
                increaseRoundNumber();
                increaseTurnNumber();
                cleanCards();
                swapCards();
                return false;
            }
        } else {
            increaseTurnNumber();
            swapCards();
            return false;
        }
    }

    public void veto(Card card) {
        if (players.get(0).getHand().specifiedCardCounter(card.getName()) >= 2) {
            if (!players.get(0).getDeck().getCards().isEmpty()) {
                System.out.println("veto card : " + card);
                players.get(0).getHand().removeCard(card.getName());
                players.get(0).getHand().add(players.get(0).getDeck().cardAt(0));
                players.get(0).getDeck().getCards().remove(0);
            }
        }

    }

    public void cleanCards() {
        for (int i = 0; i < 6; i++) {
            if (i <= 2) {
                players.get(0).getDiscardPiles().getCards().addAll(playGround.getUnitCardsInRow(i));
                players.get(0).getDiscardPiles().getCards().add(playGround.getSpecialInRow(i));
            } else {
                players.get(1).getDiscardPiles().getCards().addAll(playGround.getUnitCardsInRow(i));
                players.get(1).getDiscardPiles().getCards().add(playGround.getSpecialInRow(i));

            }
            playGround.getUnitCardsGround().set(i, new ArrayList<>());
            playGround.getSpecials().set(i, null);
        }
        playGround.resetSpells();
    }

    public ArrayList<Card> getAllCardForPlayer(int which) {
        ArrayList<Card> result = new ArrayList<>();
        if (which == 0) {
            result.addAll(playGround.getUnitCardsInRow(0));
            result.addAll(playGround.getUnitCardsInRow(1));
            result.addAll(playGround.getUnitCardsInRow(2));
        } else {
            result.addAll(playGround.getUnitCardsInRow(3));
            result.addAll(playGround.getUnitCardsInRow(4));
            result.addAll(playGround.getUnitCardsInRow(5));
        }
        return result;
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

    public void increaseRoundNumber() {
        this.roundNumber++;
    }

    public void increaseTurnNumber() {
        this.turnNumber++;
    }

    public int getTurnNumber() {
        return turnNumber;
    }


    public int playerPoint(int which) {
        int point = 0;
        if (which == 0) point += playerPointInRow(0) + playerPointInRow(1) + playerPointInRow(2);
        else point += playerPointInRow(3) + playerPointInRow(4) + playerPointInRow(5);
        return point;
    }

    public int playerPointInRow(int row) {
        int point = 0;
        for (Card card : playGround.getUnitCardsInRow(row))
            point += card.getPower();
        return point;
    }

    public void printCurrentPlayerHand() {
        System.out.println("HAND");
        ArrayList<Card> handCards = players.get(0).getHand().getCards();
        for (Card card : handCards) {
            System.out.printf(card.getName() + " = ");
        }
        System.out.println();
    }
    //leaders Abilities
    public ArrayList<Card> executeNorthern(Commander commander) {
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

    public ArrayList<Card> executeSkellige(Commander commander) {
        Player currentPlayer = getPlayers(0);
        Player opponent = getPlayers(1);
        if (commander.getCommanderInformation() == CommanderInformation.TWO_COMMANDER_SKELLIGE_CrachAnCraite) {
            currentPlayer.shuffleDiscardPilesAndAdd();
            opponent.shuffleDiscardPilesAndAdd();
            return null;
        }
        if (commander.getCommanderInformation() == CommanderInformation.TWO_COMMANDER_SKELLIGE_KingBran) {
            //TODO : complete this commander.
            return null;
        }
        return null;
    }

    public void changeMonster() {
        if (players.get(0).getFaction() == Faction.Monsters) {
            ArrayList<Card> allCards = getAllCardForPlayer(0);
            if (!allCards.isEmpty())
                players.get(0).getHand().add(allCards.get(0));
        }
        if (players.get(1).getFaction() == Faction.Monsters) {
            ArrayList<Card> allCards = getAllCardForPlayer(0);
            if (!allCards.isEmpty())
                players.get(1).getHand().add(allCards.get(0));
        }
    }
    
    public void changeSkellige() {
        if (players.get(0).getFaction() == Faction.Skellige) {
            players.get(0).shuffleDiscardPilesAndAdd();
            if (players.get(0).getDiscardPiles().getCards().size() >= 3) {
                players.get(0).getHand().getCards().addAll(Arrays.asList(players.get(0).getDiscardPiles().cardAt(0),
                        players.get(0).getDiscardPiles().cardAt(1), players.get(0).getDiscardPiles().cardAt(2)));
                players.get(0).getDiscardPiles().getCards().remove(0);
                players.get(0).getDiscardPiles().getCards().remove(0);
                players.get(0).getDiscardPiles().getCards().remove(0);
            }
        }
        if (players.get(1).getFaction() == Faction.Skellige) {
            players.get(1).shuffleDiscardPilesAndAdd();
            if (players.get(1).getDiscardPiles().getCards().size() >= 3) {
                players.get(1).getHand().getCards().addAll(Arrays.asList(players.get(1).getDiscardPiles().cardAt(0),
                        players.get(1).getDiscardPiles().cardAt(1), players.get(1).getDiscardPiles().cardAt(2)));
                players.get(1).getDiscardPiles().getCards().remove(0);
                players.get(1).getDiscardPiles().getCards().remove(0);
                players.get(1).getDiscardPiles().getCards().remove(0);
            }
        }
    }

    public void placeDecoy(Card card, int row) {
        playGround.removeCardWithNameInRow(card, row);
        playGround.getUnitCardsInRow(row).add(new Special(SpecialInformation.Decoy));
        players.get(0).getHand().removeCard(SpecialInformation.Decoy.name());
    }
}
