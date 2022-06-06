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
        imageViews[4].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/scratchoff.png"))));
        imageViews[5].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/powerball.png"))));
        imageViews[6].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/roulette.png"))));
        imageViews[7].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/craps.png"))));
        imageViews[8].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/bank.png"))));
        imageViews[9].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/store.png"))));
        imageViews[10].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/scripts.png"))));
        imageViews[11].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/betslog.png"))));

        switch (Controller.getPlayer().getTheme()) {
            case DARK -> {
                scene.getStylesheets().add(darkTheme);
                imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png"))));
            }
            case HACKER -> {
                scene.getStylesheets().add(hackerTheme);
                imageViews[0].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/cointoss.png"))));
                imageViews[1].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/diceroll.png"))));
                imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/handguess.png"))));
                imageViews[3].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/rockpaperscissors.png"))));
                imageViews[4].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/scratchoff.png"))));
                imageViews[5].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/powerball.png"))));
                imageViews[6].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/roulette.png"))));
                imageViews[7].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/craps.png"))));
                imageViews[8].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/bank.png"))));
                imageViews[9].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/store.png"))));
                imageViews[10].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/scripts.png"))));
                imageViews[11].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/hackertheme/betslog.png"))));
            }
            case SLATE -> {
                scene.getStylesheets().add(slateTheme);
                imageViews[2].setImage(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/handguess-v2.png"))));
            }
            case ROSE -> scene.getStylesheets().add(roseTheme);
            default -> scene.getStylesheets().add(lightTheme);
        }
    }

    //Sets the theme of an alert
    public static void setAlertTheme(Alert alert) {
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().clear();

        switch (Controller.getPlayer().getTheme()) {
            case DARK -> dialogPane.getStylesheets().add(darkTheme);
            case HACKER -> dialogPane.getStylesheets().add(hackerTheme);
            case SLATE -> dialogPane.getStylesheets().add(slateTheme);
            case ROSE -> dialogPane.getStylesheets().add(roseTheme);
            default -> dialogPane.getStylesheets().add(lightTheme);
        }

        alert.setDialogPane(dialogPane);
    }

    public static void setAlertTheme(Alert alert, Label[] labels, Hyperlink[] hyperlinks) {
        setAlertTheme(alert);

        if (Controller.getPlayer().getTheme() != Theme.LIGHT || Controller.getPlayer().getTheme() != Theme.ROSE){
            for (Label label : labels){
                if (Controller.getPlayer().getTheme() == Theme.SLATE) {
                    label.setStyle(" -fx-text-fill: #E0E8F6; ");
                } else if (Controller.getPlayer().getTheme() == Theme.HACKER) {
                    label.setStyle(" -fx-text-fill: #20C20E; ");

                    for (Hyperlink hyperlink : hyperlinks) {
                        if (Controller.getPlayer().getTheme() == Theme.HACKER) {
                            hyperlink.setStyle(" -fx-text-fill: #3EFF29; ");
                        }
                    }
                } else if (Controller.getPlayer().getTheme() == Theme.DARK){
                    label.setStyle(" -fx-text-fill: #FFFFFF; ");
                }
            }
        }
    }
}
