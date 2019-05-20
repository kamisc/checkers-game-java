package pl.sewerynkamil.pieces;

import javafx.scene.image.Image;
import pl.sewerynkamil.board.Board;

import java.util.HashMap;
import java.util.Map;

public class BlackPieces implements PieceInterface {

    private Image blackPieceImage = new Image("file:resources/black-piece.png");
    private Image blackLightPieceImage = new Image("file:resources/black-piece-light.png");

    private Map<PositionsPieces, Piece> blackPiecesMap = new HashMap<>();

    @Override
    public boolean isFieldNotNull(PositionsPieces position){
        return blackPiecesMap.get(position) != null;
    }

    @Override
    public void addPieceToMap(PositionsPieces position, Piece piece){
        blackPiecesMap.put(position, piece);
    }

    @Override
    public void removePieceFromMap(PositionsPieces position){
        blackPiecesMap.remove(position);
    }

    @Override
    public void setUpPieces(Board board){
        blackPiecesMap.put(new PositionsPieces(0,0), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(2,0), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(4,0), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(6,0), new Piece(Piece.Color.BLACK));

        blackPiecesMap.put(new PositionsPieces(1,1), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(3,1), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(5,1), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(7,1), new Piece(Piece.Color.BLACK));

        blackPiecesMap.put(new PositionsPieces(0,2), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(2,2), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(4,2), new Piece(Piece.Color.BLACK));
        blackPiecesMap.put(new PositionsPieces(6,2), new Piece(Piece.Color.BLACK));

        for(Map.Entry<PositionsPieces, Piece> blackPiece : blackPiecesMap.entrySet()){
            board.addPieceOnBoard(blackPiece.getKey(), blackPieceImage);
        }
    }

    public Image getBlackPieceImage() {
        return blackPieceImage;
    }

    public Image getBlackLightPieceImage() {
        return blackLightPieceImage;
    }

    public Map<PositionsPieces, Piece> getBlackPiecesMap() {
        return blackPiecesMap;
    }
}

