package pl.sewerynkamil.moves;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class Promote {

    private Board board;
    private Graphics graphics;

    public Promote(Board board, Graphics graphics) {
        this.board = board;
        this.graphics = graphics;
    }

    public void promote() {
        board.getPossiblePromote().clear();
        board.calculatePromote(board.getBoard().keySet());

        for(PositionsPieces position : board.getPossiblePromote()) {
            Piece piece = board.getPiece(position);

            if(piece.getPieceColor().isWhite() && piece.getPieceType().isNormal()) {
                graphics.removePiece(position);
                graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                board.promoteOnBoard(position, piece);
            }

            if(piece.getPieceColor().isBlack() && piece.getPieceType().isNormal()) {
                graphics.removePiece(position);
                graphics.addPiece(position, new Piece(piece.getPieceColor(), Piece.Type.QUEEN), false);

                board.promoteOnBoard(position, piece);
            }
        }
    }
}
