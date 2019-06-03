package pl.sewerynkamil.board;

import pl.sewerynkamil.game.EndGame;
import pl.sewerynkamil.moves.*;
import pl.sewerynkamil.pieces.BlackPieces;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;
import pl.sewerynkamil.pieces.WhitePieces;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private Graphics graphics;

    private NormalMoves normalMoves = new NormalMoves(this);
    private QueenMoves queenMoves = new QueenMoves(this);
    private NormalKicks normalKicks = new NormalKicks(this);
    private QueenKicks queenKicks = new QueenKicks(this);
    private Promote promote = new Promote(this, graphics);
    private EndGame endGame = new EndGame(this);

    private BlackPieces blackPieces = new BlackPieces();
    private WhitePieces whitePieces = new WhitePieces();
    private Map<PositionsPieces, Piece> board = new HashMap<>();

    public Board() {
        board.putAll(whitePieces.setUpPieces());
        board.putAll(blackPieces.setUpPieces());
    }

    public Piece addPieceToBoard(PositionsPieces position, Piece piece) {
        return board.put(position, piece);
    }

    public void removePieceFromBoard(PositionsPieces position) {
        board.remove(position);
    }

    public Piece getPiece(PositionsPieces position) {
        return board.get(position);
    }

    public boolean isFieldNull(PositionsPieces position) {
        return board.get(position) == null;
    }

    public void movePieceOnBoard(PositionsPieces newPosition, PositionsPieces oldPosition, Piece piece) {
        removePieceFromBoard(oldPosition);
        addPieceToBoard(newPosition, piece);
    }

    public void kickPieceFromBoard(PositionsPieces newPosition, PositionsPieces oldPosition, PositionsPieces kickPositon, Piece piece) {
        addPieceToBoard(newPosition, piece);
        removePieceFromBoard(oldPosition);
        removePieceFromBoard(kickPositon);
    }

    public PositionsPieces findOpositePosition(PositionsPieces position) {
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

    public NormalKicks getNormalKicks() {
        return normalKicks;
    }

    public QueenKicks getQueenKicks() {
        return queenKicks;
    }

    public NormalMoves getNormalMoves() {
        return normalMoves;
    }

    public QueenMoves getQueenMoves() {
        return queenMoves;
    }

    public EndGame getEndGame() {
        return endGame;
    }
}