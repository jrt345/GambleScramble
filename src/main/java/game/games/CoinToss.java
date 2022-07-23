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
    protected void play() throws IOException {
        GameUtils.updateBalance(-getBet());

        GameData.serialize();
        GameUtils.refreshData();

        setComputerOption(getRandomOption());

        setOutcome(calculateOutcome());

        if (getOutcome() > 0) {
            GameUtils.updateBalance(getOutcome());
        }

        GameData.serialize();

        Image[] images = {ImageUtils.CoinTossImages.HEADS, ImageUtils.CoinTossImages.TAILS};

        Image[] imagesH = {ImageUtils.CoinTossImages.HackerTheme.HEADS, ImageUtils.CoinTossImages.HackerTheme.TAILS};

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(OPTIONS, getComputerOption(), imagesH);
        } else {
            image = GameUtils.imageSetter(OPTIONS, getComputerOption(), images);
        }

        if (getUserOption().equals(getComputerOption())) {
            showOutcome(GameUtils.outcomeSetter(GameType.COINTOSS, getComputerOption(), true, getOutcome()), image);
        } else {
            showOutcome(GameUtils.outcomeSetter(GameType.COINTOSS, getComputerOption(), false, getOutcome()), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
