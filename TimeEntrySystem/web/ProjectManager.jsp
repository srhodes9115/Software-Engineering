<%-- 
    Document   : ProjectManager
    Created on : Jun 20, 2014, 3:09:21 PM
    Author     : srhodes47
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ClassesforBackEnd.*"%>
<%@include file= "CSS.css" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Manager</title>
    </head>
    <body>
        <%--Declarations --%>
        <%!EmployeeManager myManager = new EmployeeManager(); %>
        <%! Employee myEmployee; %>
        <%! ProjectManager myProject = new ProjectManager(); %>
        <%! java.util.Date utilDate = new java.util.Date(); %>
        <%! java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());%>

        <%--Project Manager --%>
        <h1>Here is the Project Manager information for <%=request.getParameter("cdidId")%></h1>
        <%--Manager Information --%>
        <div>Project Manager Information: </div>
        <% List <Employee> employeeInfo = (List <Employee>) request.getAttribute("retrieveEmployee"); %>
        <table>
            <tr><th>Manager ID </th><th>First Name</th><th>Last Name </th><th>Title</th><th>Company</th></tr>  
            <% for (Employee myEmployee: employeeInfo) { %>
                <tr><td><%=myEmployee.getCDID()%></td><td><%=myEmployee.getFirstName()%></td><td><%=myEmployee.getLastName()%></td><td><%=myEmployee.getJobTitle()%></td><td><%=myEmployee.getCompany()%></td></tr>
            <% }%>
        </table><br>
        <%--Employee Log --%>
        <% if (request.getAttribute("employeeLogHistory") != null) { %>
        <div>Employee Log for <%=request.getParameter("employeeCdid")%></div>
        <table>
            <tr><th>Project Id</th><th>Hours Worked</th><th>Date of Work</th><th>Date Logged</th><th>Type Of Work</th></tr>
            <% List <TimeLog> myEmployee = (List<TimeLog>) request.getAttribute("employeeLogHistory"); %>
            <% for (TimeLog myEmployeeLogHistory: myEmployee) { %>
                <tr><td><%=myEmployeeLogHistory.getProjectId()%></td><td><%=myEmployeeLogHistory.getWorkedTime()%></td><td><%=myEmployeeLogHistory.getWorkedThisDate()%></td><td><%=myEmployeeLogHistory.getDateEntered()%></td><td><%=myEmployeeLogHistory.getTypeOfWork()%></td></tr>
        <% } %>
        </table><br><% } %>
        <%-- Project Log --%>
        <% if (request.getAttribute("ProjectLog") != null) { %>
        <div>Project Log for Project ID <%=request.getParameter("projectLog")%></div>
        <table>
            <tr><th>Employee ID</th><th>Hours Worked</th><th>Date of Work</th><th>Log Entered</th><th>Type Of Work</th></tr>
            <% List<TimeLog> myProject = (List <TimeLog>) request.getAttribute("ProjectLog"); %>
            <% for (TimeLog projectHistory: myProject) { %>
                <tr><td><%=projectHistory.getCdid()%></td><td><%=projectHistory.getWorkedTime()%></td><td><%=projectHistory.getWorkedThisDate()%></td><td><%=projectHistory.getDateEntered()%></td><td><%=projectHistory.getTypeOfWork()%></td></tr>
            <% } %>
        </table><br>   
        <% } %>
        <%-- Manager Past Projects --%>
        <div>Management Project History</div>
        <table>
        <tr><th>Project Id</th><th>Project Name</th></tr>
        <% List <Projects> myProjects = (List<Projects>) request.getAttribute("managerHistory"); %>
        <% for (Projects myProject: myProjects) { %>
            <tr><td><%=myProject.getProjectId()%></td><td><%=myProject.getProjectName()%></td></tr>
        <% } %>
        </table><br>
        <div> To see your personal Employee record return to the previous page</div><br>
    </body>
</html>
