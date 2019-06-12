package pl.sewerynkamil;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;
import pl.sewerynkamil.game.MouseControl;
import pl.sewerynkamil.menu.LoadGame;
import pl.sewerynkamil.menu.NewGame;

/**
 * Author Kamil Seweryn
 */

public class Start extends Application {

    private static Stage primaryStage = new Stage();
    private Board board = new Board();
    private Graphics graphics = new Graphics(board);
    private MouseControl mouseControl = new MouseControl(graphics,
            board,
            board.getNormalMoves(),
            board.getQueenMoves(),
            board.getNormalKicks(),
            board.getQueenKicks(),
            board.getEndGame());

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene scene = new Scene(graphics.getBorderPane(), 585, 612, Color.BLACK);
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseControl.getMouseClick());

        primaryStage.setTitle("Checkers Game - Kamil Seweryn");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        graphics.getMenuDesign().getNewGame().setOnAction(e -> {
            new NewGame().start(board);
        });

        graphics.getMenuDesign().getLoadGame().setOnAction(e -> {
            new LoadGame().load(primaryStage);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void close() {
        primaryStage.close();
    }
}
