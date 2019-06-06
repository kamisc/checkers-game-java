package pl.sewerynkamil.pieces;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author Kamil Seweryn
 */

public class WhitePiecesTestSuite {

    private WhitePieces whitePieces;

    @Before
    public void setUp() {
        // Given
        whitePieces = new WhitePieces();
        whitePieces.setUpPieces();
    }

    @Test
    public void testPieceColorWhite() {

        // When
        Piece.Color white = whitePieces.getWhitePiecesMap().get(new PositionsPieces(7,7)).getPieceColor();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, white);
    }

    @Test
    public void testPieceTypeNormalWhite() {

        // When
        Piece.Type normal = whitePieces.getWhitePiecesMap().get(new PositionsPieces(2, 6)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Type.NORMAL, normal);
    }

    @Test
    public void testNoPieceWhite() {

        // When
        Piece piece = whitePieces.getWhitePiecesMap().get(new PositionsPieces(7, 6));

        // Then
        Assert.assertNull(piece);
    }

    @Test
    public void testWhiteMapSize() {

        // When
        int size = whitePieces.getWhitePiecesMap().size();

        // Then
        Assert.assertEquals(12, size);
    }

}
