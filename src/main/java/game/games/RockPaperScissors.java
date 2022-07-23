package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.ImageUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;

public class RockPaperScissors extends SimpleGame {

    private static final String[] options = {"Rock", "Paper", "Scissors"};

    public RockPaperScissors() {
        setTitle("Rock Paper Scissors");
        setDetails("Odds: 1:2, Payout: 2x");
        setPrompt("Rock, Paper, Scissors?!");
        setOptions(options);

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            setImage(ImageUtils.RockPaperScissors.HackerTheme.LOGO);
        } else {
            setImage(ImageUtils.RockPaperScissors.LOGO);
        }
    }

    @Override
    protected void play() throws IOException {
        GameUtils.updateBalance(-getBet());

        GameData.serialize();
        GameUtils.refreshData();

        play(getBet(), getUserOption());

        GameData.serialize();
        GameUtils.refreshData();
    }

    private static int getOutcome(String userOption, String computerOption, int bet) {
        if (userOption.equals("Rock") && computerOption.equals("Scissors")) {
            return bet*2;
        } else if (userOption.equals("Paper") && computerOption.equals("Rock")) {
            return bet*2;
        } else if (userOption.equals("Scissors") && computerOption.equals("Paper")) {
            return bet*2;
        } else {
            return bet*-1;
        }
    }

    public static void play(int bet, String userOption) throws IOException {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 2);

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

        while (userOption.equals(computerOption)){
            GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

            if (Controller.getPlayer().getTheme() == Theme.HACKER){
                GameUtils.gameOutcomeAlert("Rock Paper Scissors",
                        computerOption + ". It's a Tie! We'll go again!",
                        GameUtils.imageSetter(options, computerOption, imagesH));
            } else {
                GameUtils.gameOutcomeAlert("Rock Paper Scissors",
                        computerOption + ". It's a Tie! We'll go again!",
                        GameUtils.imageSetter(options, computerOption, images));
            }


            computerOption = GameUtils.generateComputerChoice(options, 0, 2);
        }

        int outcome = getOutcome(userOption, computerOption, bet);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(options, computerOption, imagesH);
        } else {
            image = GameUtils.imageSetter(options, computerOption, images);
        }

        GameUtils.playGameSound(GameType.ROCKPAPERSCISSORS);

        if (outcome > 0) {
            GameUtils.gameOutcomeAlert("Rock Paper Scissors",
                    computerOption + ". You win! You won: +"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + outcome, image);
        } else {
            GameUtils.gameOutcomeAlert("Rock Paper Scissors",
                    computerOption + ". You lose! You lost: -"
                            + Controller.getPlayer().getCurrency().getSymbol()
                            + outcome*-1, image);
        }
    }
}
