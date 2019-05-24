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

    private PositionsPieces oldPosition;
    private PositionsPieces pickedPiece;
    private PositionsPieces kickPosition;

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
        if(pickedPiece != null){
            removePieceFromBoard(oldPosition);
            addPieceOnBoard(oldPosition, blackPieces.getBlackPieceImage());

            pickedPiece = null;

            addLightPieceOnBoard(actualPosition, blackPieces.getBlackLightPieceImage());
        }

        pickedPiece = actualPosition;
        oldPosition = actualPosition;

        addLightPieceOnBoard(actualPosition, blackPieces.getBlackLightPieceImage());
    }

    public void moveBlackPiece(PositionsPieces newPosition){
        addPieceOnBoard(newPosition, blackPieces.getBlackPieceImage());
        removePieceFromBoard(oldPosition);

        blackPieces.removePieceFromMap(oldPosition);
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK));

        pickedPiece = null;
        oldPosition = null;
    }

    public void kickByBlack(PositionsPieces newPosition){
        // addPieceOnBoard(newPosition, blackPieces.getBlackPieceImage());
        removePieceFromBoard(oldPosition);

        blackPieces.removePieceFromMap(oldPosition);
        blackPieces.addPieceToMap(newPosition, new Piece(Piece.Color.BLACK));

        kickPosition = new PositionsPieces((oldPosition.getCol() + newPosition.getCol())/2, (oldPosition.getRow() + newPosition.getRow())/2);
        removePieceFromBoard(kickPosition);
        whitePieces.removePieceFromMap(kickPosition);

        addLightPieceOnBoard(newPosition, blackPieces.getBlackLightPieceImage());

        pickedPiece = null;
        oldPosition = null;
        /*kickPosition = null;*/
    }

    public void pickWhitePiece(PositionsPieces actualPosition){
        if(pickedPiece != null){
            removePieceFromBoard(oldPosition);
            addPieceOnBoard(oldPosition, whitePieces.getWhitePieceImage());

            addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());

            pickedPiece = null;
        }

        pickedPiece = actualPosition;
        oldPosition = actualPosition;

        addLightPieceOnBoard(actualPosition, whitePieces.getWhiteLightPieceImage());
    }

    public void moveWhitePiece(PositionsPieces newPosition){
        removePieceFromBoard(oldPosition);
        addPieceOnBoard(newPosition, whitePieces.getWhitePieceImage());

        whitePieces.removePieceFromMap(oldPosition);
        whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE));

        pickedPiece = null;
        // oldPosition = null;
    }

    public void kickByWhite(PositionsPieces newPosition){
        addPieceOnBoard(newPosition, whitePieces.getWhitePieceImage());
        removePieceFromBoard(oldPosition);

        whitePieces.removePieceFromMap(oldPosition);
        whitePieces.addPieceToMap(newPosition, new Piece(Piece.Color.WHITE));

        kickPosition = new PositionsPieces((oldPosition.getCol() + newPosition.getCol())/2, (oldPosition.getRow() + newPosition.getRow())/2);

        removePieceFromBoard(kickPosition);
        blackPieces.removePieceFromMap(kickPosition);

        addLightPieceOnBoard(newPosition, whitePieces.getWhiteLightPieceImage());

        pickedPiece = null;
        // oldPosition = null;
        // kickPosition = null;
    }

    public void addPieceOnBoard(PositionsPieces position, Image piece){
        grid.add(new ImageView(piece), position.getCol(), position.getRow());
    }

    public void addLightPieceOnBoard(PositionsPieces position, Image piece){
        grid.add(new ImageView(piece), position.getCol(), position.getRow());
    }

    public void removePieceFromBoard(PositionsPieces position){
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
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

    public PositionsPieces getPickedPiece() {
        return pickedPiece;
    }
}