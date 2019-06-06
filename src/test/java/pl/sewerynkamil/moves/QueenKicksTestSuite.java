package pl.sewerynkamil.moves;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

/**
 * Author Kamil Seweryn
 */

public class QueenKicksTestSuite {

    private Board board;
    private QueenKicks queenKicks;
    private Piece whiteQueen;
    private Piece blackQueen;

    @Before
    public void setUp() {
        board = new Board();

        // Prepare space for test queen (delete all pieces on board)
        board.removePieceFromBoard(new PositionsPieces(0,2));
        board.removePieceFromBoard(new PositionsPieces(2,2));
        board.removePieceFromBoard(new PositionsPieces(4,2));
        board.removePieceFromBoard(new PositionsPieces(6,2));
        board.removePieceFromBoard(new PositionsPieces(1,1));
        board.removePieceFromBoard(new PositionsPieces(3,1));
        board.removePieceFromBoard(new PositionsPieces(5,1));
        board.removePieceFromBoard(new PositionsPieces(7,1));
        board.removePieceFromBoard(new PositionsPieces(0,0));
        board.removePieceFromBoard(new PositionsPieces(2,0));
        board.removePieceFromBoard(new PositionsPieces(4,0));
        board.removePieceFromBoard(new PositionsPieces(6,0));

        board.removePieceFromBoard(new PositionsPieces(1,5));
        board.removePieceFromBoard(new PositionsPieces(3,5));
        board.removePieceFromBoard(new PositionsPieces(5,5));
        board.removePieceFromBoard(new PositionsPieces(7,5));
        board.removePieceFromBoard(new PositionsPieces(0,6));
        board.removePieceFromBoard(new PositionsPieces(2,6));
        board.removePieceFromBoard(new PositionsPieces(4,6));
        board.removePieceFromBoard(new PositionsPieces(6,6));
        board.removePieceFromBoard(new PositionsPieces(1,7));
        board.removePieceFromBoard(new PositionsPieces(3,7));
        board.removePieceFromBoard(new PositionsPieces(5,7));
        board.removePieceFromBoard(new PositionsPieces(7,7));

        queenKicks = new QueenKicks(board);
        whiteQueen = new Piece(Piece.Color.WHITE, Piece.Type.QUEEN);
        blackQueen = new Piece(Piece.Color.BLACK, Piece.Type.QUEEN);
    }

