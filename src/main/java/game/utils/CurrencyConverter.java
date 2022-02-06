package game.utils;

public class CurrencyConverter {

    //Returns currency enum based on string
    public static Currency stringToCurrency(String string){
        Currency currency = null;

        if (string.equals("USD")){
            currency = Currency.USD;
        }
        if (string.equals("EUR")){
            currency = Currency.EUR;
        }
        if (string.equals("GBP")){
            currency = Currency.GBP;
        }
        if (string.equals("AUD")){
            currency = Currency.AUD;
        }
        if (string.equals("CAD")){
            currency = Currency.CAD;
        }
        if (string.equals("CHF")){
            currency = Currency.CHF;
        }
        if (string.equals("NZD")){
            currency = Currency.NZD;
        }
        if (string.equals("SGD")){
            currency = Currency.SGD;
        }

        return currency;
    }

    //Converts balance to USD and returns new balance
    private static double convertToUSD(int balance, Currency currentBalance) {
        double usdBalance = 0;

        if (currentBalance.equals(Currency.USD)){
            usdBalance = balance/Currency.USD.getRate();
        }
        if (currentBalance.equals(Currency.EUR)){
            usdBalance = balance/Currency.EUR.getRate();
        }
        if (currentBalance.equals(Currency.GBP)){
            usdBalance = balance/Currency.GBP.getRate();
        }
        if (currentBalance.equals(Currency.AUD)){
            usdBalance = balance/Currency.AUD.getRate();
        }
        if (currentBalance.equals(Currency.CAD)){
            usdBalance = balance/Currency.CAD.getRate();
        }
        if (currentBalance.equals(Currency.CHF)){
            usdBalance = balance/Currency.CHF.getRate();
        }
        if (currentBalance.equals(Currency.NZD)){
            usdBalance = balance/Currency.NZD.getRate();
        }
        if (currentBalance.equals(Currency.SGD)){
            usdBalance = balance/Currency.SGD.getRate();
        }

        return usdBalance;
    }

    //Converts balance from USD and returns new balance
    private static double convertFromUSD(double usdBalance, Currency selectedCurrency){
        double newBalance = 0;

        if (selectedCurrency.equals(Currency.USD)){
            newBalance = usdBalance*Currency.USD.getRate();
        }
        if (selectedCurrency.equals(Currency.EUR)){
            newBalance = usdBalance*Currency.EUR.getRate();
        }
        if (selectedCurrency.equals(Currency.GBP)){
            newBalance = usdBalance*Currency.GBP.getRate();
        }
        if (selectedCurrency.equals(Currency.AUD)){
            newBalance = usdBalance*Currency.AUD.getRate();
        }
        if (selectedCurrency.equals(Currency.CAD)){
            newBalance = usdBalance*Currency.CAD.getRate();
        }
        if (selectedCurrency.equals(Currency.CHF)){
            newBalance = usdBalance*Currency.CHF.getRate();
        }
        if (selectedCurrency.equals(Currency.NZD)){
            newBalance = usdBalance*Currency.NZD.getRate();
        }
        if (selectedCurrency.equals(Currency.SGD)){
            newBalance = usdBalance*Currency.SGD.getRate();
        }

        return newBalance;
    }

    public static int convertCurrency(int balance, Currency currentCurrency, Currency selectedCurrency) {
        double usdBalance = convertToUSD(balance, currentCurrency);
        return (int) convertFromUSD(usdBalance, selectedCurrency);
    }
}
