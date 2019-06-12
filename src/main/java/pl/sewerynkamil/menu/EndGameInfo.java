package pl.sewerynkamil.menu;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import pl.sewerynkamil.Start;

/**
 * Author Kamil Seweryn
 */

public class EndGameInfo {

    public void whitesWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("White Player Win!");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Start().start(new Stage()));
            Start.close();
        } else {
            System.exit(0);
        }
    }

    public void blacksWin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Black Player Win!");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Start().start(new Stage()));
            Start.close();
        } else {
            System.exit(0);
        }
    }

    public void draw() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Draw! No one win!");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Start().start(new Stage()));
            Start.close();
        } else {
            System.exit(0);
        }
    }
}
