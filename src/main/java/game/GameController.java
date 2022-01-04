package game;

import game.games.CoinToss;
import game.games.DiceRoll;
import game.games.Game;
import game.games.HandGuess;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Theme;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Objects;

public class GameController {

    private static Game game;

    private static Label navBarLabel;

    public static void setNavBarLabel(Label navBarLabel) {
        GameController.navBarLabel = navBarLabel;
    }

    private static Button[] buttons;

    public static void setButtons(Button[] buttons) {
        GameController.buttons = buttons;
    }

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
        switch (game) {
            case COINTOSS -> new CoinToss(bet, gameChoices.getValue());
            case DICEROLL -> new DiceRoll(bet, gameChoices.getValue());
            case HANDGUESS -> new HandGuess(bet, gameChoices.getValue());
        }
        navBarLabel.setText("Current balance: " + Controller.getPlayer().getCurrency().getSymbol() +
                Controller.getPlayer().getBalance());
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
            }
            else {
                runGame(game, bet);
            }
        } catch (NumberFormatException e) {
            betInputWarning.setText("Please enter a number");
        }

        if (GameUtils.isPlayerBankrupt()){
            gameChoices.setDisable(true);
            betInput.setDisable(true);
            placeBetButton.setDisable(true);

            for (Button button : buttons) {
                button.setDisable(true);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "To play again, exit the game and re-open the game.");
            alert.setTitle("Bankruptcy Notice");
            alert.setHeaderText("You are bankrupt!");

            alert.showAndWait();
        }

        GameData.serialize();
    }

    public void setGame(Game game){
        GameController.game = game;

        Image coinToss = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/gamblescramble/cointoss.png")),
                60, 60, true, true);

        Image diceRoll = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/gamblescramble/diceroll.png")),
                60, 60, true, true);

        Image handGuess = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/gamblescramble/handguess.png")),
                60, 60, true, true);

        Image handGuessV2 = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/gamblescramble/handguess-v2.png")),
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

            gameChoices.getItems().add("1");
            gameChoices.getItems().add("2");
            gameChoices.getItems().add("3");
            gameChoices.getItems().add("4");
            gameChoices.getItems().add("5");
            gameChoices.getItems().add("6");

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

            gameChoices.getItems().add("0");
            gameChoices.getItems().add("1");
            gameChoices.getItems().add("2");
            gameChoices.getItems().add("3");
            gameChoices.getItems().add("4");
            gameChoices.getItems().add("5");
            gameChoices.getItems().add("6");
            gameChoices.getItems().add("7");
            gameChoices.getItems().add("8");
            gameChoices.getItems().add("9");
            gameChoices.getItems().add("10");

            gameChoices.setValue("0");
        }
    }
}
