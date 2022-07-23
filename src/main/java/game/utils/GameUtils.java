package game.utils;

import game.App;
import game.controllers.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.util.Objects;

public class GameUtils {

    private static final AudioClip bankruptSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/bankrupt.mp3")).toString());

    public static void secureSerialize() throws IOException, ClassNotFoundException {
        Controller.setPlayer(GameData.deserialize());
        GameData.serialize();
    }

    public static void updateBalance(int outcome) {
        Controller.getPlayer().setBalance(Controller.getPlayer().getBalance() + outcome);
    }

    public static void refreshNavBarLabel() {
        NodeUtils.getNavBarLabel().setText("Current balance: " + Controller.getPlayer().getCurrency().getSymbol() +
                Controller.getPlayer().getBalance());
    }

    public static void refreshData() {
        try {
            secureSerialize();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        refreshNavBarLabel();
    }

    public static boolean isPlayerBankrupt() {
        return Controller.getPlayer().getBalance() <= 0;
    }

    public static void bankruptcyAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To play again, exit the game and re-open the game.");
        alert.setTitle("Bankruptcy Notice");
        alert.setHeaderText("You are bankrupt!");
        ThemeUtils.setAlertTheme(alert);

        bankruptSound.play();
        alert.showAndWait();

        refreshNavBarLabel();
    }

    public static void bankruptcyCheck(boolean enableAlert, boolean disableOptions, boolean disableGame) {
        refreshData();
        if (isPlayerBankrupt()){

            //Disables the game buttons in the main stage
            for (Button button : NodeUtils.getGameButtons()) {
                button.setDisable(true);
            }

            /*If a player goes bankrupt in the options screen
            * disable currency exchanging */
            if (disableOptions){
                NodeUtils.getCurrencyBoxUser().setDisable(true);
                NodeUtils.getCurrencyBoxExchange().setDisable(true);
                NodeUtils.getConvertButton().setDisable(true);
            }
            /*If a player goes bankrupt while playing a game
             * disable placing further bets*/
            if (disableGame){
                NodeUtils.getBetChoiceBox().setDisable(true);
                NodeUtils.getBetTextField().setDisable(true);
                NodeUtils.getPlaceBetButton().setDisable(true);
            }
            if (enableAlert){
                bankruptcyAlert();
            }

            refreshData();
        }
    }
}
