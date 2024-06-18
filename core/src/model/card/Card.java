package model.card;

public abstract class Card {
    private int power;

    abstract public boolean isSpecial();

    abstract public String getName();

    abstract public Type getType();

    abstract public Faction getFaction();

    abstract public boolean isHero();

    abstract public String getAbility();

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void damage(int hit) {
        this.power -= hit;
    }

    public void heal(int heal) {
        this.power += heal;
    }
}