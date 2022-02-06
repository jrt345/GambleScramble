package game.utils;

public enum Currency {
    //Exchange rates as of 2/5/2022 4:39pm PST https://www.xe.com/

    USD("$",0,1.00000),
    EUR("€",1,0.873424),
    GBP("£",2,0.738749),
    AUD("A$",3,1.41380),
    CAD("C$",4,1.27650),
    NZD("NZ$",5,1.50984);

    private final String symbol;
    private final int index;
    private final double rate;

    Currency(String symbol, int index, double rate) {
        this.symbol = symbol;
        this.index = index;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }

    public double getRate() {
        return rate;
    }
}
