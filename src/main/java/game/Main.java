package game;

import game.utils.GameData;
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

        if (!isUserDataCreated){
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
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gambleScramble.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 550);
        stage.setTitle("GambleScramble");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}