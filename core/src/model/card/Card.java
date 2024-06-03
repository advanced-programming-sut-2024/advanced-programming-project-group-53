package model.card;

public abstract class Card {
    private int power;

    public int getPower() {
        return power;
    }

    public void damage(int hit) {
        this.power -= hit;
    }

    public void heal(int heal) {
        this.power += heal;
    }
}