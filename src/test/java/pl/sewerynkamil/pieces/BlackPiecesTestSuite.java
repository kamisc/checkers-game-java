package pl.sewerynkamil.pieces;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlackPiecesTestSuite {

    private BlackPieces blackPieces;

    @Before
    public void setUp() {
        // Given
        blackPieces = new BlackPieces();
        blackPieces.setUpPieces();
    }

    @Test
    public void testPieceColorBlack() {

        // When
        Piece.Color black = blackPieces.getBlackPiecesMap().get(new PositionsPieces(5,1)).getPieceColor();

        // Then
        Assert.assertEquals(Piece.Color.BLACK, black);
    }

    @Test
    public void testPieceTypeNormalBlack() {

        // When
        Piece.Type normal = blackPieces.getBlackPiecesMap().get(new PositionsPieces(4, 0)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Type.NORMAL, normal);
    }

    @Test
    public void testNoPieceBlack() {

        // When
        Piece piece = blackPieces.getBlackPiecesMap().get(new PositionsPieces(7, 0));

        // Then
        Assert.assertNull(piece);
    }

    @Test
    public void testBlackMapSize() {

        // When
        int size = blackPieces.getBlackPiecesMap().size();

        // Then
        Assert.assertEquals(12, size);
    }
}
