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

public class EndGameInfo {

    private Board board;

    public EndGameInfo(Board board) {
        this.board = board;
    }

    public void whitesWin() {
        board.getSaveLoadGame().removeFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("White Player Win!"
                + "\n\nThe last saved game has been removed.");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Checkers().start(new Stage()));
            Checkers.close();
        } else {
            System.exit(0);
        }
    }

    public void blacksWin() {
        board.getSaveLoadGame().removeFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Black Player Win!" +
                "\n\nThe last saved game has been removed.");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Checkers().start(new Stage()));
            Checkers.close();
        } else {
            System.exit(0);
        }
    }

    public void draw() {
        board.getSaveLoadGame().removeFile();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End Game");
        alert.setHeaderText(null);
        alert.setContentText("Draw! No one win!"
                + "\n\nThe last saved game has been removed.");

        ButtonType newGame = new ButtonType("New Game");
        ButtonType exit = new ButtonType("Exit");

        alert.getButtonTypes().setAll(newGame, exit);

        if(alert.showAndWait().get() == newGame) {
            Platform.runLater(() -> new Checkers().start(new Stage()));
            Checkers.close();
        } else {
            System.exit(0);
        }
    }
}
