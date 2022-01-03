package game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
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
    private ChoiceBox<String> themes;

    @FXML
    private ChoiceBox<String> currencyBoxExchange;

    @FXML
    private ChoiceBox<String> currencyBoxUser;

    @FXML
    private Button convertButton;


    @FXML
    void convertCurrency(ActionEvent event) {

    }

    @FXML
    void deleteData(ActionEvent event) {

    }

    @FXML
    void openAboutBox(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("aboutBox.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("About GambleScramble");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 500, 400));
        stage.setResizable(false);
        stage.show();
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
        themes.getItems().add("Light");
        themes.getItems().add("Dark");
        themes.getItems().add("Minimal (Light)");
        themes.getItems().add("Minimal (Dark)");
        themes.setValue(Controller.getPlayer().getTheme().getString());
    }
}
