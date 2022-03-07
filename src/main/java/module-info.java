module a4.anicholas.weather {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires java.net.http;

    opens a4.anicholas.weather to javafx.fxml;
    exports a4.anicholas.weather;
}