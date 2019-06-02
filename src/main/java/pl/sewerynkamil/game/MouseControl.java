package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class MouseControl {

    private Board board;
    private NormalMoves normalMoves;
    private QueenMoves queenMoves;
    private NormalKicks normalKicks;
    private QueenKicks queenKicks;
    private KickScanner kickScanner;
    private QueenKickScanner queenKickScanner;
    private Promote promote;
    private Computer computer;

    private PositionsPieces pickedPosition;

    private boolean turn = true;
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
        this.computer = new Computer();
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            if(turn) {

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

                                turn = false;

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

                                turn = false;

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

                            normalMoves.normalMoveCalculator(clickPosition, true);

                        } else {

                            queenMoves.normalQueenMoveCalculator(clickPosition);

                        }

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        turn = false;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        turn = false;

                        endTurn();
                    }
                }
            }

            if(!turn) {

                do {

                    kickScanner.calculateAllPossibleBlackKicks();
                    queenKickScanner.calculateAllPossibleBlackQueenKicks();

                    if (!kickScanner.getAllPossibleKicks().isEmpty() || !queenKickScanner.getAllPossibleQueenKicks().isEmpty()) {

                        Set<PositionsPieces> allBlacks = new HashSet<>();

                        allBlacks.addAll(kickScanner.getAllPiecesWhichKick());
                        allBlacks.addAll(queenKickScanner.getAllQueenPiecesWhichKick());

                        PositionsPieces computerKick = computer.selectPosition(allBlacks);

                        pickedPosition = computerKick;

                        board.pickPiece(computerKick, pickedPosition, true);

                        if (board.getPiece(pickedPosition).getPieceType().isNormal()) {

                            queenKicks.clear();

                            normalKicks.kickMovesCalculator(pickedPosition);

                            if (!normalKicks.getPossibleKickMoves().isEmpty()) {

                                computerKick = computer.selectPosition(normalKicks.getPossibleKickMoves());

                                board.kickPiece(computerKick, pickedPosition);

                                if (normalKicks.getPossibleKickMoves().isEmpty()) {

                                    endKick();

                                    turn = true;
                                }
                            }

                        } else {

                            normalKicks.clear();

                            queenKicks.calculateAllPossibleQueenKicks(pickedPosition);

                            if (!queenKicks.getPossibleKickMoves().isEmpty()) {

                                computerKick = computer.selectPosition(queenKicks.getPossibleKickMoves());

                                board.kickPiece(computerKick, pickedPosition);

                                if (queenKicks.getPossibleKickMoves().isEmpty()) {

                                    endKick();

                                    turn = true;
                                }
                            }
                        }

                    } else {

                        normalMoves.allPossibleBlackMoves();

                        PositionsPieces computerMove = computer.selectPosition(normalMoves.getAllPossibleBlack());

                        pickedPosition = computerMove;

                        if (board.getPiece(computerMove).getPieceType().isNormal()) {

                            normalMoves.clear();

                            normalMoves.normalMoveCalculator(computerMove, false);

                            computerMove = computer.selectPosition(normalMoves.getPossibleMoves());

                            board.movePiece(computerMove, pickedPosition);

                            turn = true;

                            endTurn();

                        } else {

                            queenMoves.normalQueenMoveCalculator(computerMove);

                            computerMove = computer.selectPosition(queenMoves.getPossibleQueenMoves());

                            board.movePiece(computerMove, pickedPosition);

                            turn = true;

                            endTurn();
                        }
                    }

                } while(!turn);
            }

/*            if(!turn) {

                kickScanner.calculateAllPossibleBlackKicks();
                queenKickScanner.calculateAllPossibleBlackQueenKicks();

                System.out.println(queenKickScanner.getAllQueenPiecesWhichKick());
                System.out.println(queenKickScanner.getAllPossibleQueenKicks());

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

                                turn = true;

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

                                turn = true;

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
                            normalMoves.normalMoveCalculator(clickPosition, false);

                        } else {

                            normalMoves.getPossibleMoves().clear();
                            queenMoves.normalQueenMoveCalculator(clickPosition);

                        }

                    } else if(normalMoves.getPossibleMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        turn = true;

                        endTurn();

                    } else if(queenMoves.getPossibleQueenMoves().contains(clickPosition)
                            && pickedPosition != null) {

                        board.movePiece(clickPosition, pickedPosition);

                        turn = true;

                        endTurn();
                    }
                }
            }*/
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

    private void endTurn() {
        pickedPosition = null;

        promote.promote();

        normalMoves.clear();
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