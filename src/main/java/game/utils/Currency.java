package game.utils;

public enum Currency {
    USD("$",0),
    EUR("€",1),
    GBP("£",2),
    AUD("A$",3),
    CAD("C$",4),
    NZD("NZ$",5);

    private final String symbol;
    private final int index;

    Currency(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }
}
