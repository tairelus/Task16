package bjs.task16.os;

import bjs.task16.hardware.*;
import bjs.task16.applications.*;

/**
 * Created by YM on 21.11.2015.
 * Class which presents information about operation system
 */
public class OperatingSystem {
    /**Name on the operation system*/
    private String operationSystem;
    /**Human readable computer name*/
    private String computerName;

    /**Instance of the current computer*/
    private Computer computer;

    /**Stores all installed applications*/
    Application[] installedApplications;

    /**
     * Sets reference to the current computer
     * @param computer Current computer object
     */
    public OperatingSystem (Computer computer) {
        this.computer = computer;
    }

    /**
     * Presents system requirements.
     */
    private class SystemRequirements {
        /**The CPU clock rate in GHz*/
        private final double MIN_PROCESSOR_CLOCK_RATE = 1;
        /**Random access memory size in Gb*/
        private final double MIN_RANDOM_ACCESS_MEMORY_SIZE = 2;
        /**Size of the fixed memory storage in Gb. E.g. hard-drive*/
        private final int MIN_FIXED_MEMORY_SIZE = 100;
    }

    /**
     * Checks system requirements
     * @return True if input system requirement is equal or greater than predefined
     */
    public boolean checkSystemRequirements() {
        SystemRequirements systemRequirements = new SystemRequirements();

        return computer.getProcessorClockRate() >= systemRequirements.MIN_PROCESSOR_CLOCK_RATE &&
                computer.getRandomAccessMemorySize() >= systemRequirements.MIN_RANDOM_ACCESS_MEMORY_SIZE &&
                computer.getFixedMemorySize() >= systemRequirements.MIN_FIXED_MEMORY_SIZE;
    }

    /**
     * Installs application into the operation system
     * @param application Application object
     * @return Number of the application installed
     */
    public int installApplication(Application application) {
        int applicationNum = 1;

        if (installedApplications == null) {
            installedApplications = new Application[applicationNum];
        }
        else {
            applicationNum = installedApplications.length + 1;
        }

        //This is the code from the java.util.Arrays.copyOf()
        Application[] copyArray = new Application[applicationNum];
        System.arraycopy(installedApplications, 0, copyArray, 0,
                Math.min(installedApplications.length, applicationNum));

        copyArray[applicationNum - 1] = application;
        installedApplications = copyArray;

        return installedApplications.length;
    }

    /**
     * Gets installed application by Id
     * @param applicationId Application Id
     * @return Returns installed application
     */
    public Application getApplication(String applicationId) {
        if (installedApplications != null) {
            for (Application application : installedApplications) {
                if (applicationId.equals(application.getApplicationId())) {
                    return application;
                }
            }
        }

        return null;
    }

    /**
     * Shut down operation system
     */
    public void shutDown() {
        System.out.println("Operating system " + operationSystem + " is shut down.");

        installedApplications = null;
        computer = null;
        computerName = null;
        operationSystem = null;
    }

    /**
     * Overrides Object.toString method
     * @return String with operation system characteristics and installed applications
     */
    @Override
    public String toString() {
        String result;
        result = "Operation system: " + getOperationSystem() + "\n";
        result += "Computer name: " + getComputerName() + "\n\n";

        result += "Installed applications: \n";

        if (installedApplications != null) {
            for (Application application : installedApplications) {
                result += "\t" + application.getApplicationId() + "\n";
            }
        }

        result += "\n";

        return result;
    }

    /**
     * Sets operating system type
     * @param operationSystem Type of the operation system
     */
    public void setOperationSystem(String operationSystem) {
        if (!operationSystem.isEmpty())
            this.operationSystem = operationSystem;
    }

    /**
     * Sets computer name
     * @param computerName Computer name
     */
    public void setComputerName(String computerName) {
        if (!computerName.isEmpty()) {
            this.computerName = computerName;
        }
    }

    /**
     * Gets operation system type
     * @return Operation system type
     */
    public String getOperationSystem() {
        return operationSystem;
    }

    /**
     * Gets computer name
     * @return computer name
     */
    public String getComputerName() {
        return computerName;
    }
}
