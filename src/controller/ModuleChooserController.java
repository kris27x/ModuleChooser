package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Course;
import model.Module;
import model.Schedule;
import model.StudentProfile;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import view.*;
import java.io.*;
import java.util.Collection;
import java.util.Optional;
import javafx.collections.ObservableList;

public class ModuleChooserController {

    // Fields to be used throughout the class
    private ModuleChooserRootPane view;
    private StudentProfile model;
    private CreateSelectModulesPane csmp;
    private CreateReserveModulesPane crmp;
    private CreateOverviewPane cop;
    private CreateStudentProfilePane cspp;
    private ModuleChooserMenuBar mstmb;
    private Course[] course;

    /**
     * Constructor for ModuleChooserController
     * @param view The root pane of the view
     * @param model The student profile model
     */
    public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
        // Initialize view and model fields
        this.view = view;
        this.model = model;

        // Initialize view subcontainer fields
        cspp = view.getCreateStudentProfilePane();
        mstmb = view.getModuleSelectionToolMenuBar();
        csmp = view.getCreateSelectModulesPane();
        crmp = view.getCreateReserveModulesPane();
        cop = view.getCreateOverviewPane();
        course = generateAndGetCourses();

        // Add courses to combobox in create student profile pane using the generateAndGetCourses helper method
        cspp.addCoursesToComboBox(generateAndGetCourses());

