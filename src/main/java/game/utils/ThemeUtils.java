package game.utils;

import game.App;
import game.controllers.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ThemeUtils {

    private static final String lightTheme = App.getCssLocations()[0];
    private static final String darkTheme = App.getCssLocations()[1];
    private static final String hackerTheme = App.getCssLocations()[2];

    /*Sets the stylesheet of GambleScramble, imageViews is based
     * on the all game logos which change to match the current theme*/
    public static void setSceneTheme(Scene scene, ImageView[] imageViews) {
        scene.getStylesheets().clear();
        imageViews[0].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/cointoss.png"))));
        imageViews[1].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/diceroll.png"))));
        imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess.png"))));
        imageViews[3].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/rockpaperscissors.png"))));

        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
            scene.getStylesheets().add(hackerTheme);
            imageViews[0].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png"))));
            imageViews[1].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png"))));
            imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png"))));
            imageViews[3].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png"))));
        } else if (Controller.getPlayer().getTheme() == Theme.DARK) {
            scene.getStylesheets().add(darkTheme);
            imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png"))));
        } else {
            scene.getStylesheets().add(lightTheme);
        }
    }

    //Sets the theme of an alert
    public static void setAlertTheme(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().clear();

        if (Controller.getPlayer().getTheme() == Theme.HACKER){
            dialogPane.getStylesheets().add(hackerTheme);
        } else if (Controller.getPlayer().getTheme() == Theme.DARK) {
            dialogPane.getStylesheets().add(darkTheme);
        } else {
            dialogPane.getStylesheets().add(lightTheme);
        }

        alert.setDialogPane(dialogPane);
    }
}
