package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.PieceMoves;
import pl.sewerynkamil.moves.Promote;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private Controller controller;
    private PieceMoves pieceMoves;
    private KickScanner kickScanner;
    private Computer computer;
    private Promote promote;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    PositionsPieces queenPosition;

    public MouseControl(Board board, Controller controller, PieceMoves pieceMoves, KickScanner kickScanner,
                        Computer computer, Promote promote) {
        this.board = board;
        this.controller = controller;
        this.pieceMoves = pieceMoves;
        this.kickScanner = kickScanner;
        this.computer = computer;
        this.promote = promote;
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces position = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if (playerTurn) {
                kickScanner.calculateAllPossibleWhiteKicks();

                if (!kickScanner.getAllPossibleWhiteKicks().isEmpty()){

                    if (kickScanner.getAllWhitePiecesWhichKick().contains(position)) {
                        board.pickWhitePiece(position);
                        pieceMoves.moveWhiteAfterKick(position);

                    } else if (controller.isFieldNull(position)
                        && pieceMoves.getPossibleWhitePieceMovesAfterKick().contains(position)) {

                        board.kickByWhite(position);
                        pieceMoves.moveWhiteAfterKick(position);

                        if(!pieceMoves.getPossibleWhitePieceMovesAfterKick().isEmpty()){
                            PositionsPieces kickPosition = position;
                            board.pickWhitePiece(kickPosition);
                            board.kickByWhite(position);
                            pieceMoves.moveWhiteAfterKick(position);
                        } else {
                            board.removePieceFromBoard(position);
                            board.addPieceOnBoard(position, board.getWhitePieces().getWhitePieceImage());
                            promote.promoteWhite();
                            playerTurn = false;
                            computerTurn = true;
                            kickScanner.clear();
                            pieceMoves.clear();
                        }
                    }

                } else {
                    if (controller.checkCanSelectWhitePiece(position)) {
                        board.pickWhitePiece(position);

                        if(board.getWhitePieces().getPiece(position).getPieceColor().isQueenWhite()){

                        } else {
                            pieceMoves.moveWhite(position);
                        }

                    } else if(controller.isFieldNull(position)
                            && board.getWhitePieces().getPiece(queenPosition).getPieceColor().isQueenWhite()){

                        board.moveWhitePiece(position);
                        playerTurn = false;
                        computerTurn = true;
                        kickScanner.clear();
                        pieceMoves.clear();

                    } else if (controller.isFieldNull(position) && pieceMoves.getPossibleWhitePieceMoves().contains(position)) {
                        board.moveWhitePiece(position);
                        promote.promoteWhite();
                        playerTurn = false;
                        computerTurn = true;
                        kickScanner.clear();
                        pieceMoves.clear();
                    }
                }
            }

            if (computerTurn) {
                do {
                    kickScanner.calculateAllPossibleBlackKicks();
                    pieceMoves.allPossibleBlackMoves();

                    if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {
                        PositionsPieces computerKick = computer.selectPosition(kickScanner.getAllBlackPiecesWhichKick());

                        board.pickBlackPiece(computerKick);
                        pieceMoves.moveBlackAfterKick(computerKick);

                        computerKick = computer.selectPosition(pieceMoves.getPossibleBlackPieceMovesAfterKick());

                        board.kickByBlack(computerKick);
                        board.removePieceFromBoard(computerKick);

                        pieceMoves.moveBlackAfterKick(computerKick);

                        if (!pieceMoves.getPossibleBlackPieceMovesAfterKick().isEmpty()) {
                            board.pickBlackPiece(computerKick);
                            board.kickByBlack(computerKick);
                            pieceMoves.moveBlackAfterKick(computerKick);
                        } else {
                            board.removePieceFromBoard(computerKick);
                            board.addPieceOnBoard(computerKick, board.getBlackPieces().getBlackPieceImage());
                            promote.promoteBlack();
                            playerTurn = true;
                            computerTurn = false;
                            kickScanner.clear();
                            pieceMoves.clear();
                        }

                    } else {
                        PositionsPieces computerMove = computer.selectPosition(pieceMoves.getAllPossibleBlack());

                        board.pickBlackPiece(computerMove);
                        pieceMoves.moveBlack(computerMove);

                        computerMove = computer.selectPosition(pieceMoves.getPossibleBlackPieceMoves());

                        board.moveBlackPiece(computerMove);
                        promote.promoteBlack();
                        playerTurn = true;
                        computerTurn = false;
                        kickScanner.clear();
                        pieceMoves.clear();
                    }
                } while(computerTurn);
            }
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}

/*if (computerTurn) {
                kickScanner.calculateAllPossibleBlackKicks();

                if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {

                    if (kickScanner.getAllBlackPiecesWhichKick().contains(position)) {
                        board.pickBlackPiece(position);
                        pieceMoves.moveBlackAfterKick(position);

                    } else if (controller.isFieldNull(position)
                            && pieceMoves.getPossibleBlackPieceMovesAfterKick().contains(position)) {
                        board.kickByBlack(position);
                        pieceMoves.moveBlackAfterKick(position);

                        if(!pieceMoves.getPossibleBlackPieceMovesAfterKick().isEmpty()){
                            PositionsPieces kickPosition = position;
                            board.pickBlackPiece(kickPosition);
                            board.kickByBlack(position);
                            pieceMoves.moveBlackAfterKick(position);
                        } else {
                            board.removePieceFromBoard(position);
                            board.addPieceOnBoard(position, board.getBlackPieces().getBlackPieceImage());
                            playerTurn = true;
                            computerTurn = false;
                            pieceMoves.clear();
                        }
                    }

                } else if (controller.checkCanSelectBlackPiece(position)) {
                    board.pickBlackPiece(position);
                    pieceMoves.moveBlack(position);

                } else if (controller.isFieldNull(position) && pieceMoves.getPossibleBlackPieceMoves().contains(position)) {
                    board.moveBlackPiece(position);
                    playerTurn = true;
                    computerTurn = false;
                    pieceMoves.clear();
                }
            }*/