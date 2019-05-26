package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.NormalMoves;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private PositionsPieces pickedPosition;

    private NormalMoves normalMoves;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private boolean isPicked = false;

    public MouseControl(Board board, NormalMoves normalMoves) {
        this.board = board;
        this.normalMoves = normalMoves;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                System.out.println("Wrong place!");
            }

            if(playerTurn){
                if(isPicked){
                    // Change pick piece
                    if(!pickedPosition.equals(clickPosition)
                            && !board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){

                        board.pickPiece(pickedPosition, false);
                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.moveCalculator(clickPosition, true);

                        // Move piece
                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        isPicked = false;
                        playerTurn = false;
                        computerTurn = true;
                    }
                } else {
                    // Pick new piece
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE){

                        isPicked = true;

                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.moveCalculator(clickPosition, true);
                    }
                }
            }

            if(computerTurn){
                if(isPicked){
                    // Change pick piece
                    if(!pickedPosition.equals(clickPosition)
                            && !board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){

                        board.pickPiece(pickedPosition, false);
                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.moveCalculator(clickPosition, false);

                    // Move piece
                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        isPicked = false;
                        playerTurn = true;
                        computerTurn = false;
                    }
                } else {
                    // Pick new piece
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK){

                        isPicked = true;

                        pickedPosition = clickPosition;
                        board.pickPiece(clickPosition, true);
                        normalMoves.moveCalculator(clickPosition, false);
                    }
                }
            }
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}