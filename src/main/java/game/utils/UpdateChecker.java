package game.utils;

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
}
