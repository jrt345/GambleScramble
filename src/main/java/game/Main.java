package game;

import game.controllers.Controller;
import game.utils.Currency;
import game.utils.GameData;
import game.utils.GameUtils;
import game.utils.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        boolean isUserDataCreated = new File(GameData.userDataDir).exists();

        if (!isUserDataCreated || !(new File(GameData.userDataDir.concat("player.dat")).exists())){
            isUserDataCreated = new File(GameData.userDataDir).mkdirs();
            Controller.setPlayer(new Player());
            GameData.serialize();
        }

        if (isUserDataCreated){
            try {
                Controller.setPlayer(GameData.deserialize());
            } catch (Exception e) {
                Controller.setPlayer(new Player());
                GameData.serialize();
            }
            if (Controller.getPlayer().getBalance() <= 0){
                Controller.getPlayer().setCurrency(Currency.USD);
                Controller.getPlayer().setBalance(100);
                GameData.serialize();
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gambleScramble.fxml"));
        stage.setTitle("GambleScramble");
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 900, 570);
        GameUtils.setSceneTheme(scene, true, Controller.getImageView());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(e -> {
            try {
                GameData.serialize();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}