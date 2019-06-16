package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class QueenKicks {

    private Board board;

    private Set<PositionsPieces> possibleKickMoves = new HashSet<>();
    private Set<PositionsPieces> possibleKicks = new HashSet<>();

    public QueenKicks(Board board) {
        this.board = board;
    }

    public void calculateAllPossibleQueenKicks(PositionsPieces position) {
        possibleKickMoves.clear();
        possibleKicks.clear();

        for(int i = 1; i < 8; i++) {

            PositionsPieces upLeft = new PositionsPieces(position.getCol() - i, position.getRow() - i);

            if(calculatePossibleKick(position, upLeft, -1, -1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {

            PositionsPieces downLeft = new PositionsPieces(position.getCol() - i, position.getRow() + i);

            if(calculatePossibleKick(position, downLeft, -1, +1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {

            PositionsPieces upRight = new PositionsPieces(position.getCol() + i, position.getRow() - i);

            if(calculatePossibleKick(position, upRight, +1, -1)) {
                break;
            }
        }

        for(int i = 1; i < 8; i++) {

            PositionsPieces downRight = new PositionsPieces(position.getCol() + i, position.getRow() + i);

            if(calculatePossibleKick(position, downRight, +1, +1)) {
                break;

            }
        }
    }

    private boolean calculatePossibleKick(PositionsPieces actualPosition, PositionsPieces checkPosition, int col, int row) {
        if(!checkPosition.isValidPosition()
                && !new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row).isValidPosition()) {
            return true;
        }

        if(!board.isFieldNull(checkPosition)) {
            if(board.getPiece(actualPosition).getPieceColor() != board.getPiece(checkPosition).getPieceColor()
                    && board.isFieldNull(new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row))) {
                possibleKickMoves.add(new PositionsPieces(checkPosition.getCol() + col, checkPosition.getRow() + row));
                possibleKicks.add(checkPosition);
            }
            return true;
        } else {
            return false;
        }
    }

    public Set<PositionsPieces> getPossibleKickMoves() {
        return possibleKickMoves;
    }

    public Set<PositionsPieces> getPossibleKicks() {
        return possibleKicks;
    }

    public void clear() {
        possibleKickMoves.clear();
        possibleKicks.clear();
    }
}
