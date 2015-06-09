package ClassesforBackEnd;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author srhodes47
 */
public class EmployeeManager {

    private Connection conn;
    private Statement stat;
    private ResultSet myResults = null;
    private Boolean isConnected = false;
    private ArrayList myArray;

    private PreparedStatement myStatement = null; //security issues with string, validates entered text
    private static final Logger log = Logger.getLogger(EmployeeManager.class.getName());

    //PropertyConfigurator.configure("log4j.properties");
    //Uses prepared statements only takes types restricts user
    public ArrayList retrieveEmployee(String employeeId) {
        myArray = new ArrayList();
        Employee myEmployee;
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT * FROM edwPerson WHERE cdid = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, employeeId); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();

            while (myResults.next()) {
                myEmployee = new Employee(this.myResults.getString("cdid"), this.myResults.getString("firstName"), this.myResults.getString("lastName"), this.myResults.getString("title"), this.myResults.getString("company"));
                myArray.add(myEmployee);
            }
            log.info("Search for this employee " + employeeId + " with this sql statement: " + sql);

        } catch (Exception e) {
            System.out.println("Error in retrieving employee info: " + e.getMessage());
        }
        return myArray;
    }

    //Checks whether the employee is valid
    public boolean isEmployee(String employee) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT cdid FROM edwPerson WHERE cdid = ?";
            log.info("Checking that " + employee + " is a valid cdid. Sql statement used is " + sql);
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, employee); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();
            while (this.myResults.next()) {
                sb.append(this.myResults.getString("cdid"));
            }

            if (sb.toString().equalsIgnoreCase(employee)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in checking whether you are an employee: " + e.getMessage());
        }
        return false;
    }

    //closes connections
    public void closeConnection() {
        try {
            conn.close();
            stat = null;
            myStatement = null;
        } catch (SQLException ex) {
            System.out.println("Error in closing the connection: " + ex.getMessage());
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Connection(String doWhat) {
        try {
            if (doWhat.equalsIgnoreCase("open")) {
                final long numberRetries = 5;
                for (int i = 0; i < numberRetries; i++) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance(); //always need this to access driver
                    this.conn = DriverManager.getConnection("jdbc:mysql://mysqlvmdev-dtc01.ops.aol.com:3406/EDW?user=srhodes47&password=19Sudoku!"); //connects to database
                    this.stat = this.conn.createStatement();
                }
                isConnected = true;
            } else {
                this.conn.close();
                isConnected = false;
            }
        } catch (Exception e) {
            try {
                final long time = 100;
                Thread.sleep(time);
            } catch (InterruptedException x) {
                System.out.println("Error in connection: " + x.getMessage());
            }
        }
    }

}
