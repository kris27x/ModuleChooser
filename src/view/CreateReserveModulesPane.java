package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Course;
import model.Module;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

/**
 * The CreateReserveModulesPane class represents the UI component for reserving modules.
 * It extends GridPane and includes list views for displaying available and reserved modules,
 * as well as buttons for adding, removing, and confirming module selections.
 */
public class CreateReserveModulesPane extends GridPane {
    private Accordion aModules;
    private Button t1addB, t1removeB, t1confirmB, t2addB, t2removeB, t2confirmB;
    private TextField txtCreditst1, txtCreditst2;
    private ListView<Module> t1UnSelect, t1Reserved, t2UnSelect, t2Reserved;
    private TitledPane t1, t2;

    /**
     * Constructs a new CreateReserveModulesPane and initializes its components.
     */
    public CreateReserveModulesPane() {
        // Set vertical and horizontal gaps between elements
        this.setVgap(10);
        this.setHgap(20);
        // Set padding around the grid pane
        this.setPadding(new Insets(10));
        // Align the grid pane to the center
        this.setAlignment(Pos.CENTER);

        // Initialize VBox containers for term 1 and term 2 modules
        VBox term1Vbox = new VBox();
        VBox term2Vbox = new VBox();

        // Create labels for term 1 modules
        Label lblt1UnSelect = new Label("Unselected Term 1 Modules");
        Label lblt1Reserved = new Label("Reserved Term 1 Modules");
        Label lblt1Credits = new Label("Reserve 30 credits worth of term 1 modules");

        // Create titled panes for term 1 and term 2 modules
        t1 = new TitledPane("Term 1 Modules", term1Vbox);
        t2 = new TitledPane("Term 2 Modules", term2Vbox);
        aModules = new Accordion(t1, t2);
        aModules.setExpandedPane(t1);

        // Initialize list views, buttons, and text fields for term 1 modules
        t1UnSelect = new ListView<>();
        t1Reserved = new ListView<>();
        t1addB = new Button("Add");
        t1removeB = new Button("Remove");
        t1confirmB = new Button("Confirm");
        txtCreditst1 = new TextField("0");
        txtCreditst1.setEditable(false);

        // Set preferred sizes and properties for term 1 components
        t1UnSelect.setPrefSize(350, 350);
        t1Reserved.setPrefSize(350, 350);
        txtCreditst1.setMaxWidth(40);
        t1addB.setPrefWidth(60);
        t1removeB.setPrefWidth(60);
        t1confirmB.setPrefWidth(60);

        // Create labels for term 2 modules
        Label lblt2UnSelect = new Label("Unselected Term 2 Modules");
        Label lblt2Reserved = new Label("Reserved Term 2 Modules");
        Label lblt2Credits = new Label("Reserve 30 credits worth of term 2 modules");

        // Initialize list views, buttons, and text fields for term 2 modules
        t2UnSelect = new ListView<>();
        t2Reserved = new ListView<>();
        t2addB = new Button("Add");
        t2removeB = new Button("Remove");
        t2confirmB = new Button("Confirm");
        txtCreditst2 = new TextField("0");
        txtCreditst2.setEditable(false);

        // Set preferred sizes and properties for term 2 components
        t2UnSelect.setPrefSize(350, 350);
        t2Reserved.setPrefSize(350, 350);
        txtCreditst2.setMaxWidth(40);
        t2addB.setPrefWidth(60);
        t2removeB.setPrefWidth(60);
        t2confirmB.setPrefWidth(60);

        // Create and arrange components for term 1 modules
        VBox V1 = new VBox();
        HBox hb1 = new HBox(lblt1UnSelect);
        HBox hb2 = new HBox(t1UnSelect);
        V1.getChildren().addAll(hb1, hb2);

        VBox V2 = new VBox();
        HBox hb3 = new HBox(lblt1Reserved);
        HBox hb4 = new HBox(t1Reserved);
        V2.getChildren().addAll(hb3, hb4);

        HBox H1 = new HBox(20, lblt1Credits, t1addB, t1removeB, t1confirmB);
        H1.setPadding(new Insets(1, 1, 1, 75));

        HBox H2 = new HBox(30);
        H2.setPadding(new Insets(30));
        H2.getChildren().addAll(V1, V2);

        term1Vbox.getChildren().addAll(H2, H1);
        term1Vbox.setPadding(new Insets(10, 10, 10, 10));

        // Create and arrange components for term 2 modules
        VBox Vn1 = new VBox();
        HBox hbn1 = new HBox(lblt2UnSelect);
        HBox hbn2 = new HBox(t2UnSelect);
        Vn1.getChildren().addAll(hbn1, hbn2);

        VBox Vn2 = new VBox();
        HBox hbn3 = new HBox(lblt2Reserved);
        HBox hbn4 = new HBox(t2Reserved);
        Vn2.getChildren().addAll(hbn3, hbn4);

        HBox Hn1 = new HBox(20, lblt2Credits, t2addB, t2removeB, t2confirmB);
        Hn1.setPadding(new Insets(1, 1, 1, 75));

        HBox Hn2 = new HBox(30);
        Hn2.setPadding(new Insets(30));
        Hn2.getChildren().addAll(Vn1, Vn2);

        term2Vbox.getChildren().addAll(Hn2, Hn1);
        term2Vbox.setPadding(new Insets(10, 10, 10, 10));

        // Add the accordion to the grid pane
        this.getChildren().add(aModules);
    }

