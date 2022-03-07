package a4.anicholas.weather;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WeatherViewController implements UIBind {
    private APIBridge apiBridge;

    @FXML
    ComboBox comboLoc;

    @FXML
    ImageView imgWeatherIcon, imgWindDirection;

    @FXML
    Label labLatitude, labLongitude, labHigh, labLow, labCurrent, labFeelsLike, labPressure,
            labHumidity, labWeatherDescription, labWindSpeed;

    @FXML
    protected void onLocationChanged() {
        String loc = comboLoc.getSelectionModel().getSelectedItem().toString();
        apiBridge.GenerateWeatherModel(loc);
    }

    @FXML
    protected void initialize() {
        apiBridge = new APIBridge(this);
    }

    @Override
    public void mapUI(WeatherModel weatherModel) {
        setLabelText(labLatitude, String.valueOf(weatherModel.getLat()));
        setLabelText(labLongitude, String.valueOf(weatherModel.getLon()));
        setLabelText(labHigh, weatherModel.getTempMax() + "°");
        setLabelText(labLow, weatherModel.getTempMin() + "°");
        setLabelText(labCurrent, weatherModel.getTemp() + "°");
        setLabelText(labFeelsLike, weatherModel.getFeelsLike() + "°");
        setLabelText(labHumidity, weatherModel.getHumidity() + "%");
        setLabelText(labPressure, weatherModel.getPressure() + " hPa");
        setLabelText(labWeatherDescription, weatherModel.getWeatherDescription());
        imgWeatherIcon.setImage(new Image(weatherModel.getWeatherIcon()));
        setLabelText(labWindSpeed, weatherModel.getWindSpeed() + " MPH");
        imgWindDirection.setRotate(weatherModel.getWindDirection());
    }

    private void setLabelText(Label label, String val) {
        Platform.runLater(()-> label.setText(val));
    }
}