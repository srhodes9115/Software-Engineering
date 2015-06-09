package ClassesforBackEnd;

/**
 *
 * @author srhodes47
 */
public class TimeLog {
    private String cdid;
    private int projectId;
    private int workedTime;
    private java.sql.Date workedThisDate;
    private java.sql.Date dateEntered;
    private String typeOfWork;
    
    public TimeLog (String tempCdid, int tempProjectId, int tempWorkedTime,java.sql.Date tempWorkedThisDate, java.sql.Date tempDateEntered, String tempTypeOfWork)
    {
        cdid = tempCdid;
        projectId = tempProjectId;
        workedTime= tempWorkedTime;
        workedThisDate = tempWorkedThisDate;
        dateEntered = tempDateEntered;
        typeOfWork = tempTypeOfWork;
    }

    /**
     * @return the cdid
     */
    public String getCdid() {
        return cdid;
    }

    /**
     * @param cdid the cdid to set
     */
    public void setCdid(String cdid) {
        this.cdid = cdid;
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
     * @return the workedTime
     */
    public int getWorkedTime() {
        return workedTime;
    }

    /**
     * @param workedTime the workedTime to set
     */
    public void setWorkedTime(int workedTime) {
        this.workedTime = workedTime;
    }

    /**
     * @return the workedThisDate
     */
    public java.sql.Date getWorkedThisDate() {
        return workedThisDate;
    }

    /**
     * @param workedThisDate the workedThisDate to set
     */
    public void setWorkedThisDate(java.sql.Date workedThisDate) {
        this.workedThisDate = workedThisDate;
    }

    /**
     * @return the dateEntered
     */
    public java.sql.Date getDateEntered() {
        return dateEntered;
    }

    /**
     * @param dateEntered the dateEntered to set
     */
    public void setDateEntered(java.sql.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    /**
     * @return the typeOfWork
     */
    public String getTypeOfWork() {
        return typeOfWork;
    }

    /**
     * @param typeOfWork the typeOfWork to set
     */
    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }
    
}