    /**
     * Sets the unselected term 1 modules.
     *
     * @param modules The list of unselected term 1 modules.
     */
    public void setCourseModulest1u(ObservableList<Module> modules) {
        t1UnSelect.getItems().addAll(modules);
    }

    /**
     * Sets the reserved term 1 modules.
     *
     * @param modules The list of reserved term 1 modules.
     */
    public void setCourseModulest1r(ObservableList<Module> modules) {
        t1Reserved.getItems().addAll(modules);
    }

    /**
     * Sets the unselected term 2 modules.
     *
     * @param modules The list of unselected term 2 modules.
     */
    public void setCourseModulest2u(ObservableList<Module> modules) {
        t2UnSelect.getItems().addAll(modules);
    }

    /**
     * Sets the reserved term 2 modules.
     *
     * @param modules The list of reserved term 2 modules.
     */
    public void setCourseModulest2r(ObservableList<Module> modules) {
        t2Reserved.getItems().addAll(modules);
    }

    /**
     * Adds a confirm handler for term 2.
     *
     * @param handler The event handler for the confirm button.
     */
    public void addConfirm2Handler(EventHandler<ActionEvent> handler) {
        t2confirmB.setOnAction(handler);
    }

    /**
     * Adds a confirm handler for term 1.
     *
     * @param handler The event handler for the confirm button.
     */
    public void addConfirm1Handler(EventHandler<ActionEvent> handler) {
        t1confirmB.setOnAction(handler);
    }

    /**
     * Adds a module to the reserved term 1 list and removes it from the unselected list.
     *
     * @param mod The module to add.
     */
    public void addTerm1Modules(Module mod) {
        t1UnSelect.getItems().remove(mod);
        t1Reserved.getItems().add(mod);
    }

    /**
     * Adds a module to the reserved term 2 list and removes it from the unselected list.
     *
     * @param mod The module to add.
     */
    public void addTerm2Modules(Module mod) {
        t2UnSelect.getItems().remove(mod);
        t2Reserved.getItems().add(mod);
    }

    /**
     * Removes a module from the reserved term 1 list and adds it to the unselected list.
     *
     * @param mod The module to remove.
     */
    public void removeTerm1Modules(Module mod) {
        t1Reserved.getItems().remove(mod);
        t1UnSelect.getItems().add(mod);
    }

    /**
     * Removes a module from the reserved term 2 list and adds it to the unselected list.
     *
     * @param mod The module to remove.
     */
    public void removeTerm2Modules(Module mod) {
        t2Reserved.getItems().remove(mod);
        t2UnSelect.getItems().add(mod);
    }

    /**
     * Returns the selected module from the unselected term 1 list.
     *
     * @return The selected module from the unselected term 1 list.
     */
    public Module getSelectTerm1Add() {
        return t1UnSelect.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected module from the reserved term 1 list.
     *
     * @return The selected module from the reserved term 1 list.
     */
    public Module getSelectTerm1Remove() {
        return t1Reserved.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected module from the unselected term 2 list.
     *
     * @return The selected module from the unselected term 2 list.
     */
    public Module getSelectTerm2Add() {
        return t2UnSelect.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected module from the reserved term 2 list.
     *
     * @return The selected module from the reserved term 2 list.
     */
    public Module getSelectTerm2Remove() {
        return t2Reserved.getSelectionModel().getSelectedItem();
    }

    /**
     * Adds a handler for adding a module to term 1.
     *
     * @param handler The event handler for the add button.
     */
    public void addRadd1Handler(EventHandler<ActionEvent> handler) {
        t1addB.setOnAction(handler);
    }

    /**
     * Adds a handler for adding a module to term 2.
     *
     * @param handler The event handler for the add button.
     */
    public void addRadd2Handler(EventHandler<ActionEvent> handler) {
        t2addB.setOnAction(handler);
    }

    /**
     * Adds a handler for removing a module from term 1.
     *
     * @param handler The event handler for the remove button.
     */
    public void addRremove1Handler(EventHandler<ActionEvent> handler) {
        t1removeB.setOnAction(handler);
    }

    /**
     * Adds a handler for removing a module from term 2.
     *
     * @param handler The event handler for the remove button.
     */
    public void addRremove2Handler(EventHandler<ActionEvent> handler) {
        t2removeB.setOnAction(handler);
    }

    /**
     * Returns the selected reserved module for term 1.
     *
     * @return The selected reserved module for term 1.
     */
    public Module gett1ReservedModules() {
        return t1Reserved.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the selected reserved module for term 2.
     *
     * @return The selected reserved module for term 2.
     */
    public Module gett2ReservedModules() {
        return t2Reserved.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the list of selected modules for term 1.
     *
     * @return The list of selected modules for term 1.
     */
    public ObservableList<Module> gett1SelectModule() {
        return t1Reserved.getItems();
    }

    /**
     * Returns the list of unselected modules for term 1.
     *
     * @return The list of unselected modules for term 1.
     */
    public ObservableList<Module> gett1UnSelectModule() {
        return t1UnSelect.getItems();
    }

    /**
     * Returns the list of unselected modules for term 2.
     *
     * @return The list of unselected modules for term 2.
     */
    public ObservableList<Module> gett2UnSelectModule() {
        return t2UnSelect.getItems();
    }

    /**
     * Returns the list of selected modules for term 2.
     *
     * @return The list of selected modules for term 2.
     */
    public ObservableList<Module> gett2SelectModule() {
        return t2Reserved.getItems();
    }
}
