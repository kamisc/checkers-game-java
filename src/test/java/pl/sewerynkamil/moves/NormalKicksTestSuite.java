package pl.sewerynkamil.moves;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

public class NormalKicksTestSuite {

    private Board board;
    private NormalKicks normalKicks;

    @Before
    public void setUp() {
        board = new Board();
        normalKicks = new NormalKicks(board);
    }

    @Test
    public void testNoKickPossibility() {

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(4,2));
        normalKicks.kickMovesCalculator(new PositionsPieces(3,5));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenCantMoveBehind() {
        // Given
        board.addPieceToBoard(new PositionsPieces(2,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(2,4));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenSameColor() {

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(3,1));
        normalKicks.kickMovesCalculator(new PositionsPieces(4,6));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenNotValidRightPosition() {
        // Given
        board.addPieceToBoard(new PositionsPieces(7,3), new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(6,2));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenNotValidLeftPosition() {
        // Given
        board.addPieceToBoard(new PositionsPieces(0,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(1,5));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenNotValidTopPosition() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(7,1);

        board.removePieceFromBoard(new PositionsPieces(7,1));
        board.removePieceFromBoard(new PositionsPieces(6,2));

        board.addPieceToBoard(kickerPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(7,1));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testKickPossibilityWhenNotValidBottomPosition() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(0,6);

        board.removePieceFromBoard(new PositionsPieces(0,6));
        board.removePieceFromBoard(new PositionsPieces(1,5));

        board.addPieceToBoard(kickerPosition, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(new PositionsPieces(0,6));

        // Then
        Assert.assertTrue(normalKicks.getPossibleKicks().isEmpty());
        Assert.assertTrue(normalKicks.getPossibleKickMoves().isEmpty());
    }

    @Test
    public void testOneKickPossibilityForward() {
        // Given
        PositionsPieces kickerPositon = new PositionsPieces(3,5);
        board.addPieceToBoard(new PositionsPieces(4,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(kickerPositon);
        int sizeKicks = normalKicks.getPossibleKicks().size();
        int sizeMoves = normalKicks.getPossibleKickMoves().size();

        // Then
        Assert.assertEquals(1, sizeKicks);
        Assert.assertEquals(1, sizeMoves);
    }

    @Test
    public void testTwoKicksPossibilityForward() {
        // Given
        PositionsPieces kickerPositon = new PositionsPieces(5,5);

        board.addPieceToBoard(new PositionsPieces(4,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));
        board.addPieceToBoard(new PositionsPieces(6,4), new Piece(Piece.Color.BLACK, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(kickerPositon);
        int sizeKicks = normalKicks.getPossibleKicks().size();
        int sizeMoves = normalKicks.getPossibleKickMoves().size();

        // Then
        Assert.assertEquals(2, sizeKicks);
        Assert.assertEquals(2, sizeMoves);
    }

    @Test
    public void testOneKickPossibilityBackward() {
        // Given
        PositionsPieces kickerPositon = new PositionsPieces(7,1);

        board.removePieceFromBoard(new PositionsPieces(7,1));
        board.addPieceToBoard(kickerPositon, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(kickerPositon);
        int sizeKicks = normalKicks.getPossibleKicks().size();
        int sizeMoves = normalKicks.getPossibleKickMoves().size();

        // Then
        Assert.assertEquals(1, sizeKicks);
        Assert.assertEquals(1, sizeMoves);
    }

    @Test
    public void testTwoKicksPossibilityBackward() {
        // Given
        PositionsPieces kickerPositon = new PositionsPieces(3,1);

        board.removePieceFromBoard(new PositionsPieces(3,1));

        board.addPieceToBoard(kickerPositon, new Piece(Piece.Color.WHITE, Piece.Type.NORMAL));

        // When
        normalKicks.kickMovesCalculator(kickerPositon);
        int sizeKicks = normalKicks.getPossibleKicks().size();
        int sizeMoves = normalKicks.getPossibleKickMoves().size();

        // Then
        Assert.assertEquals(2, sizeKicks);
        Assert.assertEquals(2, sizeMoves);
    }




}
