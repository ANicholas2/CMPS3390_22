module a8.anicholas.javafxtodo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens a8.anicholas.javafxtodo to javafx.fxml;
    exports a8.anicholas.javafxtodo;
}