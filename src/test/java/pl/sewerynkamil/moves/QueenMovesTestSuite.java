package pl.sewerynkamil.moves;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class QueenMovesTestSuite {

    private Board board;
    private QueenMoves queenMoves;
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

        queenMoves = new QueenMoves(board);
        whiteQueen = new Piece(Piece.Color.WHITE, Piece.Type.QUEEN);
        blackQueen = new Piece(Piece.Color.BLACK, Piece.Type.QUEEN);
    }

    @Test
    public void testNormalQueenMoveAround() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(13, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorAroundWithPiecesInCorner() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(0,0), blackQueen);
        board.addPieceToBoard(new PositionsPieces(6,0), blackQueen);
        board.addPieceToBoard(new PositionsPieces(0,6), blackQueen);
        board.addPieceToBoard(new PositionsPieces(7,7), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(9, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorAroundWithPiecesBetween() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(1,1), blackQueen);
        board.addPieceToBoard(new PositionsPieces(5,1), blackQueen);
        board.addPieceToBoard(new PositionsPieces(1,5), blackQueen);
        board.addPieceToBoard(new PositionsPieces(6,6), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(5, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorBlockedOneSide() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(4,4), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(9, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorBlockedTwoSides() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(2,4), blackQueen);
        board.addPieceToBoard(new PositionsPieces(4,4), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(6, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorBlockedThreeSides() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(4,2), blackQueen);
        board.addPieceToBoard(new PositionsPieces(2,4), blackQueen);
        board.addPieceToBoard(new PositionsPieces(4,4), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(3, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorBlockedAround() {
        // Given
        PositionsPieces queenPositon = new PositionsPieces(3,3);
        board.addPieceToBoard(new PositionsPieces(2,2), blackQueen);
        board.addPieceToBoard(new PositionsPieces(4,2), blackQueen);
        board.addPieceToBoard(new PositionsPieces(2,4), blackQueen);
        board.addPieceToBoard(new PositionsPieces(4,4), blackQueen);

        // When
        queenMoves.normalQueenMoveCalculator(queenPositon);

        // Then
        Assert.assertTrue(queenMoves.getPossibleQueenMoves().isEmpty());
    }

    @Test
    public void testNormalQueenMoveCalculatorFromRightBottomCorner() {
        // Given
        PositionsPieces corner = new PositionsPieces(7,7);

        // When
        queenMoves.normalQueenMoveCalculator(corner);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(7,size);
    }

    @Test
    public void testNormalQueenMoveCalculatorFromRightTopCorner() {
        // Given
        PositionsPieces corner = new PositionsPieces(6,0);
        PositionsPieces blockedPosition = new PositionsPieces(7, 1);
        board.addPieceToBoard(blockedPosition, whiteQueen);

        // When
        queenMoves.normalQueenMoveCalculator(corner);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(6, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorFromLeftBottomCorner() {
        // Given
        PositionsPieces corner = new PositionsPieces(1,7);
        PositionsPieces blockedPosition = new PositionsPieces(0, 6);
        board.addPieceToBoard(blockedPosition, whiteQueen);

        // When
        queenMoves.normalQueenMoveCalculator(corner);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(6, size);
    }

    @Test
    public void testNormalQueenMoveCalculatorFromLeftTopCorner() {
        // Given
        PositionsPieces corner = new PositionsPieces(0,0);

        // When
        queenMoves.normalQueenMoveCalculator(corner);
        int size = queenMoves.getPossibleQueenMoves().size();

        // Then
        Assert.assertEquals(7, size);
    }

    @Test
    public void testNormalQueenMovCalculatorWhenBlockedInRightBottomCorner() {
        // Given
        PositionsPieces corner = new PositionsPieces(7,7);
        board.addPieceToBoard(new PositionsPieces(6,6), whiteQueen);

        // When
        queenMoves.normalQueenMoveCalculator(corner);

        // Then
        Assert.assertTrue(queenMoves.getPossibleQueenMoves().isEmpty());
    }
}
