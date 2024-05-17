package Model;

public enum Faction {
    Monsters,
    EmpireNilfgaard,
    NorthernRealms,
    Scoiatael,
    Skellige,
    Neutral;

    @Override
    public String toString() {
        switch (this) {
            case Monsters:
                return "Monsters";
            case EmpireNilfgaard:
                return "EmpireNilfgaard";
            case NorthernRealms:
                return "NorthernRealms";
            case Scoiatael:
                return "Scoia'tael";
            case Skellige:
                return "Skellige";
            case Neutral:
                return "Neutral";
            default:
                return null;
        }
    }
}
