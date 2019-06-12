package pl.sewerynkamil.menu;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import pl.sewerynkamil.Checkers;

/**
 * Author Kamil Seweryn
 */

public class LoadGame {

    public void load() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Game");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want load last saving game?" +
                "\n\nWARINING! If you haven't a saved game, a new board will be loaded!");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().setAll(yes, no);

        if(alert.showAndWait().get() == yes) {
            Platform.runLater(() -> new Checkers().start(new Stage()));
            Checkers.close();
        }
    }

    public void loadInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Game");
        alert.setHeaderText(null);
        alert.setContentText("The last saved game has been Loaded!" +
                "\n\nIf you want to play new game, click New Game button in Game Menu." +
                "\n\nThe last saved game has been removed.");

        alert.showAndWait();
    }
}
