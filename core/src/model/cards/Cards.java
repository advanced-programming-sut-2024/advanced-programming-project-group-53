package model.cards;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class Cards {
    private ArrayList<Card> cards;
    public Cards() {
        cards = new ArrayList<>();
    }
    public void add(Card card) {
        cards.add(card);
    }
    public void remove(Card card) {
        cards.remove(card);
    }
    public int size() {
        return this.cards.size();
    }
    public Card cardAt(int index) {
        if (index >= 0 && index < cards.size())
            return cards.get(index);
        return null;
    }
    public boolean removeCard(String cardName) {
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(cardName)) {
                cards.remove(card);
                return true;
            }
        return false;
    }

    public Card getCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) return card;
        }
        return null;
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    public int specifiedCardCounter(String cardName) {
        int counter = 0;
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(cardName))
                counter++;
        return counter;
    }

    public boolean deleteCard(String cardName, int number) {
        int count = 0;
        Iterator<Card> itr = this.cards.iterator();
        while (itr.hasNext()) {
            String name = itr.next().getName();
            if (name.equalsIgnoreCase(cardName)) {
                count++;
                itr.remove();
            }
            if (count == number) return true;
        }
        return false;
    }
    public int unitCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (!card.isSpecial()) counter++;
        }
        return counter;
    }

    public int specialCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (card.isSpecial()) counter++;
        }
        return counter;
    }
    public int heroCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (card.isHero()) counter++;
        }
        return counter;
    }

    public ArrayList<Unit> allMusters() {
        Iterator<Card> iterator = cards.iterator();
        ArrayList<Unit> allMusters = new ArrayList<>();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getAbility().equalsIgnoreCase(Ability.Muster.name())) {
                allMusters.add(Unit.getInstanceByName(card.getName()));
                iterator.remove();
            }
        }
        return allMusters;
    }

    public ArrayList<Unit> allMaidens() {
        Iterator<Card> iterator = cards.iterator();
        ArrayList<Unit> allMaidens = new ArrayList<>();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getName().equalsIgnoreCase(UnitInformation.ClanDrummondShieldmaiden.name())) {
                allMaidens.add(Unit.getInstanceByName(card.getName()));
                iterator.remove();
            }
        }
        return allMaidens;
    }

    public void makeEmpty() {
        cards = new ArrayList<>();
    }

    public void addAll(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    //For SiegeMaster ability
    public Card impenetrableFogGetter() {
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(SpecialInformation.ImpenetrableFog.name())) {
                cards.remove(card);
                return card;
            }
        return null;
    }

    //For The White Flame ability
    public Card torrentialRainGetter() {
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(SpecialInformation.TorrentialRain.name())) {
                cards.remove(card);
                return card;
            }
        return null;
    }
    //For Pure blood elf ability
    public Card bitingFrostGetter() {
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(SpecialInformation.BitingFrost.name())) {
                cards.remove(card);
                return card;
            }
        return null;
    }
    //For Commander of the red riders ability
    public ArrayList<Card> weatherCards() {
        ArrayList<Card> allWeathers = new ArrayList<>();
        for (Card card : cards)
            if (card.getType().equals(Type.Weather))
                allWeathers.addAll(cards);
        return allWeathers;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Remaining Cards:\n");
        for (Card card : cards) {
            String isHero = (card.isHero()) ? "HERO" : "";
            String isSpecial = (card.isSpecial()) ? "SPECIAL" : "UNIT";
            result.append(card.getName()).append(" ").append(card.getType()).append(" -- ").append(card.getFaction()).
                    append(" - ").append(card.getAbility()).append("_").append(isSpecial).append(card.getPower()).
                    append("_").append(isHero).append("\n");
        }
        return result.toString();
    }
}
