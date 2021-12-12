package game.utils;

public enum Currency {
    USD("$"),
    EUR("€"),
    GBP("£"),
    AUD("A$"),
    CAD("C$"),
    NZD("NZ$");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
