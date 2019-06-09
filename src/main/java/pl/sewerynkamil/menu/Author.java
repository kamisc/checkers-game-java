package pl.sewerynkamil.menu;

import javafx.scene.control.Alert;

/**
 * Author Kamil Seweryn
 */

public class Author {

    public Author() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("About");
        alert.setContentText("Checkers Game" +
                "\nCreated by Kamil Seweyrn" +
                "\nMay-June 2019" +
                "\nhttps://github.com/kamisc");

        alert.showAndWait();
    }
}
