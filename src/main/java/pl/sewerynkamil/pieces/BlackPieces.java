package pl.sewerynkamil.pieces;

import java.util.HashMap;
import java.util.Map;

public class BlackPieces implements PieceInterface {
    private final Map<PositionsPieces, Piece> blackPiecesMap = new HashMap<>();

    @Override
    public Map<PositionsPieces, Piece> setUpPieces() {
      //  blackPiecesMap.put(new PositionsPieces(7,7), new Piece(Piece.Color.BLACK, Piece.Type.QUEEN));

        blackPiecesMap.put(new PositionsPieces(0,0), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(2,0), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(4,0), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(6,0), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        blackPiecesMap.put(new PositionsPieces(1,1), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(3,1), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(5,1), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(7,1), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        blackPiecesMap.put(new PositionsPieces(0,2), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(2,2), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(4,2), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        blackPiecesMap.put(new PositionsPieces(6,2), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        return blackPiecesMap;
    }
}

