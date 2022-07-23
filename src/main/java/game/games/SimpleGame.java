package game.games;

import game.App;
import game.controllers.Controller;
import game.utils.GameData;
import game.utils.GameUtils;
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
    private int outcome = 0;

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

    int calculateOutcome() {
        if (userOption.equals(computerOption)) {
            return ((bet*multiplier));
        } else {
            return bet*-1;
        }
    }

    Image getComputerOptionImage(Image[] images) {
        Image image = null;
        for (int i = 0;i < options.length;i++){
            if (computerOption.equals(options[i])){
                image = images[i];
            }
        }
        return image;
    }

    void updateBalance() throws IOException {
        GameUtils.updateBalance(-bet);

        GameData.serialize();
        GameUtils.refreshData();

        computerOption = getRandomOption();

        outcome = calculateOutcome();

        if (getOutcome() > 0) {
            GameUtils.updateBalance(outcome);
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

    String getResultMessage(boolean winner){
        return getComputerOptionResult().concat(getOutcomeStatus(winner));
    }

    abstract String getComputerOptionResult();

    private String getOutcomeStatus(boolean winner) {
        String status;
        if (!winner){
            status = "You lose! You lost: -"
                    + Controller.getPlayer().getCurrency().getSymbol()
                    + outcome*-1;
        } else {
            status = "You win! You won: +"
                    + Controller.getPlayer().getCurrency().getSymbol()
                    + outcome;
        }
        return status;
    }

    protected int getMultiplier() {
        return multiplier;
    }

    protected void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected String getDetails() {
        return details;
    }

    protected void setDetails(String details) {
        this.details = details;
    }

    protected String getPrompt() {
        return prompt;
    }

    protected void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    protected String[] getOptions() {
        return options;
    }

    protected void setOptions(String[] options) {
        this.options = options;
    }

    protected Image getImage() {
        return image;
    }

    protected void setImage(Image image) {
        this.image = image;
    }

    protected int getBet() {
        return bet;
    }

    protected void setBet(int bet) {
        this.bet = bet;
    }

    protected String getUserOption() {
        return userOption;
    }

    protected void setUserOption(String userOption) {
        this.userOption = userOption;
    }

    protected String getComputerOption() {
        return computerOption;
    }

    protected void setComputerOption(String computerOption) {
        this.computerOption = computerOption;
    }

    protected int getOutcome() {
        return outcome;
    }

    protected void setOutcome(int outcome) {
        this.outcome = outcome;
    }
}
