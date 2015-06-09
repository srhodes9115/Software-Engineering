/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClassesforBackEnd;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author srhodes47
 */
public class EmployeeManagerTest {
    
    public EmployeeManagerTest() {
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
     * Test of retrieveEmployee method, of class EmployeeManager.
     */
    @Test
    public void testRetrieveEmployee() {
        System.out.println("retrieveEmployee");
        String employeeId = "agarg76";
        Employee myEmployee = new Employee ("agarg76","Amit","Garg","Nonemp-IT","AOL, Inc.");
        ArrayList <Employee> myArray= (ArrayList<Employee>) new ArrayList();
        myArray.add(myEmployee);
        Employee temp = (Employee) myArray.get(0);
        String manual = temp.getCDID() + temp.getFirstName() + temp.getLastName() + temp.getJobTitle() + temp.getCompany();
        
        EmployeeManager instance = new EmployeeManager();
        Employee result = (Employee) instance.retrieveEmployee(employeeId).get(0);
        String myResult = result.getCDID()+result.getFirstName() + result.getLastName() + result.getJobTitle() + result.getCompany();
        assertEquals(manual, myResult);
    }

    /**
     * Test of isEmployee method, of class EmployeeManager.
     */
    @Test
    public void testIsEmployee() {
        System.out.println("isEmployee");
        String employee = "tchesley13";
        EmployeeManager instance = new EmployeeManager();
        boolean expResult = true;
        boolean result = instance.isEmployee(employee);
        assertEquals(expResult, result);
    }
    
}
