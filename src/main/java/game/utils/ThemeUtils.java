package game.utils;

import game.App;
import game.controllers.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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
        imageViews[0].setImage(ImageUtils.COIN_TOSS);
        imageViews[1].setImage(ImageUtils.DICE_ROLL);
        imageViews[2].setImage(ImageUtils.HAND_GUESS);
        imageViews[3].setImage(ImageUtils.ROCK_PAPER_SCISSORS);
        imageViews[4].setImage(ImageUtils.SCRATCH_OFF);
        imageViews[5].setImage(ImageUtils.POWERBALL);
        imageViews[6].setImage(ImageUtils.ROULETTE);
        imageViews[7].setImage(ImageUtils.CRAPS);
        imageViews[8].setImage(ImageUtils.BANK);
        imageViews[9].setImage(ImageUtils.STORE);
        imageViews[10].setImage(ImageUtils.SCRIPTS);
        imageViews[11].setImage(ImageUtils.BETS_LOG);

        switch (Controller.getPlayer().getTheme()) {
            case DARK -> {
                scene.getStylesheets().add(darkTheme);
                imageViews[2].setImage(ImageUtils.HAND_GUESS_V2);
            }
            case HACKER -> {
                scene.getStylesheets().add(hackerTheme);
                imageViews[0].setImage(ImageUtils.HackerTheme.COIN_TOSS);
                imageViews[1].setImage(ImageUtils.HackerTheme.DICE_ROLL);
                imageViews[2].setImage(ImageUtils.HackerTheme.HAND_GUESS);
                imageViews[3].setImage(ImageUtils.HackerTheme.ROCK_PAPER_SCISSORS);
                imageViews[4].setImage(ImageUtils.HackerTheme.SCRATCH_OFF);
                imageViews[5].setImage(ImageUtils.HackerTheme.POWERBALL);
                imageViews[6].setImage(ImageUtils.HackerTheme.ROULETTE);
                imageViews[7].setImage(ImageUtils.HackerTheme.CRAPS);
                imageViews[8].setImage(ImageUtils.HackerTheme.BANK);
                imageViews[9].setImage(ImageUtils.HackerTheme.STORE);
                imageViews[10].setImage(ImageUtils.HackerTheme.SCRIPTS);
                imageViews[11].setImage(ImageUtils.HackerTheme.BETS_LOG);
            }
            case SLATE -> {
                scene.getStylesheets().add(slateTheme);
                imageViews[2].setImage(ImageUtils.HAND_GUESS_V2);
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
