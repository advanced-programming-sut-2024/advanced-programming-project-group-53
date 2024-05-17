package Model.Card;

public enum CommanderNickName {
    BringerOfDeath,
    CommanderOfTheRedRiders,
    CrachAnCraite,
    DaisyOfTheValley,
    DestroyerOfWorlds,
    EmperorOfNilfgaard,
    HisImperialMajesty,
    HopeOfTheAenSeidhe,
    InvaderOfTheNorth,
    KingBran,
    KingOfTemeria,
    KingOfTheWildHunt,
    LordCommanderOfTheNorth,
    PureBloodElf,
    QueenOfDolBlathanna,
    SonOfMedell,
    TheBeautiful,
    TheRelentless,
    TheSiegeMaster,
    TheSteelForged,
    TheTreacherous,
    TheWhiteFlame;

    @Override

    public String toString() {
        switch (this) {
            case BringerOfDeath:
                return "Bringer Of Death";
            case CommanderOfTheRedRiders:
                return "Commander Of Red Riders";
            case CrachAnCraite:
                return "Crach An Craite";
            case DaisyOfTheValley:
                return "Daisy Of Valley";
            case DestroyerOfWorlds:
                return "Destroyer Of Worlds";
            case EmperorOfNilfgaard:
                return "Emperor Of Nilfgaard";
            case HisImperialMajesty:
                return "His Imperial Majesty";
            case HopeOfTheAenSeidhe:
                return "Hope Of Aen Seidhe";
            case InvaderOfTheNorth:
                return "Invader Of North";
            case KingBran:
                return "King Bran";
            case KingOfTemeria:
                return "King Of Temeria";
            case KingOfTheWildHunt:
                return "King Of The Wild Hunt";
            case LordCommanderOfTheNorth:
                return "Lord Commander Of The North";
            case PureBloodElf:
                return "Pure Blood Elf";
            case QueenOfDolBlathanna:
                return "Queen Of Dol Blathanna";
            case SonOfMedell:
                return "Son Of Medell";
            case TheBeautiful:
                return "The Beautiful";
            case TheRelentless:
                return "The Relentless";
            case TheSiegeMaster:
                return "The Siege Master";
            case TheSteelForged:
                return "The Steel-Forged";
            case TheTreacherous:
                return "The Treacherous";
            case TheWhiteFlame:
                return "The White Flame";
            default:
                return "";
        }
    }

    public Faction faction() {
        switch (this) {
            case BringerOfDeath:
            case CommanderOfTheRedRiders:
            case DestroyerOfWorlds:
            case KingOfTheWildHunt:
            case TheTreacherous:
                return Faction.Monsters;
            case CrachAnCraite:
            case KingBran:
                return Faction.Skellige;
            case DaisyOfTheValley:
            case HopeOfTheAenSeidhe:
            case PureBloodElf:
            case QueenOfDolBlathanna:
            case TheBeautiful:
                return Faction.Scoiatael;
            case HisImperialMajesty:
            case InvaderOfTheNorth:
            case TheRelentless:
            case TheWhiteFlame:
            case EmperorOfNilfgaard:
                return Faction.NilfgaardianEmpire;
            case LordCommanderOfTheNorth:
            case SonOfMedell:
            case KingOfTemeria:
            case TheSiegeMaster:
            case TheSteelForged:
                return Faction.NorthernRealms;
            default:
                return Faction.Neutral;
        }
    }
}