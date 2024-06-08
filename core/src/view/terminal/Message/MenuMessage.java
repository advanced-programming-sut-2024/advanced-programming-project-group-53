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
    PASSWORD_CHANGED("Password changed successfully.\nYou can login in your account.");
    private final String message;

    MenuMessage(String message) {
        this.message = message;
    }
    public String message() {
        return message;
    }
}
