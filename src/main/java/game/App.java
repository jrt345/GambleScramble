package game;

import game.controllers.Controller;
import game.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    private static final String[] cssLocations = new String[2];

    public static String[] getCssLocations() {
        return cssLocations;
    }

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

            try {
                if (GameUtils.isPlayerBankrupt()){
                    Controller.getPlayer().setCurrency(Currency.USD);
                    Controller.getPlayer().setBalance(100);
                    GameData.serialize();
                }
            } catch (Exception e) {
                Controller.setPlayer(new Player());
                GameData.serialize();
            }
        }

        cssLocations[0] = Objects.requireNonNull(App.class.getResource("lighttheme.css")).toExternalForm();
        cssLocations[1] = Objects.requireNonNull(App.class.getResource("darktheme.css")).toExternalForm();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gambleScramble.fxml"));
        stage.setTitle("GambleScramble");
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 900, 570);
        GameUtils.setSceneTheme(scene, true, Controller.getImageView());
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();

        stage.setOnCloseRequest(e -> {
            try {
                GameUtils.secureSerialize();
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        if (UpdateChecker.isUpdateAvailable() && Controller.getPlayer().getCheckForUpdates()){
            try {
                GameUtils.showUpdateAlert();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}