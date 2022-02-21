module a3.anicholas.contactsapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens a3.anicholas.contactsapp to javafx.fxml;
    exports a3.anicholas.contactsapp;
}