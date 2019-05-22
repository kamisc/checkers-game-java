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
    private Computer computer;

    private boolean playerTurn = true;
    private boolean computerTurn = false;

    public MouseControl(Board board, Controller controller, PieceMoves pieceMoves, KickScanner kickScanner, Computer computer) {
        this.board = board;
        this.controller = controller;
        this.pieceMoves = pieceMoves;
        this.kickScanner = kickScanner;
        this.computer = computer;
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
                pieceMoves.allPossibleMovingPieces();

                PositionsPieces computerNormalMove = computer.selectPosition(pieceMoves.getAllPossibleBlack());
                System.out.println(computerNormalMove);

                if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {
                    PositionsPieces computerMove = computer.selectPosition(kickScanner.getAllBlackPiecesWhichKick());

                    if (kickScanner.getAllBlackPiecesWhichKick().contains(computerMove)) {
                        board.pickBlackPiece(computerMove);
                        pieceMoves.moveBlackAfterKick(computerMove);

                    } else if (controller.isFieldNull(computerMove)
                            && pieceMoves.getPossibleBlackPieceMovesAfterKick().contains(computerMove)) {
                        board.kickByBlack(computerMove);
                        playerTurn = true;
                        computerTurn = false;
                    }

                } else if (controller.checkCanSelectBlackPiece(computerNormalMove)) {
                    board.pickBlackPiece(computerNormalMove);
                    pieceMoves.moveBlack(computerNormalMove);

                    PositionsPieces computerMove = computer.selectPosition(pieceMoves.getPossibleBlackPieceMoves());

                    if(controller.isFieldNull(computerMove) && pieceMoves.getPossibleBlackPieceMoves().contains(computerMove)){
                        board.moveBlackPiece(computerMove);
                        playerTurn = true;
                        computerTurn = false;
                    }

                }
            }

            System.out.println(pieceMoves.getAllPossibleBlack());

            // computer.computerMove(computerTurn, playerTurn, kickScanner, board, pieceMoves, controller);

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
