package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class RockPaperScissors extends SimpleGame {

    private static final int MULTIPLIER = 2;
    private static final String[] OPTIONS = {"Rock", "Paper", "Scissors"};

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setDetails("Odds: 1:2, Payout: 2x");
        setPrompt("Rock, Paper, Scissors?!");
        setMultiplier(MULTIPLIER);
        setOptions(OPTIONS);

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            setImage(ImageUtils.RockPaperScissors.HackerTheme.LOGO);
        } else {
            setImage(ImageUtils.RockPaperScissors.LOGO);
        }
    }

    @Override
    int getOutcome(String option) {
        if (getUserOption().equals("Rock") && option.equals("Scissors")) {
            return getBet()*MULTIPLIER;
        } else if (getUserOption().equals("Paper") && option.equals("Rock")) {
            return getBet()*MULTIPLIER;
        } else if (getUserOption().equals("Scissors") && option.equals("Paper")) {
            return getBet()*MULTIPLIER;
        } else {
            return getBet()*-1;
        }
    }

    @Override
    protected void play() throws IOException {
        GameUtils.updateBalance(-getBet());

        GameData.serialize();
        GameUtils.refreshData();

        String computerOption = getRandomOption();

        Image[] images = {
                ImageUtils.RockPaperScissors.ROCK,
                ImageUtils.RockPaperScissors.PAPER,
                ImageUtils.RockPaperScissors.SCISSORS
        };

        Image[] imagesH = {
                ImageUtils.RockPaperScissors.HackerTheme.ROCK,
                ImageUtils.RockPaperScissors.HackerTheme.PAPER,
                ImageUtils.RockPaperScissors.HackerTheme.SCISSORS
        };

        while (getUserOption().equals(computerOption)){
            GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

            if (Controller.getPlayer().getTheme() == Theme.HACKER){
                showOutcome(computerOption + ". It's a Tie! We'll go again!",
                        GameUtils.imageSetter(OPTIONS, computerOption, imagesH));
            } else {
                showOutcome(computerOption + ". It's a Tie! We'll go again!",
                        GameUtils.imageSetter(OPTIONS, computerOption, images));
            }


            computerOption = getRandomOption();
        }

        int outcome = getOutcome(computerOption);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(OPTIONS, computerOption, imagesH);
        } else {
            image = GameUtils.imageSetter(OPTIONS, computerOption, images);
        }

        GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

        if (outcome > 0) {
            showOutcome(computerOption + ". You win! You won: +"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + outcome, image);
        } else {
            showOutcome(computerOption + ". You lose! You lost: -"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + outcome*-1, image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
