package pl.sewerynkamil.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.HashSet;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class ComputerTestSuite {

    private Board board;
    private Computer computer;

    @Before
    public void setUp() {
        board = new Board();
        computer = new Computer(board);
    }

    @Test
    public void testCheckBlacksEnd() {

        // When
        boolean isBlacksEnd = computer.checkBlacksEnd(board.getBoard().keySet());

        // Then
        Assert.assertFalse(isBlacksEnd);
    }

    @Test
    public void testCheckBlacksNoEnd() {
        // Given
        Set<PositionsPieces> restOfBlacks = new HashSet<>();

        // When
        boolean isBlacksEnd = computer.checkBlacksEnd(restOfBlacks);

        // Then
        Assert.assertTrue(isBlacksEnd);
    }

    @Test
    public void testStartDifficultyLevel() {

        // When
        int difficultyLevel = computer.getDifficultyLevel();

        // Then
        Assert.assertEquals(0, difficultyLevel);
    }

    @Test
    public void testChangeDifficultyLevel() {

        // When
        computer.setDifficultyLevel(1);
        int difficultyLevel = computer.getDifficultyLevel();

        // Then
        Assert.assertEquals(1, difficultyLevel);
    }

}
