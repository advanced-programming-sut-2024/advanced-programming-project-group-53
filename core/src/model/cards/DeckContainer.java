package model.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.card.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeckContainer {
    private final String name;
    private final String username;
    private final Deck deck;
    private final Faction faction;
    private final Commander commander;

    public DeckContainer(String name, String username, Faction faction, Commander commander, Deck deck) {
        this.name = name;
        this.username = username;
        this.faction = faction;
        this.commander = commander;
        this.deck = deck;
    }

    public Commander getCommander() {
        return commander;
    }

    public Faction getFaction() {
        return faction;
    }

    public Deck getDeck() {
        return deck;
    }

    public String getName() {
        return name;
    }

    public void saveDeck() {
        ensureDirectoryExists(System.getProperty("user.home") + "/gwentInformation/" + username);
        String filename = name + ".json";
        File file = new File(System.getProperty("user.home") + "/gwentInformation/" + username, filename);
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDeckInAddress(String address) {
        ensureDirectoryExists(address);
        String filename = name + ".json";
        File file = new File(address, filename);
        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DeckContainer importDeck(String name, String username) {
        File file = new File(System.getProperty("user.home") + "/gwentInformation/" + username + "/" + name + ".json");
        if (!file.exists()) return null;
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(reader, DeckContainer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DeckContainer importDeckWithAddress(String address) {
        File file = new File(address);
        if (!file.exists()) return null;
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(reader, DeckContainer.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ensureDirectoryExists(String address) {
        File dir = new File(address);
        if (!dir.exists()) dir.mkdirs();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
//        deck.add(Unit.getInstanceByName(UnitInformation.GaunterODimmDarkness.name()));
//        deck.add(Unit.getInstanceByName(UnitInformation.GaunterODimmDarkness.name()));
//        deck.add(Unit.getInstanceByName(UnitInformation.KeiraMetz.name()));
//        Faction faction = Faction.NorthernRealms;
//        String username = "safari";
//        String name = "testDeckContainer";
//        Commander commander = new Commander(CommanderInformation.Foltest_LordCommanderOfTheNorth);
//        DeckContainer deckContainer = new DeckContainer(name, username, faction ,commander, deck);
//        deckContainer.saveDeckInAddress("/home/safar/GraphicPrac");
//        deckContainer.saveDeck();
        DeckContainer deckContainer = DeckContainer.importDeckWithAddress("/home/safar/GraphicPrac/testDeckContainer.json");
        System.out.println(deckContainer.getCommander().getCommanderInformation().name());
        System.out.println(deckContainer.getFaction().name());
    }
}
