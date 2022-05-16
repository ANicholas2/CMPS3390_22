package afpfx.anicholas.finalprojectfx;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField txtUserName;

    @FXML
    VBox vboxMessage;

    @FXML
    private void initialize() {
        vboxMessage.setVisible(false);
        vboxMessage.setManaged(false);

    }

    @FXML
    public void onLoginClicked(Event e) throws IOException {
        if(!validateForm()) return;

        FXMLLoader fxmlLoader = new FXMLLoader(PointApplication.class.getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.setScene(scene);
    }

    private boolean validateForm() {
        boolean nameIsValid = txtUserName.getText().matches("^\\w{2,9}[a-zA-Z0-9]$");
        if(nameIsValid){
            return true;
        } else {
            vboxMessage.setManaged(true);
            vboxMessage.setVisible(true);
            txtUserName.setStyle("-fx-border-color: #FF0000");
            return false;
        }
    }
}
