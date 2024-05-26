package model.card;

public enum HeroName {
    Cerys,
    Draug,
    Eithne,
    Ermion,
    EsteradThyssen,
    Hjalmar,
    IsengrimFaoilitarna,
    JohnNatalis,
    Kambi,
    Kayran,
    Leshen,
    MennoCoehorn,
    MorvranVoorhis,
    MysteriousElf,
    PhilippaEilhart,
    Seasenthessis,
    TiborEggebracht,
    TrissMerigold,
    VernonRoche,
    YenneferOfVengerberg;

    @Override
    public String toString() {
        switch (this) {
            case Cerys:
                return "Cerys";
            case Draug:
                return "Draug";
            case Eithne:
                return "Eithne";
            case Ermion:
                return "Ermion";
            case EsteradThyssen:
                return "Esterad Thyssen";
            case Hjalmar:
                return "Hjalmar";
            case IsengrimFaoilitarna:
                return "Isengrim Faoilitarna";
            case JohnNatalis:
                return "John Natalis";
            case Kambi:
                return "Kambi";
            case Kayran:
                return "Kayran";
            case Leshen:
                return "Leshen";
            case MennoCoehorn:
                return "Menno Coehorn";
            case MorvranVoorhis:
                return "Morvran Voorhis";
            case MysteriousElf:
                return "Mysterious Elf";
            case PhilippaEilhart:
                return "Philippa Eilhart";
            case Seasenthessis:
                return "Seasenthessis";
            case TiborEggebracht:
                return "Tibor Eggebracht";
            case TrissMerigold:
                return "Triss Merigold";
            case VernonRoche:
                return "Vernon Roche";
            case YenneferOfVengerberg:
                return "Yennefer Of Vengerberg";
            default:
                return "";
        }
    }
}
