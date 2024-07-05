package network;

public enum Command {
    LOGIN,
    REGISTER,
    REGISTER_MESSAGE,
    QUESTION,
    QUESTION_MESSAGE,
    USERNAME_CHECK,
    FORGET_PASSWORD_MESSAGE_USER,
    FORGET_PASSWORD,
    FORGET_PASSWORD_MESSAGE_PASSWORD,
    LOGIN_MESSAGE,
    PROFILE_INFORMATION,
    PROFILE_MESSAGE,
    CHANGE_FIELD_MESSAGE,
    CHANGE_USERNAME,
    CHANGE_EMAIL,
    CHANGE_NICKNAME,
    CHANGE_PASSWORD,
    HISTORY_INFORMATION,
    HISTORY_MESSAGE,
    RANKING_INFORMATION,
    RANKING_MESSAGE,
    DECK_INFORMATION,
    DECK_MESSAGE,
    HAND_INFORMATION,
    HAND_MESSAGE,
    DISCARD_PILE_INFORMATION,
    DISCARD_PILE_MESSAGE,
    LOOK_FOR_OPPONENT,
    SAVE_DECK,
    SEND,
    SEND_MESSAGE,
    SHOW_CHAT,
    SHOW_CHAT_MESSAGE,
    USERNAME_VALIDATION,
    USERNAME_VALIDATION_MESSAGE,
    ;

    public static Command fromString(String command) {
        switch (command) {
            case "LOGIN":
                return LOGIN;
            case "REGISTER":
                return REGISTER;
            case "REGISTER_MESSAGE":
                return REGISTER_MESSAGE;
            case "QUESTION":
                return QUESTION;
            case "QUESTION_MESSAGE":
                return QUESTION_MESSAGE;
            case "USERNAME_CHECK":
                return USERNAME_CHECK;
            case "FORGET_PASSWORD_MESSAGE_USER":
                return FORGET_PASSWORD_MESSAGE_USER;
            case "FORGET_PASSWORD":
                return FORGET_PASSWORD;
            case "FORGET_PASSWORD_MESSAGE_PASSWORD":
                return FORGET_PASSWORD_MESSAGE_PASSWORD;
            case "LOGIN_MESSAGE":
                return LOGIN_MESSAGE;
            case "PROFILE_INFORMATION":
                return PROFILE_INFORMATION;
            case "PROFILE_MESSAGE":
                return PROFILE_MESSAGE;
            case "CHANGE_FIELD_MESSAGE":
                return CHANGE_FIELD_MESSAGE;
            case "CHANGE_USERNAME":
                return CHANGE_USERNAME;
            case "CHANGE_EMAIL":
                return CHANGE_EMAIL;
            case "CHANGE_NICKNAME":
                return CHANGE_NICKNAME;
            case "CHANGE_PASSWORD":
                return CHANGE_PASSWORD;
            case "HISTORY_INFORMATION":
                return HISTORY_INFORMATION;
            case "HISTORY_MESSAGE":
                return HISTORY_MESSAGE;
            case "RANKING_INFORMATION":
                return RANKING_INFORMATION;
            case "RANKING_MESSAGE":
                return RANKING_MESSAGE;
            case "DECK_INFORMATION":
                return DECK_INFORMATION;
            case "DECK_MESSAGE":
                return DECK_MESSAGE;
            case "HAND_INFORMATION":
                return HAND_INFORMATION;
            case "HAND_MESSAGE":
                return HAND_MESSAGE;
            case "DISCARD_PILE_INFORMATION":
                return DISCARD_PILE_INFORMATION;
            case "DISCARD_PILE_MESSAGE":
                return DISCARD_PILE_MESSAGE;
            case "LOOK_FOR_OPPONENT":
                return LOOK_FOR_OPPONENT;
            case "SAVE_DECK":
                return SAVE_DECK;
            case "SEND":
                return SEND;
            case "SEND_MESSAGE":
                return SEND_MESSAGE;
            case "SHOW_CHAT":
                return SHOW_CHAT;
            case "SHOW_CHAT_MESSAGE":
                return SHOW_CHAT_MESSAGE;
            case "USERNAME_VALIDATION":
                return USERNAME_VALIDATION;
            case "USERNAME_VALIDATION_MESSAGE":
                return USERNAME_VALIDATION_MESSAGE;
            default:
                return null;
        }
    }
}
