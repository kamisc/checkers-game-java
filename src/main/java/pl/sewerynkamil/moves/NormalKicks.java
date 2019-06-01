package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class NormalKicks {

    private Board board;

    private Set<PositionsPieces> possibleKickMoves = new HashSet<>();
    private Set<PositionsPieces> possibleKicks = new HashSet<>();

    public NormalKicks(Board board) {
        this.board = board;
    }

    public void kickMovesCalculator(PositionsPieces position) {
        possibleKickMoves.clear();
        possibleKicks.clear();

        if(kickMove(position, 1, 1)) {
            possibleKickMoves.add(new PositionsPieces(position.getCol() + 2, position.getRow() + 2));
            possibleKicks.add(new PositionsPieces(position.getCol() + 1, position.getRow() + 1));
        }

        if(kickMove(position, - 1, - 1)) {
            possibleKickMoves.add(new PositionsPieces(position.getCol() - 2, position.getRow() - 2));
            possibleKicks.add(new PositionsPieces(position.getCol() - 1, position.getRow() - 1));
        }

        if(kickMove(position, 1, - 1)) {
            possibleKickMoves.add(new PositionsPieces(position.getCol() + 2, position.getRow() - 2));
            possibleKicks.add(new PositionsPieces(position.getCol() + 1, position.getRow() - 1));
        }

        if(kickMove(position, - 1, 1)) {
            possibleKickMoves.add(new PositionsPieces(position.getCol() - 2, position.getRow() + 2));
            possibleKicks.add(new PositionsPieces(position.getCol() - 1, position.getRow() + 1));
        }
    }

    private boolean kickMove(PositionsPieces actualPosition, int col, int row) {
        return new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row).isValidPosition() &&
                !board.isFieldNull(new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row))
                && new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)).isValidPosition()
                && board.isFieldNull(new PositionsPieces(actualPosition.getCol() + (col * 2), actualPosition.getRow() + (row * 2)))
                && (board.getPiece(actualPosition).getPieceColor() != board.getPiece(
                        new PositionsPieces(actualPosition.getCol() + col, actualPosition.getRow() + row)).getPieceColor());
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
