package pl.sewerynkamil.pieces;

import java.util.Map;

public interface PieceInterface {
    Piece getPiece(PositionsPieces position);
    boolean isFieldNotNull(PositionsPieces position);
    void addPieceToMap(PositionsPieces position, Piece piece);
    void removePieceFromMap(PositionsPieces position);
    Map<PositionsPieces, Piece> setUpPieces();
}
