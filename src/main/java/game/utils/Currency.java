package game.utils;

public enum Currency {
    //Exchange rates as of 1/3/2022 8:42pm PST https://www.xe.com/

    USD("$",0,1.00000),
    EUR("€",1,0.885499),
    GBP("£",2,0.742805),
    AUD("A$",3,1.39048),
    CAD("C$",4,1.27618),
    NZD("NZ$",5,1.47423);

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
