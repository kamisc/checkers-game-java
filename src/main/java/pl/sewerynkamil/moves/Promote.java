package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Promote {

    private Board board;

    private Set<PositionsPieces> possiblePromote = new HashSet<>();

    public Promote(Board board) {
        this.board = board;
    }

    public void promote() {
        possiblePromote.clear();
        calculatePromote();

        for(PositionsPieces position : possiblePromote) {
            Piece piece = board.getPiece(position);

            if(piece.getPieceColor().isWhite() && piece.getPieceType().isNormal()) {
                board.removePiece(position);
                board.addPiece(position, new Piece(Piece.Color.WHITE, Piece.Type.QUEEN), false);

                board.getBoard().remove(position);
                board.getBoard().put(position, new Piece(Piece.Color.WHITE, Piece.Type.QUEEN));
            }

            if(piece.getPieceColor().isBlack() && piece.getPieceType().isNormal()) {
                board.removePiece(position);
                board.addPiece(position, new Piece(Piece.Color.BLACK, Piece.Type.QUEEN), false);

                board.getBoard().remove(position);
                board.getBoard().put(position, new Piece(Piece.Color.BLACK, Piece.Type.QUEEN));
            }
        }
    }

    private void calculatePromote() {

        Set<PositionsPieces> whites = board.getBoard().keySet().stream()
                .filter(positions -> positions.getRow() == 0)
                .filter(positions -> board.getBoard().get(positions).getPieceColor() == Piece.Color.WHITE)
                .collect(Collectors.toSet());

        Set<PositionsPieces> blacks = board.getBoard().keySet().stream()
                .filter(positions -> positions.getRow() == 7)
                .filter(positions -> board.getBoard().get(positions).getPieceColor() == Piece.Color.BLACK)
                .collect(Collectors.toSet());

        possiblePromote.addAll(whites);
        possiblePromote.addAll(blacks);
    }
}
