package main;

import controller.ModuleChooserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.StudentProfile;
import view.ModuleChooserRootPane;

public class ApplicationLoader extends Application {

    private ModuleChooserRootPane view;

    /**
     * Initializes the application. This method is called before the start() method.
     * It creates the view and model, and passes their references to the controller.
     */
    @Override
    public void init() {
        // Create view and model and pass their references to the controller
        view = new ModuleChooserRootPane();
        StudentProfile model = new StudentProfile();
        new ModuleChooserController(view, model);
    }

    /**
     * Starts the application. This method is called after the init() method.
     * It sets the minimum width and height for the stage window, sets the title,
     * and displays the scene.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if an error occurs during the start of the application.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Set minimum width and height for the stage window
        stage.setMinWidth(530);
        stage.setMinHeight(500);

        // Set the title of the stage
        stage.setTitle("Final Year Module Selection Tool");

        // Set the scene and show the stage
        stage.setScene(new Scene(view));
        stage.show();
    }

    /**
     * The main method is the entry point of the application.
     * It launches the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
