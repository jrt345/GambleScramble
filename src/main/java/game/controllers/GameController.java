package game.controllers;

import game.App;
import game.games.*;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.NodeUtils;
import game.utils.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static Game game;

    @FXML
    private Label gameTitle;

    @FXML
    private ImageView gameImage1; //Upper left imageView

    @FXML
    private ImageView gameImage2; //Upper right imageView

    @FXML
    private Label gameDetails; //Shows the odds and the payout of a game

    @FXML
    private Label gameChoicesLabel; //Tells the player what they can bet on

    @FXML
    private ChoiceBox<String> gameChoices;

    @FXML
    private TextField betInput;

    @FXML
    private Label betInputWarning;

    @FXML
    private Button placeBetButton;

    private void runGame(Game game, int bet) throws IOException {
        GameUtils.updateBalance(-bet);

        GameData.serialize();
        GameUtils.refreshData();

        switch (game) {
            case COINTOSS -> new CoinToss(bet, gameChoices.getValue());
            case DICEROLL -> new DiceRoll(bet, gameChoices.getValue());
            case HANDGUESS -> new HandGuess(bet, gameChoices.getValue());
            case ROCKPAPERSCISSORS -> new RockPaperScissors(bet, gameChoices.getValue());
        }

        GameData.serialize();
        GameUtils.refreshData();
    }

    @FXML
    private void placeBet(ActionEvent event) throws IOException {
        /*Will try to convert betInput into a number as the bet
        * if any issues occur betInputWarning will update*/
        try {
            int bet = Integer.parseInt(betInput.getText());
            betInputWarning.setText("");

            if (bet > Controller.getPlayer().getBalance()){
                betInputWarning.setText("You do not have enough money");
            } else if (bet == 0){
                betInputWarning.setText("Your bet can't be zero");
            } else if (bet < 0){
                betInputWarning.setText("Your bet can't be negative");
            } else {
                runGame(game, bet);
            }
        } catch (NumberFormatException e) {
            betInputWarning.setText("Please enter a number");
        }

        GameUtils.bankruptcyCheck(true, false, true);

        GameData.serialize();
    }

    /*If the choices of a game are numerical it will convert numbers
    * between min and max into strings and add them to choiceBox*/
    private static void setNumericalGameChoices(ChoiceBox<String> choiceBox, int min, int max) {
        for (int i = min;i < max+1;i++){
            choiceBox.getItems().add(Integer.toString(i));
        }
    }

    //Sets gameTemplate.fxml appearance to match the chosen game
    public void setGame(Game game) {
        GameController.game = game;

        Image coinToss = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/cointoss.png")),
                60, 60, true, true);

        Image diceRoll = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/diceroll.png")),
                60, 60, true, true);

        Image handGuess = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess.png")),
                60, 60, true, true);

        Image handGuessV2 = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png")),
                60, 60, true, true);

        Image rps = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/rockpaperscissors.png")),
                60, 60, true, true);

        if (GameController.game == Game.COINTOSS){
            gameTitle.setText("CoinToss");
            gameDetails.setText("Odds: 1:2, Payout: 2x");
            gameChoicesLabel.setText("Heads or Tails?");
            gameImage1.setImage(coinToss);
            gameImage2.setImage(coinToss);
            betInputWarning.setText("");

            gameChoices.getItems().add("Heads");
            gameChoices.getItems().add("Tails");
            gameChoices.setValue("Heads");
        }

        if (GameController.game == Game.DICEROLL){
            gameTitle.setText("DiceRoll");
            gameDetails.setText("Odds: 1:6, Payout: 5x");
            gameChoicesLabel.setText("Choose a number between 1-6");
            gameImage1.setImage(diceRoll);
            gameImage2.setImage(diceRoll);
            betInputWarning.setText("");

            setNumericalGameChoices(gameChoices,1,6);

            gameChoices.setValue("1");
        }

        if (GameController.game == Game.HANDGUESS){
            gameTitle.setText("HandGuess");
            gameDetails.setText("Odds: 1:11, Payout: 10x");
            gameChoicesLabel.setText("Choose a number between 0-10");

            if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE){
                gameImage1.setImage(handGuessV2);
                gameImage2.setImage(handGuessV2);
            } else {
                gameImage1.setImage(handGuess);
                gameImage2.setImage(handGuess);
            }

            betInputWarning.setText("");

            setNumericalGameChoices(gameChoices,0,10);

            gameChoices.setValue("0");
        }

        if (GameController.game == Game.ROCKPAPERSCISSORS){
            gameTitle.setText("Rock Paper Scissors");
            gameTitle.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 33));
            gameDetails.setText("Odds: 1:2, Payout: 2x");
            gameChoicesLabel.setText("Rock, Paper, Scissors?!");
            gameImage1.setImage(rps);
            gameImage2.setImage(rps);
            betInputWarning.setText("");

            gameChoices.getItems().add("Rock");
            gameChoices.getItems().add("Paper");
            gameChoices.getItems().add("Scissors");
            gameChoices.setValue("Rock");
        }

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            Image coinTossH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png")));
            Image diceRollH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png")));
            Image handGuessH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png")));
            Image rpsH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png")));

            if (GameController.game == Game.COINTOSS){
                gameImage1.setImage(coinTossH);
                gameImage2.setImage(coinTossH);
            }
            if (GameController.game == Game.DICEROLL){
                gameImage1.setImage(diceRollH);
                gameImage2.setImage(diceRollH);
            }
            if (GameController.game == Game.HANDGUESS){
                gameImage1.setImage(handGuessH);
                gameImage2.setImage(handGuessH);
            }
            if (GameController.game == Game.ROCKPAPERSCISSORS){
                gameImage1.setImage(rpsH);
                gameImage2.setImage(rpsH);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NodeUtils.setBetChoiceBox(gameChoices);
        NodeUtils.setBetTextField(betInput);
        NodeUtils.setPlaceBetButton(placeBetButton);

        GameUtils.bankruptcyCheck(true, false, true);
    }
}
