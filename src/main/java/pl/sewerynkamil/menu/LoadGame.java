package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.sewerynkamil.board.Board;

/**
 * Author Kamil Seweryn
 */

public class LoadGame {

    private Board board;

    public LoadGame(Board board) {
        this.board = board;
    }

    public void load() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Load Game");
        alert.setHeaderText(null);
        alert.setContentText("Game Loaded! If you want to play new game, click New Game button in Game Menu.");

        ButtonType yes = new ButtonType("Ok");
        // ButtonType no = new ButtonType("No");

        alert.getButtonTypes().setAll(yes);

        alert.showAndWait();

        /*if(alert.showAndWait().get() == yes) {
            Platform.runLater(() -> new Start().start(new Stage()));
            primaryStage.close();
        }*/
    }
}
