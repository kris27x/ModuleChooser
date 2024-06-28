package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.Schedule;
import model.Module;

import java.util.Collection;

/**
 * The CreateSelectModulesPane class represents the UI component for selecting modules.
 * It extends GridPane and includes list views for displaying available and selected modules,
 * as well as buttons for adding, removing, submitting, and resetting module selections.
 */
public class CreateSelectModulesPane extends GridPane {

    private Button t1addB, t1removeB, t2addB, t2removeB, submitB, resetB;
    private TextField txtCreditst1, txtCreditst2;
    private ListView<Module> t1UnSelect, t1Select, t2UnSelect, t2Select, yearS;
    private int Creditst1, Creditst2;

    /**
     * Constructs a new CreateSelectModulesPane and initializes its components.
     */
    public CreateSelectModulesPane() {
        // Set vertical and horizontal gaps between elements
        this.setVgap(15);
        this.setHgap(20);
        // Align the grid pane to the center
        this.setAlignment(Pos.CENTER);
        // Set padding around the grid pane
        this.setPadding(new Insets(20));

        // Create labels for different sections
        Label lblyearS = new Label("Selected Year Long modules");
        Label lblt1TermB = new Label("Term 1:");
        Label lblt2TermB = new Label("Term 2:");
        Label lblt1UnSelect = new Label("Unselected Term 1 modules");
        Label lblt2UnSelect = new Label("Unselected Term 2 modules");
        Label lblt1Select = new Label("Selected term 1 modules");
        Label lblt2Select = new Label("Selected term 2 modules");
        Label lblCreditst1 = new Label("Current term 1 credits:");
        Label lblCreditst2 = new Label("Current term 2 credits:");

        // Initialize list views for modules
        t1UnSelect = new ListView<>();
        t2UnSelect = new ListView<>();
        t1Select = new ListView<>();
        t2Select = new ListView<>();
        yearS = new ListView<>();

        // Initialize buttons for module actions
        t1addB = new Button("Add");
        t2addB = new Button("Add");
        t1removeB = new Button("Remove");
        t2removeB = new Button("Remove");
        resetB = new Button("Reset");
        submitB = new Button("Submit");
        t1addB.setPrefWidth(70);
        t1removeB.setPrefWidth(70);
        t2addB.setPrefWidth(70);
        t2removeB.setPrefWidth(70);
        resetB.setPrefWidth(70);
        submitB.setPrefWidth(70);

        // Initialize text fields for displaying credits
        txtCreditst1 = new TextField("0");
        txtCreditst1.setEditable(false);
        txtCreditst1.setMaxWidth(50);
        txtCreditst2 = new TextField("0");
        txtCreditst2.setEditable(false);
        txtCreditst2.setMaxWidth(50);

        // Initialize observable lists for modules
        ObservableList<Module> t1UnSelectModule = FXCollections.observableArrayList();
        ObservableList<Module> t2UnSelectModule = FXCollections.observableArrayList();
        ObservableList<Module> t1SelectModule = FXCollections.observableArrayList();
        ObservableList<Module> t2SelectModule = FXCollections.observableArrayList();
        ObservableList<Module> yearSModule = FXCollections.observableArrayList();

        // Set items for list views
        yearS.setItems(yearSModule);
        t1UnSelect.setItems(t1UnSelectModule);
        t2UnSelect.setItems(t2UnSelectModule);
        t1Select.setItems(t1SelectModule);
        t2Select.setItems(t2SelectModule);

        // Set preferred sizes for list views
        t1Select.setPrefSize(350, 100);
        t2Select.setPrefSize(350, 100);
        t1UnSelect.setPrefSize(350, 100);
        t2UnSelect.setPrefSize(350, 100);
        yearS.setPrefSize(350, 50);

        // Create layout containers and arrange components
        HBox bbt = new HBox(10, submitB, resetB);
        HBox Hlaneleftup = new HBox(10, lblt1TermB, t1addB, t1removeB);
        HBox Hlaneleftdown = new HBox(10, lblt2TermB, t2addB, t2removeB);
        HBox Hlaneleftcredits = new HBox(10, lblCreditst1, txtCreditst1);
        VBox Vlane1 = new VBox(16, lblt1UnSelect, t1UnSelect, Hlaneleftup, lblt2UnSelect, t2UnSelect, Hlaneleftdown, Hlaneleftcredits);
        HBox Hlanerightcredits = new HBox(10, lblCreditst2, txtCreditst2);
        VBox Vlane2 = new VBox(12, lblyearS, yearS, lblt1Select, t1Select, lblt2Select, t2Select, Hlanerightcredits);
        HBox sumlanes = new HBox(10, Vlane1, Vlane2);

        // Add components to the grid pane
        this.add(sumlanes, 0, 0);
        this.add(bbt, 0, 1);

        // Align buttons and credits boxes to the center
        bbt.setAlignment(Pos.CENTER);
        Hlaneleftcredits.setAlignment(Pos.CENTER);
        Hlanerightcredits.setAlignment(Pos.CENTER);
        Hlaneleftup.setAlignment(Pos.CENTER);
        Hlaneleftdown.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the course modules based on their delivery schedule.
     *
     * @param modules The collection of modules to set.
     */
    public void setCourseModules(Collection<Module> modules) {
        t1UnSelect.getItems().clear();
        t2UnSelect.getItems().clear();
        t1Select.getItems().clear();
        t2Select.getItems().clear();
        yearS.getItems().clear();

        modules.forEach(mod -> {
            if (mod.getDelivery().equals(Schedule.TERM_1)) {
                if (mod.isMandatory()) {
                    t1Select.getItems().add(mod);
                } else {
                    t1UnSelect.getItems().add(mod);
                }
            } else if (mod.getDelivery().equals(Schedule.TERM_2)) {
                if (mod.isMandatory()) {
                    t2Select.getItems().add(mod);
                } else {
                    t2UnSelect.getItems().add(mod);
                }
            } else {
                yearS.getItems().add(mod);
            }
        });
    }

    /**
     * Adds a module to the selected term 1 list and removes it from the unselected list.
     *
     * @param mod The module to add.
     */
    public void addTerm1Modules(Module mod) {
        t1UnSelect.getItems().remove(mod);
        t1Select.getItems().add(mod);
    }

    /**
     * Adds a module to the selected term 2 list and removes it from the unselected list.
     *
     * @param mod The module to add.
     */
    public void addTerm2Modules(Module mod) {
        t2UnSelect.getItems().remove(mod);
        t2Select.getItems().add(mod);
    }

    /**
     * Removes a module from the selected term 1 list and adds it to the unselected list.
     *
     * @param mod The module to remove.
     */
    public void removeTerm1Modules(Module mod) {
        t1Select.getItems().remove(mod);
        t1UnSelect.getItems().add(mod);
    }

    /**
     * Removes a module from the selected term 2 list and adds it to the unselected list.
     *
     * @param mod The module to remove.
     */
    public void removeTerm2Modules(Module mod) {
        t2Select.getItems().remove(mod);
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
     * Returns the selected module from the selected term 1 list.
     *
     * @return The selected module from the selected term 1 list.
     */
    public Module getSelectTerm1Remove() {
        return t1Select.getSelectionModel().getSelectedItem();
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
     * Returns the selected module from the selected term 2 list.
     *
     * @return The selected module from the selected term 2 list.
     */
    public Module getSelectTerm2Remove() {
        return t2Select.getSelectionModel().getSelectedItem();
    }

    /**
     * Adds a reset button handler.
     *
     * @param handler The event handler for the reset button.
     */
    public void addResetHandler(EventHandler<ActionEvent> handler) {
        resetB.setOnAction(handler);
    }

    /**
     * Adds a submit button handler.
     *
     * @param handler The event handler for the submit button.
     */
    public void addSubmitHandler(EventHandler<ActionEvent> handler) {
        submitB.setOnAction(handler);
    }

    /**
     * Adds a handler for the add button of term 1.
     *
     * @param handler The event handler for the add button.
     */
    public void addt1AddHandler(EventHandler<ActionEvent> handler) {
        t1addB.setOnAction(handler);
    }

    /**
     * Adds a handler for the remove button of term 1.
     *
     * @param handler The event handler for the remove button.
     */
    public void addt1RemoveHandler(EventHandler<ActionEvent> handler) {
        t1removeB.setOnAction(handler);
    }

    /**
     * Adds a handler for the add button of term 2.
     *
     * @param handler The event handler for the add button.
     */
    public void addt2AddHandler(EventHandler<ActionEvent> handler) {
        t2addB.setOnAction(handler);
    }

    /**
     * Adds a handler for the remove button of term 2.
     *
     * @param handler The event handler for the remove button.
     */
    public void addt2RemoveHandler(EventHandler<ActionEvent> handler) {
        t2removeB.setOnAction(handler);
    }

    /**
     * Updates the credit counts for term 1 and term 2 based on selected modules.
     */
    public void Crediting() {
        Creditst1 = 0;
        Creditst2 = 0;
        t1Select.getItems().forEach(mod -> Creditst1 += mod.getModuleCredits());
        t2Select.getItems().forEach(mod -> Creditst2 += mod.getModuleCredits());
        yearS.getItems().forEach(mod -> Creditst1 += (mod.getModuleCredits() / 2));
        yearS.getItems().forEach(mod -> Creditst2 += (mod.getModuleCredits() / 2));
        txtCreditst1.setText("" + Creditst1);
        txtCreditst2.setText("" + Creditst2);
    }

    /**
     * Returns the current term 1 credit count.
     *
     * @return The current term 1 credit count.
     */
    public int getCreditst1() {
        return Creditst1;
    }

    /**
     * Returns the current term 2 credit count.
     *
     * @return The current term 2 credit count.
     */
    public int getCreditst2() {
        return Creditst2;
    }

    /**
     * Increases the term 1 credit count by a specified amount.
     *
     * @param credits The number of credits to add.
     */
    public void CreditingT1Plus(int credits) {
        Creditst1 += credits;
        txtCreditst1.setText("" + Creditst1);
    }

    /**
     * Decreases the term 1 credit count by a specified amount.
     *
     * @param credits The number of credits to subtract.
     */
    public void CreditingT1Minus(int credits) {
        Creditst1 -= credits;
        txtCreditst1.setText("" + Creditst1);
    }

    /**
     * Increases the term 2 credit count by a specified amount.
     *
     * @param credits The number of credits to add.
     */
    public void CreditingT2Plus(int credits) {
        Creditst2 += credits;
        txtCreditst2.setText("" + Creditst2);
    }

    /**
     * Decreases the term 2 credit count by a specified amount.
     *
     * @param credits The number of credits to subtract.
     */
    public void CreditingT2Minus(int credits) {
        Creditst2 -= credits;
        txtCreditst2.setText("" + Creditst2);
    }

    /**
     * Returns the list of year-long modules.
     *
     * @return The list of year-long modules.
     */
    public ObservableList<Module> getyearSModule() {
        return yearS.getItems();
    }

    /**
     * Returns the list of selected term 1 modules.
     *
     * @return The list of selected term 1 modules.
     */
    public ObservableList<Module> gett1SelectModule() {
        return t1Select.getItems();
    }

    /**
     * Returns the list of unselected term 1 modules.
     *
     * @return The list of unselected term 1 modules.
     */
    public ObservableList<Module> gett1UnSelectModule() {
        return t1UnSelect.getItems();
    }

    /**
     * Returns the list of unselected term 2 modules.
     *
     * @return The list of unselected term 2 modules.
     */
    public ObservableList<Module> gett2UnSelectModule() {
        return t2UnSelect.getItems();
    }

    /**
     * Returns the list of selected term 2 modules.
     *
     * @return The list of selected term 2 modules.
     */
    public ObservableList<Module> gett2SelectModule() {
        return t2Select.getItems();
    }
}
