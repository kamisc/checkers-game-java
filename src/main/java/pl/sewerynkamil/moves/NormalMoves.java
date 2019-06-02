package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NormalMoves {

    private Board board;

    private Set<PositionsPieces> possibleMoves = new HashSet<>();
    private Set<PositionsPieces> allPossibleBlack = new HashSet<>();

    public NormalMoves(Board board) {
        this.board = board;
    }

    public void normalMoveCalculator(PositionsPieces position, boolean up) {
        normalMove(position, possibleMoves, up);
    }

    private void normalMove(PositionsPieces actualPosition, Set<PositionsPieces> possibleMoves, boolean up) {
        int direction = up ? - 1 : 1;

        PositionsPieces left = new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PositionsPieces right = new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        if (left.isValidPosition() && board.isFieldNull(left)) {
            possibleMoves.add(left);
        }

        if (right.isValidPosition() && board.isFieldNull(right)) {
            possibleMoves.add(right);
        }
    }

    public void allPossibleBlackMoves() {
        allPossibleBlack.clear();

        for(Map.Entry<PositionsPieces, Piece> blacks : board.getBoard().entrySet()) {
            if(blacks.getValue().getPieceColor().isWhite()) {
                continue;
            }

            possibleMoves.clear();

            if(blacks.getValue().getPieceType().isNormal()) {
                normalMoveCalculator(blacks.getKey(), false);
                for(PositionsPieces position : possibleMoves){
                    if(position != null && position.isValidPosition()) {
                        allPossibleBlack.add(blacks.getKey());
                    }
                }
            } else {
                normalMoveCalculator(blacks.getKey(), true);
                normalMoveCalculator(blacks.getKey(), false);
                for(PositionsPieces position : possibleMoves){
                    if(position != null && position.isValidPosition()) {
                        allPossibleBlack.add(blacks.getKey());
                    }
                }
            }
        }
    }

    public Set<PositionsPieces> getPossibleMoves() {
        return possibleMoves;
    }

    public Set<PositionsPieces> getAllPossibleBlack() {
        return allPossibleBlack;
    }

    public void clear() {
        possibleMoves.clear();
        allPossibleBlack.clear();
    }
}
