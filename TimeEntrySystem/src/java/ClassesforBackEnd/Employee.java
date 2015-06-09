package ClassesforBackEnd;

/**
 *
 * @author srhodes47
 */
public class Employee {

    private String cdid;
    private String firstName;
    private String lastName;
    private String title;
    private String company;

    public Employee(String tempEmployeeId, String tempFirstName, String tempLastName, String tempJobTitle, String tempCostCenter) {
        cdid = tempEmployeeId;
        firstName = tempFirstName;
        lastName = tempLastName;
        title = tempJobTitle;
        company = tempCostCenter;
    }

    /**
     * @return the cdid
     */
    public String getCDID() {
        return cdid;
    }

    /**
     * @param CDID the cdid to set
     */
    public void setCDID(String CDID) {
        this.cdid = CDID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the title
     */
    public String getJobTitle() {
        return title;
    }

    /**
     * @param jobTitle the title to set
     */
    public void setJobTitle(String jobTitle) {
        this.title = jobTitle;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

}
