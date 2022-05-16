package afpfx.anicholas.finalprojectfx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Customer {
    private final String name;
//    private int points;

    public Customer(String name) {
        this.name = name;
    }

//    public Customer(String name, int points) {
//        this.name = name;
//        this.points = points;
//    }

    public String getName() {
        return name;
    }

//    public int getPoints() {
//        return points;
//    }

    @Override
    public String toString() { return this.getName(); }

//    @Override
//    public int getInt() { return this.getPoints(); }
}
