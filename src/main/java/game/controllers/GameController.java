package game.controllers;

import game.App;
import game.games.CoinToss;
import game.games.DiceRoll;
import game.games.Game;
import game.games.HandGuess;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    private static Game game;

    @FXML
    private Label gameTitle;

    @FXML
    private ImageView gameImage1;

    @FXML
    private ImageView gameImage2;

    @FXML
    private Label gameDetails;

    @FXML
    private Label gameChoicesLabel;

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

        GameUtils.refreshNavBarLabel();

        switch (game) {
            case COINTOSS -> new CoinToss(bet, gameChoices.getValue());
            case DICEROLL -> new DiceRoll(bet, gameChoices.getValue());
            case HANDGUESS -> new HandGuess(bet, gameChoices.getValue());
        }

        GameUtils.refreshNavBarLabel();
    }

    @FXML
    private void placeBet(ActionEvent event) throws IOException {
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

        if (GameUtils.isPlayerBankrupt()){
            gameChoices.setDisable(true);
            betInput.setDisable(true);
            placeBetButton.setDisable(true);

            GameUtils.bankruptcyAlert();
        }

        GameData.serialize();
    }

    private static void setNumericalGameChoices(ChoiceBox<String> choiceBox, int min, int max) {
        for (int i = min;i < max+1;i++){
            choiceBox.getItems().add(Integer.toString(i));
        }
    }

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

        if (GameController.game == (Game.DICEROLL)){
            gameTitle.setText("DiceRoll");
            gameDetails.setText("Odds: 1:6, Payout: 5x");
            gameChoicesLabel.setText("Choose a number between 1-6");
            gameImage1.setImage(diceRoll);
            gameImage2.setImage(diceRoll);
            betInputWarning.setText("");

            setNumericalGameChoices(gameChoices,1,6);

            gameChoices.setValue("1");
        }

        if (GameController.game == (Game.HANDGUESS)){
            gameTitle.setText("HandGuess");
            gameDetails.setText("Odds: 1:11, Payout: 10x");
            gameChoicesLabel.setText("Choose a number between 0-10");

            if (Controller.getPlayer().getTheme() == Theme.DARK){
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
    }
}
