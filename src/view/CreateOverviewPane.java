package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * The CreateOverviewPane class represents the UI component for creating an overview pane
 * in the module selection application. It extends GridPane and includes text areas for
 * displaying results and a button for saving the overview.
 */
public class CreateOverviewPane extends GridPane {
    private Button save;
    private TextArea result, result1, result2;

    /**
     * Constructs a new CreateOverviewPane and initializes its components.
     */
    public CreateOverviewPane() {
        // Set vertical and horizontal gaps between elements
        this.setVgap(15);
        this.setHgap(20);
        // Align the grid pane to the center
        this.setAlignment(Pos.CENTER);
        // Set padding around the grid pane
        this.setPadding(new Insets(20));

        // Initialize text areas and save button
        result = new TextArea();
        result1 = new TextArea();
        result2 = new TextArea();
        save = new Button("Save Overview");

        // Set preferred sizes and make text areas non-editable
        result.setPrefSize(600, 150);
        result1.setPrefSize(300, 300);
        result2.setPrefSize(300, 300);
        save.setPrefWidth(100);
        result.setEditable(false);
        result1.setEditable(false);
        result2.setEditable(false);

        // Add main result text area to the grid pane
        this.add(result, 0, 1);

        // Create a horizontal box for result1 and result2 text areas and add it to the grid pane
        HBox t1Buttons1 = new HBox(20, result1, result2);
        this.add(t1Buttons1, 0, 2);

        // Create a horizontal box for the save button with additional spacing elements
        HBox t1Buttons2 = new HBox(20, new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), new HBox(), save);
        this.add(t1Buttons2, 0, 3);
    }

    /**
     * Adds an event handler for the save button.
     * 
     * @param handler The event handler to be added.
     */
    public void addResultsHandler(EventHandler<ActionEvent> handler) {
        save.setOnAction(handler);
    }

    /**
     * Returns the main result text area.
     * 
     * @return The main result text area.
     */
    public TextArea getResult() {
        return result;
    }

    /**
     * Returns the first result text area.
     * 
     * @return The first result text area.
     */
    public TextArea getResult1() {
        return result1;
    }

    /**
     * Returns the second result text area.
     * 
     * @return The second result text area.
     */
    public TextArea getResult2() {
        return result2;
    }

    /**
     * Adds an event handler for the save button.
     * 
     * @param handler The event handler to be added.
     */
    public void addSaveProfileHandler(EventHandler<ActionEvent> handler) {
        save.setOnAction(handler);
    }
}
