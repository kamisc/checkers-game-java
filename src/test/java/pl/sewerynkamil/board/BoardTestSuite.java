package pl.sewerynkamil.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class BoardTestSuite {

    private Board board;

    @Before
    public void setUp() {
        // Given
        board = new Board();
    }

    @Test
    public void testBoardStartingSize() {

        // When
        int size = board.getBoard().size();

        // Then
        Assert.assertEquals(24, size);
    }

    @Test
    public void testAddNormalWhitePieceOnBoard() {

        // When
        board.addPieceToBoard(new PositionsPieces(1,3), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));
        Piece.Color color = board.getPiece(new PositionsPieces(1, 3)).getPieceColor();
        Piece.Type type = board.getPiece(new PositionsPieces(1,3)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, color);
        Assert.assertEquals(Piece.Type.NORMAL, type);
        Assert.assertNotNull(board.getPiece(new PositionsPieces(1,3)));
    }

    @Test
    public void testAddNormalBlackPieceOnBoard() {

        // When
        board.addPieceToBoard(new PositionsPieces(2,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        Piece.Color color = board.getPiece(new PositionsPieces(2, 4)).getPieceColor();
        Piece.Type type = board.getPiece(new PositionsPieces(2,4)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.BLACK, color);
        Assert.assertEquals(Piece.Type.NORMAL, type);
        Assert.assertNotNull(board.getPiece(new PositionsPieces(2,4)));
    }

    @Test
    public void testAddQueenWhitePieceOnBoard() {

        // When
        board.addPieceToBoard(new PositionsPieces(3,3), new Piece(Piece.Color.WHITE, Piece.Type.QUEEN));
        Piece.Color color = board.getPiece(new PositionsPieces(3, 3)).getPieceColor();
        Piece.Type type = board.getPiece(new PositionsPieces(3,3)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, color);
        Assert.assertEquals(Piece.Type.QUEEN, type);
        Assert.assertNotNull(board.getPiece(new PositionsPieces(3,3)));
    }

    @Test
    public void testAddQueenBlackPieceOnBoard() {

        // When
        board.addPieceToBoard(new PositionsPieces(5,3), new Piece(Piece.Color.BLACK, Piece.Type.QUEEN));
        Piece.Color color = board.getPiece(new PositionsPieces(5, 3)).getPieceColor();
        Piece.Type type = board.getPiece(new PositionsPieces(5,3)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.BLACK, color);
        Assert.assertEquals(Piece.Type.QUEEN, type);
        Assert.assertNotNull(board.getPiece(new PositionsPieces(5,3)));
    }

    @Test
    public void testRemoveOnePieceFromBoard() {

        // When
        board.removePieceFromBoard(new PositionsPieces(3, 5));
        int size = board.getBoard().size();

        // Then
        Assert.assertEquals(23, size);
    }

    @Test
    public void testRemoveTwoPiecesFromBoard() {

        // When
        board.removePieceFromBoard(new PositionsPieces(3, 5));
        board.removePieceFromBoard(new PositionsPieces(4,2));
        int size = board.getBoard().size();

        // Then
        Assert.assertEquals(22, size);
    }

    @Test
    public void testGetPieceWhite() {

        // When
        Piece white = board.getPiece(new PositionsPieces(2, 6));
        Piece.Color color = white.getPieceColor();
        Piece.Type type = white.getPieceType();

        Piece.Color expectedColor = Piece.Color.WHITE;
        Piece.Type expectedType = Piece.Type.NORMAL;

        // Then
        Assert.assertEquals(expectedColor, color);
        Assert.assertEquals(expectedType, type);
    }

    @Test
    public void testGetPieceBlack() {

        // When
        Piece black = board.getPiece(new PositionsPieces(3, 1));
        Piece.Color color = black.getPieceColor();
        Piece.Type type = black.getPieceType();

        Piece.Color expectedColor = Piece.Color.BLACK;
        Piece.Type expectedType = Piece.Type.NORMAL;

        // Then
        Assert.assertEquals(expectedColor, color);
        Assert.assertEquals(expectedType, type);
    }

    @Test
    public void testGetPieceOnCorrectPosition() {

        // When
        Piece piece1 = board.getPiece(new PositionsPieces(2, 6));
        Piece piece2 = board.getPiece(new PositionsPieces(5, 5));

        // Then
        Assert.assertNotNull(piece1);
        Assert.assertNotNull(piece2);
    }

    @Test
    public void testGetPieceOnWrongPosition() {

        // When
        Piece piece1 = board.getPiece(new PositionsPieces(2, 4));
        Piece piece2 = board.getPiece(new PositionsPieces(5, 3));

        // Then
        Assert.assertNull(piece1);
        Assert.assertNull(piece2);
    }

    @Test
    public void testIsFieldNull() {

        // When
        board.addPieceToBoard(new PositionsPieces(6, 4),new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // Then
        Assert.assertTrue(board.isFieldNull(new PositionsPieces(3,3)));
        Assert.assertFalse(board.isFieldNull(new PositionsPieces(6,4)));
    }

    @Test
    public void testVirtualMoveWhite() {
        // Given
        PositionsPieces newPosition = new PositionsPieces(4,4);
        PositionsPieces oldPosition = new PositionsPieces(3, 5);
        Piece pieceWhite = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);

        // When
        board.movePieceOnBoard(newPosition, oldPosition, pieceWhite);
        Piece piece = board.getPiece(newPosition);
        Boolean isFieldNull = board.isFieldNull(oldPosition);

        // Then
        Assert.assertEquals(piece, pieceWhite);
        Assert.assertTrue(isFieldNull);
    }

    @Test
    public void testVirtualMoveBlack() {
        // Given
        PositionsPieces newPosition = new PositionsPieces(3,3);
        PositionsPieces oldPosition = new PositionsPieces(2, 2);
        Piece pieceBlack = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);

        // When
        board.movePieceOnBoard(newPosition, oldPosition, pieceBlack);
        Piece piece = board.getPiece(newPosition);
        Boolean isFieldNull = board.isFieldNull(oldPosition);

        // Then
        Assert.assertEquals(piece, pieceBlack);
        Assert.assertTrue(isFieldNull);
    }
}