    @Test
    public void testQueenKicksNoKickPossibility() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        // Then
        Assert.assertTrue(queenKicks.getPossibleKickMoves().isEmpty());
        Assert.assertTrue(queenKicks.getPossibleKicks().isEmpty());
    }

    @Test
    public void testQueenKicksTopLeft() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);
        PositionsPieces kickerPosition = new PositionsPieces(1, 1);

        board.addPieceToBoard(queenPosition, blackQueen);
        board.addPieceToBoard(kickerPosition, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        int sizeMoves = queenKicks.getPossibleKickMoves().size();
        int sizeKicks = queenKicks.getPossibleKicks().size();

        // Then
        Assert.assertEquals(1, sizeMoves);
        Assert.assertEquals(1, sizeKicks);
    }

    @Test
    public void testQueenKicksTopRight() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);
        PositionsPieces kickerPosition = new PositionsPieces(6, 2);

        board.addPieceToBoard(queenPosition, blackQueen);
        board.addPieceToBoard(kickerPosition, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        int sizeMoves = queenKicks.getPossibleKickMoves().size();
        int sizeKicks = queenKicks.getPossibleKicks().size();

        // Then
        Assert.assertEquals(1, sizeMoves);
        Assert.assertEquals(1, sizeKicks);
    }

    @Test
    public void testQueenKicksBottomLeft() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);
        PositionsPieces kickerPosition = new PositionsPieces(2, 6);

        board.addPieceToBoard(queenPosition, blackQueen);
        board.addPieceToBoard(kickerPosition, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        int sizeMoves = queenKicks.getPossibleKickMoves().size();
        int sizeKicks = queenKicks.getPossibleKicks().size();

        // Then
        Assert.assertEquals(1, sizeMoves);
        Assert.assertEquals(1, sizeKicks);
    }

    @Test
    public void testQueenKicksBottomRight() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);
        PositionsPieces kickerPosition = new PositionsPieces(6, 6);

        board.addPieceToBoard(queenPosition, blackQueen);
        board.addPieceToBoard(kickerPosition, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        int sizeMoves = queenKicks.getPossibleKickMoves().size();
        int sizeKicks = queenKicks.getPossibleKicks().size();

        // Then
        Assert.assertEquals(1, sizeMoves);
        Assert.assertEquals(1, sizeKicks);
    }

    @Test
    public void testQueenKicksWhenPiecesAroundWithDifferentPositions() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(3,3);
        PositionsPieces piece1 = new PositionsPieces(5, 1);
        PositionsPieces piece2 = new PositionsPieces(2, 2);
        PositionsPieces piece3 = new PositionsPieces(5, 5);
        PositionsPieces piece4 = new PositionsPieces(1, 5);

        board.addPieceToBoard(queenPosition, whiteQueen);
        board.addPieceToBoard(piece1, blackQueen);
        board.addPieceToBoard(piece2, blackQueen);
        board.addPieceToBoard(piece3, blackQueen);
        board.addPieceToBoard(piece4, blackQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);
        int sizeMoves = queenKicks.getPossibleKickMoves().size();
        int sizeKicks = queenKicks.getPossibleKicks().size();

        // Then
        Assert.assertEquals(4, sizeKicks);
        Assert.assertEquals(4, sizeMoves);
    }

    @Test
    public void testQueenKicksAroundWithPiecesInCorners() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(3,3);
        PositionsPieces topLeft = new PositionsPieces(0, 0);
        PositionsPieces topRight = new PositionsPieces(6, 0);
        PositionsPieces bottomLeft = new PositionsPieces(0, 6);
        PositionsPieces bottomRight = new PositionsPieces(7, 7);

        board.addPieceToBoard(queenPosition, blackQueen);
        board.addPieceToBoard(topLeft, whiteQueen);
        board.addPieceToBoard(topRight, whiteQueen);
        board.addPieceToBoard(bottomLeft, whiteQueen);
        board.addPieceToBoard(bottomRight, whiteQueen);


        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        // Then
        Assert.assertTrue(queenKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(queenKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testQueenKicksWhenTheSameColor() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(3,3);
        PositionsPieces otherPiece = new PositionsPieces(5, 5);

        board.addPieceToBoard(queenPosition, whiteQueen);
        board.addPieceToBoard(otherPiece, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        // Then
        Assert.assertTrue(queenKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(queenKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testQueenKicksWhenPiecesInTheSameColorAroundWithDifferentPositions() {
        // Given
        PositionsPieces queenPosition = new PositionsPieces(4,4);
        PositionsPieces piece1 = new PositionsPieces(5, 3);
        PositionsPieces piece2 = new PositionsPieces(2, 2);
        PositionsPieces piece3 = new PositionsPieces(6, 6);
        PositionsPieces piece4 = new PositionsPieces(3, 5);

        board.addPieceToBoard(queenPosition, whiteQueen);
        board.addPieceToBoard(piece1, whiteQueen);
        board.addPieceToBoard(piece2, whiteQueen);
        board.addPieceToBoard(piece3, whiteQueen);
        board.addPieceToBoard(piece4, whiteQueen);

        // When
        queenKicks.calculateAllPossibleQueenKicks(queenPosition);

        // Then
        Assert.assertTrue(queenKicks.getPossibleKickMoves().isEmpty());
        Assert.assertTrue(queenKicks.getPossibleKicks().isEmpty());
    }
}
