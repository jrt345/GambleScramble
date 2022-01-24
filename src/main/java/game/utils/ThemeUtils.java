package game.utils;

import game.App;
import game.controllers.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ThemeUtils {

    private static final String lightTheme = App.getCssLocations()[0];
    private static final String darkTheme = App.getCssLocations()[1];
    private static final String hackerTheme = App.getCssLocations()[2];
    private static final String slateTheme = App.getCssLocations()[3];
    private static final String roseTheme = App.getCssLocations()[4];

    /*Sets the stylesheet of GambleScramble, imageViews is based
     * on the all game logos which change to match the current theme*/
    public static void setSceneTheme(Scene scene, ImageView[] imageViews) {
        scene.getStylesheets().clear();
        imageViews[0].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/cointoss.png"))));
        imageViews[1].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/diceroll.png"))));
        imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess.png"))));
        imageViews[3].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/rockpaperscissors.png"))));

        if (Controller.getPlayer().getTheme() == Theme.ROSE) {
            scene.getStylesheets().add(roseTheme);
        } else if (Controller.getPlayer().getTheme() == Theme.SLATE){
            scene.getStylesheets().add(slateTheme);
            imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png"))));
        } else if (Controller.getPlayer().getTheme() == Theme.HACKER) {
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

        if (Controller.getPlayer().getTheme() == Theme.ROSE) {
            dialogPane.getStylesheets().add(roseTheme);
        } else if (Controller.getPlayer().getTheme() == Theme.SLATE) {
            dialogPane.getStylesheets().add(slateTheme);
        } else if (Controller.getPlayer().getTheme() == Theme.HACKER){
            dialogPane.getStylesheets().add(hackerTheme);
        } else if (Controller.getPlayer().getTheme() == Theme.DARK) {
            dialogPane.getStylesheets().add(darkTheme);
        } else {
            dialogPane.getStylesheets().add(lightTheme);
        }

        alert.setDialogPane(dialogPane);
    }

    public static void setAlertTheme(Alert alert, Label[] labels, Hyperlink[] hyperlinks) {
        setAlertTheme(alert);

        if (Controller.getPlayer().getTheme() != Theme.LIGHT){
            for (Label label : labels){
                if (Controller.getPlayer().getTheme() == Theme.SLATE) {
                    label.setStyle(" -fx-text-fill: #E0E8F6; ");
                } else if (Controller.getPlayer().getTheme() == Theme.HACKER) {
                    label.setStyle(" -fx-text-fill: #20C20E; ");
                } else if (Controller.getPlayer().getTheme() == Theme.DARK){
                    label.setStyle(" -fx-text-fill: #FFFFFF; ");
                }
            }

            for (Hyperlink hyperlink : hyperlinks){
                if (Controller.getPlayer().getTheme() == Theme.HACKER) {
                    hyperlink.setStyle(" -fx-text-fill: #3EFF29; ");
                }
            }
        }
    }
}
