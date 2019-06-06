package pl.sewerynkamil.pieces;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author Kamil Seweryn
 */

public class PositionPiecesTestSuite {

    @Test
    public void testGetCol() {
        // Given
        PositionsPieces positionsPieces = new PositionsPieces(5, 3);

        // When
        int col = positionsPieces.getCol();

        // Then
        Assert.assertEquals(5, col);
    }

    @Test
    public void testGetRow() {
        // Given
        PositionsPieces positionsPieces = new PositionsPieces(2, 6);

        // When
        int row = positionsPieces.getRow();

        // Then
        Assert.assertEquals(6, row);
    }

    @Test
    public void testValidPosition() {
        // Given
        PositionsPieces positionsPieces = new PositionsPieces(6, 4);

        // When
        boolean isValid = positionsPieces.isValidPosition();

        // Then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testNotValidPosition() {
        // Given
        PositionsPieces positionsPieces1 = new PositionsPieces(6, 8);
        PositionsPieces positionsPieces2 = new PositionsPieces(- 2, 0);

        // When
        boolean isValid1 = positionsPieces1.isValidPosition();
        boolean isValid2 = positionsPieces2.isValidPosition();

        // Then
        Assert.assertFalse(isValid1);
        Assert.assertFalse(isValid2);
    }


}
