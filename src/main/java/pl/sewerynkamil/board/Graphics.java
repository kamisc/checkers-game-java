package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.menu.Difficulty;
import pl.sewerynkamil.menu.LoadGame;
import pl.sewerynkamil.menu.MenuDesign;
import pl.sewerynkamil.menu.NewGame;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Map;
import java.util.Objects;

/**
 * Author Kamil Seweryn
 */

public class Graphics {

    private Board board;

    private BorderPane borderPane = new BorderPane();
    private MenuDesign menuDesign = new MenuDesign();
    private static GridPane grid = new GridPane();
    private Background background;
    private Image imageBoard = new Image(Resources.getPath("board.jpg"));
    private static Image light = new Image(Resources.getPath("light.png"));

    public Graphics(Board board) {
        this.board = board;

        createBoardBackground();
        createBoardLayout();

        createPieces();

        borderPane.setCenter(grid);
        borderPane.setTop(menuDesign.getMenuBar());

        menuDesign.getNewGame().setOnAction(e -> new NewGame().start(board));
        menuDesign.getSaveGame().setOnAction(e -> board.getSaveGame().save());
        menuDesign.getLoadGame().setOnAction(e -> new LoadGame().load());
        menuDesign.getDifficulty().setOnAction(e -> new Difficulty(board));
    }

    private void createPieces() {
        for(Map.Entry<PositionsPieces, Piece> pieces : board.getBoard().entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }
    }

    private Background createBoardBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(612, 612, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundImage);
        return background;
    }

    private void createBoardLayout() {
        grid = new GridPane();
        grid.setPadding(new Insets(58,59,58,56));
        grid.setBackground(createBoardBackground());

        for(int i = 0; i < 8; i++) {
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(496/8);
            columnConstraints.setHalignment(HPos.CENTER);
            grid.getColumnConstraints().add(columnConstraints);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(496/8);
            rowConstraints.setValignment(VPos.CENTER);
            grid.getRowConstraints().add(rowConstraints);
        }
        grid.setGridLinesVisible(true);
    }

    public static void addPiece(PositionsPieces position, Piece piece, boolean light) {
        grid.add(new ImageView(generateImagePath(piece, light)), position.getCol(), position.getRow());
    }

    public static void addLightMove(PositionsPieces position) {
        grid.add(new ImageView(light), position.getCol(), position.getRow());
    }

    public static void removePiece(PositionsPieces position) {
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    private static Image generateImagePath(Piece piece, boolean light) {
        if(light) {
            return new Image(Resources.getPath(piece.getPieceColor() + "-" + piece.getPieceType() + "-light.png"));
        } else {
            return new Image(Resources.getPath(piece.getPieceColor() + "-" + piece.getPieceType() + ".png"));
        }
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
