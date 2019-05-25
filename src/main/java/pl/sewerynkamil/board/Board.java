package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.pieces.BlackPieces;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.Objects;

public class Board {

    private GridPane grid = new GridPane();
    private Background background;
    private Image imageBoard = new Image("file:resources/board.jpg");

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();

    private PositionsPieces oldBlackPosition;
    private PositionsPieces pickedBlackPiece;
    private PositionsPieces kickBlackPosition;

    private PositionsPieces oldWhitePosition;
    private PositionsPieces pickedWhitePiece;
    private PositionsPieces kickWhitePosition;

    public Board(){
        createBoardBackground();
        createBoardLayout();
        whitePieces.setUpPieces(this);
        blackPieces.setUpPieces(this);
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
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK));

        pickedBlackPiece = null;
        oldBlackPosition = null;
    }

    public void kickByBlack(PositionsPieces newPosition){
        removePieceFromBoard(oldBlackPosition);

        blackPieces.removePieceFromMap(oldBlackPosition);
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK));

        kickBlackPosition = new PositionsPieces((oldBlackPosition.getCol() + newPosition.getCol())/2, (oldBlackPosition.getRow() + newPosition.getRow())/2);
        removePieceFromBoard(kickBlackPosition);
        whitePieces.removePieceFromMap(kickBlackPosition);

        addLightPieceOnBoard(newPosition, blackPieces.getBlackLightPieceImage());

        pickedBlackPiece = null;
    }

    public void pickWhitePiece(PositionsPieces actualPosition){
        if(pickedWhitePiece != null) {
            if(whitePieces.getWhitePiecesMap().get(oldWhitePosition).getPieceColor() == Piece.Color.QUEEN_WHITE){
                addLightPieceOnBoard(oldWhitePosition, whitePieces.getWhiteCrownImage());
            } else {
                addLightPieceOnBoard(oldWhitePosition, whitePieces.getWhitePieceImage());
            }

            if(whitePieces.getWhitePiecesMap().get(actualPosition).getPieceColor() == Piece.Color.QUEEN_WHITE){
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightCrownImage());
            } else {
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());
            }

            pickedWhitePiece = actualPosition;
            oldWhitePosition = actualPosition;

        } else {
            if(whitePieces.getWhitePiecesMap().get(actualPosition).getPieceColor() == Piece.Color.QUEEN_WHITE){
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightCrownImage());
            } else {
                addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());
            }

            pickedWhitePiece = actualPosition;
            oldWhitePosition = actualPosition;
        }

    }

    public void moveWhitePiece(PositionsPieces newPosition){
        if(whitePieces.getPiece(oldWhitePosition).getPieceColor().isQueenWhite()){
            removePieceFromBoard(oldWhitePosition);
            addPieceOnBoard(newPosition, whitePieces.getWhiteCrownImage());

            whitePieces.removePieceFromMap(oldWhitePosition);
            whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.QUEEN_WHITE));

            pickedWhitePiece = null;
            oldWhitePosition = null;
        } else {
            removePieceFromBoard(oldWhitePosition);
            addPieceOnBoard(newPosition, whitePieces.getWhitePieceImage());

            whitePieces.removePieceFromMap(oldWhitePosition);
            whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE));

            pickedWhitePiece = null;
            oldWhitePosition = null;
        }

        /*pickedWhitePiece = null;
        oldWhitePosition = null;*/
    }

    public void kickByWhite(PositionsPieces newPosition){
        removePieceFromBoard(oldWhitePosition);

        whitePieces.removePieceFromMap(oldWhitePosition);
        whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE));

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

    public void removePieceFromBoard(PositionsPieces position){
        if(position != null){
            grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                    && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
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