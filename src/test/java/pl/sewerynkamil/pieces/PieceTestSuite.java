package pl.sewerynkamil.pieces;

import org.junit.Assert;
import org.junit.Test;

public class PieceTestSuite {

    @Test
    public void testPieceColorWhite() {
        // Given
        Piece pieceWhite = new Piece(Piece.Color.WHITE, Piece.Type.QUEEN);

        // When
        Piece.Color white = pieceWhite.getPieceColor();

        // Then
        Assert.assertEquals(Piece.Color.WHITE, white);
    }

    @Test
    public void testPieceColorIsWhite() {
        // Given
        Piece pieceWhite = new Piece(Piece.Color.WHITE, Piece.Type.QUEEN);

        // When
        boolean isWhite = pieceWhite.getPieceColor().isWhite();
        boolean isBlack = pieceWhite.getPieceColor().isBlack();

        // Then
        Assert.assertTrue(isWhite);
        Assert.assertFalse(isBlack);
    }

    @Test
    public void testPieceColorBlack() {
        // Given
        Piece pieceBlack = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);

        // When
        Piece.Color black = pieceBlack.getPieceColor();

        // Then
        Assert.assertEquals(Piece.Color.BLACK, black);
    }

    @Test
    public void testPieceColorIsBlack() {
        // Given
        Piece pieceBlack = new Piece(Piece.Color.BLACK, Piece.Type.NORMAL);

        // When
        boolean isBlack = pieceBlack.getPieceColor().isBlack();
        boolean isWhite = pieceBlack.getPieceColor().isWhite();

        // Then
        Assert.assertTrue(isBlack);
        Assert.assertFalse(isWhite);
    }

    @Test
    public void testPieceTypeNormal() {
        // Given
        Piece pieceNormal = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);

        // When
        Piece.Type normal = pieceNormal.getPieceType();

        // Then
        Assert.assertEquals(Piece.Type.NORMAL, normal);
    }

    @Test
    public void testPieceTypeIsNormal() {
        // Given
        Piece pieceNormal = new Piece(Piece.Color.WHITE, Piece.Type.NORMAL);

        // When
        boolean isNormal = pieceNormal.getPieceType().isNormal();
        boolean isQueen = pieceNormal.getPieceType().isQueen();

        // Then
        Assert.assertTrue(isNormal);
        Assert.assertFalse(isQueen);
    }

    @Test
    public void testPieceTypeQueen() {
        // Given
        Piece pieceQueen = new Piece(Piece.Color.BLACK, Piece.Type.QUEEN);

        // When
        Piece.Type queen = pieceQueen.getPieceType();

        // Then
        Assert.assertEquals(Piece.Type.QUEEN, queen);
    }

    @Test
    public void testPieceTypeIsQueen() {
        // Given
        Piece pieceQueen = new Piece(Piece.Color.BLACK, Piece.Type.QUEEN);

        // When
        boolean isQueen = pieceQueen.getPieceType().isQueen();
        boolean isNormal = pieceQueen.getPieceType().isNormal();

        // Then
        Assert.assertTrue(isQueen);
        Assert.assertFalse(isNormal);
    }
}
