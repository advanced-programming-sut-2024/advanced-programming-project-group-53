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

    @Override
    public String toString() {
        String isHero = (this.isHero()) ? "HERO" : "";
        String isSpecial = (this.isSpecial()) ? "SPECIAL" : "UNIT";
        return this.getName() + " " + this.getType() + " -- " + this.getFaction() +
                " - " + this.getAbility() + "-" + this.getPower() + "---->" + isHero + "_" + isSpecial;
    }
}