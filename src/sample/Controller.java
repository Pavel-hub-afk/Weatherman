package sample;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONObject;
import sample.animation.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField CityField;

    @FXML
    private Button DiscoverButton;

    @FXML
    private Text temp;

    @FXML
    private Text max;

    @FXML
    private Text min;

    @FXML
    private Text press;

    @FXML
    void initialize() {
        DiscoverButton.setOnAction(actionEvent -> {
            String getCity = CityField.getText().trim();

            if (getCity.equals("Генка") || getCity.equals("генка")){
                CityField.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("view/vygu.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.setResizable(false);
                stage.show();

            }

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+getCity+"&appid=643005def2aea93e32c6b9c0e2808870");
                InputStream input = url.openStream();
                byte [] buffer = input.readAllBytes();
                String str = new String(buffer);

                if(!str.isEmpty()){
                    JSONObject json = new JSONObject(str);
                    temp.setText("Temperature: " + (json.getJSONObject("main").getInt("temp")-272) + "\u00B0");
                    max.setText("Max: " + (json.getJSONObject("main").getInt("temp_max")-272) + "\u00B0");
                    min.setText("Min: " + (json.getJSONObject("main").getInt("temp_min")-272) + "\u00B0");
                    press.setText("Pressure: " + (json.getJSONObject("main").getInt("pressure")-272) + "\u00B0");
                }

            } catch (MalformedURLException e) {
                System.out.println("No city");
            } catch (IOException e) {
                System.out.println("No city");

                temp.setText("Temperature: ");
                max.setText("Max: ");
                min.setText("Min: ");
                press.setText("Pressure: ");

                CityField.clear();
                CityField.setPromptText("Wrong city");
                Shake LogCity = new Shake(CityField);
            }
        });
    }
}
