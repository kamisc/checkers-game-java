package pl.sewerynkamil.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Author Kamil Seweryn
 */

public class MenuDesign {

    MenuBar menuBar = new MenuBar();

    Menu game = new Menu("Game");
    MenuItem startGame = new MenuItem("Start game");
    MenuItem difficulty = new MenuItem("Difficulty");

    Menu data = new Menu("Data");
    MenuItem ranking = new MenuItem("Ranking");
    MenuItem saveGame = new MenuItem("Save game");
    MenuItem loadGame = new MenuItem("Load game");

    Menu author = new Menu("Author");

    public MenuDesign() {
        menuBar.getMenus().add(game);
        game.getItems().add(startGame);
        game.getItems().add(difficulty);

        menuBar.getMenus().add(data);
        data.getItems().add(ranking);
        data.getItems().add(saveGame);
        data.getItems().add(loadGame);

        menuBar.getMenus().add(author);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
