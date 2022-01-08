package game.utils;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NodeUtils {
    /*This class stores several nodes from some stages as static objects,
    * so that they can be modified as the game progresses*/

    private static Label navBarLabel;

    private static Button[] gameButtons;

    private static ChoiceBox<String> currencyBoxUser;

    private static ChoiceBox<String> currencyBoxExchange;

    private static Button convertButton;

    private static ChoiceBox<String> betChoiceBox;

    private static TextField betTextField;

    private static Button placeBetButton;

    public static Label getNavBarLabel() {
        return navBarLabel;
    }

    public static void setNavBarLabel(Label navBarLabel) {
        NodeUtils.navBarLabel = navBarLabel;
    }

    public static Button[] getGameButtons() {
        return gameButtons;
    }

    public static void setGameButtons(Button[] gameButtons) {
        NodeUtils.gameButtons = gameButtons;
    }

    public static ChoiceBox<String> getCurrencyBoxUser() {
        return currencyBoxUser;
    }

    public static void setCurrencyBoxUser(ChoiceBox<String> currencyBoxUser) {
        NodeUtils.currencyBoxUser = currencyBoxUser;
    }

    public static ChoiceBox<String> getCurrencyBoxExchange() {
        return currencyBoxExchange;
    }

    public static void setCurrencyBoxExchange(ChoiceBox<String> currencyBoxExchange) {
        NodeUtils.currencyBoxExchange = currencyBoxExchange;
    }

    public static Button getConvertButton() {
        return convertButton;
    }

    public static void setConvertButton(Button optionsButton) {
        NodeUtils.convertButton = optionsButton;
    }

    public static ChoiceBox<String> getBetChoiceBox() {
        return betChoiceBox;
    }

    public static void setBetChoiceBox(ChoiceBox<String> betChoiceBox) {
        NodeUtils.betChoiceBox = betChoiceBox;
    }

    public static TextField getBetTextField() {
        return betTextField;
    }

    public static void setBetTextField(TextField betTextField) {
        NodeUtils.betTextField = betTextField;
    }

    public static Button getPlaceBetButton() {
        return placeBetButton;
    }

    public static void setPlaceBetButton(Button placeBetButton) {
        NodeUtils.placeBetButton = placeBetButton;
    }
}
