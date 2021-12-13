package game.games;

import game.Main;
import game.utils.GameUtils;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class CoinToss {

    private static void loadCoinToss() throws IOException {
        GameUtils.loadGame(Game.COINTOSS, "CoinToss");
    }

    public CoinToss() {
        try {
            loadCoinToss();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CoinToss(int bet, String userOption) {
        String[] options = {"Heads", "Tails"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 1);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 2);

        Image[] images = {
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/cointoss/heads.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/cointoss/tails.png")),
                        50, 50, true, true)
        };

        Image image = GameUtils.imageSetter(options, computerOption, images);

        if (userOption.equals(computerOption)){
            GameUtils.gameOutcome("CoinToss",
                    GameUtils.outcomeSetter(Game.COINTOSS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcome("CoinToss",
                    GameUtils.outcomeSetter(Game.COINTOSS, computerOption, false, outcome), image);
        }

        GameUtils.updateBalance(outcome);
    }
}
