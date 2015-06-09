package ClassesforBackEnd;

/**
 *
 * @author srhodes47
 */
public class Projects {

    private int projectId;
    private String projectName;
    private String projectManager;

    public Projects(int tempProjectId, String tempProjectName, String tempProjectManager) {
        projectId = tempProjectId;
        projectName = tempProjectName;
        projectManager = tempProjectManager;
    }

    /**
     * @return the projectId
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the projectManager
     */
    public String getProjectManager() {
        return projectManager;
    }

    /**
     * @param projectManager the projectManager to set
     */
    public void setProjectManager(String tempProjectManager) {
        this.projectManager = tempProjectManager;
    }

}
