package pl.sewerynkamil.game;

import pl.sewerynkamil.board.Board;
import pl.sewerynkamil.pieces.Piece;
import pl.sewerynkamil.pieces.PositionsPieces;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author Kamil Seweryn
 */

public class SaveLoadGame implements Serializable {

    private File file = new File("board.list");
    private Map<PositionsPieces, Piece> loadBoard = new HashMap<>();
    private Board board;

    public SaveLoadGame(Board board) {
        this.board = board;
    }

    public boolean isFileExist() {
        File file = new File("board.list");
        return file.exists();
    }

    public void saveGame() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(board.getBoard());
            oos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readBoard = ois.readObject();
            if(readBoard instanceof HashMap) {
                loadBoard = (HashMap<PositionsPieces, Piece>) readBoard;
            }
            ois.close();
            board.setBoard(loadBoard);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void removeFile() {
        File file = new File("board.list");
        file.delete();
    }

    public Map<PositionsPieces, Piece> getLoadBoard() {
        return loadBoard;
    }
}
