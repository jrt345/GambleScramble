package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class HandGuess extends SimpleGame {

    private static final int MULTIPLIER = 10;
    private static final String[] OPTIONS = {"0","1","2","3","4","5","6","7","8","9","10"};

    public HandGuess() {
        setTitle("HandGuess");
        setDetails("Odds: 1:11, Payout: 10x");
        setPrompt("Choose a number between 0-10");
        setMultiplier(MULTIPLIER);
        setOptions(OPTIONS);

        if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE){
            setImage(ImageUtils.HandGuessImages.DarkTheme.LOGO);
        } else if (Controller.getPlayer().getTheme() == Theme.HACKER){
            setImage(ImageUtils.HandGuessImages.HackerTheme.LOGO);
        } else {
            setImage(ImageUtils.HandGuessImages.LOGO);
        }
    }

    @Override
    String getComputerOptionResult() {
        if (getComputerOption().equals("0")) {
            return "I have no fingers up. ";
        } else if (getComputerOption().equals("1")) {
            return "I have " + getComputerOption() + " finger up. ";
        } else {
            return "I have " + getComputerOption() + " fingers up. ";
        }
    }

    @Override
    protected void play() throws IOException {
        updateBalance();

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
            image = getComputerOptionImage(imagesH);
        } else if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE) {
            image = getComputerOptionImage(imagesD);
        } else {
            image = getComputerOptionImage(images);
        }

        GameUtils.playGameSound(GameType.HANDGUESS);
        if (getUserOption().equals(getComputerOption())) {
            showOutcome(getResultMessage(true), image);
        } else {
            showOutcome(getResultMessage(false), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
