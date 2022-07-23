package game.games;

import game.controllers.Controller;
import game.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.net.URL;
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

        if (GameController.game == GameType.COINTOSS){
            title.setText("CoinToss");
            details.setText("Odds: 1:2, Payout: 2x");
            prompt.setText("Heads or Tails?");
            leftImage.setImage(ImageUtils.CoinTossImages.LOGO);
            rightImage.setImage(ImageUtils.CoinTossImages.LOGO);
            betInputWarning.setText("");

            optionsChoiceBox.getItems().add("Heads");
            optionsChoiceBox.getItems().add("Tails");
            optionsChoiceBox.setValue("Heads");
        }

        if (GameController.game == GameType.DICEROLL){
            title.setText("DiceRoll");
            details.setText("Odds: 1:6, Payout: 5x");
            prompt.setText("Choose a number between 1-6");
            leftImage.setImage(ImageUtils.DiceRollImages.LOGO);
            rightImage.setImage(ImageUtils.DiceRollImages.LOGO);
            betInputWarning.setText("");

            setNumericalGameChoices(optionsChoiceBox,1,6);

            optionsChoiceBox.setValue("1");
        }

        if (GameController.game == GameType.HANDGUESS){
            title.setText("HandGuess");
            details.setText("Odds: 1:11, Payout: 10x");
            prompt.setText("Choose a number between 0-10");

            if (Controller.getPlayer().getTheme() == Theme.DARK || Controller.getPlayer().getTheme() == Theme.SLATE){
                leftImage.setImage(ImageUtils.HandGuessImages.DarkTheme.LOGO);
                rightImage.setImage(ImageUtils.HandGuessImages.DarkTheme.LOGO);
            } else {
                leftImage.setImage(ImageUtils.HandGuessImages.LOGO);
                rightImage.setImage(ImageUtils.HandGuessImages.LOGO);
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
            leftImage.setImage(ImageUtils.RockPaperScissors.LOGO);
            rightImage.setImage(ImageUtils.RockPaperScissors.LOGO);
            betInputWarning.setText("");

            optionsChoiceBox.getItems().add("Rock");
            optionsChoiceBox.getItems().add("Paper");
            optionsChoiceBox.getItems().add("Scissors");
            optionsChoiceBox.setValue("Rock");
        }

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {

            if (GameController.game == GameType.COINTOSS){
                leftImage.setImage(ImageUtils.CoinTossImages.HackerTheme.LOGO);
                rightImage.setImage(ImageUtils.CoinTossImages.HackerTheme.LOGO);
            }
            if (GameController.game == GameType.DICEROLL){
                leftImage.setImage(ImageUtils.DiceRollImages.HackerTheme.LOGO);
                rightImage.setImage(ImageUtils.DiceRollImages.HackerTheme.LOGO);
            }
            if (GameController.game == GameType.HANDGUESS){
                leftImage.setImage(ImageUtils.HandGuessImages.HackerTheme.LOGO);
                rightImage.setImage(ImageUtils.HandGuessImages.HackerTheme.LOGO);
            }
            if (GameController.game == GameType.ROCKPAPERSCISSORS){
                leftImage.setImage(ImageUtils.RockPaperScissors.HackerTheme.LOGO);
                rightImage.setImage(ImageUtils.RockPaperScissors.HackerTheme.LOGO);
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
