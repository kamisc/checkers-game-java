package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;

/**
 * Author Kamil Seweryn
 */

public class StartGame {

    public void start() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Start Game");
        alert.setHeaderText(null);
        alert.setContentText("Enjoy Yourself!");

        alert.showAndWait();
    }
}
