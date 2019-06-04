package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Map;
import java.util.Objects;

public class Graphics {

    private Board board;

    private GridPane grid = new GridPane();
    private Background background;
    private Image imageBoard = new Image(Resources.getPath("board.jpg"));

    public Graphics(Board board){
        this.board = board;

        createBoardBackground();
        createBoardLayout();

        for(Map.Entry<PositionsPieces, Piece> pieces : board.getBoard().entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }
    }

    public Background createBoardBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageBoard, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        background = new Background(backgroundImage);
        return background;
    }

    public void createBoardLayout() {
        grid = new GridPane();
        grid.setPadding(new Insets(59));
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
        grid.setGridLinesVisible(false);
    }

    public void addPiece(PositionsPieces position, Piece piece, boolean light) {
        getGrid().add(new ImageView(generateImagePath(piece, light)), position.getCol(), position.getRow());
    }

    public void removePiece(PositionsPieces position) {
        getGrid().getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    public void pickPiece(PositionsPieces position, PositionsPieces oldPosition, boolean light) {
        Piece pieceNew = board.getPiece(position);
        Piece pieceOld = board.getPiece(oldPosition);

        if(oldPosition != null) {
            removePiece(oldPosition);
            addPiece(oldPosition, pieceOld, !light);
        }

        removePiece(position);
        addPiece(position, pieceNew, light);
    }

    public void movePiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = board.getPiece(oldPosition);

        addPiece(newPosition, piece, false);
        removePiece(oldPosition);

        board.movePieceOnBoard(newPosition, oldPosition, piece);
    }

    public void kickPiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = board.getPiece(oldPosition);

        PositionsPieces kickPositon = board.findOpositePosition(newPosition, board.getNormalKicks().getPossibleKicks(), board.getQueenKicks().getPossibleKicks());

        addPiece(newPosition, piece, false);
        removePiece(oldPosition);
        removePiece(kickPositon);

        board.kickPieceFromBoard(newPosition, oldPosition, kickPositon, piece);

        board.getNormalKicks().kickMovesCalculator(newPosition);
        board.getQueenKicks().calculateAllPossibleQueenKicks(newPosition);

        if(!board.getNormalKicks().getPossibleKickMoves().isEmpty() && piece.getPieceType().isNormal()) {
            removePiece(oldPosition);
            addPiece(newPosition, piece, true);
        }

        if(!board.getQueenKicks().getPossibleKickMoves().isEmpty() && piece.getPieceType().isQueen()) {
            removePiece(oldPosition);
            addPiece(newPosition, piece, true);
        }
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
}
