package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The Course class represents a course in the system.
 * It implements Serializable to allow course objects to be serialized.
 */
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseName;
    private Map<String, Module> modulesOnCourse;

    /**
     * Constructs a new Course with the specified course name.
     * Initializes the modulesOnCourse map.
     * 
     * @param courseName The name of the course.
     */
    public Course(String courseName) {
        this.courseName = courseName;
        modulesOnCourse = new HashMap<String, Module>();
    }

    /**
     * Returns the name of the course.
     * 
     * @return The course name.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course.
     * 
     * @param courseName The name to set for the course.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Adds a module to the course.
     * 
     * @param m The module to add.
     */
    public void addModuleToCourse(Module m) {
        modulesOnCourse.put(m.getModuleCode(), m);
    }

    /**
     * Returns the module associated with the specified module code.
     * 
     * @param code The module code.
     * @return The module associated with the specified code, or null if no module is found.
     */
    public Module getModuleByCode(String code) {
        return modulesOnCourse.get(code);
    }

    /**
     * Returns a collection of all modules on the course.
     * 
     * @return A collection of all modules.
     */
    public Collection<Module> getAllModulesOnCourse() {
        return modulesOnCourse.values();
    }

    /**
     * Returns a string representation of the course.
     * This method overrides the default toString method to provide a simple course name representation.
     * 
     * @return The course name.
     */
    @Override
    public String toString() {
        // A non-standard toString that simply returns the course name,
        // to assist in displaying courses correctly in a ComboBox<Course>
        return courseName;
    }

    /**
     * Returns a detailed string representation of the course.
     * 
     * @return A string containing the course name and modules on the course.
     */
    public String actualToString() {
        return "Course:[courseName=" + courseName + ", modulesOnCourse=" + modulesOnCourse + "]";
    }
}
