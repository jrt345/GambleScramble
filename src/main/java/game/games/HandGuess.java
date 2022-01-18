package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Theme;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class HandGuess {

    //Launches HandGuess game
    public HandGuess() {
        try {
            GameUtils.loadGame(Game.HANDGUESS, "HandGuess");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Displays alert showing the outcome of the bet
    public HandGuess(int bet, String userOption) throws IOException {
        String[] options = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String computerOption = GameUtils.generateComputerChoice(options, 0, 10);

        int outcome = GameUtils.getOutcome(userOption, computerOption, bet, 10);

        if (outcome > 0){
            GameUtils.updateBalance(outcome);
        }

        GameData.serialize();

        Image[] images = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand0.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand6.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand7.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand8.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand9.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hand10.png")),
                        50, 50, true, true)
        };

        Image[] imagesD = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand0.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand6.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand7.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand8.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand9.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/darktheme/hand10.png")),
                        50, 50, true, true)
        };

        Image[] imagesH = {
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand0.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand1.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand2.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand3.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand4.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand5.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand6.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand7.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand8.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand9.png")),
                        50, 50, true, true),
                new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/handguess/hackertheme/hand10.png")),
                        50, 50, true, true)
        };


        Image image;
        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            image = GameUtils.imageSetter(options, computerOption, imagesH);
        } else if (Controller.getPlayer().getTheme() == Theme.DARK) {
            image = GameUtils.imageSetter(options, computerOption, imagesD);

        } else {
            image = GameUtils.imageSetter(options, computerOption, images);
        }

        if (userOption.equals(computerOption)) {
            GameUtils.gameOutcomeAlert("HandGuess",
                    GameUtils.outcomeSetter(Game.HANDGUESS, computerOption, true, outcome), image);
        } else {
            GameUtils.gameOutcomeAlert("HandGuess",
                    GameUtils.outcomeSetter(Game.HANDGUESS, computerOption, false, outcome), image);
        }
    }
}
