package game;

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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (Controller.getPlayer().getBalance() <= 0){
                Controller.getPlayer().setBalance(100);
                GameData.serialize();
            }
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gambleScramble.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 570);
        stage.setTitle("GambleScramble");
        stage.setScene(scene);
        GameUtils.setSceneTheme(scene, true);
        stage.show();

        stage.setOnCloseRequest(e -> {
            try {
                GameData.serialize();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}