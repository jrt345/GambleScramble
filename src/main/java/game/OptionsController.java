package game;

import game.utils.Currency;
import game.utils.CurrencyConverter;
import game.utils.GameData;
import game.utils.GameUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

    private static Label navBarLabel;

    public void setNavBarLabel(Label navBarLabel){
        OptionsController.navBarLabel = navBarLabel;
    }

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
    private void convertCurrency(ActionEvent event) throws IOException {
        int balance = Controller.getPlayer().getBalance();
        Currency currentCurrency = CurrencyConverter.stringToCurrency(currencyBoxUser.getValue());
        Currency selectedCurrency = CurrencyConverter.stringToCurrency(currencyBoxExchange.getValue());
        int newBalance = CurrencyConverter.convertCurrency(balance, currentCurrency, selectedCurrency);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You will convert " +
                currentCurrency.getSymbol() + balance + " to " +
                selectedCurrency.getSymbol() + newBalance);
        alert.setTitle("Currency Exchange");
        alert.setHeaderText("Would you like to convert " +
                currencyBoxUser.getValue() + " to " +
                currencyBoxExchange.getValue() + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)) {
            Controller.getPlayer().setCurrency(selectedCurrency);
            Controller.getPlayer().setBalance(newBalance);
            navBarLabel.setText("Current balance: " + Controller.getPlayer().getCurrency().getSymbol() +
                    Controller.getPlayer().getBalance());

            updateCurrencyBoxes(false);
            GameData.serialize();
        }
    }

    @FXML
    private void deleteData(ActionEvent event) {

    }

    @FXML
    private void openAboutBox(ActionEvent event) throws IOException {
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
    private void openGitHubPage(ActionEvent event) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url = "https://github.com/jrt345/GambleScramble";
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
    }

    @FXML
    private void applySettings(ActionEvent event) {

    }

    @FXML
    private void cancelSettings(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setNewSettings(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    private void updateCurrencyBoxes(boolean isInitial){
        if (!isInitial){
            currencyBoxUser.getItems().remove(0);
            currencyBoxExchange.getItems().remove(0,5);
        }

        currencyBoxUser.getItems().add(Controller.getPlayer().getCurrency().toString());
        currencyBoxUser.setValue(Controller.getPlayer().getCurrency().toString());

        currencyBoxExchange.getItems().add("USD");
        currencyBoxExchange.getItems().add("EUR");
        currencyBoxExchange.getItems().add("GBP");
        currencyBoxExchange.getItems().add("AUD");
        currencyBoxExchange.getItems().add("CAD");
        currencyBoxExchange.getItems().add("NZD");
        currencyBoxExchange.getItems().remove(Controller.getPlayer()
                .getCurrency().getIndex());
        currencyBoxExchange.setValue(currencyBoxExchange.getItems().get(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        themes.getItems().add("Light");
        themes.getItems().add("Dark");
        themes.getItems().add("Minimal (Light)");
        themes.getItems().add("Minimal (Dark)");
        themes.setValue(Controller.getPlayer().getTheme().getString());

        updateCurrencyBoxes(true);

        if (GameUtils.isPlayerBankrupt()){
            currencyBoxUser.setDisable(true);
            currencyBoxExchange.setDisable(true);
            convertButton.setDisable(true);
        }
    }

}
