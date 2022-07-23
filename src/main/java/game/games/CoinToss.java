package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class CoinToss {

    //Displays alert showing the outcome of the bet
    public static void play(int bet, String userOption) throws IOException {
        String[] options = {"Heads", "Tails"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 1);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 2);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {ImageUtils.CoinTossImages.HEADS, ImageUtils.CoinTossImages.TAILS};

        Image[] imagesH = {ImageUtils.CoinTossImages.HackerTheme.HEADS, ImageUtils.CoinTossImages.HackerTheme.TAILS};

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
             image = GameUtils.imageSetter(options, computerOption, imagesH);
        } else {
             image = GameUtils.imageSetter(options, computerOption, images);
        }

        if (userOption.equals(computerOption)) {
            GameUtils.gameOutcomeAlert("CoinToss",
                    GameUtils.outcomeSetter(GameType.COINTOSS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcomeAlert("CoinToss",
                    GameUtils.outcomeSetter(GameType.COINTOSS, computerOption, false, outcome), image);
        }
    }
}
