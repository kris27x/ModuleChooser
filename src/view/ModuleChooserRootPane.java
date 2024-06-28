package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;

/**
 * The ModuleChooserRootPane class represents the root pane of the module chooser application.
 * It extends BorderPane and includes a tab pane for navigating between different views and a menu bar.
 */
public class ModuleChooserRootPane extends BorderPane {

    private CreateStudentProfilePane cspp;
    private CreateSelectModulesPane csmp;
    private CreateOverviewPane cop;
    private CreateReserveModulesPane crmp;
    private ModuleChooserMenuBar mstmb;
    private TabPane tp;

    /**
     * Constructs a new ModuleChooserRootPane and initializes its components.
     */
    public ModuleChooserRootPane() {
        // Create tab pane and disable tabs from being closed
        tp = new TabPane();
        tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        // Create panes
        cspp = new CreateStudentProfilePane();
        csmp = new CreateSelectModulesPane();
        cop = new CreateOverviewPane();
        crmp = new CreateReserveModulesPane();

        // Create tabs with panes added
        Tab t1 = new Tab("Create Profile", cspp);
        Tab t2 = new Tab("Select Modules", csmp);
        Tab t3 = new Tab("Reserve Modules", crmp);
        Tab t4 = new Tab("Overview Selection", cop);

        // Add tabs to tab pane
        tp.getTabs().addAll(t1, t2, t3, t4);

        // Create menu bar
        mstmb = new ModuleChooserMenuBar();

        // Add menu bar and tab pane to this root pane
        this.setTop(mstmb);
        this.setCenter(tp);
    }

    /**
     * Returns the CreateStudentProfilePane instance.
     *
     * @return The CreateStudentProfilePane instance.
     */
    public CreateStudentProfilePane getCreateStudentProfilePane() {
        return cspp;
    }

    /**
     * Returns the CreateSelectModulesPane instance.
     *
     * @return The CreateSelectModulesPane instance.
     */
    public CreateSelectModulesPane getCreateSelectModulesPane() {
        return csmp;
    }

    /**
     * Returns the CreateReserveModulesPane instance.
     *
     * @return The CreateReserveModulesPane instance.
     */
    public CreateReserveModulesPane getCreateReserveModulesPane() {
        return crmp;
    }

    /**
     * Returns the CreateOverviewPane instance.
     *
     * @return The CreateOverviewPane instance.
     */
    public CreateOverviewPane getCreateOverviewPane() {
        return cop;
    }

    /**
     * Returns the ModuleChooserMenuBar instance.
     *
     * @return The ModuleChooserMenuBar instance.
     */
    public ModuleChooserMenuBar getModuleSelectionToolMenuBar() {
        return mstmb;
    }

    /**
     * Allows the controller to change tabs by index.
     *
     * @param index The index of the tab to be selected.
     */
    public void changeTab(int index) {
        tp.getSelectionModel().select(index);
    }
}
