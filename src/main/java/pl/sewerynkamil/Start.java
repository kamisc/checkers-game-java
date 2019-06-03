package pl.sewerynkamil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;

public class Start extends Application {

    private Board board = new Board();
    private Graphics graphics = new Graphics(board);

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(graphics.getGrid(), 612, 612, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, graphics.getMouseControl().getMouseClick());

        primaryStage.setTitle("Checkers Game - Kamil Seweryn");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
