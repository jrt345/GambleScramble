package game.utils;

public enum Currency {
    //Exchange rates as of 2/6/2022 3:32pm PST https://www.xe.com/

    USD("$",0,1.00000),
    EUR("€",1,0.873097),
    GBP("£",2,0.738912),
    AUD("A$",3,1.41275),
    CAD("C$",4,1.27503),
    CHF("CHF",5,0.925538),
    NZD("NZ$",6,1.51173),
    SGD("S$", 7,1.34550);

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
