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
    private NormalQueenMoves normalQueenMoves;
    private NormalKick normalKick;
    private KickScanner kickScanner;
    private Promote promote;

    private PositionsPieces pickedPosition;

    private boolean playerTurn = true;
    private boolean computerTurn = false;
    private boolean isPicked = false;
    private boolean isKick = false;

    public MouseControl(Board board, NormalMoves normalMoves, NormalQueenMoves normalQueenMoves /*KickScanner kickScanner*/,
                        NormalKick normalKick, Promote promote) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.normalQueenMoves = normalQueenMoves;
        this.normalKick = normalKick;
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

            /*if(!board.isFieldNull(clickPosition)
                    && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE) {

            board.pickPiece(clickPosition, pickedPosition,true);
            pickedPosition = clickPosition;

            normalQueenMoves.normalQueenMoveCalculator(clickPosition);

            System.out.println(normalQueenMoves.getPossibleQueenMoves());
            System.out.println(normalQueenMoves.getPossibleQueenKicks());

        } else if(normalQueenMoves.getPossibleQueenMoves().contains(clickPosition)) {
            board.movePiece(clickPosition, pickedPosition);

            promote.promote();

            pickedPosition = null;
            playerTurn = false;
            computerTurn = true;

            promote.promote();

            normalQueenMoves.getPossibleQueenMoves().clear();
        }*/

            if(playerTurn) {

                kickScanner.calculateAllPossibleWhiteKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty()) {

                    isKick = true;

                    if(kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE) {

                        board.pickPiece(clickPosition, pickedPosition, true);
                        pickedPosition = clickPosition;

                        normalKick.kickMovesCalculator(pickedPosition);

                    } else if(isKick) {

                        if(normalKick.getPossibleKickMoves().contains(clickPosition)) {
                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKick.getPossibleKickMoves().isEmpty()) {
                                isKick = false;
                                pickedPosition = null;
                                playerTurn = false;
                                computerTurn = true;

                                promote.promote();

                                normalKick.getPossibleKickMoves().clear();
                                kickScanner.clear();
                            }
                        }

                    } else {
                        promote.promote();

                        pickedPosition = null;
                        playerTurn = false;
                        computerTurn = true;
                        isKick = false;

                        normalKick.getPossibleKickMoves().clear();
                        kickScanner.clear();
                    }
                } else {
                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.WHITE) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, true);
                        normalQueenMoves.normalQueenMoveCalculator(clickPosition);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isNormal()) {
                        board.movePiece(clickPosition, pickedPosition);

                        promote.promote();

                        pickedPosition = null;
                        playerTurn = false;
                        computerTurn = true;

                        normalMoves.getPossibleMoves().clear();
                        normalQueenMoves.getPossibleQueenMoves().clear();

                    } else if(normalQueenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        playerTurn = false;
                        computerTurn = true;

                        normalMoves.getPossibleMoves().clear();
                        normalQueenMoves.getPossibleQueenMoves().clear();
                    }

                }
            }

            if(computerTurn) {

                kickScanner.calculateAllPossibleBlackKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty()) {

                    isKick = true;

                    if(kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        normalKick.kickMovesCalculator(pickedPosition);

                    } else if(isKick) {

                        if(normalKick.getPossibleKickMoves().contains(clickPosition)) {
                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKick.getPossibleKickMoves().isEmpty()) {
                                isKick = false;
                                pickedPosition = null;
                                playerTurn = true;
                                computerTurn = false;

                                promote.promote();

                                normalKick.getPossibleKickMoves().clear();
                                kickScanner.clear();
                            }
                        }

                    } else {
                        promote.promote();

                        pickedPosition = null;
                        playerTurn = true;
                        computerTurn = false;
                        isKick = false;

                        normalKick.getPossibleKickMoves().clear();
                        kickScanner.clear();
                    }
                } else {

                    if(!board.isFieldNull(clickPosition)
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        normalMoves.normalMoveCalculator(clickPosition, false);
                        normalQueenMoves.normalQueenMoveCalculator(clickPosition);

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isNormal()) {
                        board.movePiece(clickPosition, pickedPosition);

                        promote.promote();

                        pickedPosition = null;
                        playerTurn = true;
                        computerTurn = false;

                        promote.promote();

                        normalMoves.getPossibleMoves().clear();
                        normalQueenMoves.getPossibleQueenMoves().clear();

                    } else if(normalQueenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()) {

                        board.movePiece(clickPosition, pickedPosition);

                        pickedPosition = null;
                        playerTurn = true;
                        computerTurn = false;

                        normalMoves.getPossibleMoves().clear();
                        normalQueenMoves.getPossibleQueenMoves().clear();
                    }
                }
            }
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }
}