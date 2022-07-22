package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.ThemeUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public abstract class SimpleGame implements Game {

    private String title = "GameTitle";
    private String details = "Odds: 1:11, Payout: 10x";
    private String prompt = "Choose a number between 0-10";
    private String[] options = null;
    private Image image = null;

    private int bet = 0;
    private String userOption;

    protected abstract void play();

    @Override
    public void run() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("gameTemplate.fxml"));
        Parent root = fxmlLoader.load();

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
}
