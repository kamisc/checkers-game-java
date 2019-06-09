package pl.sewerynkamil.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import pl.sewerynkamil.board.Board;

/**
 * Author Kamil Seweryn
 */

public class MenuDesign {

    private Board board;

    private MenuBar menuBar = new MenuBar();

    private Menu game = new Menu("Game");
    private MenuItem startGame = new MenuItem("Start game");
    private MenuItem difficulty = new MenuItem("Difficulty");

    private Menu data = new Menu("Data");
    private MenuItem ranking = new MenuItem("Ranking");
    private MenuItem saveGame = new MenuItem("Save game");
    private MenuItem loadGame = new MenuItem("Load game");

    private Menu author = new Menu("Author");
    private MenuItem info = new MenuItem("Author");

    public MenuDesign(Board board) {
        this.board = board;

        menuBar.getMenus().add(game);
        game.getItems().add(startGame);
        game.getItems().add(difficulty);

        menuBar.getMenus().add(data);
        data.getItems().add(ranking);
        data.getItems().add(saveGame);
        data.getItems().add(loadGame);

        menuBar.getMenus().add(author);
        author.getItems().add(info);

        author.setOnAction(e -> new Author());
        ranking.setOnAction(e -> new Ranking().showRanking());
        saveGame.setOnAction(e -> new SaveGame(board).save());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
