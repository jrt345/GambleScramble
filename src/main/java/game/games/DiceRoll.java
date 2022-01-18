package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class DiceRoll {

    //Launches DiceRoll game
    public DiceRoll() {
        try {
            GameUtils.loadGame(Game.DICEROLL, "DiceRoll");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays alert showing the outcome of the bet
    public DiceRoll(int bet, String userOption) throws IOException {
        String[] options = {"1", "2", "3", "4", "5", "6"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 5);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 5);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/dice6.png")),
                        50, 50, true, true)
        };

        Image[] imagesH = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/diceroll/hackertheme/dice6.png")),
                        50, 50, true, true)
        };

        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(options, computerOption, imagesH);
        } else {
            image = GameUtils.imageSetter(options, computerOption, images);
        }

        if (userOption.equals(computerOption)) {
            GameUtils.gameOutcomeAlert("DiceRoll",
                    GameUtils.outcomeSetter(Game.DICEROLL, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcomeAlert("DiceRoll",
                    GameUtils.outcomeSetter(Game.DICEROLL, computerOption, false, outcome), image);
        }
    }
}
