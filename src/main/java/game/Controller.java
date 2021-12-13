package game;

import game.games.CoinToss;
import game.utils.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static Player player;

    public static Player getPlayer(){
        return player;
    }

    public static void setPlayer(Player player){
        Controller.player = player;
    }

    @FXML
    private Label navBarLabel;

    @FXML
    private Button coinTossButton;

    @FXML
    private Button diceRollButton;

    @FXML
    private Button handGuessButton;

    @FXML
    void playCoinToss(ActionEvent event) {
        new CoinToss();
    }

    @FXML
    void playDiceRoll(ActionEvent event) {

    }

    @FXML
    void playHandGuess(ActionEvent event) {

    }

    @FXML
    void openOptions(ActionEvent event) {

    }

    @FXML
    void quitGame(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button[] buttons = {coinTossButton, diceRollButton, handGuessButton};

        navBarLabel.setText("Current balance: $" + getPlayer().getBalance());
        GameController.setNavBarLabel(navBarLabel);
        GameController.setButtons(buttons);
    }
}