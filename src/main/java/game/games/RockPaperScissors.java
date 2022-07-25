package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.util.Objects;

public final class RockPaperScissors extends SimpleGame {

    private static final int MULTIPLIER = 2;
    private static final String[] OPTIONS = {"Rock", "Paper", "Scissors"};
    private static final AudioClip SOUND = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/rockpaperscissors.mp3")).toString());

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setDetails("Odds: 1:2, Payout: 2x");
        setPrompt("Rock, Paper, Scissors?!");
        setMultiplier(MULTIPLIER);
        setOptions(OPTIONS);
        setSound(SOUND);

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
    String getComputerOptionResult() {
        if (getUserOption().equals(getComputerOption())) {
            return getComputerOption() + ". It's a Tie! We'll go again!";
        } else {
            return getComputerOption().concat(". ");
        }
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
            if (Controller.getPlayer().getTheme() == Theme.HACKER){
                showOutcome(getComputerOptionResult(), getComputerOptionImage(imagesH));
            } else {
                showOutcome(getComputerOptionResult(), getComputerOptionImage(images));
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

        if (getOutcome() > 0) {
            showOutcome(getResultMessage(true), image);
        } else {
            showOutcome(getResultMessage(false), image);
        }

        GameData.serialize();
        GameUtils.refreshData();
    }
}
