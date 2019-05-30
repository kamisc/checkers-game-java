package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

public class QueenKicks {

    private Board board;

    private Set<PositionsPieces> possibleKickMoves = new HashSet<>();

    public QueenKicks(Board board) {
        this.board = board;
    }
}
