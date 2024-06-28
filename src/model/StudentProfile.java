package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

/**
 * The StudentProfile class represents a student's profile, including their personal details,
 * selected course, and selected and reserved modules.
 * It implements the Serializable interface to allow student profile objects to be serialized.
 */
public class StudentProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentPnumber;
    private Name studentName;
    private String studentEmail;
    private LocalDate studentDate;
    private Course studentCourse;
    private Set<Module> selectedModules;
    private Set<Module> reservedModules;

    /**
     * Default constructor that initializes the fields with default values.
     */
    public StudentProfile() {
        studentPnumber = "";
        studentName = new Name();
        studentEmail = "";
        studentDate = null;
        studentCourse = null;
        selectedModules = new TreeSet<Module>();
        reservedModules = new TreeSet<Module>();
    }

    /**
     * Returns the student P number.
     *
     * @return The student P number.
     */
    public String getStudentPnumber() {
        return studentPnumber;
    }

    /**
     * Sets the student P number.
     *
     * @param studentPnumber The student P number to set.
     */
    public void setStudentPnumber(String studentPnumber) {
        this.studentPnumber = studentPnumber;
    }

    /**
     * Returns the student's name.
     *
     * @return The student's name.
     */
    public Name getStudentName() {
        return studentName;
    }

    /**
     * Sets the student's name.
     *
     * @param studentName The student's name to set.
     */
    public void setStudentName(Name studentName) {
        this.studentName = studentName;
    }

    /**
     * Returns the student's email.
     *
     * @return The student's email.
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets the student's email.
     *
     * @param studentEmail The student's email to set.
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Returns the student's submission date.
     *
     * @return The student's submission date.
     */
    public LocalDate getSubmissionDate() {
        return studentDate;
    }

    /**
     * Sets the student's submission date.
     *
     * @param studentDate The student's submission date to set.
     */
    public void setSubmissionDate(LocalDate studentDate) {
        this.studentDate = studentDate;
    }

    /**
     * Returns the student's course.
     *
     * @return The student's course.
     */
    public Course getStudentCourse() {
        return studentCourse;
    }

    /**
     * Sets the student's course.
     *
     * @param studentCourse The student's course to set.
     */
    public void setStudentCourse(Course studentCourse) {
        this.studentCourse = studentCourse;
    }

    /**
     * Adds a module to the selected modules set.
     *
     * @param m The module to add.
     * @return true if the module was added, false if it was already in the set.
     */
    public boolean addSelectedModule(Module m) {
        return selectedModules.add(m);
    }

    /**
     * Returns all selected modules.
     *
     * @return A set of all selected modules.
     */
    public Set<Module> getAllSelectedModules() {
        return selectedModules;
    }

    /**
     * Clears all selected modules.
     */
    public void clearSelectedModules() {
        selectedModules.clear();
    }

    /**
     * Adds a module to the reserved modules set.
     *
     * @param m The module to add.
     * @return true if the module was added, false if it was already in the set.
     */
    public boolean addReservedModule(Module m) {
        return reservedModules.add(m);
    }

    /**
     * Returns all reserved modules.
     *
     * @return A set of all reserved modules.
     */
    public Set<Module> getAllReservedModules() {
        return reservedModules;
    }

    /**
     * Clears all reserved modules.
     */
    public void clearReservedModules() {
        reservedModules.clear();
    }

    /**
     * Returns a string representation of the student profile.
     *
     * @return A string representation of the student profile.
     */
    @Override
    public String toString() {
        return "StudentProfile:[Pnumber=" + studentPnumber + ", studentName="
                + studentName + ", studentEmail=" + studentEmail + ", studentDate="
                + studentDate + ", studentCourse=" + (studentCourse != null ? studentCourse.actualToString() : "null")
                + ", selectedModules=" + selectedModules
                + ", reservedModules=" + reservedModules + "]";
    }
}
