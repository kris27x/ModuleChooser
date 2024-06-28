package view;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Course;
import model.Name;

/**
 * The CreateStudentProfilePane class represents the UI component for creating a student profile.
 * It extends GridPane and includes input fields for student details and a button for profile creation.
 */
public class CreateStudentProfilePane extends GridPane {

    private ComboBox<Course> cboCourses;
    private DatePicker inputDate;
    private TextField txtFirstName, txtSurname, txtPnumber, txtEmail;
    private Button btnCreateProfile;

    /**
     * Constructs a new CreateStudentProfilePane and initializes its components.
     */
    public CreateStudentProfilePane() {
        // Styling
        this.setVgap(15);
        this.setHgap(20);
        this.setAlignment(Pos.CENTER);

        ColumnConstraints column0 = new ColumnConstraints();
        column0.setHalignment(HPos.RIGHT);

        this.getColumnConstraints().addAll(column0);

        // Create labels
        Label lblTitle = new Label("Select course: ");
        Label lblPnumber = new Label("Input P number: ");
        Label lblFirstName = new Label("Input first name: ");
        Label lblSurname = new Label("Input surname: ");
        Label lblEmail = new Label("Input email: ");
        Label lblDate = new Label("Input date: ");

        // Initialize combobox
        cboCourses = new ComboBox<Course>(); // This is populated via method towards the end of the class

        // Setup text fields
        txtFirstName = new TextField();
        txtSurname = new TextField();
        txtPnumber = new TextField();
        txtEmail = new TextField();
        
        inputDate = new DatePicker();

        // Initialize create profile button
        btnCreateProfile = new Button("Create Profile");

        // Add controls and labels to the container
        this.add(lblTitle, 0, 0);
        this.add(cboCourses, 1, 0);

        this.add(lblPnumber, 0, 1);
        this.add(txtPnumber, 1, 1);
        
        this.add(lblFirstName, 0, 2);
        this.add(txtFirstName, 1, 2);

        this.add(lblSurname, 0, 3);
        this.add(txtSurname, 1, 3);
        
        this.add(lblEmail, 0, 4);
        this.add(txtEmail, 1, 4);
        
        this.add(lblDate, 0, 5);
        this.add(inputDate, 1, 5);

        this.add(new HBox(), 0, 6);
        this.add(btnCreateProfile, 1, 6);
    }
    
    /**
     * Adds courses to the combobox.
     * 
     * @param courses The array of courses to add.
     */
    public void addCoursesToComboBox(Course[] courses) {
        cboCourses.getItems().addAll(courses);
        cboCourses.getSelectionModel().select(0); // Select first course by default
    }

    /**
     * Returns the selected course from the combobox.
     * 
     * @return The selected course.
     */
    public Course getSelectedCourse() {
        return cboCourses.getSelectionModel().getSelectedItem();
    }

    /**
     * Returns the student P number from the text field.
     * 
     * @return The student P number.
     */
    public String getStudentPnumber() {
        return txtPnumber.getText();
    }

    /**
     * Returns the student's name as a Name object.
     * 
     * @return The student's name.
     */
    public Name getStudentName() {
        return new Name(txtFirstName.getText(), txtSurname.getText());
    }

    /**
     * Returns the student's email from the text field.
     * 
     * @return The student's email.
     */
    public String getStudentEmail() {
        return txtEmail.getText();
    }

    /**
     * Returns the student's submission date from the date picker.
     * 
     * @return The student's submission date.
     */
    public LocalDate getStudentDate() {
        return inputDate.getValue();
    }

    /**
     * Adds an event handler to the create profile button.
     * 
     * @param handler The event handler to be added.
     */
    public void addCreateStudentProfileHandler(EventHandler<ActionEvent> handler) {
        btnCreateProfile.setOnAction(handler);
    }

    /**
     * Sets the selected course in the combobox.
     * 
     * @param course The course to be set.
     */
    public void setCboCourses(Course course) {
        if(course.getCourseName().equals("Computer Science")) {
            cboCourses.getSelectionModel().select(0);
        } else {
            cboCourses.getSelectionModel().select(1);
        }
    }

    /**
     * Sets the student P number in the text field.
     * 
     * @param p The student P number to be set.
     */
    public void setStudentPnumber(String p) {
        txtPnumber.setText(p);
    }

    /**
     * Sets the student's name in the text fields.
     * 
     * @param StName The student's name to be set.
     */
    public void setStudentName(Name StName) {
        txtFirstName.setText(StName.getFirstName());
        txtSurname.setText(StName.getFamilyName());
    }

    /**
     * Sets the student's email in the text field.
     * 
     * @param email The student's email to be set.
     */
    public void setStudentEmail(String email) {
        txtEmail.setText(email);
    }

    /**
     * Sets the student's submission date in the date picker.
     * 
     * @param date The student's submission date to be set.
     */
    public void setStudentDate(LocalDate date) {
        inputDate.setValue(date);
    }

    /**
     * Clears all input fields.
     */
    public void clearFunction() {
        txtPnumber.clear();
        txtFirstName.clear();
        txtSurname.clear();
        txtEmail.clear();
        inputDate.setValue(null);
    }

    /**
     * Checks if any input fields are empty.
     * 
     * @return true if any fields are empty, false otherwise.
     */
    public boolean checkFieldsValidation() {
        return txtFirstName.getText().isEmpty() || txtSurname.getText().isEmpty() ||
               txtPnumber.getText().isEmpty() || txtEmail.getText().isEmpty() || inputDate.getValue() == null;
    }

    /**
     * Validates the P number format.
     * 
     * @return true if the P number does not start with "P" or "p", false otherwise.
     */
    public boolean checkPnumberValidation() {
        return !txtPnumber.getText().matches("[P-p].*");
    }

    /**
     * Returns the index of the selected course in the combobox.
     * 
     * @return The index of the selected course.
     */
    public int getCboCoursesValue() {
        return cboCourses.getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns the index of the selected course in the combobox.
     * 
     * @return The index of the selected course.
     */
    public int getCboCourses() {
        return cboCourses.getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns the student P number from the text field.
     * 
     * @return The student P number.
     */
    public String getTxtPnumber() {
        return txtPnumber.getText();
    }

    /**
     * Returns the student's name as a Name object.
     * 
     * @return The student's name.
     */
    public Name getTxtStudentName() {
        return new Name(txtFirstName.getText(), txtSurname.getText());
    }

    /**
     * Returns the student's email from the text field.
     * 
     * @return The student's email.
     */
    public String getTxtStudentEmail() {
        return txtEmail.getText();
    }

    /**
     * Returns the student's submission date from the date picker.
     * 
     * @return The student's submission date.
     */
    public LocalDate getDate() {
        return inputDate.getValue();
    }
}
