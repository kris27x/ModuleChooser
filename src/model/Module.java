package model;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Module class represents a module in the system.
 * It implements Comparable and Serializable interfaces.
 */
public class Module implements Comparable<Module>, Serializable {

    private static final long serialVersionUID = 1L;

    private String moduleCode;
    private String moduleName;
    private int moduleCredits;
    private boolean mandatory;
    private Schedule delivery;

    /**
     * Constructs a new Module with the specified details.
     *
     * @param moduleCode The code of the module.
     * @param moduleName The name of the module.
     * @param moduleCredits The credits of the module.
     * @param mandatory Indicates if the module is mandatory.
     * @param delivery The schedule of the module delivery.
     */
    public Module(String moduleCode, String moduleName, int moduleCredits, boolean mandatory, Schedule delivery) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.moduleCredits = moduleCredits;
        this.mandatory = mandatory;
        this.delivery = delivery;
    }

    /**
     * Returns the module code.
     *
     * @return The module code.
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Sets the module code.
     *
     * @param moduleCode The module code to set.
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Returns the module name.
     *
     * @return The module name.
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Sets the module name.
     *
     * @param moduleName The module name to set.
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Returns the module credits.
     *
     * @return The module credits.
     */
    public int getModuleCredits() {
        return moduleCredits;
    }

    /**
     * Sets the module credits.
     *
     * @param moduleCredits The module credits to set.
     */
    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    /**
     * Returns whether the module is mandatory.
     *
     * @return true if the module is mandatory, false otherwise.
     */
    public boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets whether the module is mandatory.
     *
     * @param mandatory true if the module is mandatory, false otherwise.
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Returns the schedule of the module delivery.
     *
     * @return The schedule of the module delivery.
     */
    public Schedule getDelivery() {
        return delivery;
    }

    /**
     * Sets the schedule of the module delivery.
     *
     * @param delivery The schedule of the module delivery.
     */
    public void setDelivery(Schedule delivery) {
        this.delivery = delivery;
    }

    /**
     * Returns a string representation of the module.
     * This method overrides the default toString method to provide a simple module code and name representation.
     *
     * @return The module code and name.
     */
    @Override
    public String toString() {
        // A non-standard toString that simply returns the module code and name,
        // to assist in displaying modules correctly in a ListView<Module> in the view
        return moduleCode + " : " + moduleName;
    }

    /**
     * Returns a detailed string representation of the module.
     *
     * @return A string containing the module details.
     */
    public String actualToString() {
        return "Module:[moduleCode=" + moduleCode + ", moduleName=" + moduleName +
                ", moduleCredits=" + moduleCredits + ", mandatory=" + mandatory + ", delivery=" + delivery + "]";
    }

    /**
     * Compares this module to another module for ordering.
     * Comparison is primarily based on module code, followed by credits, mandatory status, name, and delivery schedule.
     *
     * @param other The module to be compared.
     * @return A negative integer, zero, or a positive integer as this module is less than, equal to, or greater than the specified module.
     */
    @Override
    public int compareTo(Module other) {
        int result = this.moduleCode.compareTo(other.moduleCode);

        if (result == 0) {
            result = Integer.compare(this.moduleCredits, other.moduleCredits);

            if (result == 0) {
                result = Boolean.compare(other.mandatory, this.mandatory);

                if (result == 0) {
                    result = this.moduleName.compareTo(other.moduleName);

                    if (result == 0) {
                        result = this.delivery.compareTo(other.delivery);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return true if this object is the same as the obj argument, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Module other = (Module) obj;

        return this.mandatory == other.mandatory && this.moduleCredits == other.moduleCredits &&
                this.moduleCode.equals(other.moduleCode) && this.moduleName.equals(other.moduleName) &&
                this.delivery.equals(other.delivery);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this module.
     */
    @Override
    public int hashCode() {
        return Objects.hash(mandatory, moduleCredits, moduleCode, moduleName, delivery);
    }
}
