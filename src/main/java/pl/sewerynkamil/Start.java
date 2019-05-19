package pl.sewerynkamil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.game.Controller;
import pl.sewerynkamil.game.MouseControl;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.PieceMoves;

public class Start extends Application {

    private Board board = new Board();
    private Controller controller = new Controller(board);
    private KickScanner kickScanner = new KickScanner(board, controller);
    private PieceMoves pieceMoves = new PieceMoves(board, kickScanner, controller);
    private MouseControl mouseControl = new MouseControl(board, controller, pieceMoves, kickScanner);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(board.getGrid(), 612, 612, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseControl.getMouseClick());

        primaryStage.setTitle("Checkers Game - Kamil Seweryn");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
