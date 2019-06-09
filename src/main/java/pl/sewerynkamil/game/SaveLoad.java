package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.board.Resources;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.io.*;
import java.util.HashMap;

/**
 * Author Kamil Seweryn
 */

public class SaveLoad implements Serializable {

    private File file = new File(Resources.getPath("board.txt"));
    private HashMap<PositionsPieces, Piece> saveBoard = new HashMap<>();
    private Board board;

    public SaveLoad(Board board) {
        this.board = board;
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(board.getBoard());
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readBoard = ois.readObject();
            if(readBoard instanceof HashMap) {
                saveBoard.putAll((HashMap) readBoard);
            }
            ois.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
