package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class DiceRoll extends SimpleGame {

    private static final int MULTIPLIER = 5;
    private static final String[] OPTIONS = {"1","2","3","4","5","6"};

    public DiceRoll() {
        setTitle("DiceRoll");
        setDetails("Odds: 1:6, Payout: 5x");
        setPrompt("Choose a number between 1-6");
        setMultiplier(MULTIPLIER);
        setOptions(OPTIONS);

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            setImage(ImageUtils.DiceRollImages.HackerTheme.LOGO);
        } else {
            setImage(ImageUtils.DiceRollImages.LOGO);
        }
    }

    @Override
    protected void play() throws IOException {
        GameUtils.updateBalance(-getBet());

        GameData.serialize();
        GameUtils.refreshData();

        String computerOption = getRandomOption();

        int outcome = getOutcome(computerOption);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {
                ImageUtils.DiceRollImages.DICE_1,
                ImageUtils.DiceRollImages.DICE_2,
                ImageUtils.DiceRollImages.DICE_3,
                ImageUtils.DiceRollImages.DICE_4,
                ImageUtils.DiceRollImages.DICE_5,
                ImageUtils.DiceRollImages.DICE_6
        };

        Image[] imagesH = {
                ImageUtils.DiceRollImages.HackerTheme.DICE_1,
                ImageUtils.DiceRollImages.HackerTheme.DICE_2,
                ImageUtils.DiceRollImages.HackerTheme.DICE_3,
                ImageUtils.DiceRollImages.HackerTheme.DICE_4,
                ImageUtils.DiceRollImages.HackerTheme.DICE_5,
                ImageUtils.DiceRollImages.HackerTheme.DICE_6
        };


        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(OPTIONS, computerOption, imagesH);
        } else {
            image = GameUtils.imageSetter(OPTIONS, computerOption, images);
        }

        if (getUserOption().equals(computerOption)) {
            showOutcome(GameUtils.outcomeSetter(GameType.DICEROLL, computerOption, true, outcome), image);
        } else {
            showOutcome(GameUtils.outcomeSetter(GameType.DICEROLL, computerOption, false, outcome), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
