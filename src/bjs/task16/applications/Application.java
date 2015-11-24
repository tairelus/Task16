package bjs.task16.applications;

/**
 * Created by YM on 22.11.2015.
 * Base class for applications which will be installed on the computer
 */
abstract public class Application {
    protected String applicationName;
    protected String applicationVersion;

    /**
     * @return Application Id for the current application.
     */
    public String getApplicationId() {
        return applicationName + " v" + applicationVersion;
    }

    /**
     * @return Application name
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @return Application version
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Sets application name
     * @param applicationName Application name
     */
    protected void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * Sets application version
     * @param applicationVersion Application version
     */
    protected void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }
}
