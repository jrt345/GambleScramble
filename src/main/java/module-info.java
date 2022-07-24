module game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens game to javafx.fxml;
    exports game;
    opens game.utils to javafx.fxml;
    exports game.utils;
    opens game.games to javafx.fxml;
    exports game.games;
    opens game.controllers to javafx.fxml;
    exports game.controllers;
    exports game.games.scratchoff;
    opens game.games.scratchoff to javafx.fxml;
}