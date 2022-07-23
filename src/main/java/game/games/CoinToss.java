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

        String computerOption = getRandomOption();

        int outcome = getOutcome(computerOption);

        if (outcome > 0) {
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {ImageUtils.CoinTossImages.HEADS, ImageUtils.CoinTossImages.TAILS};

        Image[] imagesH = {ImageUtils.CoinTossImages.HackerTheme.HEADS, ImageUtils.CoinTossImages.HackerTheme.TAILS};

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(OPTIONS, computerOption, imagesH);
        } else {
            image = GameUtils.imageSetter(OPTIONS, computerOption, images);
        }

        if (getUserOption().equals(computerOption)) {
            showOutcome(GameUtils.outcomeSetter(GameType.COINTOSS, computerOption, true, outcome), image);
        } else {
            showOutcome(GameUtils.outcomeSetter(GameType.COINTOSS, computerOption, false, outcome), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
