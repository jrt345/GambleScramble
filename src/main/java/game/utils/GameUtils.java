package game.utils;

import game.App;
import game.controllers.Controller;
import game.controllers.GameController;
import game.games.Game;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class GameUtils {

    /*Set the player object in Controller to data from player.dat, and then serialize
    * the data to properly and accurately serialize user data*/
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

    public static void showUpdateAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Update available!");

        VBox primaryVbox = new VBox();
        VBox vboxPt1 = new VBox();
        VBox vboxPt2 = new VBox();

        //vboxPt1 contains the introLabel and flowPane (with label and repoLink)
        Label introLabel = new Label("A new version of GambleScramble has been released! Version: " + UpdateChecker.getLatestVersion());

        Label label = new Label(" is available at ");
        Hyperlink repoLink = new Hyperlink("github.com/jrt345/GambleScramble.");

        FlowPane flowPane = new FlowPane(label, repoLink);
        flowPane.setAlignment(Pos.CENTER);

        vboxPt1.getChildren().addAll(introLabel, flowPane);
        vboxPt1.setAlignment(Pos.CENTER);

        //vboxPt2 contains the downloadLabel and downloadLink
        Label downloadLabel = new Label("You can download version: " + UpdateChecker.getLatestVersion() + " here: ");
        Hyperlink downloadLink = new Hyperlink("https://github.com/jrt345/GambleScramble/releases/latest");

        vboxPt2.getChildren().addAll(downloadLabel, downloadLink);
        vboxPt2.setAlignment(Pos.CENTER);

        /*primaryVbox contains vboxPt1, an empty label and vboxPt2;
        * the empty label provides a gap between vboxPt1 and vboxPt2*/
        primaryVbox.getChildren().addAll(vboxPt1, new Label(), vboxPt2);
        primaryVbox.setAlignment(Pos.CENTER);

        //Open main page of GambleScramble repository
        repoLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/GambleScramble";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        //Open downloads page of latest GambleScramble release
        downloadLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/GambleScramble/releases/latest";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        alert.getDialogPane().contentProperty().set(primaryVbox);

        alert.showAndWait();
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    //Load gameTemplate.fxml with different attributes based on the game
    public static void loadGame(Game game, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gameTemplate.fxml"));
        Parent root = fxmlLoader.load();

        GameController controller = fxmlLoader.getController();
        controller.setGame(game);
        Stage stage = new Stage();

        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 450, 240);
        ThemeUtils.setSceneTheme(scene, Controller.getImageViews());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();
    }

    //Generate a random choice for the games based on the game's options
    public static String generateComputerChoice(String[] options, int min, int max) {
        return options[getRandomNumber(min, max)];
    }

    //Returns the profit or loss a player receives after placing their bet
    public static int getOutcome(String userOption, String computerOption, int bet, int multiplier) {
        if (userOption.equals(computerOption)) {
            return ((bet*multiplier));
        } else {
            return bet*-1;
        }
    }

    //Returns an image to match the randomly generated outcome
    public static Image imageSetter(String[] options, String computerOption, Image[] images) {
        Image image = null;
        for (int i = 0;i < options.length;i++){
            if (computerOption.equals(options[i])){
                image = images[i];
            }
        }
        return image;
    }

    //Returns a description of the randomly generated outcome
    private static String computerOptionIntroSetter(Game game, String computerOption) {
        String computerOptionIntro = null;
        switch (game){
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

    //Returns a string describing if the player won or lost and how much
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

    //Combines computerOptionIntroSetter and statusSetter into one string
    public static String outcomeSetter(Game game, String computerOption, boolean win, int outcome) {
        return computerOptionIntroSetter(game, computerOption).concat(statusSetter(win, outcome));
    }

    public static void gameOutcomeAlert(String game, String result, Image image) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
        alert.setTitle("Results");
        alert.setHeaderText(game);

        alert.setGraphic(new ImageView(image));
        alert.showAndWait();
    }

    public static boolean isPlayerBankrupt() {
        return Controller.getPlayer().getBalance() <= 0;
    }

    public static void bankruptcyAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "To play again, exit the game and re-open the game.");
        alert.setTitle("Bankruptcy Notice");
        alert.setHeaderText("You are bankrupt!");
        alert.showAndWait();

        refreshNavBarLabel();
    }

    /*Checks if the user is bankrupt an if to show an alert and what buttons
    * to disable if the user is bankrupt*/
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
