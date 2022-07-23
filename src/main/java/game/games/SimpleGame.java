package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.ThemeUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class SimpleGame implements Game {

    private int multiplier = 1;

    private String title = "GameTitle";
    private String details = "Odds: 1:11, Payout: 10x";
    private String prompt = "Choose a number between 0-10";
    private String[] options = null;
    private Image image = null;

    private int bet = 0;
    private String userOption;
    private String computerOption;

    protected abstract void play() throws IOException;

    @Override
    public void run() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gameTemplate.fxml"));
        Parent root = fxmlLoader.load();

        GameController controller = fxmlLoader.getController();
        controller.setSimpleGame(this);
        controller.loadGame();

        Stage stage = new Stage();

        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root, 450, 240);
        ThemeUtils.setSceneTheme(scene, Controller.getImageViews());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/gamblescramble/gamblescramble.png"))));
        stage.show();
    }

    String getRandomOption() {
        return options[new Random().nextInt(options.length)];
    }

    int getOutcome(String option) {
        if (userOption.equals(option)) {
            return ((bet*multiplier));
        } else {
            return bet*-1;
        }
    }

    void showOutcome(String result, Image image) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, result);
        alert.setTitle("Results");
        alert.setHeaderText(getTitle());

        alert.setGraphic(new ImageView(image));
        ThemeUtils.setAlertTheme(alert);

        alert.showAndWait();
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getUserOption() {
        return userOption;
    }

    public void setUserOption(String userOption) {
        this.userOption = userOption;
    }

    public String getComputerOption() {
        return computerOption;
    }

    public void setComputerOption(String computerOption) {
        this.computerOption = computerOption;
    }
}
