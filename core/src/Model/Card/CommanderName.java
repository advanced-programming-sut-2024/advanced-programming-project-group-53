package Model.Card;

public enum CommanderName {
    EredinBreaccGlas,
    EmhyrVarEmreis,
    FrancescaFindabair,
    Foltest,
    None;

    @Override
    public String toString() {
        switch (this) {
            case EredinBreaccGlas:
                return "Eredin Bréacc Glas";
            case EmhyrVarEmreis:
                return "Emhyr Var Emreis";
            case FrancescaFindabair:
                return "Francesca Findabair";
            case Foltest:
                return "Foltest";
            default:
                return "";
        }
    }

    public Faction faction() {
        switch (this) {
            case EredinBreaccGlas:
                return Faction.Monsters;
            case EmhyrVarEmreis:
                return Faction.NilfgaardianEmpire;
            case FrancescaFindabair:
                return Faction.Scoiatael;
            case Foltest:
                return Faction.NorthernRealms;
            default:
                return Faction.Neutral;
        }
    }

    public static CommanderName finder(Faction faction) {
        switch (faction) {
            case Monsters:
                return CommanderName.EredinBreaccGlas;
            case NilfgaardianEmpire:
                return CommanderName.EmhyrVarEmreis;
            case Scoiatael:
                return CommanderName.FrancescaFindabair;
            case NorthernRealms:
                return CommanderName.Foltest;
            default:
                return CommanderName.None;
        }
    }
}
