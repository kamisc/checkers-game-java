package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.util.Random;
import java.util.Set;

/**
 * Author Kamil Seweryn
 */

public class Computer {

    private Random random = new Random();
    private Board board;

    private int difficultyLevel = 0;

    public Computer(Board board) {
        this.board = board;
    }

    public PositionsPieces selectPosition(Set<PositionsPieces> positions) {
        Object[] object = positions.toArray();
        return (PositionsPieces) object[random.nextInt(object.length)];
    }

    public boolean checkBlacksEnd(Set<PositionsPieces> restOfBlacks) {
        return restOfBlacks.size() == 0;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
