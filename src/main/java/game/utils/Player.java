package game.utils;

import java.io.Serial;
import java.io.Serializable;

public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 1095516776402291379L;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (balance != player.balance) return false;
        if (checkForUpdates != player.checkForUpdates) return false;
        if (theme != player.theme) return false;
        return currency == player.currency;
    }

    @Override
    public int hashCode() {
        int result = balance;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (checkForUpdates ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "balance=" + balance +
                ", theme=" + theme +
                ", currency=" + currency +
                ", checkForUpdates=" + checkForUpdates +
                '}';
    }
}
