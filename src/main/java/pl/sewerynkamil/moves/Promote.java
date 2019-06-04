package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Promote {

    private Board board;
    private Graphics graphics;

    private Set<PositionsPieces> possiblePromote = new HashSet<>();

    public Promote(Board board, Graphics graphics) {
        this.board = board;
        this.graphics = graphics;
    }

    public void promote() {
        possiblePromote.clear();
        calculatePromote();

        for(PositionsPieces position : possiblePromote) {
            Piece piece = board.getPiece(position);

            if(piece.getPieceColor().isWhite() && piece.getPieceType().isNormal()) {
                graphics.removePiece(position);
                graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                board.getBoard().remove(position);
                board.getBoard().put(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN));
            }

            if(piece.getPieceColor().isBlack() && piece.getPieceType().isNormal()) {
                graphics.removePiece(position);
                graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                board.getBoard().remove(position);
                board.getBoard().put(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN));
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
