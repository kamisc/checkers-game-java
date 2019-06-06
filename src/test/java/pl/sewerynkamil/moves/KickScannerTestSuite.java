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

public class KickScannerTestSuite {

    private Board board;
    private KickScanner kickScanner;
    private Piece pieceWhite;
    private Piece pieceBlack;


    @Before
    public void setUp() {
        board = new Board();
        kickScanner = new KickScanner(board);
        pieceWhite = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);
        pieceBlack = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);
    }

    @Test
    public void testKickScannerOnStartingWhitesPosition() {

        // When
        kickScanner.calculateAllPossibleWhiteKicks();

        // Then
        Assert.assertTrue(kickScanner.getAllPiecesWhichKick().isEmpty());
        Assert.assertTrue(kickScanner.getAllPossibleKicks().isEmpty());
    }

    @Test
    public void testKickScannerOnStartingBlacksPosition() {

        // When
        kickScanner.calculateAllPossibleBlackKicks();

        // Then
        Assert.assertTrue(kickScanner.getAllPiecesWhichKick().isEmpty());
        Assert.assertTrue(kickScanner.getAllPossibleKicks().isEmpty());
    }

    @Test
    public void testKickScannerForWhites() {
        // Given
        board.addPieceToBoard(new PositionsPieces(0,4), pieceBlack);
        board.addPieceToBoard(new PositionsPieces(2,4), pieceBlack);
        board.addPieceToBoard(new PositionsPieces(4,4), pieceBlack);
        board.addPieceToBoard(new PositionsPieces(6,4), pieceBlack);

        // When
        kickScanner.calculateAllPossibleWhiteKicks();
        int possibleKicks = kickScanner.getAllPossibleKicks().size();
        int possibleKickers = kickScanner.getAllPiecesWhichKick().size();

        // Then
        Assert.assertEquals(3,possibleKicks);
        Assert.assertEquals(4,possibleKickers);
    }

    @Test
    public void testKickScannerForBlacks() {
        // Given
        board.addPieceToBoard(new PositionsPieces(1,3), pieceWhite);
        board.addPieceToBoard(new PositionsPieces(3,3), pieceWhite);
        board.addPieceToBoard(new PositionsPieces(5,3), pieceWhite);
        board.addPieceToBoard(new PositionsPieces(7,3), pieceWhite);

        // When
        kickScanner.calculateAllPossibleBlackKicks();
        int possibleKicks = kickScanner.getAllPossibleKicks().size();
        int possibleKickers = kickScanner.getAllPiecesWhichKick().size();

        // Then
        Assert.assertEquals(3,possibleKicks);
        Assert.assertEquals(4,possibleKickers);
    }

    @Test
    public void testKickScannerWhenBlackPieceBlocked() {
        // Given
        board.addPieceToBoard(new PositionsPieces(3,3), pieceBlack);
        board.addPieceToBoard(new PositionsPieces(2,4), pieceBlack);
        board.addPieceToBoard(new PositionsPieces(1,3), pieceBlack);

        // When
        kickScanner.calculateAllPossibleWhiteKicks();

        // Then
        Assert.assertTrue(kickScanner.getAllPiecesWhichKick().isEmpty());
        Assert.assertTrue(kickScanner.getAllPossibleKicks().isEmpty());
    }

    @Test
    public void testKickScannerWhenWhitePieceBlocked() {
        // Given
        board.addPieceToBoard(new PositionsPieces(3,3), pieceWhite);
        board.addPieceToBoard(new PositionsPieces(2,4), pieceWhite);
        board.addPieceToBoard(new PositionsPieces(4,4), pieceWhite);

        // When
        kickScanner.calculateAllPossibleBlackKicks();

        // Then
        Assert.assertTrue(kickScanner.getAllPiecesWhichKick().isEmpty());
        Assert.assertTrue(kickScanner.getAllPossibleKicks().isEmpty());
    }
}
