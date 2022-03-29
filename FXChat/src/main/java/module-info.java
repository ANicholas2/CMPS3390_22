module a6.anicholas.fxchat.fxchat {
    requires javafx.controls;
    requires javafx.fxml;


    opens a6.anicholas.fxchat to javafx.fxml;
    exports a6.anicholas.fxchat;
}