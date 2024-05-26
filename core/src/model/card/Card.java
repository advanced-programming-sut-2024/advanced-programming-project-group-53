package model.card;

public abstract class Card {
    private Faction faction;
    private Type type;
    private Ability ability;
    private double power;
    private double hitpoint;
    public abstract void attack();
    public abstract void ability();
    public abstract void hit();
}