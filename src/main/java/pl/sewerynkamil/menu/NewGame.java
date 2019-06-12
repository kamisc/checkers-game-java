package pl.sewerynkamil.menu;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import pl.sewerynkamil.Checkers;
import pl.sewerynkamil.board.Board;

/**
 * Author Kamil Seweryn
 */

public class NewGame {

    public void start(Board board) {
        board.getSaveLoadGame().removeFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("New Game");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to start new game?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        alert.getButtonTypes().setAll(yes, no);

        if(alert.showAndWait().get() == yes) {
            Platform.runLater(() -> new Checkers().start(new Stage()));
            Checkers.close();
        }
    }
}
