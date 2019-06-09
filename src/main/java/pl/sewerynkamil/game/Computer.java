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
    private Graphics graphics;

    public Computer(Board board, Graphics graphics) {
        this.board = board;
        this.graphics = graphics;
    }

    public PositionsPieces selectPosition(Set<PositionsPieces> positions) {
        Object[] object = positions.toArray();
        return (PositionsPieces) object[random.nextInt(object.length)];
    }

    public void computerMove() {

    }
}
