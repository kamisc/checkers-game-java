package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.PieceMoves;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Random;

public class MouseControl {

    private Board board;
    private Controller controller;
    private PieceMoves pieceMoves;
    private KickScanner kickScanner;
    private Random random = new Random();

    private boolean playerTurn = true;
    private boolean computerTurn = false;

    public MouseControl(Board board, Controller controller, PieceMoves pieceMoves, KickScanner kickScanner) {
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
                kickScanner.calculateAllPossibleWhiteKicks();

                if (!kickScanner.getAllPossibleWhiteKicks().isEmpty() && !kickScanner.getAllPossibleWhiteMovesAfterKick().isEmpty()){

                    if (kickScanner.getAllWhitePiecesWhichKick().contains(position)) {
                        board.pickWhitePiece(position);
                        pieceMoves.moveWhiteAfterKick(position);

                    } else if (controller.isFieldNull(position)
                            && pieceMoves.getPossibleWhitePieceMovesAfterKick().contains(position)) {
                        board.kickByWhite(position);
                        playerTurn = false;
                        computerTurn = true;
                    }

                } else if (controller.checkCanSelectWhitePiece(position)) {
                    board.pickWhitePiece(position);
                    pieceMoves.moveWhite(position);

                } else if (controller.isFieldNull(position) && pieceMoves.getPossibleWhitePieceMoves().contains(position)) {
                    board.moveWhitePiece(position);
                    playerTurn = false;
                    computerTurn = true;
                }
            }

            if (computerTurn) {
                kickScanner.calculateAllPossibleBlackKicks();

                if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {

                    PositionsPieces compPosition = new PositionsPieces(random.nextInt(), random.nextInt());

                    if (kickScanner.getAllBlackPiecesWhichKick().contains(position)) {
                        board.pickBlackPiece(position);
                        pieceMoves.moveBlackAfterKick(position);

                    } else if (controller.isFieldNull(position)
                            && pieceMoves.getPossibleBlackPieceMovesAfterKick().contains(position)) {
                        board.kickByBlack(position);
                        playerTurn = true;
                        computerTurn = false;
                    }

                } else if (controller.checkCanSelectBlackPiece(position)) {
                    board.pickBlackPiece(position);
                    pieceMoves.moveBlack(position);

                } else if (controller.isFieldNull(position) && pieceMoves.getPossibleBlackPieceMoves().contains(position)) {
                    board.moveBlackPiece(position);
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

// 2nd player
/*
        if (computerTurn) {
        kickScanner.calculateAllPossibleBlackKicks();

        if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {

        if (kickScanner.getAllBlackPiecesWhichKick().contains(position)) {
        board.pickBlackPiece(position);
        pieceMoves.moveBlackAfterKick(position);

        } else if (controller.isFieldNull(position)
        && pieceMoves.getPossibleBlackPieceMovesAfterKick().contains(position)) {
        board.kickByBlack(position);
        playerTurn = true;
        computerTurn = false;
        }

        } else if (controller.checkCanSelectBlackPiece(position)) {
        board.pickBlackPiece(position);
        pieceMoves.moveBlack(position);

        } else if (controller.isFieldNull(position) && pieceMoves.getPossibleBlackPieceMoves().contains(position)) {
        board.moveBlackPiece(position);
        playerTurn = true;
        computerTurn = false;
        }
        }*/
