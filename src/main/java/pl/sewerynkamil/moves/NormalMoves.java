package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class NormalMoves {

    private Board board;

    public NormalMoves(Board board) {
        this.board = board;
    }

    private Set<PositionsPieces> possibleMoves = new HashSet<>();

    public void normalMoveCalculator(PositionsPieces position, boolean up){
        if(up){
            possibleMoves.clear();
            normalMove(position, possibleMoves, true);
        } else {
            possibleMoves.clear();
            normalMove(position, possibleMoves, false);
        }
    }

    private void normalMove(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? - 1 : 1;

        PositionsPieces left = new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PositionsPieces right = new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        if (left.isValidPosition() && board.isFieldNull(left)) {
            positionsPieces.add(left);
        }

        if (right.isValidPosition() && board.isFieldNull(right)) {
            positionsPieces.add(right);
        }
    }

    public Set<PositionsPieces> getPossibleMoves() {
        return possibleMoves;
    }
}
