package game.games;

public enum GameType {
    COINTOSS("CoinToss"),
    DICEROLL("DiceRoll"),
    HANDGUESS("HandGuess"),
    ROCKPAPERSCISSORS("Rock Paper Scissors");

    private final String title;

    GameType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
