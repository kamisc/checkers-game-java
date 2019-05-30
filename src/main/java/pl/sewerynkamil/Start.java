package pl.sewerynkamil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.sewerynkamil.board.Board;

public class Start extends Application {

    private Board board = new Board();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(board.getGrid(), 612, 612, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, board.getMouseControl().getMouseClick());

        primaryStage.setTitle("Checkers Game - Kamil Seweryn");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
