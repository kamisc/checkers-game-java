package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.PieceMoves;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private Controller controller;
    private PieceMoves pieceMoves;
    private KickScanner kickScanner;

    private boolean playerTurn = true;
    private boolean computerTurn = false;

    public MouseControl(Board board, Controller controller, PieceMoves pieceMoves, KickScanner kickScanner){
        this.board = board;
        this.controller = controller;
        this.pieceMoves = pieceMoves;
        this.kickScanner = kickScanner;
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

            System.out.println(kickScanner.calculateAllPossibleBlackKicks());
            System.out.println(kickScanner.calculateAllPossibleWhiteKicks());

            }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}

