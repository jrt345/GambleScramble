package game.utils;

import game.Controller;
import game.GameController;
import game.Main;
import game.games.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class GameUtils {
    public static int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }

    public static void loadGame(Game game, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameTemplate.fxml"));
        Parent root = fxmlLoader.load();

        GameController controller = fxmlLoader.getController();
        controller.setGame(game);
        Stage stage = new Stage();

        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 450, 240));
        stage.setResizable(false);
        stage.show();
    }

    public static String generateComputerChoice(String[] options, int min, int max) {
        return options[getRandomNumber(min, max)];
    }

    public static int getOutcome(String userOption, String computerOption, int bet, int multiplier) {
        if (userOption.equals(computerOption)){
            return ((bet*multiplier)-bet);
        } else {
            return bet*-1;
        }
    }

    public static void updateBalance(int outcome){
        Controller.getPlayer().setBalance(Controller.getPlayer().getBalance() + outcome);
    }

    public static Image imageSetter(String[] options, String computerOption, Image[] images){
        Image image = null;
        for (int i = 0;i < options.length;i++){
            if (computerOption.equals(options[i])){
                image = images[i];
            }
        }
        return image;
    }

    private static String computerOptionIntroSetter(Game game, String computerOption){
        String computerOptionIntro = null;
        switch (game){
            case COINTOSS -> computerOptionIntro = "Just got " + computerOption + ". ";
            case DICEROLL -> computerOptionIntro = "Just rolled a " + computerOption + ". ";
            case HANDGUESS -> {
                if (computerOption.equals("0")){
                    computerOptionIntro = "I have no fingers up. ";
                } else if (computerOption.equals("1")){
                    computerOptionIntro = "I have " + computerOption + " finger up. ";
                } else {
                    computerOptionIntro = "I have " + computerOption + " fingers up. ";
                }
            }
        }
        return computerOptionIntro;
    }

    private static String statusSetter(boolean win, int outcome){
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

    public static String outcomeSetter(Game game, String computerOption, boolean win, int outcome){
        return computerOptionIntroSetter(game, computerOption).concat(statusSetter(win, outcome));
    }

    public static void gameOutcome(String game, String result, Image image) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
        alert.setTitle("Results");
        alert.setHeaderText(game);

        alert.setGraphic(new ImageView(image));

        alert.showAndWait();
    }

    public static boolean isPlayerBankrupt(){
        return Controller.getPlayer().getBalance() <= 0;
    }
}
