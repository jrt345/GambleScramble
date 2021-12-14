package game.games;

import game.Main;
import game.utils.GameUtils;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class DiceRoll {
    private static void loadDiceRoll() throws IOException {
        GameUtils.loadGame(Game.DICEROLL, "DiceRoll");
    }

    public DiceRoll() {
        try {
            loadDiceRoll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DiceRoll(int bet, String userOption) {
        String[] options = {"1", "2", "3", "4", "5", "6"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 5);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 5);

        Image[] images = {
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/diceroll/dice6.png")),
                        50, 50, true, true)
        };

        Image image = GameUtils.imageSetter(options, computerOption, images);

        if (userOption.equals(computerOption)){
            GameUtils.gameOutcome("DiceRoll",
                    GameUtils.outcomeSetter(Game.DICEROLL, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcome("DiceRoll",
                    GameUtils.outcomeSetter(Game.DICEROLL, computerOption, false, outcome), image);
        }

        GameUtils.updateBalance(outcome);
    }
}
