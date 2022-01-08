package game.games;

import game.App;
import game.utils.GameData;
import game.utils.GameUtils;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class CoinToss {

    //Launches CoinToss game
    public CoinToss() {
        try {
            GameUtils.loadGame(Game.COINTOSS, "CoinToss");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays alert showing the outcome of the bet
    public CoinToss(int bet, String userOption) throws IOException {
        String[] options = {"Heads", "Tails"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 1);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 2);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/heads.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/cointoss/tails.png")),
                        50, 50, true, true)
        };

        Image image = GameUtils.imageSetter(options, computerOption, images);

        if (userOption.equals(computerOption)) {
            GameUtils.gameOutcomeAlert("CoinToss",
                    GameUtils.outcomeSetter(Game.COINTOSS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcomeAlert("CoinToss",
                    GameUtils.outcomeSetter(Game.COINTOSS, computerOption, false, outcome), image);
        }
    }
}
