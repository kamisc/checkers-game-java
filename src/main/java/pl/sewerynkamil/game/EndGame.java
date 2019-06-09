package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author Kamil Seweryn
 */

public class EndGame {

    private Board board;
    private Set<PositionsPieces> restOfWhites = new HashSet<>();
    private Set<PositionsPieces> restOfBlacks = new HashSet<>();

    public EndGame(Board board) {
        this.board = board;
    }

    public void checkEndGame(Set<PositionsPieces> positions) {
        calculatePieces(positions);

        if(restOfWhites.size() == 0) {
            System.out.println("Black player win!");
            board.getRanking().setBlackWins();
        }

        if(restOfBlacks.size() == 0) {
            System.out.println("White player win!");
            board.getRanking().setWhiteWins();
        }

        if(restOfWhites.size() == 1 && restOfBlacks.size() == 1) {
            System.out.println("Draw!");
            board.getRanking().setDraws();
        }
    }

    private void calculatePieces(Set<PositionsPieces> positions) {
        restOfWhites.clear();
        restOfBlacks.clear();

        Set<PositionsPieces> whites = positions.stream()
                .filter(position -> board.getPiece(position).getPieceColor().isWhite())
                .collect(Collectors.toSet());

        Set<PositionsPieces> blacks = positions.stream()
                .filter(position -> board.getPiece(position).getPieceColor().isBlack())
                .collect(Collectors.toSet());

        restOfWhites.addAll(whites);
        restOfBlacks.addAll(blacks);
    }
}
