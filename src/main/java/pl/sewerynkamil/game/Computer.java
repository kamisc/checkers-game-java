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
            pieceMoves.allPossibleMovingPieces();

            PositionsPieces computerNormalMove = selectPosition(pieceMoves.getAllPossibleBlack());

            if (!kickScanner.getAllPossibleBlackKicks().isEmpty() && !kickScanner.getAllPossibleBlackMovesAfterKick().isEmpty()) {
                PositionsPieces computerMove = selectPosition(kickScanner.getAllBlackPiecesWhichKick());

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

            } else if (controller.isFieldNull(computerNormalMove) && pieceMoves.getPossibleBlackPieceMoves().contains(computerNormalMove)) {
                board.moveBlackPiece(computerNormalMove);
                playerTurn = true;
                computerTurn = false;
            }
        }
    }
}
