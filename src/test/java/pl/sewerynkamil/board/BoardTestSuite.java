package pl.sewerynkamil.board;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.moves.NormalKicks;
import pl.sewerynkamil.moves.QueenKicks;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

/**
 * Author Kamil Seweryn
 */

public class BoardTestSuite {

    private Board board;
    private NormalKicks normalKicks;
    private QueenKicks queenKicks;

    @Before
    public void setUp() {
        // Given
        board = new Board();
        normalKicks = new NormalKicks(board);
        queenKicks = new QueenKicks(board);
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
    public void testMovePieceOnBoardWhite() {
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
    public void testMovePieceOnBoardBlack() {
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

    @Test
    public void testFindOpositePiece() {
        // Given
        PositionsPieces oldPosition = new PositionsPieces(2,2);
        PositionsPieces newPosition = new PositionsPieces(4,4);
        PositionsPieces expectedPosition = new PositionsPieces(3,3);

        board.removePieceFromBoard(new PositionsPieces(5,5));
        board.addPieceToBoard(expectedPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        normalKicks.kickMovesCalculator(oldPosition);

        // When
        PositionsPieces kickPosition = board.findOpositePosition(newPosition, normalKicks.getPossibleKicks(), queenKicks.getPossibleKicks());

        // Then
        Assert.assertEquals(expectedPosition, kickPosition);
    }

    @Test
    public void testKickPieceFromBoardByWhite() {
        // Given
        PositionsPieces oldPosition = new PositionsPieces(3,5);
        PositionsPieces newPosition = new PositionsPieces(5,3);
        PositionsPieces expectedKickPosition = new PositionsPieces(4, 4);

        Piece piece = board.getPiece(oldPosition);

        board.removePieceFromBoard(new PositionsPieces(6,2));
        board.addPieceToBoard(expectedKickPosition, new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        normalKicks.kickMovesCalculator(oldPosition);;

        PositionsPieces kickPositon = board.findOpositePosition(newPosition, normalKicks.getPossibleKicks(), queenKicks.getPossibleKicks());

        // When
        board.kickPieceFromBoard(newPosition, oldPosition, kickPositon, piece);
        long blacksAmount = board.getBoard().keySet().stream()
                .filter(position -> board.getPiece(position).getPieceColor().isBlack())
                .count();

        // Then
        Assert.assertEquals(11, blacksAmount);
        Assert.assertEquals(expectedKickPosition,kickPositon);
    }

    @Test
    public void testKickPieceFromBoardByBlack() {
        // Given
        PositionsPieces oldPosition = new PositionsPieces(0,2);
        PositionsPieces newPosition = new PositionsPieces(2,4);
        PositionsPieces expectedKickPosition = new PositionsPieces(1, 3);

        Piece piece = board.getPiece(oldPosition);

        board.removePieceFromBoard(new PositionsPieces(3,5));
        board.addPieceToBoard(expectedKickPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        normalKicks.kickMovesCalculator(oldPosition);

        PositionsPieces kickPositon = board.findOpositePosition(newPosition, normalKicks.getPossibleKicks(), queenKicks.getPossibleKicks());

        // When
        board.kickPieceFromBoard(newPosition, oldPosition, kickPositon, piece);
        long whitesAmount = board.getBoard().keySet().stream()
                .filter(position -> board.getPiece(position).getPieceColor().isWhite())
                .count();

        // Then
        Assert.assertEquals(11, whitesAmount);
        Assert.assertEquals(expectedKickPosition,kickPositon);
    }

    @Test
    public void testPromoteWhite() {
        // Given
        PositionsPieces position = new PositionsPieces(0 ,6);
        Piece piece = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);

        // When
        board.promoteOnBoard(position, piece);

        Piece.Color expectedColor = board.getPiece(position).getPieceColor();
        Piece.Type expectedType = board.getPiece(position).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, expectedColor);
        Assert.assertEquals(Piece.Type.QUEEN, expectedType);
    }

    @Test
    public void testPromoteBlack() {
        // Given
        PositionsPieces position = new PositionsPieces(7 ,7);
        Piece piece = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);

        // When
        board.promoteOnBoard(position, piece);

        Piece.Color expectedColor = board.getPiece(position).getPieceColor();
        Piece.Type expectedType = board.getPiece(position).getPieceType();

        // Then
        Assert.assertEquals(Piece.Color.BLACK, expectedColor);
        Assert.assertEquals(Piece.Type.QUEEN, expectedType);
    }

    @Test
    public void testCalculatePromoteWhenStart() {

        // When
        board.calculatePromote(board.getBoard().keySet());
        int size = board.getPossiblePromote().size();

        // Then
        Assert.assertEquals(0, size);
    }

    @Test
    public void testCalculatePromote() {
        // Given
        PositionsPieces positionWhite = new PositionsPieces(3,7);
        PositionsPieces positionBlack = new PositionsPieces(6,0);

        Piece pieceWhite = board.getPiece(positionWhite);
        Piece pieceBlack = board.getPiece(positionBlack);

        board.removePieceFromBoard(positionWhite);
        board.removePieceFromBoard(positionBlack);

        board.addPieceToBoard(positionBlack, pieceWhite);
        board.addPieceToBoard(positionWhite, pieceBlack);

        // When
        board.calculatePromote(board.getBoard().keySet());
        int size = board.getPossiblePromote().size();

        // Then
        Assert.assertEquals(2, size);
    }
}
