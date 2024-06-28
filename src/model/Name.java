package model;

import java.io.Serializable;

/**
 * The Name class represents a person's name with a first name and a family name.
 * It implements the Serializable interface to allow name objects to be serialized.
 */
public class Name implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String familyName;

    /**
     * Default constructor that initializes the first name and family name to empty strings.
     */
    public Name() {
        firstName = "";
        familyName = "";
    }

    /**
     * Constructs a new Name with the specified first name and family name.
     *
     * @param firstName  The first name of the person.
     * @param familyName The family name of the person.
     */
    public Name(String firstName, String familyName) {
        this.firstName = firstName;
        this.familyName = familyName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the family name.
     *
     * @param familyName The family name to set.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Returns the first name.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the family name.
     *
     * @return The family name.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Returns the full name, which is the concatenation of the first name and family name.
     * If both names are empty, returns an empty string.
     *
     * @return The full name.
     */
    public String getFullName() {
        if (firstName.isEmpty() && familyName.isEmpty()) {
            return "";
        } else {
            return firstName + " " + familyName;
        }
    }

    /**
     * Returns a string representation of the Name object.
     *
     * @return A string representation of the Name object.
     */
    @Override
    public String toString() {
        return "Name:[firstName=" + firstName + ", familyName=" + familyName + "]";
    }
}
