package pl.sewerynkamil.pieces;

import org.junit.Assert;
import org.junit.Test;

public class WhitePiecesTestSuite {

    @Test
    public void testPieceColorWhite() {
        // Given
        WhitePieces whitePieces = new WhitePieces();
        whitePieces.setUpPieces();

        // When
        Piece.Color white = whitePieces.getWhitePiecesMap().get(new PositionsPieces(7,7)).getPieceColor();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, white);
    }

    @Test
    public void testPieceTypeNormalWhite() {
        // Given
        WhitePieces whitePieces = new WhitePieces();
        whitePieces.setUpPieces();

        // When
        Piece.Type normal = whitePieces.getWhitePiecesMap().get(new PositionsPieces(2, 6)).getPieceType();

        // Then
        Assert.assertEquals(Piece.Type.NORMAL, normal);
    }

    @Test
    public void testNoPieceWhite() {
        // Given
        WhitePieces whitePieces = new WhitePieces();
        whitePieces.setUpPieces();

        // When
        Piece piece = whitePieces.getWhitePiecesMap().get(new PositionsPieces(7, 6));

        // Then
        Assert.assertNull(piece);
    }

    @Test
    public void testWhiteMapSize() {
        // Given
        WhitePieces whitePieces = new WhitePieces();
        whitePieces.setUpPieces();

        // When
        int size = whitePieces.getWhitePiecesMap().size();

        // Then
        Assert.assertEquals(12, size);
    }

}
