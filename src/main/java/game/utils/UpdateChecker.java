package game.utils;

import game.controllers.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker {

    private static final String currentVersionString = "1.0.0";

    //Gets the latest version of GambleScramble (as a string) from the GitHub repository
    public static String getLatestVersion() {
        try {
            /*Link to README.md; containing a comment "<!--Version-MAJOR.MINOR.PATCH-->"
             * in line 1; stating the latest version of the GitHub repository*/
            URL latestVersion = new URL("https://raw.githubusercontent.com/jrt345/GambleScramble/master/README.md");
            URLConnection gitHub = latestVersion.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(gitHub.getInputStream()));

            //Converts the first line of README.md into a version string
            return in.readLine().replaceAll("<", "")
                    .replaceAll("!", "")
                    .replaceAll("-", "")
                    .replaceAll("Version", "")
                    .replaceAll(">", "")
                    .replaceAll("\\s", "");
        } catch (IOException e) {
            return "0.0.0";
        }

    }

    //Returns if there is a new version available
    public static boolean isUpdateAvailable() {
        try {
            String latestVersionString = getLatestVersion();

            //Turn version strings into an array of version increments (MAJOR.MINOR.PATCH)
            String[] currentVersionStringIncrements = currentVersionString.split("\\.");
            String[] latestVersionStringIncrements = latestVersionString.split("\\.");

            /*int arrays of the current and latest version increments
            * Array index key: 0 = MAJOR, 1 = MINOR, 2 = PATCH*/
            int[] currentVersionNumericalIncrements = new int[3];
            int[] latestVersionNumericalIncrements = new int[3];

            //Convert version increments into int arrays
            for (int i = 0;i < 3;i++){
                currentVersionNumericalIncrements[i] = Integer.parseInt(currentVersionStringIncrements[i]);
                latestVersionNumericalIncrements[i] = Integer.parseInt(latestVersionStringIncrements[i]);
            }

            /*Compares current and latest MAJOR MINOR and PATCH versions and if any
            * of the latest version increments are greater than the current version
            * increments return true, otherwise return false*/
            if (currentVersionNumericalIncrements[0] < latestVersionNumericalIncrements[0]){
                return true;
            } else if (currentVersionNumericalIncrements[1] < latestVersionNumericalIncrements[1]) {
                return true;
            } else {
                return currentVersionNumericalIncrements[2] < latestVersionNumericalIncrements[2];
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static void setUpdateAlertTheme(Label[] labels, Hyperlink[] hyperlinks) {
        if (Controller.getPlayer().getTheme() != Theme.LIGHT){
            for (Label label : labels){
                if (Controller.getPlayer().getTheme() == Theme.HACKER) {
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

    public static void showUpdateAlert() throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Update available!");

        VBox primaryVbox = new VBox();
        VBox vboxPt1 = new VBox();
        VBox vboxPt2 = new VBox();

        //vboxPt1 contains the introLabel and flowPane (with label and repoLink)
        Label introLabel = new Label("A new version of GambleScramble has been released! Version: " + UpdateChecker.getLatestVersion());

        Label label = new Label(" is available at ");
        Hyperlink repoLink = new Hyperlink("github.com/jrt345/GambleScramble.");

        FlowPane flowPane = new FlowPane(label, repoLink);
        flowPane.setAlignment(Pos.CENTER);

        vboxPt1.getChildren().addAll(introLabel, flowPane);
        vboxPt1.setAlignment(Pos.CENTER);

        //vboxPt2 contains the downloadLabel and downloadLink
        Label downloadLabel = new Label("You can download version: " + UpdateChecker.getLatestVersion() + " here: ");
        Hyperlink downloadLink = new Hyperlink("https://github.com/jrt345/GambleScramble/releases/latest");

        vboxPt2.getChildren().addAll(downloadLabel, downloadLink);
        vboxPt2.setAlignment(Pos.CENTER);

        /*primaryVbox contains vboxPt1, an empty label and vboxPt2;
         * the empty label provides a gap between vboxPt1 and vboxPt2*/
        primaryVbox.getChildren().addAll(vboxPt1, new Label(), vboxPt2);
        primaryVbox.setAlignment(Pos.CENTER);

        //Open main page of GambleScramble repository
        repoLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/GambleScramble";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        //Open downloads page of latest GambleScramble release
        downloadLink.setOnAction( (evt) -> {
            alert.close();
            Runtime rt = Runtime.getRuntime();
            String url = "https://github.com/jrt345/GambleScramble/releases/latest";
            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } );

        alert.getDialogPane().contentProperty().set(primaryVbox);

        ThemeUtils.setAlertTheme(alert);
        setUpdateAlertTheme(new Label[]{introLabel, label, downloadLabel},
                new Hyperlink[]{repoLink, downloadLink});
        alert.showAndWait();
    }
}
