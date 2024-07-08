package model.cards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.card.Commander;
import model.card.Faction;

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
        ensureDirectoryExists("~/gwentInformation/" + username);
        String filename = name + ".json";
        File file = new File("~/gwentInformation/" + username, filename);
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
        File file = new File("~/gwentInformation/" + username + "/" + name + ".json");
        if (!file.exists()) return null;
        try (FileReader reader = new FileReader(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.fromJson(reader, DeckContainer.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ensureDirectoryExists(String address) {
        Path path = Paths.get(address);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
