package Webpage;

import ClassesforBackEnd.*;
import BackEnd.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author srhodes47
 */
public class InformationDisplay extends HttpServlet {

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
        EmployeeManager myManager = new EmployeeManager();
        ProjectManager myProject = new ProjectManager();
        try (PrintWriter out = response.getWriter()) {

            if ("noManager".equals(request.getParameter("projectManager"))) {
                if (myProject.validateInput(request) == true) {
                //Employee Information
                request.setAttribute("retrieveEmployee", myManager.retrieveEmployee(request.getParameter("cdidId")));
                    
                //Employee Log History
                request.setAttribute("employeeLogHistory", myProject.getEmployeeLogHistory(request.getParameter("cdidId")));
                //Employee Project History
                request.setAttribute("employeeProjectHistory", myProject.getEmployeeProject(request.getParameter("cdidId")));
                request.getRequestDispatcher("Employee.jsp").include(request, response);
                } else {
                    request.getRequestDispatcher("Error.jsp").include(request, response);
                }
                //Project Manager
            } else {
                if (myProject.validateInput(request) == true) {
                    //Manager Information
                    request.setAttribute("retrieveEmployee", myManager.retrieveEmployee(request.getParameter("cdidId")));

                    //Manager History
                    request.setAttribute("managerHistory", myProject.getManagerHistory(request.getParameter("cdidId")));
                    request.getRequestDispatcher("ProjectManager.jsp").include(request, response);
                }
                else {
                    request.getRequestDispatcher("Error.jsp").include(request, response);
                }
            }

            out.close();
        }
        finally {
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("BackEndServlet.java");
            requestDispatcher.forward(request, response);
            myManager.closeConnection();
            myProject.closeConnection();
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
