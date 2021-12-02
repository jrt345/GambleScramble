module com.company.gamblescramble {
    requires javafx.controls;
    requires javafx.fxml;


    opens game to javafx.fxml;
    exports game;
    opens game.utils to javafx.fxml;
    exports game.utils;
}