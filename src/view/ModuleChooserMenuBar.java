package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

/**
 * The ModuleChooserMenuBar class represents the menu bar of the module chooser application.
 * It extends MenuBar and includes menus for file operations and help.
 */
public class ModuleChooserMenuBar extends MenuBar {

    private MenuItem saveItem, loadItem, aboutItem, exitItem;

    /**
     * Constructs a new ModuleChooserMenuBar and initializes its menus and menu items.
     */
    public ModuleChooserMenuBar() { 

        // Temporary variable for menus within this MenuBar
        Menu menu;

        // File menu with load, save, and exit options
        menu = new Menu("_File");

        loadItem = new MenuItem("Load Student Data");
        loadItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
        menu.getItems().add(loadItem);

        saveItem = new MenuItem("Save Student Data");
        saveItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
        menu.getItems().add(saveItem);

        menu.getItems().add(new SeparatorMenuItem());

        exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
        menu.getItems().add(exitItem);

        this.getMenus().add(menu);

        // Help menu with an about option
        menu = new Menu("_Help");

        aboutItem = new MenuItem("About");
        aboutItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+A"));
        menu.getItems().add(aboutItem);

        this.getMenus().add(menu); 
    }

    /**
     * Adds an event handler for the save menu item.
     *
     * @param handler The event handler to be added.
     */
    public void addSaveHandler(EventHandler<ActionEvent> handler) {
        saveItem.setOnAction(handler);
    }
    
    /**
     * Adds an event handler for the load menu item.
     *
     * @param handler The event handler to be added.
     */
    public void addLoadHandler(EventHandler<ActionEvent> handler) {
        loadItem.setOnAction(handler);
    }
    
    /**
     * Adds an event handler for the about menu item.
     *
     * @param handler The event handler to be added.
     */
    public void addAboutHandler(EventHandler<ActionEvent> handler) {
        aboutItem.setOnAction(handler);
    }

    /**
     * Adds an event handler for the exit menu item.
     *
     * @param handler The event handler to be added.
     */
    public void addExitHandler(EventHandler<ActionEvent> handler) {
        exitItem.setOnAction(handler);
    }
}
