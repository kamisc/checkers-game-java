package pl.sewerynkamil.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class MouseControl {

    private Board board;
    private NormalMoves normalMoves;
    private QueenMoves queenMoves;
    private NormalKicks normalKicks;
    private QueenKicks queenKicks;
    private KickScanner kickScanner;
    private QueenKickScanner queenKickScanner;
    private Computer computer;
    private EndGame endGame;

    private PositionsPieces pickedPosition;

    private boolean turn = true;
    private boolean isKick = false;

    public MouseControl(Board board, NormalMoves normalMoves, QueenMoves queenMoves, NormalKicks normalKicks,
                        QueenKicks queenKicks, EndGame endGame) {
        this.board = board;
        this.normalMoves = normalMoves;
        this.queenMoves = queenMoves;
        this.normalKicks = normalKicks;
        this.queenKicks = queenKicks;
        this.endGame = endGame;

        this.kickScanner = new KickScanner(board);
        this.queenKickScanner = new QueenKickScanner(board);
        this.computer = new Computer(board);
    }

    private EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            PositionsPieces clickPosition = new PositionsPieces((int) ((event.getX() - 59) / 62), (int) ((event.getY() - 59) / 62));

            if(!clickPosition.isValidPosition()) {
                return;
            }

            board.handleMove(clickPosition);

            /*if(turn) {

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

                    if(computer.checkBlacksEnd()) {
                        break;
                    }

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

                            board.getQueenKicks().clear();

                            board.getNormalKicks().kickMovesCalculator(pickedPosition);

                            if (!board.getNormalKicks().getPossibleKickMoves().isEmpty()) {

                                computerKick = computer.selectPosition(board.getNormalKicks().getPossibleKickMoves());

                                board.kickPiece(computerKick, pickedPosition);

                                if (board.getNormalKicks().getPossibleKickMoves().isEmpty()) {

                                    endKick();

                                    setTurn(true);
                                }
                            }

                        } else {

                            board.getNormalKicks().clear();

                            board.getQueenKicks().calculateAllPossibleQueenKicks(pickedPosition);

                            if (!board.getQueenKicks().getPossibleKickMoves().isEmpty()) {

                                computerKick = computer.selectPosition(board.getQueenKicks().getPossibleKickMoves());

                                board.kickPiece(computerKick, pickedPosition);

                                if (board.getQueenKicks().getPossibleKickMoves().isEmpty()) {

                                    endKick();

                                    setTurn(true);
                                }
                            }
                        }

                    } else {

                        normalMoves.allPossibleBlackMoves();

                        PositionsPieces computerMove = computer.selectPosition(normalMoves.getAllPossibleBlack());

                        pickedPosition = computerMove;

                        if (board.getPiece(computerMove).getPieceType().isNormal()) {

                            board.getNormalMoves().clear();

                            board.getNormalMoves().normalMoveCalculator(computerMove, false);

                            computerMove = computer.selectPosition(board.getNormalMoves().getPossibleMoves());

                            board.movePiece(computerMove, pickedPosition);

                            setTurn(true);

                            endTurn();

                        } else {

                            board.getQueenMoves().normalQueenMoveCalculator(computerMove);

                            computerMove = computer.selectPosition(board.getQueenMoves().getPossibleQueenMoves());

                            board.movePiece(computerMove, pickedPosition);

                            setTurn(true);

                            endTurn();
                        }
                    }

                } while(!turn);
            }*/
        }
    };

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

    protected void endTurn() {
        pickedPosition = null;

        board.promote();
        endGame.checkEndGame(board.getBoard().keySet());

        normalMoves.clear();
        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
    }

    protected void endKick() {
        pickedPosition = null;

        board.promote();
        endGame.checkEndGame(board.getBoard().keySet());

        normalKicks.clear();
        queenKicks.clear();
        kickScanner.clear();
        queenKickScanner.clear();
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
}