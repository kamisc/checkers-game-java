package pl.sewerynkamil.moves;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class NormalMovesTestSuite {

    private Board board;
    private NormalMoves normalMoves;
    private Piece pieceWhite;
    private Piece pieceBlack;

    @Before
    public void setUp() {
        board = new Board();
        normalMoves = new NormalMoves(board);
        pieceWhite = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);
        pieceBlack = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);
    }

    @Test
    public void testAllPossibleBlackMovesWhenStart() {

        // When
        normalMoves.allPossibleBlackMoves();
        int size = normalMoves.getAllPossibleBlack().size();

        // Then
        Assert.assertEquals(4, size);
    }

    @Test
    public void testAllPossibleBlackMoves() {
        // Given
        Piece piece1 = board.getPiece(new PositionsPieces(6,2));
        Piece piece2 = board.getPiece(new PositionsPieces(0,2));

        board.removePieceFromBoard(new PositionsPieces(6,2));
        board.removePieceFromBoard(new PositionsPieces(0,2));

        board.addPieceToBoard(new PositionsPieces(5,3), piece1);
        board.addPieceToBoard(new PositionsPieces(2,4), piece2);

        // When
        normalMoves.allPossibleBlackMoves();
        int size = normalMoves.getAllPossibleBlack().size();

        // Then
        Assert.assertEquals(6, size);
    }

    @Test
    public void testNormalMovesWhiteTwoPossibility() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(5,5), true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(2, size);
    }

    @Test
    public void testNormalMovesWhiteRightBlocked() {
        // Given
        board.addPieceToBoard(new PositionsPieces(4,4), pieceBlack);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(3,5), true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesWhiteLeftBlocked() {
        // Givem
        board.addPieceToBoard(new PositionsPieces(4,4), pieceBlack);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(5,5), true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesWhiteWhenBlockedBothSide() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(4,6),true);

        // Then
        Assert.assertTrue(normalMoves.getPossibleMoves().isEmpty());
    }

    @Test
    public void testNormalMovesWhiteWhenInBottomCorner() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(6,6));

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(7,7),true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesWhiteWhenInTopCorner() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(0,0));
        board.addPieceToBoard(new PositionsPieces(0,0), pieceWhite);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(0,0),true);

        // Then
        Assert.assertTrue(normalMoves.getPossibleMoves().isEmpty());
    }

    @Test
    public void testNormalMovesWhiteWhenOnLeftEndSide() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(1,5));

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(0,6),true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesWhiteWhenOnRightEndSide() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(7,5),true);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesBlackTwoPossibility() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(4,2), false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(2, size);
    }

    @Test
    public void testNormalMovesBlackRightBlocked() {
        // Givem
        board.addPieceToBoard(new PositionsPieces(3,3), pieceWhite);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(2,2), false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesBlackLeftBlocked() {
        // Givem
        board.addPieceToBoard(new PositionsPieces(3,3), pieceWhite);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(4,2), false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesBlackWhenBothSideBlocked() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(5,1),false);

        // Then
        Assert.assertTrue(normalMoves.getPossibleMoves().isEmpty());
    }

    @Test
    public void testNormalMovesBlackWhenInTopCorner() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(1,1));

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(0,0),false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesBlackWhenInBottomCorner() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(7,7));
        board.addPieceToBoard(new PositionsPieces(7,7), pieceBlack);

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(7,7),false);

        // Then
        Assert.assertTrue(normalMoves.getPossibleMoves().isEmpty());
    }

    @Test
    public void testNormalMovesBlackWhenOnLeftEndSide() {

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(0,2),false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }

    @Test
    public void testNormalMovesBlackWhenOnRightEndSide() {
        // Given
        board.removePieceFromBoard(new PositionsPieces(6,2));

        // When
        normalMoves.normalMoveCalculator(new PositionsPieces(7,1),false);
        int size = normalMoves.getPossibleMoves().size();

        // Then
        Assert.assertEquals(1, size);
    }
}
