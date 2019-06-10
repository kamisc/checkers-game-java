package pl.sewerynkamil.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import pl.sewerynkamil.board.Board;

/**
 * Author Kamil Seweryn
 */

public class MenuDesign {

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

    public MenuDesign() {
        createMenu();

        authorAction();
        rankingAction();
        saveGameAction();
    }

    private void createMenu() {
        menuBar.getMenus().add(game);
        game.getItems().add(startGame);
        game.getItems().add(difficulty);

        menuBar.getMenus().add(data);
        data.getItems().add(ranking);
        data.getItems().add(saveGame);
        data.getItems().add(loadGame);

        menuBar.getMenus().add(author);
        author.getItems().add(info);
    }

    private void authorAction() {
        author.setOnAction(e -> new Author());
    }

    private void rankingAction() {
        ranking.setOnAction(e -> new Ranking().showRanking());
    }

    private void saveGameAction() {
        saveGame.setOnAction(e -> new SaveGame(new Board()).save());
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
