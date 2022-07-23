package game;

import game.controllers.Controller;
import game.utils.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    //String array to store the locations of the css stylesheets
    private static final String[] cssLocations = new String[5];

    public static String[] getCssLocations() {
        return cssLocations;
    }

    @Override
    public void start(Stage stage) throws IOException {

        //Checks if "userdata" folder exists
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

        cssLocations[0] = Objects.requireNonNull(App.class.getResource("lightTheme.css")).toExternalForm();
        cssLocations[1] = Objects.requireNonNull(App.class.getResource("darkTheme.css")).toExternalForm();
        cssLocations[2] = Objects.requireNonNull(App.class.getResource("hackerTheme.css")).toExternalForm();
        cssLocations[3] = Objects.requireNonNull(App.class.getResource("slateTheme.css")).toExternalForm();
        cssLocations[4] = Objects.requireNonNull(App.class.getResource("roseTheme.css")).toExternalForm();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gambleScramble.fxml"));
        stage.setTitle("GambleScramble");
        stage.setResizable(false);
        Scene scene = new Scene(fxmlLoader.load(), 1450, 870);
        ThemeUtils.setSceneTheme(scene,  Controller.getImageViews());
        stage.setScene(scene);
        stage.getIcons().add(ImageUtils.LOGO);
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
                UpdateChecker.showUpdateAlert();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}