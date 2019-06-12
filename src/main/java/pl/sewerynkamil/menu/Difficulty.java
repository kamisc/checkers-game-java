package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Author Kamil Seweryn
 */

public class Difficulty {

    public Difficulty() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Difficulty");
        alert.setContentText("Select difficulty level :-)");

        ButtonType easy = new ButtonType("Easy");
        ButtonType normal = new ButtonType("Normal");

        alert.getButtonTypes().setAll(easy, normal);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == easy){

        } else {

        }
    }
}
