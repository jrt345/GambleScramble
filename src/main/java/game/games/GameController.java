package game.games;

import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.NodeUtils;
import javafx.collections.FXCollections;
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
import java.util.Arrays;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private SimpleGame simpleGame;
    public void setSimpleGame(SimpleGame simpleGame) {
        this.simpleGame = simpleGame;
    }

    private static GameType game;

    @FXML
    private Label title;

    @FXML
    private ImageView leftImage;

    @FXML
    private ImageView rightImage;

    @FXML
    private Label details;

    @FXML
    private Label prompt;

    @FXML
    private ChoiceBox<String> optionsChoiceBox;

    @FXML
    private TextField betInput;

    @FXML
    private Label betInputWarning;

    @FXML
    private Button placeBetButton;

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
                simpleGame.setBet(bet);
                simpleGame.setUserOption(optionsChoiceBox.getValue());

                simpleGame.play();
            }
        } catch (NumberFormatException e) {
            betInputWarning.setText("Please enter a number");
        }

        GameUtils.bankruptcyCheck(true, false, true);

        GameData.serialize();
    }

    public void loadGame() {
        title.setText(simpleGame.getTitle());
        details.setText(simpleGame.getDetails());
        prompt.setText(simpleGame.getPrompt());
        leftImage.setImage(simpleGame.getImage());
        rightImage.setImage(simpleGame.getImage());
        betInputWarning.setText("");

        optionsChoiceBox.setItems(FXCollections.observableList(Arrays.asList(simpleGame.getOptions())));
        optionsChoiceBox.setValue(optionsChoiceBox.getItems().get(0));

        if (simpleGame.getTitle().equals("Rock Paper Scissors")) {
            title.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 33));
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
