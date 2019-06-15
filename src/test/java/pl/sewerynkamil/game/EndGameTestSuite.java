package pl.sewerynkamil.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

/**
 * Author Kamil Seweryn
 */

public class EndGameTestSuite {

    private Board board;
    private EndGame endGame;

    @Before
    public void setUp() {
        board = new Board();
        endGame = new EndGame(board);
    }

    @Test
    public void testEndGame() {

        // When
        boolean isEnd = endGame.checkEndGame(board.getBoard().keySet());
        int restOfWhites = endGame.getRestOfWhites().size();
        int restOfBlacks = endGame.getRestOfBlacks().size();

        // Then
        Assert.assertFalse(isEnd);
        Assert.assertEquals(12, restOfWhites);
        Assert.assertEquals(12, restOfBlacks);
    }

    @Test
    public void testCalculateWhitesWin() {
        // When
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

        // When
        endGame.calculatePieces(board.getBoard().keySet());
        int restOfWhites = endGame.getRestOfWhites().size();
        int restOfBlacks = endGame.getRestOfBlacks().size();

        // Then
        Assert.assertEquals(12, restOfWhites);
        Assert.assertEquals(0, restOfBlacks);
    }

    @Test
    public void testCalculateBlacksWin() {
        // When
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

        // When
        endGame.calculatePieces(board.getBoard().keySet());
        int restOfBlacks = endGame.getRestOfBlacks().size();
        int restOfWhites = endGame.getRestOfWhites().size();

        // Then
        Assert.assertEquals(12, restOfBlacks);
        Assert.assertEquals(0, restOfWhites);
    }

    @Test
    public void testCalculateDraw() {
        // When
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

        // When
        endGame.calculatePieces(board.getBoard().keySet());
        int restOfBlacks = endGame.getRestOfBlacks().size();
        int restOfWhites = endGame.getRestOfWhites().size();

        // Then
        Assert.assertEquals(1, restOfBlacks);
        Assert.assertEquals(1, restOfWhites);
    }
}
