package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.*;
import java.util.ArrayList;

/**
 * Author Kamil Seweryn
 */

public class Ranking implements Serializable {

    private ArrayList<Integer> ranking = new ArrayList<>();
    private ArrayList<Integer> rankingTemp = new ArrayList<>();
    private File file = new File("ranking.list");

    private int whiteWins = 0;
    private int blackWins = 0;
    private int draws = 0;

    public Ranking() {
        loadRanking();
    }

    public void showRanking() {
        Alert alert = new Alert(Alert.AlertType.NONE);
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
                rankingTemp.addAll((ArrayList) readList);
            }

            rankingTemp = (ArrayList<Integer>) readList;

            whiteWins = rankingTemp.get(0);
            blackWins = rankingTemp.get(1);
            draws = rankingTemp.get(2);
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
