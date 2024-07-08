package model.card;

public class Card {
    private int power;

    public boolean isSpecial() {
        return false;
    }

    public String getName() {
        return null;
    }

    public Type getType() {
        return null;
    }

    public Faction getFaction() {
        return null;
    }

    public boolean isHero() {
        return false;
    }

    public String getAbility() {
        return null;
    }

    public String address() {
        return null;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void damage(int hit) {
        this.power -= hit;
    }

    public void increasePower(int heal) {
        this.power += heal;
    }

    public int power() {
        return 0;
    }

    @Override
    public String toString() {
        String isHero = (this.isHero()) ? "HERO" : "";
        String isSpecial = (this.isSpecial()) ? "SPECIAL" : "UNIT";
        return this.getName() + " " + this.getType() + " -- " + this.getFaction() +
                " - " + this.getAbility() + "-" + this.getPower() + "---->" + isHero + "_" + isSpecial;
    }


}