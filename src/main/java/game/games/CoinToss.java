package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class CoinToss extends SimpleGame {

    private static final int MULTIPLIER = 2;
    private static final String[] OPTIONS = {"Heads", "Tails"};

    public CoinToss() {
        setTitle("CoinToss");
        setDetails("Odds: 1:2, Payout: 2x");
        setPrompt("Heads or Tails?");
        setMultiplier(MULTIPLIER);
        setOptions(OPTIONS);

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            setImage(ImageUtils.CoinTossImages.HackerTheme.LOGO);
        } else {
            setImage(ImageUtils.CoinTossImages.LOGO);
        }
    }

    @Override
    String getComputerOptionResult() {
        return "Just got " + getComputerOption() + ". ";
    }

    @Override
    protected void play() throws IOException {
        updateBalance();

        Image[] images = {ImageUtils.CoinTossImages.HEADS, ImageUtils.CoinTossImages.TAILS};

        Image[] imagesH = {ImageUtils.CoinTossImages.HackerTheme.HEADS, ImageUtils.CoinTossImages.HackerTheme.TAILS};

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = getComputerOptionImage(imagesH);
        } else {
            image = getComputerOptionImage(images);
        }

        GameUtils.playGameSound(GameType.COINTOSS);
        if (getUserOption().equals(getComputerOption())) {
            showOutcome(getResultMessage(true), image);
        } else {
            showOutcome(getResultMessage(false), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
