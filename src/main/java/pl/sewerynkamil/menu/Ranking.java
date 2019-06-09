package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import pl.sewerynkamil.board.Resources;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Author Kamil Seweryn
 */

public class Ranking implements Serializable {

    ArrayList<Integer> ranking = new ArrayList<>();
    File file = new File("ranking.list");

    private int whiteWins = 0;
    private int blackWins = 0;
    private int draws = 0;

    public Ranking() {

    }

    public void showRanking() {
        loadRanking();

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setHeaderText(null);
        alert.setTitle("Ranking");
        alert.setContentText("White Player Wins: " + whiteWins +
                "\nBlack Player Wins: " + blackWins +
                "\nDraws: " + draws);

        ButtonType ok = new ButtonType("OK");
        ButtonType clear = new ButtonType("Clear");

        alert.getButtonTypes().setAll(clear, ok);

        alert.showAndWait();
    }

    private void saveRanking() {
        try {
            ranking.add(whiteWins);
            ranking.add(blackWins);
            ranking.add(draws);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(ranking);
            oos.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void loadRanking() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Object readList = ois.readObject();
            if(readList instanceof ArrayList) {
                ranking.addAll((ArrayList) readList);
            }

            whiteWins = ranking.get(0);
            blackWins = ranking.get(1);
            draws = ranking.get(2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setWhiteWins() {
        whiteWins++;
        saveRanking();
    }

    public void setBlackWins() {
        blackWins++;
        saveRanking();
    }

    public void setDraws() {
        draws++;
        saveRanking();
    }
}
