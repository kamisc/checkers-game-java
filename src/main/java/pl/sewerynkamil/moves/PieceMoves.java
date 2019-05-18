package pl.sewerynkamil.moves;

import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class PieceMoves {

    private MovesCalculator movesCalculator;

    private Set<PositionsPieces> possibleBlackPieceMoves = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMoves = new HashSet<>();

    private Set<PositionsPieces> possibleBlackPieceMovesAfterKick = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMovesAfterKick = new HashSet<>();

    public PieceMoves(MovesCalculator movesCalculator){
        this.movesCalculator = movesCalculator;
    }

    public void moveBlack(PositionsPieces actualPosition) {
        possibleBlackPieceMoves.clear();
        move(actualPosition, possibleBlackPieceMoves, true);
    }

    public void moveWhite(PositionsPieces actualPosition){
        possibleWhitePieceMoves.clear();
        move(actualPosition, possibleWhitePieceMoves, false);
    }

    private void move(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? 1 : -1;

        // Move forward-left
        if (new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction));
        }
        // Move forward-right
        if (new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction));
        }
    }

    public void moveBlackKick(PositionsPieces actualPosition) {
        possibleBlackPieceMovesAfterKick.clear();
        moveAfterKick(actualPosition, possibleBlackPieceMovesAfterKick, true);
    }

    public void moveWhiteKick(PositionsPieces actualPosition){
        possibleWhitePieceMovesAfterKick.clear();
        moveAfterKick(actualPosition, possibleWhitePieceMovesAfterKick, false);
    }

    private void moveAfterKick(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? 2 : -2;

        // Move forward-left
        if (new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() + direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() + direction));
        }
        // Move forward-right
        if (new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() + direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() + direction));
        }
        // Move backward-left
        if (new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() - direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() - direction));
        }
        // Move backward-right
        if (new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() - direction).isValidPosition()) {
            positionsPieces.add(new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() - direction));
        }
    }

    // King moves - later

    public Set<PositionsPieces> getPossibleBlackPieceMoves() {
        return possibleBlackPieceMoves;
    }

    public Set<PositionsPieces> getPossibleWhitePieceMoves() {
        return possibleWhitePieceMoves;
    }

    public Set<PositionsPieces> getPossibleBlackPieceMovesAfterKick() {
        return possibleBlackPieceMovesAfterKick;
    }

    public Set<PositionsPieces> getPossibleWhitePieceMovesAfterKick() {
        return possibleWhitePieceMovesAfterKick;
    }


}

