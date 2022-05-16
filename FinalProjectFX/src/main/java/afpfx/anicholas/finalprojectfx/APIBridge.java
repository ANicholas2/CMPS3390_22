package afpfx.anicholas.finalprojectfx;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.json.JSONObject;

public class APIBridge {
    private static final HttpClient client = HttpClient.newBuilder().build();
    private static String baseURL = "https://finalproject-7c132-default-rtdb.firebaseio.com/";

    public static void addCustomer(String selectedTab, Customer customer) {
        String path = URLEncoder.encode(customer.getName(), StandardCharsets.UTF_8);

        JSONObject obj = new JSONObject(customer);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + selectedTab + "/" + path + ".json"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(obj.toString()))
                .build();

        try {
            client.send(request, HttpResponse.BodyHandlers.discarding());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void getList(String selectedTab, ObservableList<Customer> customers) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + selectedTab + ".json"))
                .header("Content-Type", "application/json")
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    JSONObject obj = new JSONObject(response.body());
                    Iterator<String> keys = obj.keys();

                    while (keys.hasNext()) {
                        JSONObject jsonCustomer = obj.getJSONObject(keys.next());
                        Platform.runLater(() -> {
                            customers.add(new Customer(jsonCustomer.getString("name")));
                        });
                    }
                    System.out.println("Customer added to database");
                    return response;
                });
    }

    public static void deleteCustomer(String selectedTab, Customer selectedCustomer) {
        String path = URLEncoder.encode(selectedCustomer.getName(), StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + selectedTab + "/" + path + ".json"))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    System.out.println("Customer deleted from database");
                    return response;
                });
    }
}
