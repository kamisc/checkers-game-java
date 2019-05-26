package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class NormalMoves {

    private Board board;

    public NormalMoves(Board board) {
        this.board = board;
    }

    private Set<PositionsPieces> possibleMoves = new HashSet<>();

    public void moveCalculator(PositionsPieces position, boolean up){
        if(up){
            possibleMoves.clear();
            move(position, possibleMoves, true);
        } else {
            possibleMoves.clear();
            move(position, possibleMoves, false);
        }
    }

/*
    public Set<PositionsPieces> moveBlack(PositionsPieces actualPosition) {
        possibleMoves.clear();
        move(actualPosition, possibleMoves, true);
        return possibleMoves;
    }

    public Set<PositionsPieces> moveWhite(PositionsPieces actualPosition){
        possibleMoves.clear();
        move(actualPosition, possibleMoves, false);
        return possibleMoves;
    }*/


    private void move(PositionsPieces actualPosition, Set<PositionsPieces> positionsPieces, boolean up) {
        int direction = up ? - 1 : 1;

        PositionsPieces left = new PositionsPieces(actualPosition.getCol() - 1, actualPosition.getRow() + direction);
        PositionsPieces right = new PositionsPieces(actualPosition.getCol() + 1, actualPosition.getRow() + direction);

        // Move forward-left
        if (left.isValidPosition() && board.isFieldNull(left)) {
            positionsPieces.add(left);
        }
        // Move forward-right
        if (right.isValidPosition() && board.isFieldNull(right)) {
            positionsPieces.add(right);
        }
    }

    public Set<PositionsPieces> getPossibleMoves() {
        return possibleMoves;
    }
}
