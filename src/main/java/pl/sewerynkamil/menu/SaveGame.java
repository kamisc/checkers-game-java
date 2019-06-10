package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;
import pl.sewerynkamil.board.Board;

/**
 * Author Kamil Seweryn
 */

public class SaveGame {

    private Board board;

    public SaveGame(Board board) {
        this.board = board;
    }

    public void save() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Save Game");
        alert.setHeaderText(null);
        alert.setContentText("Game Saved!");

        board.getSaveLoadGame().saveGame();

        alert.showAndWait();
    }

}
