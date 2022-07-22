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

    private static GameType game;

    @FXML
    private Label title;

    @FXML
    private ImageView leftImage; //Upper left imageView

    @FXML
    private ImageView rightImage; //Upper right imageView

    @FXML
    private Label details; //Shows the odds and the payout of a game

    @FXML
    private Label prompt; //Tells the player what they can bet on

    @FXML
    private ChoiceBox<String> optionsChoiceBox;

    @FXML
    private TextField betInput;

    @FXML
    private Label betInputWarning;

    @FXML
    private Button placeBetButton;

    private void runGame(GameType game, int bet) throws IOException {
        GameUtils.updateBalance(-bet);

        GameData.serialize();
        GameUtils.refreshData();

        switch (game) {
            case COINTOSS -> CoinToss.play(bet, optionsChoiceBox.getValue());
            case DICEROLL -> DiceRoll.play(bet, optionsChoiceBox.getValue());
            case HANDGUESS -> HandGuess.play(bet, optionsChoiceBox.getValue());
            case ROCKPAPERSCISSORS -> RockPaperScissors.play(bet, optionsChoiceBox.getValue());
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
    public void setGame(GameType game) {
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

        if (GameController.game == GameType.COINTOSS){
            title.setText("CoinToss");
            details.setText("Odds: 1:2, Payout: 2x");
            prompt.setText("Heads or Tails?");
            leftImage.setImage(coinToss);
            rightImage.setImage(coinToss);
            betInputWarning.setText("");

            optionsChoiceBox.getItems().add("Heads");
            optionsChoiceBox.getItems().add("Tails");
            optionsChoiceBox.setValue("Heads");
        }

        if (GameController.game == GameType.DICEROLL){
            title.setText("DiceRoll");
            details.setText("Odds: 1:6, Payout: 5x");
            prompt.setText("Choose a number between 1-6");
            leftImage.setImage(diceRoll);
            rightImage.setImage(diceRoll);
            betInputWarning.setText("");

            setNumericalGameChoices(optionsChoiceBox,1,6);

            optionsChoiceBox.setValue("1");
        }

        if (GameController.game == GameType.HANDGUESS){
            title.setText("HandGuess");
            details.setText("Odds: 1:11, Payout: 10x");
            prompt.setText("Choose a number between 0-10");

            if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE){
                leftImage.setImage(handGuessV2);
                rightImage.setImage(handGuessV2);
            } else {
                leftImage.setImage(handGuess);
                rightImage.setImage(handGuess);
            }

            betInputWarning.setText("");

            setNumericalGameChoices(optionsChoiceBox,0,10);

            optionsChoiceBox.setValue("0");
        }

        if (GameController.game == GameType.ROCKPAPERSCISSORS){
            title.setText("Rock Paper Scissors");
            title.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 33));
            details.setText("Odds: 1:2, Payout: 2x");
            prompt.setText("Rock, Paper, Scissors?!");
            leftImage.setImage(rps);
            rightImage.setImage(rps);
            betInputWarning.setText("");

            optionsChoiceBox.getItems().add("Rock");
            optionsChoiceBox.getItems().add("Paper");
            optionsChoiceBox.getItems().add("Scissors");
            optionsChoiceBox.setValue("Rock");
        }

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            Image coinTossH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png")));
            Image diceRollH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png")));
            Image handGuessH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png")));
            Image rpsH = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png")));

            if (GameController.game == GameType.COINTOSS){
                leftImage.setImage(coinTossH);
                rightImage.setImage(coinTossH);
            }
            if (GameController.game == GameType.DICEROLL){
                leftImage.setImage(diceRollH);
                rightImage.setImage(diceRollH);
            }
            if (GameController.game == GameType.HANDGUESS){
                leftImage.setImage(handGuessH);
                rightImage.setImage(handGuessH);
            }
            if (GameController.game == GameType.ROCKPAPERSCISSORS){
                leftImage.setImage(rpsH);
                rightImage.setImage(rpsH);
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NodeUtils.setBetChoiceBox(optionsChoiceBox);
        NodeUtils.setBetTextField(betInput);
        NodeUtils.setPlaceBetButton(placeBetButton);

        GameUtils.bankruptcyCheck(true, false, true);
    }
}
