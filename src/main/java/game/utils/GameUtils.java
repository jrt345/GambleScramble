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
            return bet*multiplier;
        } else {
            return bet*-1;
        }
    }

    public static void updateBalance(int outcome){
        Controller.getPlayer().setBalance(Controller.getPlayer().getBalance() + outcome);
    }

    public static void gameOutcome(String game, String result, Image image) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
        alert.setTitle("Results");
        alert.setHeaderText(game);

        alert.setGraphic(new ImageView(image));

        alert.showAndWait();
    }
}
