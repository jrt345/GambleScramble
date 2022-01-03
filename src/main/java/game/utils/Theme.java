package game.utils;

public enum Theme {
    LIGHT("Light"),
    DARK("Dark"),
    LIGHT_MINIMAL("Minimal (Light)"),
    DARK_MINIMAL("Minimal (Dark)");

    private final String string;

    Theme(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

}
