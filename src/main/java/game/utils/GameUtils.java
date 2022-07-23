package game.utils;

import game.App;
import game.controllers.Controller;
import game.games.GameType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.util.Objects;

public class GameUtils {

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

    public static Image imageSetter(String[] options, String computerOption, Image[] images) {
        Image image = null;
        for (int i = 0;i < options.length;i++){
            if (computerOption.equals(options[i])){
                image = images[i];
            }
        }
        return image;
    }

    private static String computerOptionIntroSetter(GameType game, String computerOption) {
        String computerOptionIntro = null;
        switch (game) {
            case COINTOSS -> computerOptionIntro = "Just got " + computerOption + ". ";
            case DICEROLL -> computerOptionIntro = "Just rolled a " + computerOption + ". ";
            case HANDGUESS -> {
                if (computerOption.equals("0")) {
                    computerOptionIntro = "I have no fingers up. ";
                } else if (computerOption.equals("1")) {
                    computerOptionIntro = "I have " + computerOption + " finger up. ";
                } else {
                    computerOptionIntro = "I have " + computerOption + " fingers up. ";
                }
            }
        }
        return computerOptionIntro;
    }

    private static String statusSetter(boolean win, int outcome) {
        String status;
        if (!win){
            status = "You lose! You lost: -"
                    + Controller.getPlayer().getCurrency().getSymbol()
                    + outcome*-1;
        } else {
            status = "You win! You won: +"
                    + Controller.getPlayer().getCurrency().getSymbol()
                    + outcome;
        }
        return status;
    }

    private static final AudioClip coinTossSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/cointoss.mp3")).toString());
    private static final AudioClip diceRollSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/diceroll.mp3")).toString());
    private static final AudioClip handGuessSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/handguess.mp3")).toString());
    private static final AudioClip rpsSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/rockpaperscissors.mp3")).toString());

    private static final AudioClip bankruptSound = new AudioClip(Objects.requireNonNull(App.class.getResource("sound/bankrupt.mp3")).toString());

    public static void playGameSound(GameType game) {
        switch (game) {
            case COINTOSS -> coinTossSound.play();
            case DICEROLL -> diceRollSound.play();
            case HANDGUESS -> handGuessSound.play();
            case ROCKPAPERSCISSORS -> rpsSound.play();
        }
    }

    public static String outcomeSetter(GameType game, String computerOption, boolean win, int outcome) {
        playGameSound(game);
        return computerOptionIntroSetter(game, computerOption).concat(statusSetter(win, outcome));
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
