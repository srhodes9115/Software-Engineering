package BackEnd;

import ClassesforBackEnd.EmployeeManager;
import ClassesforBackEnd.JsonManager;
import ClassesforBackEnd.ProjectManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.simple.JSONObject;

/**
 *
 * @author srhodes47
 */
public class BackEndServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String employee1 = "http://localhost:8084/TimeEntrySystem/InformationDisplay?cdidId=alextimb&projectManager=noManager&employeeCdid=smukhia&projectLog=2&projectName=&createNew=noNew&projectId=3&hours=6&workDate=2011-02-22&typeOfWork=requirement"; 
            String employee2 = "http://localhost:8084/TimeEntrySystem/InformationDisplay?cdidId=smukhia&projectManager=noManager&employeeCdid=smukhia&projectLog=2&projectName=&createNew=noNew&projectId=3&hours=6&workDate=2011-02-22&typeOfWork=requirement";
            String manager1 = "http://localhost:8084/TimeEntrySystem/InformationDisplay?cdidId=agarg76&projectManager=yesManager&employeeCdid=smukhia&projectLog=2&projectName=&createNew=noNew&projectId=3&hours=6&workDate=2011-02-22&typeOfWork=requirement";
            String manager2 = "http://localhost:8084/TimeEntrySystem/InformationDisplay?cdidId=erebao&projectManager=yesManager&employeeCdid=vasantraj1&projectLog=3&projectName=&createNew=noNew&projectId=3&hours=6&workDate=2011-02-22&typeOfWork=requirement";
            
            System.out.println(request.getParameter("cdidId"));
            URL aol = new URL(manager1);
            HttpURLConnection open = (HttpURLConnection) aol.openConnection();

            BufferedReader linebyline = new BufferedReader(new InputStreamReader(open.getInputStream()));

            String output;
            StringBuilder text = new StringBuilder();

            while ((output = linebyline.readLine()) != null) {
                text.append(output);
            }

            JsonManager myJson = new JsonManager();
            EmployeeManager manageEmployee = new EmployeeManager();
            ProjectManager manageManager = new ProjectManager();
            
            //Employee Information
            /*
            JSONObject employee = new JSONObject();
            employee.put("Employee Information: ",myJson.htmlToJavaEmployee(text.toString()));
            employee.put("Log History: ",myJson.htmlToJavaLogHistory(text.toString()));
            employee.put("Project History: ",myJson.htmlToJsonProjectHistory(text.toString()));
            out.println(employee);*/
            
            //Project Information
            JSONObject manager = new JSONObject();
            manager.put("Project Manager Information: ", myJson.htmlToJavaEmployee(text.toString()));
            manager.put("Requested Employee Log: ",myJson.htmlToJsonEmployeeLog(text.toString()));
            manager.put("Requested Project Log: ",myJson.htmlToJsonProjectId(text.toString()));
            manager.put("Management History: ",myJson.htmlToJsonManagementHistory(text.toString()));
            out.println(manager);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
