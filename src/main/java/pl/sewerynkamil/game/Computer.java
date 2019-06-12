package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Graphics;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Random;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class Computer {

    private Random random = new Random();
    private Board board;
    private MouseControl mouseControl;

    private int difficultyLevel = 0;

    public Computer(Board board, MouseControl mouseControl) {
        this.board = board;
        this.mouseControl = mouseControl;
    }

    public PositionsPieces selectPosition(Set<PositionsPieces> positions) {
        Object[] object = positions.toArray();
        return (PositionsPieces) object[random.nextInt(object.length)];
    }

    public boolean checkBlacksEnd() {
        return board.getEndGame().getRestOfBlacks().size() == 0;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
