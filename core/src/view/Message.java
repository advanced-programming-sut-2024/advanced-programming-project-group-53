package view;

public enum Message {
    INVALID_COMMAND("The command that you have entered is invalid.\n"),
    INVALID_MENU("You can't enter this menu directly.\n"),
    ENTER_LOGIN_MENU("You have entered the login menu.\n"),
    ENTER_REGISTER_MENU("You have entered the register menu.\n"),
    ENTER_MAIN_MENU("You have entered the main menu.\n"),
    ENTER_GAME_MENU("You have entered the game menu.\n"),
    ENTER_PROFILE_MENU("You have entered the profile menu.\n"),
    ENTER_START_MENU("You have entered the start menu.\n"),
    LOGIN_MENU("You are in login menu.\n"),
    REGISTER_MENU("You are in register menu.\n"),
    MAIN_MENU("You are in main menu.\n"),
    GAME_MENU("You are in game menu.\n"),
    PROFILE_MENU("You are in profile menu.\n"),
    START_MENU("You are in start menu.\n"),
    NO_USER("No user with such username exists.\n"),
    INCORRECT_PASSWORD("Incorrect password!\n"),
    EMPTY_GAME_HISTORY("There is no game in your game history to show!\n"),
    INVALID_NUMBER("Invalid number.\n"),
    LOGOUT("You have logged out.\n"),
    CHANGE_USERNAME("Username changed successfully.\n"),
    CHANGE_NICKNAME("Nickname changed successfully.\n"),
    CHANGE_EMAIL("Email changed successfully.\n"),
    CHANGE_PASSWORD("Password changed successfully.\n"),
    PASSWORD_LENGTH_ERROR("Password should at least contains 8 characters.\n"),
    PASSWORD_INVALID_CHARACTERS_ERROR("Password should contains just letters,digits and special characters.\n"),
    INVALID_USERNAME("Invalid username.\n"),
    INVALID_NICKNAME("Invalid nickname.\n"),
    INVALID_EMAIL("Invalid email.\n"),
    WEAK_PASSWORD("Weak password.\n"),
    TRY_AGAIN("TRY AGAIN...\n"),
    WRONG_ANSWER("Wrong answer!\n"),
    PASSWORD_CHANGED("Password changed successfully.\nYou can login in your account.\n"),
    WRONG_NUMBER_FORMAT("Wrong number format.\n"),
    THERE_IS_NO_CARD_WITH_THIS_NAME("There is no card with this name.\n"),
    COUNT_OUT_OF_RANGE("Your count for this card is out of range.\n"),
    MORE_THAT_AVAILABILITY("This count is not available for this card.\n"),
    ADD_CARD("Card(s) added successfully.\n"),
    INVALID_FACTION("Invalid faction name.\n"),
    FACTION_SELECTED("Faction selected successfully.\n"),
    COMMANDER_SELECTED("Commander selected successfully.\n"),
    INVALID_COMMANDER_INDEX("Invalid commander index.\n"),
    YOU_HAVE_COMMANDER("You have already chosen a commander.\n"),
    YOU_HAVE_LESS("Your deck contains less cards of this type.\n"),
    CARD_DELETE("Card(s) deleted successfully.\n"),
    YOU_CAM_JUST_START("You can't pass the turn, just start the game.\n"),
    YOU_CAN_JUST_CHANGE("You can just change the turn.\n"),
    CARDS_QUANTITY_VALIDATION("You should have at least 22 unit card and at most 10 special cards.\n"),
    YOU_HAVE_NOT_SELECTED_FACTION("You haven't selected faction yet.\n"),
    YOU_HAVE_NOT_SELECTED_COMMANDER("You haven't selected a commander.\n"),
    GAME_CREATED_SUCCESSFULLY("Game created successfully.\n"),
    YOU_CANT_VETO("You can't veto cards after round 1!\n"),
    YOU_JUST_ONLY_HAVE_ONE("You just only have one card of this type.\n"),
    VETO_CARD("Veto card successfully.\n"),
    INVALID_PLAYGROUND_NUMBER("Invalid playground number.\n"),
    INVALID_RANGE("Invalid range.\n"),
    PASSWORD_IS_NOT_THE_SAME("Password is not the same.\n"),
    ILLEGAL_CHARACTER("Illegal character. You can not use \"\\\" or \"/\"in chat.\n"),;
    private final String message;

    Message(String message) {
        this.message = message;
    }
    public String message() {
        return message;
    }
}
