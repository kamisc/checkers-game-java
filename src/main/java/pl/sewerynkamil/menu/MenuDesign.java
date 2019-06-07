package pl.sewerynkamil.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * Author Kamil Seweryn
 */

public class MenuDesign {

    MenuBar menuBar = new MenuBar();

    Menu game = new Menu("Game");
    Menu data = new Menu("Data");
    Menu author = new Menu("Author");

    public MenuDesign() {
        menuBar.getMenus().add(game);
        menuBar.getMenus().add(data);
        menuBar.getMenus().add(author);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}
