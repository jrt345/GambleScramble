package game;

import game.games.CoinToss;
import game.games.DiceRoll;
import game.games.HandGuess;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Player;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    private void playCoinToss(ActionEvent event) {
        new CoinToss();
    }

    @FXML
    private void playDiceRoll(ActionEvent event) {
        new DiceRoll();
    }

    @FXML
    private void playHandGuess(ActionEvent event) {
        new HandGuess();
    }

    @FXML
    private void openOptions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("options.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Options");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 500,300);
        GameUtils.setSceneTheme(scene, true);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void quitGame(ActionEvent event) throws IOException {
        GameData.serialize();
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button[] buttons = new Button[]{coinTossButton, diceRollButton, handGuessButton};

        navBarLabel.setText("Current balance: " + getPlayer().getCurrency().getSymbol() +
                getPlayer().getBalance());

        GameController.setNavBarLabel(navBarLabel);
        GameController.setButtons(buttons);
        OptionsController.setNavBarLabel(navBarLabel);
        OptionsController.setButtons(buttons);
    }
}