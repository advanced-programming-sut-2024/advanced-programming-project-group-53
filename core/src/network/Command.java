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
    RANKING,
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
    EMAIL_VALIDATION,
    EMAIL_VALIDATION_MESSAGE,
    REGISTER_VALIDATION,
    REGISTER_VALIDATION_MESSAGE,
    REQUEST_IN,
    REQUEST_IN_MESSAGE,
    REQUEST_OUT,
    REQUEST_OUT_MESSAGE,
    FRIEND,
    FRIEND_MESSAGE,
    FRIEND_REQUEST,
    FRIEND_REQUEST_MESSAGE,
    SEND_REQUEST,
    SEND_REQUEST_MESSAGE,
    SEARCH_RANDOM,
    EMPTY,
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
            case "RANKING":
                return RANKING;
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
            case "EMAIL_VALIDATION":
                return EMAIL_VALIDATION;
            case "EMAIL_VALIDATION_MESSAGE":
                return EMAIL_VALIDATION_MESSAGE;
            case "REGISTER_VALIDATION":
                return REGISTER_VALIDATION;
            case "REGISTER_VALIDATION_MESSAGE":
                return REGISTER_VALIDATION_MESSAGE;
            case "REQUEST_IN":
                return REQUEST_IN;
            case "REQUEST_IN_MESSAGE":
                return REQUEST_IN_MESSAGE;
            case "REQUEST_OUT":
                return REQUEST_OUT;
            case "REQUEST_OUT_MESSAGE":
                return REQUEST_OUT_MESSAGE;
            case "FRIEND":
                return FRIEND;
            case "FRIEND_MESSAGE":
                return FRIEND_MESSAGE;
            case "FRIEND_REQUEST":
                return FRIEND_REQUEST;
            case "FRIEND_REQUEST_MESSAGE":
                return FRIEND_REQUEST_MESSAGE;
            case "SEND_REQUEST":
                return SEND_REQUEST;
            case "SEND_REQUEST_MESSAGE":
                return SEND_REQUEST_MESSAGE;
            case "SEARCH_RANDOM":
                return SEARCH_RANDOM;
            case "EMPTY":
                return EMPTY;
            default:
                return null;
        }
    }
}
