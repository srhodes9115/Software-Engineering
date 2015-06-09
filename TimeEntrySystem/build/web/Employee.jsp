<%-- 
    Document   : Employee
    Created on : Jun 20, 2014, 3:08:25 PM
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
        <title>Employee Time Entry</title>
    </head>
    <body>
        <%--Declarations --%>
        <%!EmployeeManager myManager = new EmployeeManager(); %>
        <%! Employee myEmployee; %>
        <%! ProjectManager myProject = new ProjectManager(); %>
        <%! java.util.Date utilDate = new java.util.Date(); %>
        <%! java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); %>
        <%--Employee --%>
        <%--Employee Information--%>
        <h1>Here is the Employee Information for <%=request.getParameter("cdidId")%></h1> 
        <div>Employee Information: </div>
        <% List <Employee> employeeInfo = (List <Employee>) request.getAttribute("retrieveEmployee"); %>
        <table>
            <tr><th>Employee ID </th><th>First Name</th><th>Last Name </th><th>Title</th><th>Company</th></tr>
            <% for (Employee myEmployee: employeeInfo) { %>
                <tr><td><%=myEmployee.getCDID()%></td><td><%=myEmployee.getFirstName()%></td><td><%=myEmployee.getLastName()%></td><td><%=myEmployee.getJobTitle()%></td><td><%=myEmployee.getCompany()%></td></tr>
            <% } %>
        </table><br>
        <%--Employee Current Log--%>
        <% if (request.getAttribute("typeOfWork") != null) { %>
        <div>Current Log:</div>
        <table>
            <tr><th>Employee Id</th><th>Project Id</th><th>Hours Worked</th><th>Date of Work</th><th>Date Logged</th><th>Type Of Work</th></tr>
            <tr><td><%=request.getParameter("cdidId")%></td><td><%=request.getParameter("projectId")%></td><td><%=request.getParameter("hours")%></td><td><%=request.getParameter("workDate")%></td><td><%=sqlDate%></td><td><%=request.getAttribute("typeOfWork")%></td></tr>
        </table><br><% } %>
        <%--Employee Log History --%>
        <div>Log History</div>
        <table>
        <tr><th>Project Id</th><th>Hours Worked</th><th>Date of Work</th><th>Date Logged</th><th>Type Of Work</th></tr>
        <% List <TimeLog> employeeLogHistory = (List<TimeLog>) request.getAttribute("employeeLogHistory"); %>
        <% for (TimeLog myEmployeeLogHistory: employeeLogHistory) { %>
            <tr><td><%=myEmployeeLogHistory.getProjectId()%></td><td><%=myEmployeeLogHistory.getWorkedTime()%></td><td><%=myEmployeeLogHistory.getWorkedThisDate()%></td><td><%=myEmployeeLogHistory.getDateEntered()%></td><td><%=myEmployeeLogHistory.getTypeOfWork()%></td></tr>
        <% } %>
        </table><br>
        
        <%--Project History --%>
        <div>Project History </div>
        <table>
        <tr><th>Project ID</th><th>Project Name</th><th>Project Manager ID</th></tr>
        <% List <Projects> projectLog = (List<Projects>) request.getAttribute("employeeProjectHistory"); %>
        <% for (Projects myProject: projectLog) { %>
            <tr><td><%=myProject.getProjectId()%></td><td><%=myProject.getProjectName()%></td><td><%=myProject.getProjectManager()%></td></tr>
        <% } %>
        </table>
        
    </body>
</html>
