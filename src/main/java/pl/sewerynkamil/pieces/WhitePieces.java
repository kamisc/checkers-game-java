package pl.sewerynkamil.pieces;

import java.util.HashMap;
import java.util.Map;

public class WhitePieces implements PieceInterface {
    private final Map<PositionsPieces, Piece> whitePiecesMap = new HashMap<>();

    @Override
    public Map<PositionsPieces, Piece> setUpPieces() {
        whitePiecesMap.put(new PositionsPieces(5,5), new Piece(Piece.Color.WHITE, Piece.Type.QUEEN));

/*        whitePiecesMap.put(new PositionsPieces(1,5), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(3,5), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(5,5), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(7,5), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        whitePiecesMap.put(new PositionsPieces(0,6), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(2,6), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(4,6), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(6,6), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        whitePiecesMap.put(new PositionsPieces(1,7), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(3,7), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(5,7), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        whitePiecesMap.put(new PositionsPieces(7,7), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));*/

        return whitePiecesMap;
    }
}

