package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class MouseControl {

    private Board board;
    private NormalMoves normalMoves;
    private QueenMoves queenMoves;
    private NormalKicks normalKicks;
    private QueenKicks queenKicks;
    private KickScanner kickScanner;
    private QueenKickScanner queenKickScanner;
    private Promote promote;

    private PositionsPieces pickedPosition;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private boolean isKick = true;

    public MouseControl(Board board, NormalMoves normalMoves, QueenMoves queenMoves /*KickScanner kickScanner*/,
                        NormalKicks normalKicks, QueenKicks queenKicks, QueenKickScanner queenKickScanner, Promote promote) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.queenMoves = queenMoves;
        this.normalKicks = normalKicks;
        this.queenKicks = queenKicks;
        this.queenKickScanner = queenKickScanner;
        this.promote = promote;

        this.kickScanner = new KickScanner(board);
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                System.out.println("Wrong place!");
            }

            if(playerTurn) {

                kickScanner.calculateAllPossibleWhiteKicks();
                queenKickScanner.calculateAllPossibleWhiteQueenKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                    if(!isKick
                            && kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            || queenKickScanner.getAllQueenPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor().isWhite()) {

                        board.pickPiece(clickPosition, pickedPosition, true);
                        pickedPosition = clickPosition;

                        if(board.getPiece(clickPosition).getPieceType().isNormal()){
                            normalKicks.kickMovesCalculator(clickPosition);
                        } else {
                            queenKicks.calculateAllPossibleQueenKicks(clickPosition);
                        }

                    } else {

                        if(normalKicks.getPossibleKickMoves().contains(clickPosition)
                                && board.getPiece(pickedPosition).getPieceType().isNormal()) {

                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKicks.getPossibleKickMoves().isEmpty()) {
                                playerTurn = false;
                                computerTurn = true;

                                isKick = false;

                                endKick();

                            } else {
                                isKick = true;
                            }

                        } else if(queenKicks.getPossibleKickMoves().contains(clickPosition)
                                && board.getPiece(pickedPosition).getPieceType().isQueen()){

                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(queenKicks.getPossibleKickMoves().isEmpty()) {
                                playerTurn = false;
                                computerTurn = true;

                                isKick = false;

                                endKick();

                            } else {
                                isKick = true;
                            }
                        }

                    }

                } else {
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, true);
                        queenMoves.normalQueenMoveCalculator(clickPosition);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isNormal()) {
                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = false;
                        computerTurn = true;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()) {

                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = false;
                        computerTurn = true;

                        endTurn();
                    }
                }
            }

            if(computerTurn) {

                kickScanner.calculateAllPossibleBlackKicks();
                queenKickScanner.calculateAllPossibleBlackQueenKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                    if(!isKick
                            && kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            || queenKickScanner.getAllQueenPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        if(board.getPiece(clickPosition).getPieceType().isNormal()){
                            normalKicks.kickMovesCalculator(clickPosition);
                        } else {
                            queenKicks.calculateAllPossibleQueenKicks(clickPosition);
                        }

                    } else {

                        if(normalKicks.getPossibleKickMoves().contains(clickPosition)
                                && board.getPiece(pickedPosition).getPieceType().isNormal()) {

                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKicks.getPossibleKickMoves().isEmpty()) {
                                playerTurn = false;
                                computerTurn = true;

                                isKick = false;

                                endKick();

                            } else {
                                isKick = true;
                            }
                        } else if(queenKicks.getPossibleKickMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()){

                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(queenKicks.getPossibleKickMoves().isEmpty()) {
                                playerTurn = true;
                                computerTurn = false;
                                isKick = false;

                                endKick();

                            } else {
                                isKick = true;
                            }
                        }
                    }

                } else {

                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, false);
                        queenMoves.normalQueenMoveCalculator(clickPosition);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isNormal()) {
                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = true;
                        computerTurn = false;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()) {

                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = true;
                        computerTurn = false;

                        endTurn();
                    }
                }
            }
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

    private void endTurn(){
        promote.promote();

        pickedPosition = null;

        normalMoves.getPossibleMoves().clear();
        kickScanner.clear();
        queenMoves.getPossibleQueenMoves().clear();
        queenKicks.clear();
    }

    private void endKick() {
        pickedPosition = null;

        promote.promote();

        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
    }
}