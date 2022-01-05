module com.company.gamblescramble {
    requires javafx.controls;
    requires javafx.fxml;


    opens game to javafx.fxml;
    exports game;
    opens game.utils to javafx.fxml;
    exports game.utils;
    opens game.games to javafx.fxml;
    exports game.games;
    exports game.controllers;
    opens game.controllers to javafx.fxml;
}