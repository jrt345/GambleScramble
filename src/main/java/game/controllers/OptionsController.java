package game.controllers;

import game.App;
import game.utils.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
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
    private ImageView currencyImageView;

    @FXML
    private ChoiceBox<String> currencyBoxExchange;

    @FXML
    private ChoiceBox<String> currencyBoxUser;

    @FXML
    private Button convertButton;

    @FXML
    private CheckBox checkForUpdatesCheckBox;

    @FXML
    private Button updateButton;
    
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

        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            Controller.getPlayer().setCurrency(selectedCurrency);
            Controller.getPlayer().setBalance(newBalance);
            GameUtils.refreshNavBarLabel();

            updateCurrencyBoxes(false);
            GameData.serialize();
        }

        GameUtils.bankruptcyCheck(true, true, false);
    }

    @FXML
    private void openGitHubPage(ActionEvent event) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String url = "https://github.com/jrt345/GambleScramble";
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
    }

    @FXML
    private void deleteData(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Your data will be deleted permanently!");
        alert.setTitle("Confirm delete");
        alert.setHeaderText("Are you sure you want to delete your data?");

        ButtonType deleteButton = new ButtonType("Delete");

        alert.getButtonTypes().set(1, deleteButton);
        alert.getButtonTypes().set(0, ButtonType.CANCEL);

        alert.getDialogPane().lookupButton(deleteButton).setFocusTraversable(false);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(deleteButton)){
            Controller.setPlayer(new Player());
            GameData.serialize();
            Platform.exit();
        }
    }

    @FXML
    private void openAboutBox(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("aboutBox.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("About GambleScramble");
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 500, 350);
        GameUtils.setSceneTheme(scene,true, Controller.getImageView());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();
    }

    private void updateTheme(boolean isApply){
        if (themes.getValue().equals("Light")){
            Controller.getPlayer().setTheme(Theme.LIGHT);
        }
        if (themes.getValue().equals("Dark")){
            Controller.getPlayer().setTheme(Theme.DARK);
        }

        if (isApply){
            GameUtils.setSceneTheme(applyButton.getScene(), false, Controller.getImageView());

            if (Controller.getPlayer().getTheme() == Theme.DARK) {
                currencyImageView.setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows-v2.png"))));
            } else {
                currencyImageView.setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows.png"))));
            }
        }

        GameUtils.setSceneTheme(NodeUtils.getNavBarLabel().getScene(), false, Controller.getImageView());
    }

    @FXML
    private void applySettings(ActionEvent event) throws IOException {
        updateTheme(true);

        if (checkForUpdatesCheckBox.isSelected() != Controller.getPlayer().getCheckForUpdates()){
            Controller.getPlayer().setCheckForUpdates(checkForUpdatesCheckBox.isSelected());
        }

        GameData.serialize();
    }

    @FXML
    private void cancelSettings(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setNewSettings(ActionEvent event) throws IOException {
        updateTheme(false);

        if (checkForUpdatesCheckBox.isSelected() != Controller.getPlayer().getCheckForUpdates()){
            Controller.getPlayer().setCheckForUpdates(checkForUpdatesCheckBox.isSelected());
        }

        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();

        GameData.serialize();
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

    @FXML
    private void updateGame(ActionEvent event) throws IOException {
        GameUtils.showUpdateAlert();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Controller.getPlayer().getCheckForUpdates()){
            checkForUpdatesCheckBox.fire();
        }

        if (!UpdateChecker.isUpdateAvailable()){
            updateButton.setDisable(true);
            updateButton.setOpacity(0);
        }

        themes.getItems().add("Light");
        themes.getItems().add("Dark");
        themes.setValue(Controller.getPlayer().getTheme().getString());

        updateCurrencyBoxes(true);

        NodeUtils.setCurrencyBoxUser(currencyBoxUser);
        NodeUtils.setCurrencyBoxExchange(currencyBoxExchange);
        NodeUtils.setOptionsButton(convertButton);

        if (Controller.getPlayer().getTheme().equals(Theme.DARK)){
            currencyImageView.setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows-v2.png"))));
        }

        GameUtils.bankruptcyCheck(false, true, false);
    }
}
