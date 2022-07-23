package game.controllers;

import game.App;
import game.games.CoinToss;
import game.games.DiceRoll;
import game.games.HandGuess;
import game.games.RockPaperScissors;
import game.utils.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private ImageView coinTossImageView;

    @FXML
    private ImageView diceRollImageView;

    @FXML
    private ImageView handGuessImageView;

    @FXML
    private ImageView rpsImageView;

    @FXML
    private ImageView scratchOffsImageView;

    @FXML
    private ImageView powerballImageView;

    @FXML
    private ImageView rouletteImageView;

    @FXML
    private ImageView crapsImageView;

    @FXML
    private ImageView bankImageView;

    @FXML
    private ImageView storeImageView;

    @FXML
    private ImageView scriptsImageView;

    @FXML
    private ImageView betsLogImageView;

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
    private Button scratchOffsButton;

    @FXML
    private Button powerballButton;

    @FXML
    private Button rouletteButton;

    @FXML
    private Button crapsButton;

    @FXML
    private Button bankButton;

    @FXML
    private Button storeButton;

    @FXML
    private Button scriptsButton;

    @FXML
    private Button betsLogButton;

    @FXML
    private void playCoinToss(ActionEvent event) throws IOException {
        new CoinToss().run();
    }

    @FXML
    private void playDiceRoll(ActionEvent event) throws IOException {
        new DiceRoll().run();
    }

    @FXML
    private void playHandGuess(ActionEvent event) throws IOException {
        new HandGuess().run();
    }

    @FXML
    private void playRPS(ActionEvent event) throws IOException {
        new RockPaperScissors().run();
    }

    @FXML
    private void playScratchOffs(ActionEvent event) {

    }

    @FXML
    private void playPowerball(ActionEvent event) {

    }

    @FXML
    private void playRoulette(ActionEvent event) {

    }

    @FXML
    private void playCraps(ActionEvent event) {

    }

    @FXML
    private void openBank(ActionEvent event) {

    }

    @FXML
    private void openStore(ActionEvent event) {

    }

    @FXML
    private void openScripts(ActionEvent event) {

    }

    @FXML
    private void openBetsLog(ActionEvent event) {

    }

    @FXML
    private void openOptions(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("options.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Options");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 500,300);
        ThemeUtils.setSceneTheme(scene,  getImageViews());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(ImageUtils.LOGO);
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
        imageViews = new ImageView[]{
                coinTossImageView, diceRollImageView, handGuessImageView, rpsImageView,
                scratchOffsImageView, powerballImageView, rouletteImageView, crapsImageView,
                bankImageView, storeImageView, scriptsImageView, betsLogImageView};

        Button[] buttons = new Button[]{coinTossButton, diceRollButton, handGuessButton, rpsButton};

        navBarLabel.setText("Current balance: " + getPlayer().getCurrency().getSymbol() +
                getPlayer().getBalance());

        NodeUtils.setNavBarLabel(navBarLabel);
        NodeUtils.setGameButtons(buttons);
    }
}