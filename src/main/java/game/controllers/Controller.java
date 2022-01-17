package game.controllers;

import game.App;
import game.games.CoinToss;
import game.games.DiceRoll;
import game.games.HandGuess;
import game.games.RockPaperScissors;
import game.utils.GameUtils;
import game.utils.NodeUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    private ImageView coinTossImageView;

    @FXML
    private ImageView diceRollImageView;

    @FXML
    private ImageView handGuessImageView;

    @FXML
    private ImageView rpsImageView;

    private static ImageView[] imageViews;

    public static ImageView[] getImageViews() {
        return imageViews;
    }

    @FXML
    private Button coinTossButton;

    @FXML
    private Button diceRollButton;

    @FXML
    private Button handGuessButton;

    @FXML
    private Button rpsButton;

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
    private void playRPS() {
        new RockPaperScissors();
    }

    @FXML
    private void openOptions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("options.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Options");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 500,300);
        GameUtils.setSceneTheme(scene,  getImageViews());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();
    }

    @FXML
    private void quitGame(ActionEvent event) {
        try {
            GameUtils.secureSerialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageViews = new ImageView[]{coinTossImageView, diceRollImageView, handGuessImageView, rpsImageView};

        Button[] buttons = new Button[]{coinTossButton, diceRollButton, handGuessButton, rpsButton};

        navBarLabel.setText("Current balance: " + getPlayer().getCurrency().getSymbol() +
                getPlayer().getBalance());

        NodeUtils.setNavBarLabel(navBarLabel);
        NodeUtils.setGameButtons(buttons);
    }
}