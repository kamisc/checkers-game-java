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

public class QueenKickScannerTestSuite {

    private Board board;
    private QueenKickScanner queenKickScanner;
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

        queenKickScanner = new QueenKickScanner(board);
        whiteQueen = new Piece(Piece.Color.WHITE, Piece.Type.QUEEN);
        blackQueen = new Piece(Piece.Color.BLACK, Piece.Type.QUEEN);
    }

    @Test
    public void testQueenKickScannerForWhitesWhenPiecesAroundInDifferentPositions() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(4,2);
        PositionsPieces kicked1 = new PositionsPieces(1,5);
        PositionsPieces kicked2 = new PositionsPieces(5,1);
        PositionsPieces kicked3 = new PositionsPieces(3,1);
        PositionsPieces kicked4 = new PositionsPieces(6,4);

        board.addPieceToBoard(kickerPosition, whiteQueen);
        board.addPieceToBoard(kicked1, blackQueen);
        board.addPieceToBoard(kicked2, blackQueen);
        board.addPieceToBoard(kicked3, blackQueen);
        board.addPieceToBoard(kicked4, blackQueen);

        // When
        queenKickScanner.calculateAllPossibleWhiteQueenKicks();

        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicks = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(1, sizeKickers);
        Assert.assertEquals(4, sizeKicks);
    }

    @Test
    public void testQueenKickScannerForBlacksWhenPiecesAroundInDifferentPositions() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(4,2);
        PositionsPieces kicked1 = new PositionsPieces(1,5);
        PositionsPieces kicked2 = new PositionsPieces(5,1);
        PositionsPieces kicked3 = new PositionsPieces(3,1);
        PositionsPieces kicked4 = new PositionsPieces(6,4);

        board.addPieceToBoard(kickerPosition, blackQueen);
        board.addPieceToBoard(kicked1, whiteQueen);
        board.addPieceToBoard(kicked2, whiteQueen);
        board.addPieceToBoard(kicked3, whiteQueen);
        board.addPieceToBoard(kicked4, whiteQueen);

        // When
        queenKickScanner.calculateAllPossibleBlackQueenKicks();

        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicks = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(1, sizeKickers);
        Assert.assertEquals(4, sizeKicks);
    }

    @Test
    public void testQueenKickScannerForWhitesWhenPiecesInCorners() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(3,3);
        PositionsPieces topLeft = new PositionsPieces(0,0);
        PositionsPieces topRight = new PositionsPieces(6,0);
        PositionsPieces bottomLeft = new PositionsPieces(1,7);
        PositionsPieces bottomRight = new PositionsPieces(7,7);

        board.addPieceToBoard(kickerPosition, whiteQueen);
        board.addPieceToBoard(topLeft, blackQueen);
        board.addPieceToBoard(topRight, blackQueen);
        board.addPieceToBoard(bottomLeft, blackQueen);
        board.addPieceToBoard(bottomRight, blackQueen);

        // When
        queenKickScanner.calculateAllPossibleWhiteQueenKicks();

        // Then
        Assert.assertTrue(queenKickScanner.getAllPossibleQueenKicks().isEmpty());
        Assert.assertTrue(queenKickScanner.getAllQueenPiecesWhichKick().isEmpty());
    }

    @Test
    public void testQueenKickScannerForBlacksWhenPiecesInCorners() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(3,3);
        PositionsPieces topLeft = new PositionsPieces(0,0);
        PositionsPieces topRight = new PositionsPieces(6,0);
        PositionsPieces bottomLeft = new PositionsPieces(1,7);
        PositionsPieces bottomRight = new PositionsPieces(7,7);

        board.addPieceToBoard(kickerPosition, blackQueen);
        board.addPieceToBoard(topLeft, whiteQueen);
        board.addPieceToBoard(topRight, whiteQueen);
        board.addPieceToBoard(bottomLeft, whiteQueen);
        board.addPieceToBoard(bottomRight, whiteQueen);

        // When
        queenKickScanner.calculateAllPossibleBlackQueenKicks();

        // Then
        Assert.assertTrue(queenKickScanner.getAllPossibleQueenKicks().isEmpty());
        Assert.assertTrue(queenKickScanner.getAllQueenPiecesWhichKick().isEmpty());
    }

    @Test
    public void testQueenKickScannerForWhitesWhenBlocked() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(4,4);
        PositionsPieces blocked1 = new PositionsPieces(2,2);
        PositionsPieces blocked2= new PositionsPieces(1,1);

        board.addPieceToBoard(kickerPosition, whiteQueen);
        board.addPieceToBoard(blocked1, blackQueen);
        board.addPieceToBoard(blocked2, blackQueen);

        // When
        queenKickScanner.calculateAllPossibleWhiteQueenKicks();

        // Then
        Assert.assertTrue(queenKickScanner.getAllPossibleQueenKicks().isEmpty());
        Assert.assertTrue(queenKickScanner.getAllQueenPiecesWhichKick().isEmpty());
    }

    @Test
    public void testQueenKickScannerForBlacksWhenBlocked() {
        // Given
        PositionsPieces kickerPosition = new PositionsPieces(3,3);
        PositionsPieces blocked1 = new PositionsPieces(4,2);
        PositionsPieces blocked2= new PositionsPieces(5,1);

        board.addPieceToBoard(kickerPosition, blackQueen);
        board.addPieceToBoard(blocked1, whiteQueen);
        board.addPieceToBoard(blocked2, whiteQueen);

        // When
        queenKickScanner.calculateAllPossibleBlackQueenKicks();

        // Then
        Assert.assertTrue(queenKickScanner.getAllPossibleQueenKicks().isEmpty());
        Assert.assertTrue(queenKickScanner.getAllQueenPiecesWhichKick().isEmpty());
    }

    @Test
    public void testQueenKickScannerForWhites2On1() {
        // Given
        PositionsPieces kickerPosition1 = new PositionsPieces(1,1);
        PositionsPieces kickerPosition2 = new PositionsPieces(6,6);
        PositionsPieces kickedPosition = new PositionsPieces(3,3);

        board.addPieceToBoard(kickerPosition1, whiteQueen);
        board.addPieceToBoard(kickerPosition2, whiteQueen);
        board.addPieceToBoard(kickedPosition, blackQueen);

        // When
        queenKickScanner.calculateAllPossibleWhiteQueenKicks();
        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicked = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(2, sizeKickers);
        Assert.assertEquals(1, sizeKicked);
    }

    @Test
    public void testQueenKickScannerForBlacks2On1() {
        // Given
        PositionsPieces kickerPosition1 = new PositionsPieces(1,5);
        PositionsPieces kickerPosition2 = new PositionsPieces(5,1);
        PositionsPieces kickedPosition = new PositionsPieces(3,3);

        board.addPieceToBoard(kickerPosition1, blackQueen);
        board.addPieceToBoard(kickerPosition2, blackQueen);
        board.addPieceToBoard(kickedPosition, whiteQueen);

        // When
        queenKickScanner.calculateAllPossibleBlackQueenKicks();
        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicked = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(2, sizeKickers);
        Assert.assertEquals(1, sizeKicked);
    }

    @Test
    public void testQueenKickScannerForWhites2On2() {
        // Given
        PositionsPieces kickerPosition1 = new PositionsPieces(3,5);
        PositionsPieces kickerPosition2 = new PositionsPieces(5,1);
        PositionsPieces kickedPosition1 = new PositionsPieces(2,4);
        PositionsPieces kickedPosition2 = new PositionsPieces(6,2);

        board.addPieceToBoard(kickerPosition1, whiteQueen);
        board.addPieceToBoard(kickerPosition2, whiteQueen);
        board.addPieceToBoard(kickedPosition1, blackQueen);
        board.addPieceToBoard(kickedPosition2, blackQueen);

        // When
        queenKickScanner.calculateAllPossibleWhiteQueenKicks();
        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicked = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(2, sizeKickers);
        Assert.assertEquals(2, sizeKicked);
    }

    @Test
    public void testQueenKickScannerForBlacks2On2() {
        // Given
        PositionsPieces kickerPosition1 = new PositionsPieces(2,2);
        PositionsPieces kickerPosition2 = new PositionsPieces(7,1);
        PositionsPieces kickedPosition1 = new PositionsPieces(4,4);
        PositionsPieces kickedPosition2 = new PositionsPieces(1,1);

        board.addPieceToBoard(kickerPosition1, blackQueen);
        board.addPieceToBoard(kickerPosition2, blackQueen);
        board.addPieceToBoard(kickedPosition1, whiteQueen);
        board.addPieceToBoard(kickedPosition2, whiteQueen);

        // When
        queenKickScanner.calculateAllPossibleBlackQueenKicks();
        int sizeKickers = queenKickScanner.getAllQueenPiecesWhichKick().size();
        int sizeKicked = queenKickScanner.getAllPossibleQueenKicks().size();

        // Then
        Assert.assertEquals(2, sizeKickers);
        Assert.assertEquals(2, sizeKicked);
    }
}
