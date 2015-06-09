package ClassesforBackEnd;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author srhodes47
 */
public class ProjectManagerTest {
    
    public ProjectManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProjectLog method, of class ProjectManager.
     */
    @Test
    public void testGetProjectLog() {
        System.out.println("getProjectLog");
        String projectId = "2";
        ProjectManager myManager = new ProjectManager();
        TimeLog temp = new TimeLog ("alextimb",2,10,myManager.sqlEnteredDate("2014-05-19"),myManager.sqlEnteredDate("2007-08-08"),"Testing");
        TimeLog temp2 = new TimeLog ("bykowskipatch",2,3,myManager.sqlEnteredDate("2014-01-20"),myManager.sqlEnteredDate("2012-11-07"),"Development");
        String tempString = temp.getCdid() + temp.getProjectId() + temp.getWorkedTime() + temp.getWorkedThisDate() + temp.getDateEntered() + temp.getTypeOfWork();
        String tempString2 = temp2.getCdid() + temp2.getProjectId() + temp2.getWorkedTime() + temp2.getWorkedThisDate() + temp2.getDateEntered() + temp2.getTypeOfWork();
        
        ProjectManager instance = new ProjectManager();
        TimeLog result = (TimeLog) instance.getProjectLog(projectId).get(0);
        TimeLog result2 = (TimeLog) instance.getProjectLog(projectId).get(1);
        String myResult = result.getCdid() + result.getProjectId() + result.getWorkedTime() + result.getWorkedThisDate() + result.getDateEntered() + result.getTypeOfWork();
        String myResult2 = result2.getCdid() + result2.getProjectId() + result2.getWorkedTime() + result2.getWorkedThisDate() + result2.getDateEntered() + result2.getTypeOfWork();
        
        assertEquals(tempString, myResult);
        assertEquals(tempString2,myResult2);
    }

    /**
     * Test of getManagerHistory method, of class ProjectManager.
     */
    @Test
    public void testGetManagerHistory() {
        System.out.println("getManagerHistory");
        String projectManager = "tchesley13";
        ProjectManager instance = new ProjectManager();
        Projects myProject = new Projects(4,"Program Ad Market",projectManager);
        String project = myProject.getProjectId() + myProject.getProjectName() + myProject.getProjectManager();
        
        Projects result = (Projects) instance.getManagerHistory(projectManager).get(0);
        String myResult = result.getProjectId() + result.getProjectName() + result.getProjectManager();
        assertEquals(project, myResult);
    }

    /**
     * Test of getEmployeeProject method, of class ProjectManager.
     */
    @Test
    public void testGetEmployeeProject() {
        System.out.println("getEmployeeProject");
        String employee_id = "vasantraj1";
        ProjectManager instance = new ProjectManager();
        Projects myProject = new Projects (3,"Fix Computers","erebao");
        String project = myProject.getProjectId() + myProject.getProjectName() + myProject.getProjectManager();
        
        Projects result = (Projects) instance.getEmployeeProject(employee_id).get(0);
        String myResult = result.getProjectId() + result.getProjectName() + result.getProjectManager();
        assertEquals(project, myResult);
    }

    /**
     * Test of addLog method, of class ProjectManager.
     */
    @Test
    public void testAddLog() {
        System.out.println("addLog");
        String id = "";
        String projectId = "";
        int hours = 0;
        Date dateWorked = null;
        Date dateEntered = null;
        String type = "";
        ProjectManager instance = new ProjectManager();
        instance.addLog(id, projectId, hours, dateWorked, dateEntered, type);
    }


    /**
     * Test of getEmployeeLogHistory method, of class ProjectManager.
     */
    @Test
    public void testGetEmployeeLogHistory() {
        System.out.println("getEmployeeLogHistory");
        String employee_id = "vasantraj1";
        ProjectManager instance = new ProjectManager();
        TimeLog expResult = new TimeLog (employee_id,3,6,instance.sqlEnteredDate("2013-09-12"),instance.sqlEnteredDate("2013-04-17"),"Testing");
        String expected = expResult.getCdid() + expResult.getProjectId() + expResult.getWorkedTime() + expResult.getWorkedThisDate() + expResult.getDateEntered() + expResult.getTypeOfWork();
        
        TimeLog result = (TimeLog) instance.getEmployeeLogHistory(employee_id).get(0);
        String myResult = result.getCdid() + result.getProjectId() + result.getWorkedTime() + result.getWorkedThisDate() + result.getDateEntered() + result.getTypeOfWork();
        assertEquals(expected, myResult);
    }

    /**
     * Test of addProject method, of class ProjectManager.
     */
    @Test
    public void testAddProject() {
        System.out.println("addProject");
        String name = "TownHall Meeting";
        String manager = "erebao";
        ProjectManager instance = new ProjectManager();
        instance.addProject(name, manager);  
    }

    /**
     * Test of isManager method, of class ProjectManager.
     */
    @Test
    public void testIsManager() {
        System.out.println("isManager");
        String Employee = "alextimb";
        ProjectManager instance = new ProjectManager();
        boolean expResult = false;
        boolean result = instance.isManager(Employee);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMyEmployee method, of class ProjectManager.
     */
    @Test
    public void testIsMyEmployee() {
        System.out.println("isMyEmployee");
        String cdid = "agarg76";
        String employee = "smukhia";
        ProjectManager instance = new ProjectManager();
        boolean expResult = true;
        boolean result = instance.isMyEmployee(cdid, employee);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAProject method, of class ProjectManager.
     */
    @Test
    public void testIsAProject() {
        System.out.println("isAProject");
        String projectId = "3";
        ProjectManager instance = new ProjectManager();
        boolean expResult = true;
        boolean result = instance.isAProject(projectId);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMyProject method, of class ProjectManager.
     */
    @Test
    public void testIsMyProject() {
        System.out.println("isMyProject");
        String cdid = "agarg76";
        String projectId = "2";
        ProjectManager instance = new ProjectManager();
        boolean expResult = true;
        boolean result = instance.isMyProject(cdid, projectId);
        assertEquals(expResult, result);
    }

    
}
