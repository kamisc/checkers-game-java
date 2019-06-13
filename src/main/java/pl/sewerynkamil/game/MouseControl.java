package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

/**
 * Author Kamil Seweryn
 */

public class MouseControl {

    private Board board;

    public MouseControl(Board board) {
        this.board = board;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            board.handleMove(clickPosition);
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

}