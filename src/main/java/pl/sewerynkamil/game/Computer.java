package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.moves.KickScanner;
import pl.sewerynkamil.moves.PieceMoves;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Random;
import java.util.Set;

public class Computer {

    private Random random = new Random();

    public PositionsPieces selectPosition(Set<PositionsPieces> positions){
        Object[] object = positions.toArray();
        return (PositionsPieces) object[random.nextInt(object.length)];
    }

    public void computerMove(boolean computerTurn, boolean playerTurn, KickScanner kickScanner, Board board, PieceMoves pieceMoves, Controller controller) {
        if (computerTurn) {
            kickScanner.calculateAllPossibleBlackKicks();
            pieceMoves.allPossibleBlackMoves();

            if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {
                PositionsPieces computerKick = selectPosition(kickScanner.getAllBlackPiecesWhichKick());

                board.pickBlackPiece(computerKick);
                pieceMoves.moveBlackAfterKick(computerKick);

                computerKick = selectPosition(pieceMoves.getPossibleBlackPieceMovesAfterKick());

                board.kickByBlack(computerKick);
                board.removePieceFromBoard(computerKick);

                pieceMoves.moveBlackAfterKick(computerKick);

                if(!pieceMoves.getPossibleBlackPieceMovesAfterKick().isEmpty()){
                    board.pickBlackPiece(computerKick);
                    board.kickByBlack(computerKick);
                    pieceMoves.moveBlackAfterKick(computerKick);
                } else {
                    board.removePieceFromBoard(computerKick);
                    board.addPieceOnBoard(computerKick, board.getBlackPieces().getBlackPieceImage());
                    playerTurn = true;
                    computerTurn = false;
                }
            } else {
                PositionsPieces computerMove = selectPosition(pieceMoves.getAllPossibleBlack());

                board.pickBlackPiece(computerMove);
                pieceMoves.moveBlack(computerMove);

                computerMove = selectPosition(pieceMoves.getPossibleBlackPieceMoves());

                board.moveBlackPiece(computerMove);
                playerTurn = true;
                computerTurn = false;
            }
        }
    }
}
