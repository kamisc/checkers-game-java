package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.game.Resources;
import pl.sewerynkamil.pieces.BlackPieces;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {

    private GridPane grid = new GridPane();
    private Background background;
    private Image imageBoard = new Image(Resources.getPath("board.jpg"));

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();
    private Map<PositionsPieces, Piece> board= new HashMap<>();

    private PositionsPieces oldBlackPosition;
    private PositionsPieces pickedBlackPiece;
    private PositionsPieces kickBlackPosition;

    private PositionsPieces oldWhitePosition;
    private PositionsPieces pickedWhitePiece;
    private PositionsPieces kickWhitePosition;

    public Board(){
        createBoardBackground();
        createBoardLayout();

        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());

        for(Map.Entry<PositionsPieces, Piece> pieces : board.entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }
    }

    public Background createBoardBackground(){
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundImage);
        return background;
    }

    public void createBoardLayout(){
        grid = new GridPane();
        grid.setPadding(new Insets(59));
        grid.setBackground(createBoardBackground());

        for(int i = 0; i < 8; i++){
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(496/8);
            columnConstraints.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(columnConstraints);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(496/8);
            rowConstraints.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(rowConstraints);
        }
        grid.setGridLinesVisible(false);
    }

    public Piece getPiece(PositionsPieces position){
        return board.get(position);
    }

    public boolean isFieldNull(PositionsPieces position){
        return board.get(position) == null;
    }

    public void addPiece(PositionsPieces position, Piece piece, boolean light){
        grid.add(new ImageView(generateImagePath(piece, light)), position.getCol(), position.getRow());
    }

    public void removePieceFromBoard(PositionsPieces position){
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));

    }

    public void pickBlackPiece(PositionsPieces actualPosition){
        if(pickedBlackPiece != null){
            removePieceFromBoard(oldBlackPosition);
            addPieceOnBoard(oldBlackPosition, blackPieces.getBlackPieceImage());

            pickedBlackPiece = null;

            addLightPieceOnBoard(actualPosition, blackPieces.getBlackLightPieceImage());
        }

        pickedBlackPiece = actualPosition;
        oldBlackPosition = actualPosition;

        addLightPieceOnBoard(actualPosition, blackPieces.getBlackLightPieceImage());
    }

    public void moveBlackPiece(PositionsPieces newPosition){
        addPieceOnBoard(newPosition, blackPieces.getBlackPieceImage());
        removePieceFromBoard(oldBlackPosition);

        blackPieces.removePieceFromMap(oldBlackPosition);
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        pickedBlackPiece = null;
        oldBlackPosition = null;
    }

    public void kickByBlack(PositionsPieces newPosition){
        removePieceFromBoard(oldBlackPosition);

        blackPieces.removePieceFromMap(oldBlackPosition);
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        kickBlackPosition = new PositionsPieces((oldBlackPosition.getCol() + newPosition.getCol())/2, (oldBlackPosition.getRow() + newPosition.getRow())/2);
        removePieceFromBoard(kickBlackPosition);
        whitePieces.removePieceFromMap(kickBlackPosition);

        addLightPieceOnBoard(newPosition, blackPieces.getBlackLightPieceImage());

        pickedBlackPiece = null;
    }

    public void pickWhitePiece(PositionsPieces actualPosition){
        if(pickedWhitePiece != null) {
            if(whitePieces.getWhitePiecesMap().get(oldWhitePosition).getPieceType() == Piece.Type.QUEEN){
                addLightPieceOnBoard(oldWhitePosition, whitePieces.getWhiteCrownImage());
            } else {
                addLightPieceOnBoard(oldWhitePosition, whitePieces.getWhitePieceImage());
            }

            if(whitePieces.getWhitePiecesMap().get(actualPosition).getPieceType() == Piece.Type.QUEEN){
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightCrownImage());
            } else {
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());
            }

            pickedWhitePiece = actualPosition;
            oldWhitePosition = actualPosition;

        } else {
            if(whitePieces.getWhitePiecesMap().get(actualPosition).getPieceType() == Piece.Type.QUEEN){
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightCrownImage());
            } else {
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());
            }

            pickedWhitePiece = actualPosition;
            oldWhitePosition = actualPosition;
        }

    }

    public void moveWhitePiece(PositionsPieces newPosition){
        if(whitePieces.getPiece(oldWhitePosition).getPieceType().isQueen()){
            removePieceFromBoard(oldWhitePosition);
            addPieceOnBoard(newPosition, whitePieces.getWhiteCrownImage());

            whitePieces.removePieceFromMap(oldWhitePosition);
            whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

            pickedWhitePiece = null;
            oldWhitePosition = null;
        } else {
            removePieceFromBoard(oldWhitePosition);
            addPieceOnBoard(newPosition, whitePieces.getWhitePieceImage());

            whitePieces.removePieceFromMap(oldWhitePosition);
            whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

            pickedWhitePiece = null;
            oldWhitePosition = null;
        }

        /*pickedWhitePiece = null;
        oldWhitePosition = null;*/
    }

    public void kickByWhite(PositionsPieces newPosition){
        removePieceFromBoard(oldWhitePosition);

        whitePieces.removePieceFromMap(oldWhitePosition);
        whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        kickWhitePosition = new PositionsPieces((oldWhitePosition.getCol() + newPosition.getCol())/2, (oldWhitePosition.getRow() + newPosition.getRow())/2);

        removePieceFromBoard(kickWhitePosition);
        blackPieces.removePieceFromMap(kickWhitePosition);

        addLightPieceOnBoard(newPosition, whitePieces.getWhiteLightPieceImage());

        pickedWhitePiece = null;
    }



    public void addPieceOnBoard(PositionsPieces position, Image piece){
        grid.add(new ImageView(piece), position.getCol(), position.getRow());
    }

    public void addLightPieceOnBoard(PositionsPieces position, Image piece){
        grid.add(new ImageView(piece), position.getCol(), position.getRow());
    }



    private Image generateImagePath(Piece piece, boolean light) {
        if(light) {
            return new Image(Resources.getPath(piece.getPieceColor() + "-" + piece.getPieceType() + "-light.png"));
        } else {
            return new Image(Resources.getPath(piece.getPieceColor() + "-" + piece.getPieceType() + ".png"));
        }
    }

    public GridPane getGrid() {
        return grid;
    }

    public WhitePieces getWhitePieces() {
        return whitePieces;
    }

    public BlackPieces getBlackPieces() {
        return blackPieces;
    }

    public PositionsPieces getOldWhitePosition() {
        return oldWhitePosition;
    }

    public PositionsPieces getPickedWhitePiece() {
        return pickedWhitePiece;
    }

}