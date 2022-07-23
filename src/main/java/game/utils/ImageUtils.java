package game.utils;

import game.App;
import javafx.scene.image.Image;

import java.util.Objects;

public class ImageUtils {

    public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png")));
    public static final Image BANK = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/bank.png")));
    public static final Image BETS_LOG = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/betslog.png")));
    public static final Image COIN_TOSS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/cointoss.png")));
    public static final Image CRAPS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/craps.png")));
    public static final Image DICE_ROLL = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/diceroll.png")));
    public static final Image BLACK_EXCHANGE_ARROWS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows.png")));
    public static final Image WHITE_EXCHANGE_ARROWS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows-v2.png")));
    public static final Image HAND_GUESS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess.png")));
    public static final Image HAND_GUESS_V2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png")));
    public static final Image POWERBALL = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/powerball.png")));
    public static final Image ROCK_PAPER_SCISSORS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/rockpaperscissors.png")));
    public static final Image ROULETTE = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/roulette.png")));
    public static final Image SCRATCH_OFF = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/scratchoff.png")));
    public static final Image SCRIPTS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/scripts.png")));
    public static final Image STORE = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/store.png")));

    public static class HackerTheme {
        public static final Image BANK = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/bank.png")));
        public static final Image BETS_LOG = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/betslog.png")));
        public static final Image COIN_TOSS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png")));
        public static final Image CRAPS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/craps.png")));
        public static final Image DICE_ROLL = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png")));
        public static final Image EXCHANGE_ARROWS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/exchangeArrows.png")));
        public static final Image HAND_GUESS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png")));
        public static final Image POWERBALL = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/powerball.png")));
        public static final Image ROCK_PAPER_SCISSORS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png")));
        public static final Image ROULETTE = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/roulette.png")));
        public static final Image SCRATCH_OFF = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/scratchoff.png")));
        public static final Image SCRIPTS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/scripts.png")));
        public static final Image STORE = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/store.png")));
    }

    public static class CoinTossImages {
        public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/cointoss.png")), 60, 60, true, true);

        public static final Image HEADS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/heads.png")), 50, 50, true, true);
        public static final Image TAILS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/tails.png")), 50, 50, true, true);

        public static class HackerTheme {
            public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png")), 60, 60, true, true);

            public static final Image HEADS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/hackertheme/heads.png")), 50, 50, true, true);
            public static final Image TAILS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/hackertheme/tails.png")), 50, 50, true, true);
        }
    }

    public static class DiceRollImages {
        public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/diceroll.png")), 60, 60, true, true);

        public static final Image DICE_1 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice1.png")), 50, 50, true, true);
        public static final Image DICE_2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice2.png")), 50, 50, true, true);
        public static final Image DICE_3 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice3.png")), 50, 50, true, true);
        public static final Image DICE_4 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice4.png")), 50, 50, true, true);
        public static final Image DICE_5 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice5.png")), 50, 50, true, true);
        public static final Image DICE_6 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice6.png")), 50, 50, true, true);

        public static class HackerTheme {
            public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png")), 60, 60, true, true);

            public static final Image DICE_1 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
            public static final Image DICE_2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
            public static final Image DICE_3 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
            public static final Image DICE_4 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
            public static final Image DICE_5 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
            public static final Image DICE_6 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")), 50, 50, true, true);
        }
    }

    public static class HandGuessImages {
        public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess.png")), 60, 60, true, true);

        public static final Image HAND_0 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand0.png")), 50, 50, true, true);
        public static final Image HAND_1 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand1.png")), 50, 50, true, true);
        public static final Image HAND_2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand2.png")), 50, 50, true, true);
        public static final Image HAND_3 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand3.png")), 50, 50, true, true);
        public static final Image HAND_4 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand4.png")), 50, 50, true, true);
        public static final Image HAND_5 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand5.png")), 50, 50, true, true);
        public static final Image HAND_6 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand6.png")), 50, 50, true, true);
        public static final Image HAND_7 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand7.png")), 50, 50, true, true);
        public static final Image HAND_8 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand8.png")), 50, 50, true, true);
        public static final Image HAND_9 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand9.png")), 50, 50, true, true);
        public static final Image HAND_10 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand10.png")), 50, 50, true, true);

        public static class DarkTheme {
            public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png")), 60, 60, true, true);

            public static final Image HAND_0 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand0.png")), 50, 50, true, true);
            public static final Image HAND_1 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand1.png")), 50, 50, true, true);
            public static final Image HAND_2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand2.png")), 50, 50, true, true);
            public static final Image HAND_3 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand3.png")), 50, 50, true, true);
            public static final Image HAND_4 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand4.png")), 50, 50, true, true);
            public static final Image HAND_5 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand5.png")), 50, 50, true, true);
            public static final Image HAND_6 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand6.png")), 50, 50, true, true);
            public static final Image HAND_7 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand7.png")), 50, 50, true, true);
            public static final Image HAND_8 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand8.png")), 50, 50, true, true);
            public static final Image HAND_9 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand9.png")), 50, 50, true, true);
            public static final Image HAND_10 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand10.png")), 50, 50, true, true);
        }

        public static class HackerTheme {
            public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png")), 60, 60, true, true);

            public static final Image HAND_0 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand0.png")), 50, 50, true, true);
            public static final Image HAND_1 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand1.png")), 50, 50, true, true);
            public static final Image HAND_2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand2.png")), 50, 50, true, true);
            public static final Image HAND_3 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand3.png")), 50, 50, true, true);
            public static final Image HAND_4 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand4.png")), 50, 50, true, true);
            public static final Image HAND_5 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand5.png")), 50, 50, true, true);
            public static final Image HAND_6 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand6.png")), 50, 50, true, true);
            public static final Image HAND_7 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand7.png")), 50, 50, true, true);
            public static final Image HAND_8 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand8.png")), 50, 50, true, true);
            public static final Image HAND_9 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand9.png")), 50, 50, true, true);
            public static final Image HAND_10 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand10.png")), 50, 50, true, true);
        }
    }

    public static class RockPaperScissors {
        public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/rockpaperscissors.png")), 60, 60, true, true);

        public static final Image ROCK = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsR.png")), 50, 50, true, true);
        public static final Image PAPER = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsP.png")), 50, 50, true, true);
        public static final Image SCISSORS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsS.png")), 50, 50, true, true);

        public static class HackerTheme {
            public static final Image LOGO = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png")), 60, 60, true, true);

            public static final Image ROCK = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsR.png")), 50, 50, true, true);
            public static final Image PAPER = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsP.png")), 50, 50, true, true);
            public static final Image SCISSORS = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsS.png")), 50, 50, true, true);
        }
    }
}
