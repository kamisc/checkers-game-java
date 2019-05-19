package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.MovesCalculator;
import pl.sewerynkamil.moves.PieceMoves;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private Controller controller;
    private PieceMoves pieceMoves;
    private MovesCalculator movesCalculator;

    private boolean playerTurn = true;
    private boolean computerTurn = false;

    public MouseControl(Board board, Controller controller, PieceMoves pieceMoves, MovesCalculator movesCalculator){
        this.board = board;
        this.controller = controller;
        this.pieceMoves = pieceMoves;
        this.movesCalculator = movesCalculator;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces position = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if (playerTurn) {
                /*pieceMoves.moveAfterKick(position);
                movesCalculator.calculateAllPossibleBlackKicks();*/
                /*if (!movesCalculator.calculateAllPossibleBlackKicks().isEmpty() && !pieceMoves.getPossibleBlackPieceMovesAfterKick().isEmpty()) {
                    if(pieceMoves.getPossibleBlackPieceMovesAfterKick().contains(position))
                        board.pickBlackPiece(position);
                    } else */if (controller.checkCanSelectBlackPiece(position)) {
                        board.pickBlackPiece(position);
                        pieceMoves.moveBlack(position);
                    } else if (controller.isFieldNull(position) &&
                            pieceMoves.getPossibleBlackPieceMoves().contains(position)) {
                        board.moveBlackPiece(position);
                        playerTurn = false;
                        computerTurn = true;
                    }
                }

                if (computerTurn) {
                    if (controller.checkCanSelectWhitePiece(position)) {
                        board.pickWhitePiece(position);
                        // pieceMoves.getPossibleWhitePieceMoves().clear();
                        pieceMoves.moveWhite(position);
                    } else if (controller.isFieldNull(position) &&
                            pieceMoves.getPossibleWhitePieceMoves().contains(position)) {
                        board.moveWhitePiece(position);
                        playerTurn = true;
                        computerTurn = false;
                    }
                }
            }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}

