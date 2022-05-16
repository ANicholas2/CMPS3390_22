package afpfx.anicholas.finalprojectfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PointController extends CustomerController {
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private String selectedTab;

    @FXML
    TextField txtPoints;

    @FXML
    ListView<Customer> listPoints;

    public void initialize() {
        listPoints.setItems(customers);
    }

    private void onSelectedTab(Event e) {
        Tab tab = (Tab) e.getTarget();
        if(tab.isSelected()) {
            selectedTab = tab.getText();
            customers.clear();
            APIBridge.getList(selectedTab, customers);
        }
    }

    @FXML
    private void onBtnBackClicked(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PointApplication.class.getResource("customer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) txtPoints.getScene().getWindow();
        stage.setScene(scene);
    }
}
