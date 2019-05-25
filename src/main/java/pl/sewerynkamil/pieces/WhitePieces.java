package pl.sewerynkamil.pieces;

import javafx.scene.image.Image;
import pl.sewerynkamil.board.Board;

import java.util.HashMap;
import java.util.Map;

public class WhitePieces implements PieceInterface {

    private Image whitePieceImage = new Image("file:resources/white-piece.png");
    private Image whiteLightPieceImage = new Image("file:resources/white-piece-light.png");
    private Image whiteCrownImage = new Image("file:resources/white-piece-crown.png");
    private Image whiteLightCrownImage = new Image("file:resources/white-piece-light-crown.png");

    private Map<PositionsPieces, Piece> whitePiecesMap = new HashMap<>();

    @Override
    public Piece getPiece(PositionsPieces position){
        return whitePiecesMap.get(position);
    }

    @Override
    public boolean isFieldNotNull(PositionsPieces position){
        return whitePiecesMap.get(position) != null;
    }

    @Override
    public void addPieceToMap(PositionsPieces position, Piece piece){
        whitePiecesMap.put(position, piece);
    }

    @Override
    public void removePieceFromMap(PositionsPieces position){
        whitePiecesMap.remove(position);
    }

    @Override
    public void setUpPieces(Board board){

        whitePiecesMap.put(new PositionsPieces(5,1), new Piece(Piece.Color.WHITE));

        /*whitePiecesMap.put(new PositionsPieces(1,5), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(3,5), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(5,5), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(7,5), new Piece(Piece.Color.WHITE));

        whitePiecesMap.put(new PositionsPieces(0,6), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(2,6), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(4,6), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(6,6), new Piece(Piece.Color.WHITE));

        whitePiecesMap.put(new PositionsPieces(1,7), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(3,7), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(5,7), new Piece(Piece.Color.WHITE));
        whitePiecesMap.put(new PositionsPieces(7,7), new Piece(Piece.Color.WHITE));*/

        for(Map.Entry<PositionsPieces, Piece> whitePiece : whitePiecesMap.entrySet()){
            board.addPieceOnBoard(whitePiece.getKey(), whitePieceImage);
        }
    }

    public Image getWhitePieceImage() {
        return whitePieceImage;
    }

    public Image getWhiteLightPieceImage() {
        return whiteLightPieceImage;
    }

    public Image getWhiteCrownImage() {
        return whiteCrownImage;
    }

    public Image getWhiteLightCrownImage() {
        return whiteLightCrownImage;
    }

    public Map<PositionsPieces, Piece> getWhitePiecesMap() {
        return whitePiecesMap;
    }
}

