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
    private boolean multiKick = false;

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
                            && pieceMoves.getPossibleWhitePieceMovesAfterKick().contains(position) && !multiKick) {
                        board.kickByWhite(position);
                        pieceMoves.moveWhiteAfterKick(position);

                        if(!pieceMoves.getPossibleWhitePieceMovesAfterKick().isEmpty()){
                            PositionsPieces kickPosition = position;
                            board.pickWhitePiece(kickPosition);
                            board.kickByWhite(position);
                            pieceMoves.moveWhiteAfterKick(position);
                        } else {
                            board.addPieceOnBoard(position, board.getWhitePieces().getWhitePieceImage());
                            playerTurn = false;
                            computerTurn = true;
                        }
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

            System.out.println(pieceMoves.getPossibleWhitePieceMovesAfterKick());


            /*if (computerTurn) {
                kickScanner.calculateAllPossibleBlackKicks();
                pieceMoves.allPossibleBlackMoves();

                if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {
                    PositionsPieces computerKick = computer.selectPosition(kickScanner.getAllBlackPiecesWhichKick());

                    board.pickBlackPiece(computerKick);
                    pieceMoves.moveBlackAfterKick(computerKick);

                    computerKick = computer.selectPosition(pieceMoves.getPossibleBlackPieceMovesAfterKick());

                    board.kickByBlack(computerKick);
                    playerTurn = true;
                    computerTurn = false;
                } else {
                    PositionsPieces computerMove = computer.selectPosition(pieceMoves.getAllPossibleBlack());

                    board.pickBlackPiece(computerMove);
                    pieceMoves.moveBlack(computerMove);

                    computerMove = computer.selectPosition(pieceMoves.getPossibleBlackPieceMoves());

                    board.moveBlackPiece(computerMove);
                    playerTurn = true;
                    computerTurn = false;
                }
            }*/

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
