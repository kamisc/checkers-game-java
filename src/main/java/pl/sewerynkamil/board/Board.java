package pl.sewerynkamil.board;

import pl.sewerynkamil.game.EndGame;
import pl.sewerynkamil.game.MouseControl;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.BlackPieces;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Graphics graphics;

    private MouseControl mouseControl;
    private NormalMoves normalMoves = new NormalMoves(this);
    private QueenMoves queenMoves = new QueenMoves(this);
    private NormalKicks normalKicks = new NormalKicks(this);
    private QueenKicks queenKicks = new QueenKicks(this);
    private Promote promote = new Promote(this);
    private EndGame endGame = new EndGame(this);

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();
    private Map<PositionsPieces, Piece> board = new HashMap<>();

    public Board() {
        graphics = new Graphics();

        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());

        for(Map.Entry<PositionsPieces, Piece> pieces : board.entrySet()){
             graphics.addPiece(pieces.getKey(), pieces.getValue(), false);
        }

        mouseControl = new MouseControl(this, normalMoves, queenMoves, normalKicks, queenKicks, promote, endGame);
    }

    public Piece getPiece(PositionsPieces position) {
        return board.get(position);
    }

    public boolean isFieldNull(PositionsPieces position) {
        return board.get(position) == null;
    }

    public void pickPiece(PositionsPieces position, PositionsPieces oldPosition, boolean light) {
        Piece pieceNew = getPiece(position);
        Piece pieceOld = getPiece(oldPosition);

        if(oldPosition != null) {
            graphics.removePiece(oldPosition);
            graphics.addPiece(oldPosition, pieceOld, !light);
        }

        graphics.removePiece(position);
        graphics.addPiece(position, pieceNew, light);
    }

    public void movePiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        graphics.addPiece(newPosition, piece, false);
        graphics.removePiece(oldPosition);

        board.remove(oldPosition);
        board.put(newPosition, piece);
    }

    public void kickPiece(PositionsPieces newPosition, PositionsPieces oldPosition) {
        Piece piece = getPiece(oldPosition);

        PositionsPieces kickPositon = findOpositePosition(newPosition);

        graphics.addPiece(newPosition, piece, false);
        graphics.removePiece(oldPosition);
        graphics.removePiece(kickPositon);

        board.put(newPosition, piece);
        board.remove(oldPosition);
        board.remove(kickPositon);

        normalKicks.kickMovesCalculator(newPosition);
        queenKicks.calculateAllPossibleQueenKicks(newPosition);

        if(!normalKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isNormal()) {
            graphics.removePiece(oldPosition);
            graphics.addPiece(newPosition, piece, true);
        }

        if(!queenKicks.getPossibleKickMoves().isEmpty() && piece.getPieceType().isQueen()) {
            graphics.removePiece(oldPosition);
            graphics.addPiece(newPosition, piece, true);
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

    public Map<PositionsPieces, Piece> getBoard() {
        return board;
    }

    public MouseControl getMouseControl() {
        return mouseControl;
    }

    public Graphics getGraphics() {
        return graphics;
    }
}