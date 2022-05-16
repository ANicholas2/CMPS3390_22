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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    private String selectedTab;

    @FXML
    TextField txtCustomer;

    @FXML
    ListView<Customer> listCustomers;
    @FXML
    public void initialize() {
        listCustomers.setItems(customers);
    }

    @FXML
    private void onSelectedTab(Event e) {
        Tab tab = (Tab) e.getTarget();
        if(tab.isSelected()) {
            selectedTab = tab.getText();
            customers.clear();
            APIBridge.getList(selectedTab, customers);
        }
    }

    @FXML
    private void onCustomerClicked(MouseEvent click) {
        if(click.getClickCount() == 2) {
            Customer selectedCustomer = listCustomers.getSelectionModel().getSelectedItem();
            customers.remove(selectedCustomer);
            APIBridge.deleteCustomer(selectedTab, selectedCustomer);
        }
    }

    @FXML
    private void onBtnAdd(Event e) {
        Customer customer = new Customer(txtCustomer.getText());
        customers.add(customer);
        txtCustomer.setText(null);
        APIBridge.addCustomer(selectedTab, customer);
    }

    @FXML
    private void onBtnPointsClicked(Event e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PointApplication.class.getResource("points-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) txtCustomer.getScene().getWindow();
        stage.setScene(scene);
    }
}