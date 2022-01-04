package game.utils;

import java.io.Serializable;

public class Player implements Serializable {
    int balance;
    Theme theme;
    Currency currency;

    public Player() {
        this(100, Theme.LIGHT, Currency.USD);
    }

    public Player(int balance, Theme theme, Currency currency){
        this.balance = balance;
        this.theme = theme;
        this.currency = currency;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
