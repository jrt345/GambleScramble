package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button applyButton;

    @FXML
    private ChoiceBox<?> themes;

    @FXML
    private ChoiceBox<?> currencyBoxExchange;

    @FXML
    private ChoiceBox<?> currencyBoxUser;

    @FXML
    private Button convertButton;


    @FXML
    void convertCurrency(ActionEvent event) {

    }

    @FXML
    void deleteData(ActionEvent event) {

    }

    @FXML
    void openAboutBox(ActionEvent event) {

    }

    @FXML
    void openGitHubPage(ActionEvent event) {

    }

    @FXML
    void applySettings(ActionEvent event) {

    }

    @FXML
    void cancelSettings(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void setNewSettings(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
