package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.game.MouseControl;
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

    private MouseControl mouseControl;

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();
    private Map<PositionsPieces, Piece> board= new HashMap<>();

    public Board(){
        createBoardBackground();
        createBoardLayout();

        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());

        for(Map.Entry<PositionsPieces, Piece> pieces : board.entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }

        mouseControl = new MouseControl(this);
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

    public void removePiece(PositionsPieces position){
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    public void pickPiece(PositionsPieces position, boolean light){
        Piece piece = getPiece(position);

        removePiece(position);
        addPiece(position, piece, light);
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

    public MouseControl getMouseControl() {
        return mouseControl;
    }
}