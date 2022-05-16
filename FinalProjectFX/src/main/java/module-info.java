module afpfx.anicholas.finalprojectfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;

    opens afpfx.anicholas.finalprojectfx to javafx.fxml;
    exports afpfx.anicholas.finalprojectfx;
}