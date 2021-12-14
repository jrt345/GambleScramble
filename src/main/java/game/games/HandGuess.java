package game.games;

import game.Main;
import game.utils.GameUtils;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class HandGuess {
    private static void loadHandGuess() throws IOException {
        GameUtils.loadGame(Game.HANDGUESS, "HandGuess");
    }

    public HandGuess() {
        try {
            loadHandGuess();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HandGuess(int bet, String userOption) {
        String[] options = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 10);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 10);

        Image[] images = {
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand0.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand6.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand7.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand8.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand9.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/handguess/hand10.png")),
                        50, 50, true, true)
        };

        Image image = GameUtils.imageSetter(options, computerOption, images);

        if (userOption.equals(computerOption)){
            GameUtils.gameOutcome("HandGuess",
                    GameUtils.outcomeSetter(Game.HANDGUESS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcome("HandGuess",
                    GameUtils.outcomeSetter(Game.HANDGUESS, computerOption, false, outcome), image);
        }

        GameUtils.updateBalance(outcome);
    }
}
