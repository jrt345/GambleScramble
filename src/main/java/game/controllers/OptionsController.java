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
import javafx.scene.media.AudioClip;
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

    private static final Image blackExchangeArrows = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows.png")));
    private static final Image whiteExchangeArrows = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/exchangeArrows-v2.png")));
    private static final Image hackerThemeExchangeArrows = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/exchangeArrows.png")));

    private static final AudioClip exchangeSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/exchange.mp3")).toString());

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

        ThemeUtils.setAlertTheme(alert);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            Controller.getPlayer().setCurrency(selectedCurrency);
            Controller.getPlayer().setBalance(newBalance);
            GameUtils.refreshNavBarLabel();

            updateCurrencyBoxes(false);

            exchangeSound.play();
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

        ThemeUtils.setAlertTheme(alert);
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
        Scene scene = new Scene(root, 500, 380);
        ThemeUtils.setSceneTheme(scene, Controller.getImageViews());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();
    }


    private void updateExchangeArrowsImageView() {
        switch (Controller.getPlayer().getTheme()) {
            case DARK, SLATE -> currencyImageView.setImage(whiteExchangeArrows);
            case HACKER -> currencyImageView.setImage(hackerThemeExchangeArrows);
            default -> currencyImageView.setImage(blackExchangeArrows);
        }
    }

    private void updateTheme(boolean isApply){
        switch (themes.getValue()) {
            case "Light" -> Controller.getPlayer().setTheme(Theme.LIGHT);
            case "Dark" -> Controller.getPlayer().setTheme(Theme.DARK);
            case "Hacker" -> Controller.getPlayer().setTheme(Theme.HACKER);
            case "Slate" -> Controller.getPlayer().setTheme(Theme.SLATE);
            case "Rose" -> Controller.getPlayer().setTheme(Theme.ROSE);
        }

        if (isApply) {
            ThemeUtils.setSceneTheme(applyButton.getScene(),  Controller.getImageViews());
            updateExchangeArrowsImageView();
        }

        ThemeUtils.setSceneTheme(NodeUtils.getNavBarLabel().getScene(),  Controller.getImageViews());
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
            currencyBoxExchange.getItems().remove(0,7);
        }

        currencyBoxUser.getItems().add(Controller.getPlayer().getCurrency().toString());
        currencyBoxUser.setValue(Controller.getPlayer().getCurrency().toString());

        //Adds all currencies to currencyBoxExchange ChoiceBox
        for (int i = 0; i < Currency.values().length; i++) {
            currencyBoxExchange.getItems().add(Currency.values()[i].toString());
        }

        currencyBoxExchange.getItems().remove(Controller.getPlayer()
                .getCurrency().getIndex());
        currencyBoxExchange.setValue(currencyBoxExchange.getItems().get(0));
    }

    @FXML
    private void updateGame(ActionEvent event) throws IOException {
        UpdateChecker.showUpdateAlert();
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

        //Adds all themes to "themes" ChoiceBox
        for (int i = 0; i < Theme.values().length; i++) {
            themes.getItems().add(Theme.values()[i].getString());
        }

        themes.setValue(Controller.getPlayer().getTheme().getString());

        updateCurrencyBoxes(true);

        NodeUtils.setCurrencyBoxUser(currencyBoxUser);
        NodeUtils.setCurrencyBoxExchange(currencyBoxExchange);
        NodeUtils.setConvertButton(convertButton);

        updateExchangeArrowsImageView();

        GameUtils.bankruptcyCheck(false, true, false);
    }
}
