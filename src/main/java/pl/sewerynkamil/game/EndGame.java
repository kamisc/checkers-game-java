package pl.sewerynkamil.game;

import javafx.stage.Stage;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.menu.EndGameInfo;
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
            board.getRanking().setBlackWins();
            new EndGameInfo().blacksWin();
        }

        if(restOfBlacks.size() == 0) {
            board.getRanking().setWhiteWins();
            new EndGameInfo().whitesWin();
        }

        if(restOfWhites.size() == 1 && restOfBlacks.size() == 1) {
            board.getRanking().setDraws();
            new EndGameInfo().draw();
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

    public Set<PositionsPieces> getRestOfBlacks() {
        return restOfBlacks;
    }
}
