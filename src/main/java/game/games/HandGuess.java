package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class HandGuess {

    //Displays alert showing the outcome of the bet
    public static void play(int bet, String userOption) throws IOException {
        String[] options = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 10);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 10);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {
                ImageUtils.HandGuessImages.HAND_0,
                ImageUtils.HandGuessImages.HAND_1,
                ImageUtils.HandGuessImages.HAND_2,
                ImageUtils.HandGuessImages.HAND_3,
                ImageUtils.HandGuessImages.HAND_4,
                ImageUtils.HandGuessImages.HAND_5,
                ImageUtils.HandGuessImages.HAND_6,
                ImageUtils.HandGuessImages.HAND_7,
                ImageUtils.HandGuessImages.HAND_8,
                ImageUtils.HandGuessImages.HAND_9,
                ImageUtils.HandGuessImages.HAND_10
        };

        Image[] imagesD = {
                ImageUtils.HandGuessImages.DarkTheme.HAND_0,
                ImageUtils.HandGuessImages.DarkTheme.HAND_1,
                ImageUtils.HandGuessImages.DarkTheme.HAND_2,
                ImageUtils.HandGuessImages.DarkTheme.HAND_3,
                ImageUtils.HandGuessImages.DarkTheme.HAND_4,
                ImageUtils.HandGuessImages.DarkTheme.HAND_5,
                ImageUtils.HandGuessImages.DarkTheme.HAND_6,
                ImageUtils.HandGuessImages.DarkTheme.HAND_7,
                ImageUtils.HandGuessImages.DarkTheme.HAND_8,
                ImageUtils.HandGuessImages.DarkTheme.HAND_9,
                ImageUtils.HandGuessImages.DarkTheme.HAND_10
        };

        Image[] imagesH = {
                ImageUtils.HandGuessImages.HackerTheme.HAND_0,
                ImageUtils.HandGuessImages.HackerTheme.HAND_1,
                ImageUtils.HandGuessImages.HackerTheme.HAND_2,
                ImageUtils.HandGuessImages.HackerTheme.HAND_3,
                ImageUtils.HandGuessImages.HackerTheme.HAND_4,
                ImageUtils.HandGuessImages.HackerTheme.HAND_5,
                ImageUtils.HandGuessImages.HackerTheme.HAND_6,
                ImageUtils.HandGuessImages.HackerTheme.HAND_7,
                ImageUtils.HandGuessImages.HackerTheme.HAND_8,
                ImageUtils.HandGuessImages.HackerTheme.HAND_9,
                ImageUtils.HandGuessImages.HackerTheme.HAND_10
        };


        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(options, computerOption, imagesH);
        } else if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE) {
            image = GameUtils.imageSetter(options, computerOption, imagesD);

        } else {
            image = GameUtils.imageSetter(options, computerOption, images);
        }

        if (userOption.equals(computerOption)) {
            GameUtils.gameOutcomeAlert("HandGuess",
                    GameUtils.outcomeSetter(GameType.HANDGUESS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcomeAlert("HandGuess",
                    GameUtils.outcomeSetter(GameType.HANDGUESS, computerOption, false, outcome), image);
        }
    }
}
