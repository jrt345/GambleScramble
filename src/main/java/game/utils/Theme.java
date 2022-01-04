package game.utils;

public enum Theme {
    LIGHT("Light"),
    DARK("Dark");

    private final String string;

    Theme(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
