package game.utils;

public class Player {
    int balance;
    int streak;
    Theme theme;
    Currency currency;

    public Player() {
        this(100, 0, Theme.LIGHT, Currency.USD);
    }

    public Player(int balance, int streak, Theme theme, Currency currency){
        this.balance = balance;
        this.streak = streak;
        this.theme = theme;
        this.currency = currency;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
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
