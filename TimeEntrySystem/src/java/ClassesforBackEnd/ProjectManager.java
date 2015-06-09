package ClassesforBackEnd;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author srhodes47
 */
public class ProjectManager {

    private Connection conn;
    private Statement stat;
    private ResultSet myResults = null;
    private Boolean isConnected = false;
    private PreparedStatement myStatement = null;
    private ArrayList<String> myArray = new ArrayList<String>();
    private ArrayList useArray = new ArrayList();
    private static final Logger log = Logger.getLogger(ProjectManager.class.getName());

    //get specific project Log request for Manager
    public ArrayList getProjectLog(String projectId) {
        useArray = new ArrayList();
        TimeLog myTimeLog;
        ProjectManager myManager = new ProjectManager();
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT DISTINCT projectId FROM ProjectsHistory WHERE projectId = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, projectId); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();

            log.info("Checking that this project ID #" + projectId + " exists using this sql: " + sql);

            while (this.myResults.next()) {

                myArray.add(this.myResults.getString("projectId")); //array of the one project 
            }
            myResults = this.stat.executeQuery("SELECT * FROM timeLog WHERE projectId = '" + myArray.get(0) + "';");
            while (this.myResults.next()) {
                myTimeLog = new TimeLog(this.myResults.getString("cdid"), Integer.parseInt(this.myResults.getString("projectId")), Integer.parseInt(this.myResults.getString("workedTime")), myManager.sqlEnteredDate(this.myResults.getString("workedThisDate")), myManager.sqlEnteredDate(this.myResults.getString("dateEntered")), this.myResults.getString("typeOfWork"));
                useArray.add(myTimeLog);
            }
        } catch (Exception e) {
            System.out.println("Error in retrieving Project Log: " + e.getMessage());
        }
        return useArray;

    }

    //get all projects managed for the project Manager
    public ArrayList getManagerHistory(String projectManager) {
        useArray = new ArrayList();
        Projects myProject;

        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT * FROM ProjectsHistory WHERE projectManagerCdid = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, projectManager); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();
            log.info("Getting this project Manager: " + projectManager + " history with this sql: " + sql);

            while (this.myResults.next()) { //scans database
                myProject = new Projects(Integer.parseInt(this.myResults.getString("projectId")), this.myResults.getString("projectName"), this.myResults.getString("projectManagerCdid"));
                useArray.add(myProject);
            }

        } catch (Exception ex) { //use try catch structure instead of throwing exceptions
            System.out.println("Error in retrieving Manager History: " + ex.getMessage());
        }
        return useArray;
    }

    //shows total employee Log
    public ArrayList getEmployeeProject(String employee_id) {
        useArray = new ArrayList();
        Projects myProject;
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT DISTINCT projectId FROM timeLog WHERE cdid = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, employee_id); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();
            log.info("Retrieving the employee project history using this id: " + employee_id + ". This is the sql statement: " + sql);
            while (this.myResults.next()) { //scans database
                myArray.add(this.myResults.getString("projectId"));
                //make an array of projectId
            }

            for (int i = 0; i < myArray.size(); i++) {

                myResults = this.stat.executeQuery("SELECT * FROM ProjectsHistory WHERE projectId = " + myArray.get(i) + ";");

                while (this.myResults.next()) {
                    myProject = new Projects(Integer.parseInt(this.myResults.getString("projectId")), this.myResults.getString("projectName"), this.myResults.getString("projectManagerCdid"));
                    useArray.add(myProject);
                }
            }
        } catch (Exception ex) { //use try catch structure instead of throwing exceptions
            System.out.println("Error is getEmployeeProject: " + ex.getMessage());
        }

        return useArray;
    }

    //Adds to Employee Log
    public void addLog(String id, String projectId, int hours, Date dateWorked, Date dateEntered, String type) {

        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "INSERT INTO timeLog (cdid, projectId, workedTime, workedThisDate, dateEntered, typeOfWork) VALUES (?,?,?,?,?,?)";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, id); //validates entered text must be String (sequence #,value)
            myStatement.setString(2, projectId);
            myStatement.setInt(3, hours);
            myStatement.setDate(4, dateWorked);
            myStatement.setDate(5, dateEntered); //find current date
            myStatement.setString(6, type);
            myStatement.executeUpdate();
            log.info("Adding log to the local database timeLog with these values: " + id + ", " + projectId + ", " + hours + ", " + dateWorked + ", " + dateEntered + ", " + type + ". This is the sql: " + sql);

        } catch (Exception e) {
            System.out.println("Error in adding log: " + e.getMessage());
        }
    }

    public java.sql.Date sqlEnteredDate(String myDate) {

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date;
        java.sql.Date mySqlDate = null;
        try {
            date = ft.parse(myDate);
            mySqlDate = new java.sql.Date(date.getTime());
            log.info("This is the date entered by the user " + myDate);
            return mySqlDate;
        } catch (ParseException ex) {
            System.out.println("Error in sql current Date: " + ex.getMessage());
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mySqlDate;
    }

    //Gets employee Log all time
    public ArrayList getEmployeeLogHistory(String employee_id) {
        useArray = new ArrayList();
        ProjectManager myManager = new ProjectManager();
        TimeLog myTimeLog;
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            String sql = "SELECT * FROM timeLog WHERE cdid = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, employee_id); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();
            log.info("Retrieving the employee log history of " + employee_id + " using this sql: " + sql);

            while (this.myResults.next()) {
                myTimeLog = new TimeLog(this.myResults.getString("cdid"), Integer.parseInt(this.myResults.getString("projectId")), Integer.parseInt(this.myResults.getString("workedTime")), myManager.sqlEnteredDate(this.myResults.getString("workedThisDate")), myManager.sqlEnteredDate(this.myResults.getString("dateEntered")), this.myResults.getString("typeOfWork"));
                useArray.add(myTimeLog);
            }
        } catch (Exception ex) {
            System.out.println("Error in getEmployeeHistory: " + ex.getMessage());
        }
        return useArray;
    }

    //Adds project to Managers Projects
    public void addProject(String name, String manager) {
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "INSERT INTO ProjectsHistory (projectName,projectManagerCdid) VALUES (?,?)";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, name); //validates entered text must be String (sequence #,value)
            myStatement.setString(2, manager);
            myStatement.executeUpdate();
            log.info("Adding project with this name: " + name + " and this manager id: " + manager + ". This is the sql: " + sql);

        } catch (Exception e) {
            System.out.println("Error in adding Project: " + e.getMessage());
        }
    }

    public boolean validateInput(HttpServletRequest request) {
        ProjectManager myProject = new ProjectManager();
        EmployeeManager myManager = new EmployeeManager();

        if ("noManager".equals(request.getParameter("projectManager"))) {
            if (myManager.isEmployee(request.getParameter("cdidId")) == true) {
                if ("yesNew".equals(request.getParameter("createNew"))) {
                    if (request.getParameter("projectId").length() != 0) {
                        if (myProject.isAProject(request.getParameter("projectId")) == true) {
                            if (request.getParameter("hours").length() != 0) {
                                if (request.getParameter("workDate").length() != 0) {
                                    if (!"nothing".equals(request.getParameter("typeOfWork"))) {
                                        if ("yesNew".equals(request.getParameter("createNew"))) {
                                            if ("requirement".equals(request.getParameter("typeOfWork"))) {
                                                String typeOfWork = "Requirements";
                                                request.setAttribute("typeOfWork", typeOfWork);
                                            } else if ("development".equals(request.getParameter("typeOfWork"))) {
                                                String typeOfWork = "Development";
                                                request.setAttribute("typeOfWork", typeOfWork);
                                            } else {
                                                String typeOfWork = "Testing";
                                                request.setAttribute("typeOfWork", typeOfWork);
                                            }
                                            java.util.Date utilDate = new java.util.Date();
                                            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                                            myProject.addLog(request.getParameter("cdidId"), request.getParameter("projectId"), Integer.parseInt(request.getParameter("hours")), myProject.sqlEnteredDate(request.getParameter("workDate")), sqlDate, (String) request.getAttribute("typeOfWork"));
                                        }
                                        return true;
                                    }

                                }
                            }
                        }
                    }
                } else {
                    return true;
                }
            }
        } else {
            if (myManager.isEmployee(request.getParameter("cdidId")) == true) {
                if (myProject.isManager(request.getParameter("cdidId")) == true) {
                    if (request.getParameter("projectName").length() != 0) {
                        myProject.addProject(request.getParameter("projectName"), request.getParameter("cdidId"));
                    }
                    if (request.getParameter("employeeCdid").trim().compareTo("") != 0) {
                        if (myManager.isEmployee(request.getParameter("employeeCdid")) == true) {
                            if (myProject.isMyEmployee(request.getParameter("cdidId"), request.getParameter("employeeCdid")) == true) {
                                if (myProject.getEmployeeLogHistory(request.getParameter("employeeCdid")).get(0).toString().equalsIgnoreCase("") == false) {
                                    request.setAttribute("employeeLogHistory", myProject.getEmployeeLogHistory(request.getParameter("employeeCdid")));
                                }
                            }
                        }
                    }
                    if (request.getParameter("projectLog").trim().compareTo("") != 0) {
                        if (myProject.isAProject(request.getParameter("projectLog")) == true) {
                            if (myProject.isMyProject(request.getParameter("cdidId"), request.getParameter("projectLog")) == true) {
                                if (myProject.getProjectLog(request.getParameter("projectLog")).get(0).toString().equalsIgnoreCase("") == false) {
                                    request.setAttribute("ProjectLog", myProject.getProjectLog(request.getParameter("projectLog")));
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    //Keeps employee from viewing logs of Employees & Projects
    public boolean isManager(String Employee) {
        StringBuilder sb = new StringBuilder(); //way to write to server output, can't use System.out.println
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "SELECT DISTINCT projectManagerCdid FROM ProjectsHistory WHERE projectManagerCdid = ?";
            myStatement = conn.prepareStatement(sql); //saves some security issues
            myStatement.setString(1, Employee); //validates entered text must be String (sequence #,value)
            myResults = myStatement.executeQuery();
            log.info("Checking that this cdid ID: " + Employee + " is a project Manager. This is the sql: " + sql);
            while (this.myResults.next()) {
                sb.append(this.myResults.getString("projectManagerCdid"));
            }
            if (sb.toString().equalsIgnoreCase(Employee)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in varifying that you are a manager: " + e.getMessage());
        }
        return false;
    }

    public boolean isMyEmployee(String cdid, String employee) {
        StringBuilder sb = new StringBuilder(); //way to write to server output, can't use System.out.println
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "SELECT projectId FROM ProjectsHistory WHERE projectManagerCdid = ?";
            myStatement = conn.prepareStatement(sql);
            myStatement.setString(1, cdid);
            myResults = myStatement.executeQuery();

            while (this.myResults.next()) {
                myArray.add(this.myResults.getString("projectId"));
            }
            ArrayList<String> myId = new ArrayList<String>();
            String sql2 = "SELECT DISTINCT projectId FROM timeLog WHERE cdid = ?";
            myStatement = conn.prepareStatement(sql2);
            myStatement.setString(1, employee);
            myResults = myStatement.executeQuery();
            log.info("Checking whether " + employee + " works under " + cdid + ". The sql is " + sql + " and " + sql2);
            while (this.myResults.next()) {
                myId.add(this.myResults.getString("projectId"));
            }
            for (int i = 0; i < myArray.size(); i++) {
                for (int j = 0; j < myId.size(); j++) {
                    if (myArray.get(i).equalsIgnoreCase(myId.get(j)) == true) {
                        return true;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Error in isMyEmployee: " + e.getMessage());
        }
        return false;
    }

    public boolean isAProject(String projectId) {
        StringBuilder sb = new StringBuilder(); //way to write to server output, can't use System.out.println
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "SELECT projectId FROM ProjectsHistory WHERE projectId = ?";
            myStatement = conn.prepareStatement(sql);
            myStatement.setString(1, projectId);
            myResults = myStatement.executeQuery();
            log.info("Check that the project Id: " + projectId + " is valid to log time.");

            while (this.myResults.next()) {
                sb.append(this.myResults.getString("projectId"));
            }
            if (sb.toString().equalsIgnoreCase(projectId)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in checking whether project exists: " + e.getMessage());
        }
        return false;
    }

    public boolean isMyProject(String cdid, String projectId) {
        StringBuilder sb = new StringBuilder(); //way to write to server output, can't use System.out.println
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "SELECT DISTINCT projectId FROM ProjectsHistory WHERE projectManagerCdid = ?";
            myStatement = conn.prepareStatement(sql);
            myStatement.setString(1, cdid);
            myResults = myStatement.executeQuery();
            log.info("Checking that the project Manager: " + cdid + " is accessing a project ID: " + projectId + " of a project that they actually manage");

            ArrayList<String> tempArray = new ArrayList<String>();

            while (this.myResults.next()) {
                tempArray.add(this.myResults.getString("projectId"));
            }

            for (int i = 0; i < tempArray.size(); i++) {
                if (tempArray.get(i).equalsIgnoreCase(projectId) == true) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in checking that the project matches the project Manager id : " + e.getMessage());
        }
        return false;
    }

    public void closeConnection() {
        try {
            this.conn.close();
            this.stat = null;
            this.myStatement = null;
        } catch (SQLException ex) {
            System.out.println("Error in closing Connection: " + ex.getMessage());
            Logger.getLogger(EmployeeManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Connection(String doWhat) {

        try {

            if (doWhat.equalsIgnoreCase("open")) {
                final long numberRetry = 5;
                for (int i = 0; i < numberRetry; i++) {
                    Class.forName("com.mysql.jdbc.Driver").newInstance(); //always need this to access driver
                    this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Employee_Information?user=root"); //connects to database
                    this.stat = this.conn.createStatement();
                }
                isConnected = true;
            } else {
                this.conn.close();
                isConnected = false;
            }
        } catch (Exception ex) {
            System.out.println("Connection error:" + ex.getMessage());
            try {
                final long time = 100;
                Thread.sleep(time);
            } catch (InterruptedException x) {
            }
        }
    }
}
