package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameController {

    @FXML
    private Label gameTitle;

    @FXML
    private Label gameDetails;

    @FXML
    private Label gameChoicesLabel;

    @FXML
    private ChoiceBox<?> gameChoices;

    @FXML
    private TextField betInput;

    @FXML
    private Label betInputWarning;

    @FXML
    private Button placeBetButton;

    @FXML
    void placeBet(ActionEvent event) {

    }
}
