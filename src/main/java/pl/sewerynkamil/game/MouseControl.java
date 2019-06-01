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
    private boolean isKick = false;

    public MouseControl(Board board, NormalMoves normalMoves, QueenMoves queenMoves, NormalKicks normalKicks,
                        QueenKicks queenKicks, Promote promote) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.queenMoves = queenMoves;
        this.normalKicks = normalKicks;
        this.queenKicks = queenKicks;
        this.promote = promote;

        this.kickScanner = new KickScanner(board);
        this.queenKickScanner = new QueenKickScanner(board);
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            if(playerTurn) {

                kickScanner.calculateAllPossibleWhiteKicks();
                queenKickScanner.calculateAllPossibleWhiteQueenKicks();

                if(!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                    if((kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            || queenKickScanner.getAllQueenPiecesWhichKick().contains(clickPosition))
                            && board.getPiece(clickPosition).getPieceColor().isWhite()
                            && !isKick) {

                        board.pickPiece(clickPosition, pickedPosition, true);
                        pickedPosition = clickPosition;

                        if(board.getPiece(clickPosition).getPieceType().isNormal()) {

                            queenKicks.clear();
                            normalKicks.kickMovesCalculator(clickPosition);

                        } else {

                            normalKicks.clear();
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
                                && board.getPiece(pickedPosition).getPieceType().isQueen()) {

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

                        if(board.getPiece(clickPosition).getPieceType().isNormal()) {

                            queenMoves.getPossibleQueenMoves().clear();
                            normalMoves.normalMoveCalculator(clickPosition, true);

                        } else {

                            normalMoves.getPossibleMoves().clear();
                            queenMoves.normalQueenMoveCalculator(clickPosition);

                        }

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = false;
                        computerTurn = true;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && pickedPosition != null) {

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

                    if((kickScanner.getAllPiecesWhichKick().contains(clickPosition)
                            || queenKickScanner.getAllQueenPiecesWhichKick().contains(clickPosition))
                            && board.getPiece(clickPosition).getPieceColor() == Piece.Color.BLACK
                            && !isKick) {

                        board.pickPiece(clickPosition, pickedPosition,true);
                        pickedPosition = clickPosition;

                        if(board.getPiece(clickPosition).getPieceType().isNormal()) {

                            queenKicks.clear();
                            normalKicks.kickMovesCalculator(clickPosition);

                        } else {

                            normalKicks.clear();
                            queenKicks.calculateAllPossibleQueenKicks(clickPosition);

                        }

                    } else {

                        if(normalKicks.getPossibleKickMoves().contains(clickPosition)
                                && board.getPiece(pickedPosition).getPieceType().isNormal()) {

                            board.kickPiece(clickPosition, pickedPosition);
                            pickedPosition = clickPosition;

                            if(normalKicks.getPossibleKickMoves().isEmpty()) {

                                playerTurn = true;
                                computerTurn = false;

                                isKick = false;

                                endKick();

                            } else {

                                isKick = true;

                            }

                        } else if(queenKicks.getPossibleKickMoves().contains(clickPosition)
                            && board.getPiece(pickedPosition).getPieceType().isQueen()) {

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

                        if(board.getPiece(clickPosition).getPieceType().isNormal()) {

                            queenMoves.getPossibleQueenMoves().clear();
                            normalMoves.normalMoveCalculator(clickPosition, true);

                        } else {

                            normalMoves.getPossibleMoves().clear();
                            queenMoves.normalQueenMoveCalculator(clickPosition);

                        }

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        playerTurn = true;
                        computerTurn = false;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && pickedPosition != null) {

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

    private void endTurn() {
        pickedPosition = null;

        promote.promote();

        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
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