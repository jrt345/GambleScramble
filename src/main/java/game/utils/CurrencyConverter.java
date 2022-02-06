package game.utils;

public class CurrencyConverter {

    //Returns currency enum based on string
    public static Currency stringToCurrency(String string) {
        return switch (string) {
            case "USD" -> Currency.USD;
            case "EUR" -> Currency.EUR;
            case "GBP" -> Currency.GBP;
            case "AUD" -> Currency.AUD;
            case "CAD" -> Currency.CAD;
            case "CHF" -> Currency.CHF;
            case "NZD" -> Currency.NZD;
            case "SGD" -> Currency.SGD;
            default -> null;
        };
    }

    //Converts balance to USD and returns new balance
    private static double convertToUSD(int balance, Currency currentCurrency) {
        return switch (currentCurrency) {
            case USD -> balance/Currency.USD.getRate();
            case EUR -> balance/Currency.EUR.getRate();
            case GBP -> balance/Currency.GBP.getRate();
            case AUD -> balance/Currency.AUD.getRate();
            case CAD -> balance/Currency.CAD.getRate();
            case CHF -> balance/Currency.CHF.getRate();
            case NZD -> balance/Currency.NZD.getRate();
            case SGD -> balance/Currency.SGD.getRate();
        };
    }

    //Converts balance from USD and returns new balance
    private static double convertFromUSD(double usdBalance, Currency selectedCurrency) {
        return switch (selectedCurrency) {
            case USD -> usdBalance*Currency.USD.getRate();
            case EUR -> usdBalance*Currency.EUR.getRate();
            case GBP -> usdBalance*Currency.GBP.getRate();
            case AUD -> usdBalance*Currency.AUD.getRate();
            case CAD -> usdBalance*Currency.CAD.getRate();
            case CHF -> usdBalance*Currency.CHF.getRate();
            case NZD -> usdBalance*Currency.NZD.getRate();
            case SGD -> usdBalance*Currency.SGD.getRate();
        };
    }

    public static int convertCurrency(int balance, Currency currentCurrency, Currency selectedCurrency) {
        double usdBalance = convertToUSD(balance, currentCurrency);
        return (int) convertFromUSD(usdBalance, selectedCurrency);
    }
}
