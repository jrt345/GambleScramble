package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class RockPaperScissors {

    //Launches Rock Paper Scissors game
    public RockPaperScissors() {
        try {
            GameUtils.loadGame(GameType.ROCKPAPERSCISSORS, "Rock Paper Scissors");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Custom getOutcome method to work with Rock Paper Scissors
    private int getOutcome(String userOption, String computerOption, int bet) {
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

    //Displays alert showing the outcome of the bet
    public RockPaperScissors(int bet, String userOption) throws IOException {
        String[] options = {"Rock", "Paper", "Scissors"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 2);

        Image[] images = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsR.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsP.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/rpsS.png")),
                        50, 50, true, true)
        };

        Image[] imagesH = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsR.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsP.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/rockpaperscissors/hackertheme/rpsS.png")),
                        50, 50, true, true)
        };

        /*While the user and computer are tied, display an alert and
        * generate a new computerOption*/
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
