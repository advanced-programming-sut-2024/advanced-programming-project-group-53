package Model.game;

public enum ValidationRegexes {
    Email("(?<email>.+)@(yahoo|gmail|outlook|hotmail)\\.(com)\b"),
    Username("(?<username>([a-z]|[A-Z]|[0-9]|-)+)"),
    Password("(?<password>([a-z]|[A-Z]|[0-9]|[!@#$%^&*])+)");
    private String value;

    ValidationRegexes(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