        // Attach event handlers to view using private helper method
        this.attachEventHandlers();
    }

    /**
     * Helper method to attach event handlers
     */
    private void attachEventHandlers() {
        // Attach event handlers to the create student profile pane
        cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
        csmp.addt1AddHandler(new t1AddHandler());
        csmp.addt2AddHandler(new t2AddHandler());
        csmp.addt1RemoveHandler(new t1RemoveHandler());
        csmp.addt2RemoveHandler(new t2RemoveHandler());
        csmp.addResetHandler(new ResetHandler());
        csmp.addSubmitHandler(new SubmitHandler());

        // Attach event handlers to the menu bar
        mstmb.addExitHandler(e -> System.exit(0));
        mstmb.addLoadHandler(new LoadHandler());
        mstmb.addSaveHandler(new SaveHandler());
        mstmb.addAboutHandler(e -> this.alertDialogBuilder(AlertType.INFORMATION, "Information", null, ""));

        // Attach event handlers to the overview and reserve modules pane
        cop.addSaveProfileHandler(new SaveProfileHandler());
        crmp.addConfirm2Handler(new Confirm2Handler());
        crmp.addConfirm1Handler(new Confirm1Handler());
        crmp.addRremove1Handler(new Rremove1Handler());
        crmp.addRremove2Handler(new Rremove2Handler());
        crmp.addRadd1Handler(new Radd1Handler());
        crmp.addRadd2Handler(new Radd2Handler());
    }

    /**
     * Event handler for creating a student profile
     */
    private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (cspp.checkFieldsValidation()) {
                alertDialogBuilder(AlertType.ERROR, "Error", "Missing information.", "Please fulfill empty fields");
                return;
            }
            if (cspp.checkPnumberValidation()) {
                alertDialogBuilder(AlertType.ERROR, "Error", "Invalid P number", "P number must start with a P");
                return;
            }
            model.setStudentPnumber(cspp.getStudentPnumber());
            model.setStudentName(cspp.getStudentName());
            model.setStudentEmail(cspp.getStudentEmail());
            model.setSubmissionDate(cspp.getStudentDate());
            model.setStudentCourse(cspp.getSelectedCourse());

            csmp.setCourseModules(course[cspp.getCboCoursesValue()].getAllModulesOnCourse());
            csmp.Crediting();
            alertDialogBuilder(AlertType.CONFIRMATION, "Profile Created", null, "Your profile has been created");
            view.changeTab(1);
        }
    }

    /**
     * Event handler for adding a module to term 1 selection
     */
    public class t1AddHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = csmp.getSelectTerm1Add();
            if (selection != null) {
                if (csmp.getCreditst1() >= 60) {
                    alertDialogBuilder(AlertType.ERROR, "Please reduce modules", null, "Credit limit exceeded");
                    return;
                }
                csmp.CreditingT1Plus(selection.getModuleCredits());
                csmp.addTerm1Modules(selection);
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for removing a module from term 1 selection
     */
    public class t1RemoveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = csmp.getSelectTerm1Remove();
            if (selection != null) {
                if (selection.isMandatory()) {
                    alertDialogBuilder(AlertType.ERROR, "Mandatory Module", null, "This module is mandatory");
                    return;
                }
                csmp.CreditingT1Minus(selection.getModuleCredits());
                csmp.removeTerm1Modules(selection);
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for adding a module to term 2 selection
     */
    public class t2AddHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = csmp.getSelectTerm2Add();
            if (selection != null) {
                if (csmp.getCreditst2() >= 60) {
                    alertDialogBuilder(AlertType.ERROR, "Too many modules", null, "This exceeds your credit limit");
                    return;
                }
                csmp.CreditingT2Plus(selection.getModuleCredits());
                csmp.addTerm2Modules(selection);
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for removing a module from term 2 selection
     */
    public class t2RemoveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = csmp.getSelectTerm2Remove();
            if (selection != null) {
                if (selection.isMandatory()) {
                    alertDialogBuilder(AlertType.ERROR, "Mandatory Module", null, "This module is mandatory");
                    return;
                }
                csmp.CreditingT2Minus(selection.getModuleCredits());
                csmp.removeTerm2Modules(selection);
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for resetting module selections
     */
    public class ResetHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            csmp.setCourseModules(course[cspp.getCboCoursesValue()].getAllModulesOnCourse());
            csmp.Crediting();
        }
    }

    /**
     * Event handler for submitting module selections
     */
    public class SubmitHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (csmp.getCreditst1() + csmp.getCreditst2() != 120) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select 60 credits");
                return;
            }
            ObservableList<Module> yearSModule = csmp.getyearSModule();
            ObservableList<Module> t1SelectModule = csmp.gett1SelectModule();
            ObservableList<Module> t2SelectModule = csmp.gett2SelectModule();
            ObservableList<Module> t1UnSelectModule = csmp.gett1UnSelectModule();
            ObservableList<Module> t2UnSelectModule = csmp.gett2UnSelectModule();
            yearSModule.forEach(m -> model.addSelectedModule(m));
            t1SelectModule.forEach(m -> model.addSelectedModule(m));
            t2SelectModule.forEach(m -> model.addSelectedModule(m));
            setResult();
            setResult1();
            setResult2();
            crmp.setCourseModulest1u(t1UnSelectModule);
            crmp.setCourseModulest1r(t1SelectModule);
            crmp.setCourseModulest2u(t2UnSelectModule);
            crmp.setCourseModulest2r(t2SelectModule);
            alertDialogBuilder(AlertType.CONFIRMATION, "Modules selected", null, "Modules have been selected.");
            view.changeTab(2);
        }
    }

    /**
     * Event handler for saving the student profile to a file
     */
    public class SaveProfileHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Collection<Module> modules = model.getAllSelectedModules();
            File file = new File(model.getStudentPnumber().toUpperCase() + ".txt");
            try {
                PrintWriter writer = new PrintWriter(file);
                writer.println("Name: " + model.getStudentName().getFullName());
                writer.println("PNum: " + model.getStudentPnumber().toUpperCase());
                writer.println("Email: " + model.getStudentEmail());
                writer.println("Date: " + model.getSubmissionDate());
                writer.println("Course: " + model.getStudentCourse());
                modules.forEach(m -> {
                    writer.println(m.toString());
                });
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            alertDialogBuilder(AlertType.CONFIRMATION, "Success", null, "Your profile is saved to " + model.getStudentPnumber().toUpperCase() + ".txt! \n\n" +
                    "This file can be found in this program's directory.");
        }
    }

    /**
     * Sets the result in the overview pane with the student profile information
     */
    public void setResult() {
        TextArea profileArea = cop.getResult();
        profileArea.setText(
                "Name: " + model.getStudentName().getFullName() + "\n" +
                "Pnumber: " + model.getStudentPnumber().toUpperCase() + "\n" +
                "StudentEmail: " + model.getStudentEmail() + "\n" +
                "StudentDate: " + model.getSubmissionDate() + "\n" +
                "Course: " + model.getStudentCourse() + "\n\n"
        );
    }

    /**
     * Helper method to generate course and module data
     * @return An array of Course objects
     */
    private Course[] generateAndGetCourses() {
        Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
        Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
        Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);    
        Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
        Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
        Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);    
        Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);    
        Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
        Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
        Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
        Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
        Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
        Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
        Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
        Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
        Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
        Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
        Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);

        Course compSci = new Course("Computer Science");
        compSci.addModuleToCourse(imat3423);
        compSci.addModuleToCourse(ctec3451);
        compSci.addModuleToCourse(ctec3902_CompSci);
        compSci.addModuleToCourse(ctec3110);
        compSci.addModuleToCourse(ctec3605);
        compSci.addModuleToCourse(ctec3606);
        compSci.addModuleToCourse(ctec3410);
        compSci.addModuleToCourse(ctec3904);
        compSci.addModuleToCourse(ctec3905);
        compSci.addModuleToCourse(ctec3906);
        compSci.addModuleToCourse(ctec3911);
        compSci.addModuleToCourse(imat3410);
        compSci.addModuleToCourse(imat3406);
        compSci.addModuleToCourse(imat3611);
        compSci.addModuleToCourse(imat3613);
        compSci.addModuleToCourse(imat3614);
        compSci.addModuleToCourse(imat3428_CompSci);

        Course softEng = new Course("Software Engineering");
        softEng.addModuleToCourse(imat3423);
        softEng.addModuleToCourse(ctec3451);
        softEng.addModuleToCourse(ctec3902_SoftEng);
        softEng.addModuleToCourse(ctec3110);
        softEng.addModuleToCourse(ctec3605);
        softEng.addModuleToCourse(ctec3606);
        softEng.addModuleToCourse(ctec3410);
        softEng.addModuleToCourse(ctec3904);
        softEng.addModuleToCourse(ctec3905);
        softEng.addModuleToCourse(ctec3906);
        softEng.addModuleToCourse(ctec3911);
        softEng.addModuleToCourse(imat3410);
        softEng.addModuleToCourse(imat3406);
        softEng.addModuleToCourse(imat3611);
        softEng.addModuleToCourse(imat3613);
        softEng.addModuleToCourse(imat3614);

        Course[] courses = new Course[2];
        courses[0] = compSci;
        courses[1] = softEng;

        return courses;
    }

    /**
     * Helper method to build and display an alert dialog
     * @param type The type of alert
     * @param title The title of the alert
     * @param header The header text of the alert
     * @param content The content text of the alert
     */
    private void alertDialogBuilder(AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Event handler for confirming module selection for term 2
     */
    public class Confirm2Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (csmp.getCreditst1() + csmp.getCreditst2() != 120) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select 60 credits");
                return;
            }
            ObservableList<Module> yearSModule = csmp.getyearSModule();
            ObservableList<Module> t1SelectModule = csmp.gett1SelectModule();
            ObservableList<Module> t2SelectModule = csmp.gett2SelectModule();
            ObservableList<Module> t1UnSelectModule = csmp.gett1UnSelectModule();
            ObservableList<Module> t2UnSelectModule = csmp.gett2UnSelectModule();
            yearSModule.forEach(m -> model.addSelectedModule(m));
            t1SelectModule.forEach(m -> model.addSelectedModule(m));
            t2SelectModule.forEach(m -> model.addSelectedModule(m));
            setResult();
            setResult1();
            setResult2();
            crmp.setCourseModulest1u(t1UnSelectModule);
            crmp.setCourseModulest1r(t1SelectModule);
            crmp.setCourseModulest2u(t2UnSelectModule);
            crmp.setCourseModulest2r(t2SelectModule);
            alertDialogBuilder(AlertType.CONFIRMATION, "Modules selected", null, "Modules have been reserved.");
            view.changeTab(3);
        }
    }

    /**
     * Sets the result in the overview pane with term 1 reserved modules
     */
    public void setResult1() {
        TextArea profileArea = cop.getResult1();
        Collection<Module> reservedModulesT1 = crmp.gett1SelectModule(); // Get term 1 reserved modules
        profileArea.setText("Reserved Term 1 Modules:\n");
        reservedModulesT1.forEach(m -> {
            profileArea.appendText(m.toString() + "\n");
        });
    }

    /**
     * Sets the result in the overview pane with term 2 reserved modules
     */
    public void setResult2() {
        TextArea profileArea = cop.getResult2();
        Collection<Module> reservedModulesT2 = crmp.gett2SelectModule(); // Get term 2 reserved modules
        profileArea.setText("Reserved Term 2 Modules:\n");
        reservedModulesT2.forEach(m -> {
            profileArea.appendText(m.toString() + "\n");
        });
    }

    /**
     * Event handler for adding a reserved module to term 1
     */
    public class Radd1Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = crmp.getSelectTerm1Add();
            if (selection != null) {
                if (csmp.getCreditst1() >= 60) {
                    alertDialogBuilder(AlertType.ERROR, "Too many modules", null, "This exceeds your credit limit");
                    return;
                }
                csmp.CreditingT1Plus(selection.getModuleCredits());
                crmp.addTerm1Modules(selection);
                setResult1();
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for removing a reserved module from term 1
     */
    public class Rremove1Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = crmp.getSelectTerm1Remove();
            if (selection != null) {
                if (selection.isMandatory()) {
                    alertDialogBuilder(AlertType.ERROR, "Mandatory Module", null, "This module is mandatory");
                    return;
                }
                csmp.CreditingT1Minus(selection.getModuleCredits());
                crmp.removeTerm1Modules(selection);
                setResult1();
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for adding a reserved module to term 2
     */
    public class Radd2Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = crmp.getSelectTerm2Add();
            if (selection != null) {
                if (csmp.getCreditst2() >= 60) {
                    alertDialogBuilder(AlertType.ERROR, "Too many modules", null, "This exceeds your credit limit");
                    return;
                }
                csmp.CreditingT2Plus(selection.getModuleCredits());
                crmp.addTerm2Modules(selection);
                setResult2();
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for removing a reserved module from term 2
     */
    public class Rremove2Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Module selection = crmp.getSelectTerm2Remove();
            if (selection != null) {
                if (selection.isMandatory()) {
                    alertDialogBuilder(AlertType.ERROR, "Mandatory Module", null, "This module is mandatory");
                    return;
                }
                csmp.CreditingT2Minus(selection.getModuleCredits());
                crmp.removeTerm2Modules(selection);
                setResult2();
            } else {
                alertDialogBuilder(AlertType.INFORMATION, "No selection!", null, "Please select an option");
            }
        }
    }

    /**
     * Event handler for confirming module selection for term 1
     */
    public class Confirm1Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            if (csmp.getCreditst1() + csmp.getCreditst2() != 120) {
                alertDialogBuilder(AlertType.ERROR, "Not enough modules", null, "Please select 60 credits");
                return;
            }
            ObservableList<Module> yearSModule = csmp.getyearSModule();
            ObservableList<Module> t1SelectModule = csmp.gett1SelectModule();
            ObservableList<Module> t2SelectModule = csmp.gett2SelectModule();
            crmp.gett1UnSelectModule();
            csmp.gett2UnSelectModule();
            yearSModule.forEach(m -> model.addSelectedModule(m));
            t1SelectModule.forEach(m -> model.addSelectedModule(m));
            t2SelectModule.forEach(m -> model.addSelectedModule(m));
            setResult();
            alertDialogBuilder(AlertType.CONFIRMATION, "Modules selected", null, "Modules have been reserved.");
            view.changeTab(2);
        }
    }

    /**
     * Event handler for saving the student profile data
     */
    public class SaveHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            try {
                if (cspp.getStudentPnumber().isEmpty()) {
                    alertDialogBuilder(AlertType.ERROR, "PNumber Required", null, "PNumber is required in order to save.");
                    return;
                }

                // Create a new StudentProfile object
                StudentProfile profile = new StudentProfile();
                profile.setStudentPnumber(cspp.getStudentPnumber());
                profile.setStudentName(cspp.getStudentName());
                profile.setStudentEmail(cspp.getStudentEmail());
                profile.setSubmissionDate(cspp.getStudentDate());
                profile.setStudentCourse(cspp.getSelectedCourse());

                String filePath = profile.getStudentPnumber().toUpperCase() + "Obj.dat";
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
                    oos.writeObject(profile);
                    oos.flush();
                    System.out.println("File saved successfully: " + filePath);
                }

                alertDialogBuilder(AlertType.CONFIRMATION, "Success", null, "Your data has been saved");
            } catch (IOException ioE) {
                ioE.printStackTrace();
            }
        }
    }

    /**
     * Event handler for loading the student profile data
     */
    public class LoadHandler implements EventHandler<ActionEvent> {
        private String Pnumber;

        public void handle(ActionEvent e) {
            TextInputDialog input = new TextInputDialog();
            input.setTitle("Enter Pnumber");
            input.setContentText("Please enter a Pnumber to load: ");
            Optional<String> inputPNum = input.showAndWait();
            if (inputPNum.isPresent()) {
                Pnumber = inputPNum.get(); // Assign the entered Pnumber to the instance variable
            } else {
                alertDialogBuilder(AlertType.ERROR, "Error", null, "PNumber is required to load data.");
                return;
            }

            String filePath = Pnumber.toUpperCase() + "Obj.dat";
            System.out.println("Attempting to load file: " + filePath);

            File file = new File(filePath);
            if (!file.exists()) {
                alertDialogBuilder(AlertType.ERROR, "Error", null, "The requested ssp data was not found.");
                return;
            }

            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                StudentProfile profile = (StudentProfile) ois.readObject();
                System.out.println("File loaded successfully.");

                // Populate the CreateStudentProfilePane with the loaded data
                cspp.setStudentPnumber(profile.getStudentPnumber());
                cspp.setStudentName(profile.getStudentName());
                cspp.setStudentEmail(profile.getStudentEmail());
                cspp.setStudentDate(profile.getSubmissionDate());
                cspp.setCboCourses(profile.getStudentCourse());

                alertDialogBuilder(AlertType.INFORMATION, "Success", null, "Student profile loaded successfully.");
            } catch (IOException ioE) {
                ioE.printStackTrace();  // Print the stack trace for debugging
                alertDialogBuilder(AlertType.ERROR, "Error", null, "An error occurred while loading the data.");
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
        }
    }
}
