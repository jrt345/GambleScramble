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
    int calculateOutcome() {
        if (getUserOption().equals("Rock") && getComputerOption().equals("Scissors")) {
            return getBet()*MULTIPLIER;
        } else if (getUserOption().equals("Paper") && getComputerOption().equals("Rock")) {
            return getBet()*MULTIPLIER;
        } else if (getUserOption().equals("Scissors") && getComputerOption().equals("Paper")) {
            return getBet()*MULTIPLIER;
        } else {
            return getBet()*-1;
        }
    }

    @Override
    void updateBalance() throws IOException {
        GameUtils.updateBalance(-getBet());

        GameData.serialize();
        GameUtils.refreshData();

        setComputerOption(getRandomOption());
    }

    @Override
    protected void play() throws IOException {
        updateBalance();

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

        while (getUserOption().equals(getComputerOption())){
            GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

            if (Controller.getPlayer().getTheme() == Theme.HACKER){
                showOutcome(getComputerOption() + ". It's a Tie! We'll go again!",
                        getComputerOptionImage(imagesH));
            } else {
                showOutcome(getComputerOption() + ". It's a Tie! We'll go again!",
                        getComputerOptionImage(images));
            }


            setComputerOption(getRandomOption());
        }

        setOutcome(calculateOutcome());

        if (getOutcome() > 0){
            GameUtils.updateBalance(getOutcome());
        }

        GameData.serialize();

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = getComputerOptionImage(imagesH);
        } else {
            image = getComputerOptionImage(images);
        }

        GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

        if (getOutcome() > 0) {
            showOutcome(getComputerOption() + ". You win! You won: +"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + getOutcome(), image);
        } else {
            showOutcome(getComputerOption() + ". You lose! You lost: -"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + getOutcome()*-1, image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
