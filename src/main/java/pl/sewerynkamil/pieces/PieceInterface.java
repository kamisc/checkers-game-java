package pl.sewerynkamil.pieces;

import pl.sewerynkamil.board.Board;

public interface PieceInterface {
    Piece getPiece(PositionsPieces position);
    boolean isFieldNotNull(PositionsPieces position);
    void addPieceToMap(PositionsPieces position, Piece piece);
    void removePieceFromMap(PositionsPieces position);
    void setUpPieces(Board board);
}