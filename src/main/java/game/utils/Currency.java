package game.utils;

public enum Currency {
    //Exchange rates as of 1/8/2022 11:00pm PST https://www.xe.com/

    USD("$",0,1.00000),
    EUR("€",1,0.880107),
    GBP("£",2,0.735820),
    AUD("A$",3,1.39246),
    CAD("C$",4,1.26451),
    NZD("NZ$",5,1.47461);

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
