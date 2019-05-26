package pl.sewerynkamil.pieces;

import javafx.scene.image.Image;
import pl.sewerynkamil.board.Board;

import java.util.HashMap;
import java.util.Map;

public class BlackPieces implements PieceInterface {

    private Image blackPieceImage = new Image("file:resources/black-piece.png");
    private Image blackLightPieceImage = new Image("file:resources/black-piece-light.png");
    private Image blackCrownImage = new Image("file:resources/black-piece-crown.png");
    private Image blackLightCrownImage = new Image("file:resources/black-piece-light-crown.png");

    private Map<PositionsPieces, Piece> blackPiecesMap = new HashMap<>();

    @Override
    public Piece getPiece(PositionsPieces position){
        return blackPiecesMap.get(position);
    }

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
    public Map<PositionsPieces, Piece> setUpPieces(){

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

    public Image getBlackPieceImage() {
        return blackPieceImage;
    }

    public Image getBlackLightPieceImage() {
        return blackLightPieceImage;
    }

    public Image getBlackCrownImage() {
        return blackCrownImage;
    }

    public Image getBlackLightCrownImage() {
        return blackLightCrownImage;
    }

    public Map<PositionsPieces, Piece> getBlackPiecesMap() {
        return blackPiecesMap;
    }
}

