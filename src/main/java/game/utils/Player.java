package game.utils;

import java.io.Serializable;

public class Player implements Serializable {
    private int balance;
    private Theme theme;
    private Currency currency;
    private boolean checkForUpdates;

    public Player() {
        this(100, Theme.LIGHT, Currency.USD, true);
    }

    public Player(int balance, Theme theme, Currency currency, boolean checkForUpdates) {
        this.balance = balance;
        this.theme = theme;
        this.currency = currency;
        this.checkForUpdates = checkForUpdates;
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

    public boolean getCheckForUpdates() {
        return checkForUpdates;
    }

    public void setCheckForUpdates(boolean checkForUpdates) {
        this.checkForUpdates = checkForUpdates;
    }
}
