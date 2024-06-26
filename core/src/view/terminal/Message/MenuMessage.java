package view.terminal.Message;

public enum MenuMessage {
    INVALID_COMMAND("The command that you have entered is invalid."),
    INVALID_MENU("You can't enter this menu directly."),
    ENTER_LOGIN_MENU("You have entered the login menu."),
    ENTER_REGISTER_MENU("You have entered the register menu."),
    ENTER_MAIN_MENU("You have entered the main menu."),
    ENTER_GAME_MENU("You have entered the game menu."),
    ENTER_PROFILE_MENU("You have entered the profile menu."),
    ENTER_START_MENU("You have entered the start menu."),
    LOGIN_MENU("You are in login menu."),
    MAIN_MENU("You are in main menu."),
    GAME_MENU("You are in game menu."),
    PROFILE_MENU("You are in profile menu."),
    START_MENU("You are in start menu."),
    NO_USER("No user with such username exist."),
    INCORRECT_PASSWORD("Incorrect password!"),
    EMPTY_GAME_HISTORY("There is no game in your game history to show!"),
    INVALID_NUMBER("Invalid number."),
    LOGOUT("You logged out."),
    CHANGE_USERNAME("Username changed successfully."),
    CHANGE_NICKNAME("Nickname changed successfully."),
    CHANGE_EMAIL("Email changed successfully."),
    CHANGE_PASSWORD("Password changed successfully."),
    PASSWORD_LENGTH_ERROR("Password should at least contains 8 characters."),
    PASSWORD_INVALID_CHARACTERS_ERROR("Password should contains just letters,digits and special characters."),
    INVALID_USERNAME("Invalid username."),
    INVALID_NICKNAME("Invalid nickname."),
    INVALID_EMAIL("Invalid email."),
    WEAK_PASSWORD("Weak password."),
    TRY_AGAIN("TRY AGAIN..."),
    WRONG_ANSWER("Wrong answer!"),
    PASSWORD_CHANGED("Password changed successfully.\nYou can login in your account."),
    WRONG_NUMBER_FORMAT("Wrong number format."),
    THERE_IS_NO_CARD_WITH_THIS_NAME("There is no card with this name."),
    COUNT_OUT_OF_RANGE("Your count for this card is out of range."),
    MORE_THAT_AVAILABILITY("This count is not available for this card."),
    ADD_CARD("Card(s) added successfully."),
    INVALID_FACTION("Invalid faction name."),
    FACTION_SELECTED("Faction selected successfully."),
    COMMANDER_SELECTED("Commander selected successfully."),
    INVALID_COMMANDER_INDEX("Invalid commander index."),
    YOU_HAVE_COMMANDER("You have already chosen a commander."),
    YOU_HAVE_LESS("Your deck contains less cards of this type."),
    CARD_DELETE("Card(s) deleted successfully."),
    YOU_CAM_JUST_START("You can't pass the turn, just start the game"),
    YOU_CAN_JUST_CHANGE("You can just change the turn."),
    CARDS_QUANTITY_VALIDATION("You should have at least 22 unit card and at most 10 special cards."),
    YOU_HAVE_NOT_SELECTED_FACTION("You haven't selected faction yet."),
    YOU_HAVE_NOT_SELECTED_COMMANDER("You haven't selected a commander."),
    GAME_CREATED_SUCCESSFULLY("Game created successfully.");
    private final String message;

    MenuMessage(String message) {
        this.message = message;
    }
    public String message() {
        return message;
    }
}
