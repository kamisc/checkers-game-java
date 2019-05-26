package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;

    private boolean playerTurn = true;
    private boolean computerTurn = false;

    public MouseControl(Board board) {
        this.board = board;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces position = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            board.pickPiece(position, true);
            System.out.println(board.getPiece(position));
            System.out.println(board.isFieldNull(position));
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}