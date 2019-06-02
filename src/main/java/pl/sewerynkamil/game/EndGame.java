package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class EndGame {

    private Board board;
    private Set<PositionsPieces> restOfWhites = new HashSet<>();
    private Set<PositionsPieces> restOfBlacks = new HashSet<>();

    public EndGame(Board board) {
        this.board = board;
    }

    public void checkEndGame() {
        calculatePieces();

        if(restOfWhites.size() == 0) {
            System.out.println("Black player win!");
        }

        if(restOfBlacks.size() == 0) {
            System.out.println("White player win!");
        }

        if(restOfWhites.size() == 1 && restOfBlacks.size() == 1) {
            System.out.println("Draw!");
        }
    }

    private void calculatePieces(){
        restOfWhites.clear();
        restOfBlacks.clear();

        Set<PositionsPieces> whites = board.getBoard().keySet().stream()
                .filter(position -> board.getPiece(position).getPieceColor().isWhite())
                .collect(Collectors.toSet());

        Set<PositionsPieces> blacks = board.getBoard().keySet().stream()
                .filter(position -> board.getPiece(position).getPieceColor().isBlack())
                .collect(Collectors.toSet());

        restOfWhites.addAll(whites);
        restOfBlacks.addAll(blacks);
    }
}
