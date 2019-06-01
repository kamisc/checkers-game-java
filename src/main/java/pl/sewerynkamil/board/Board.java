package pl.sewerynkamil.board;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.sewerynkamil.game.MouseControl;
import pl.sewerynkamil.game.Resources;
import pl.sewerynkamil.moves.*;
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
    private NormalMoves normalMoves = new NormalMoves(this);
    private QueenMoves queenMoves = new QueenMoves(this);
    private NormalKicks normalKicks = new NormalKicks(this);
    private QueenKicks queenKicks = new QueenKicks(this);
    private Promote promote = new Promote(this);

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();
    private Map<PositionsPieces, Piece> board = new HashMap<>();

    public Board() {
        createBoardBackground();
        createBoardLayout();

        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());

        for(Map.Entry<PositionsPieces, Piece> pieces : board.entrySet()){
            addPiece(pieces.getKey(), pieces.getValue(), false);
        }

        mouseControl = new MouseControl(this, normalMoves, queenMoves, normalKicks, queenKicks, promote);
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

    public Piece getPiece(PositionsPieces position) {
        return board.get(position);
    }

    public boolean isFieldNull(PositionsPieces position) {
        return board.get(position) == null;
    }

    public void addPiece(PositionsPieces position, Piece piece, boolean light) {
        grid.add(new ImageView(generateImagePath(piece, light)), position.getCol(), position.getRow());
    }

    public void removePiece(PositionsPieces position) {
        grid.getChildren().removeIf(node -> node instanceof ImageView && Objects.equals(GridPane.getColumnIndex(node), position.getCol())
                && Objects.equals(GridPane.getRowIndex(node), position.getRow()));
    }

    public void pickPiece(PositionsPieces position, PositionsPieces oldPosition, boolean light) {
        Piece pieceNew = getPiece(position);
        Piece pieceOld = getPiece(oldPosition);

        if(oldPosition != null) {
            removePiece(oldPosition);
            addPiece(oldPosition, pieceOld, !light);
        }

        removePiece(position);
        addPiece(position, pieceNew, light);
    }

    public void movePiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        addPiece(newPosition, piece, false);
        removePiece(oldPosition);

        board.remove(oldPosition);
        board.put(newPosition, piece);
    }

    public void kickPiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        PositionsPieces kickPositon = findOpositePosition(newPosition);

        System.out.println(kickPositon);

        addPiece(newPosition, piece, false);
        removePiece(oldPosition);
        removePiece(kickPositon);

        board.put(newPosition, piece);
        board.remove(oldPosition);
        board.remove(kickPositon);

        normalKicks.kickMovesCalculator(newPosition);
        queenKicks.calculateAllPossibleQueenKicks(newPosition);

        if(!normalKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isNormal()) {
            removePiece(oldPosition);
            addPiece(newPosition, piece, true);
        }

        if(!queenKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isQueen()) {
            removePiece(oldPosition);
            addPiece(newPosition, piece, true);
        }
    }

    private PositionsPieces findOpositePosition(PositionsPieces position) {
        PositionsPieces upLeft = new PositionsPieces(position.getCol() - 1, position.getRow() - 1);

        if(queenKicks.getPossibleKicks().contains(upLeft) || normalKicks.getPossibleKicks().contains(upLeft)) {
            return upLeft;
        }

        PositionsPieces downLeft = new PositionsPieces(position.getCol() - 1, position.getRow() + 1);

        if(queenKicks.getPossibleKicks().contains(downLeft) || normalKicks.getPossibleKicks().contains(downLeft)) {
            return downLeft;
        }

        PositionsPieces upRight = new PositionsPieces(position.getCol() + 1, position.getRow() - 1);

        if(queenKicks.getPossibleKicks().contains(upRight) || normalKicks.getPossibleKicks().contains(upRight)) {
            return upRight;
        }

        PositionsPieces downRight = new PositionsPieces(position.getCol() + 1, position.getRow() + 1);

        if(queenKicks.getPossibleKicks().contains(downRight) || normalKicks.getPossibleKicks().contains(downRight)) {
            return downRight;
        }

        return null;
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

    public Map<PositionsPieces, Piece> getBoard() {
        return board;
    }

    public MouseControl getMouseControl() {
        return mouseControl;
    }
}