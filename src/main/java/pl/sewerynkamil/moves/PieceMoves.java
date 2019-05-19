package pl.sewerynkamil.moves;

import pl.sewerynkamil.game.Controller;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class PieceMoves {

    private MovesCalculator movesCalculator;
    private Controller controller;

    private Set<PositionsPieces> possibleBlackPieceMoves = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMoves = new HashSet<>();

    private Set<PositionsPieces> possibleBlackPieceMovesAfterKick = new HashSet<>();
    private Set<PositionsPieces> possibleWhitePieceMovesAfterKick = new HashSet<>();

    public PieceMoves(MovesCalculator movesCalculator, Controller controller){
        this.movesCalculator = movesCalculator;
        this.controller = controller;
    }

    public Set<PositionsPieces> moveBlack(PositionsPieces actualPosition) {
        possibleBlackPieceMoves.clear();
        move(actualPosition, possibleBlackPieceMoves, true);
        return possibleBlackPieceMoves;
    }

    public Set<PositionsPieces> moveWhite(PositionsPieces actualPosition){
        possibleWhitePieceMoves.clear();
        move(actualPosition, possibleWhitePieceMoves, false);
        return possibleWhitePieceMoves;
    }

    private void move(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? 1 : -1;

        PositionsPieces left = new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PositionsPieces right = new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        // Move forward-left
        if (left.isValidPosition()) {
            positionsPieces.add(left);
        }
        // Move forward-right
        if (right.isValidPosition()) {
            positionsPieces.add(right);
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

        PositionsPieces forwardLeft = new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() + direction);
        PositionsPieces forwardRight = new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() + direction);
        PositionsPieces backwardLeft = new PositionsPieces(actualPosition.getCol() - 2, actualPosition.getRow() - direction);
        PositionsPieces backwardRight = new PositionsPieces(actualPosition.getCol() + 2, actualPosition.getRow() - direction);

        // Move forward-left
        if (forwardLeft.isValidPosition()) {
            positionsPieces.add(forwardLeft);
        }
        // Move forward-right
        if (forwardRight.isValidPosition()) {
            positionsPieces.add(forwardRight);
        }
        // Move backward-left
        if (backwardLeft.isValidPosition()) {
            positionsPieces.add(backwardLeft);
        }
        // Move backward-right
        if (backwardRight.isValidPosition()) {
            positionsPieces.add(backwardRight);
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

