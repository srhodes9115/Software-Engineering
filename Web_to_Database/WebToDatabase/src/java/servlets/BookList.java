package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookManager;
import dao.Book;

/**
 *
 * @author srhodes47
 */
@WebServlet(name = "BookList", urlPatterns = {"/BookList"})
public class BookList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            BookManager bkManager = new BookManager();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table><table border=\"1\" style=\"width:300px\">");
            out.println("GET ARRAY TABLE:<br> ");
            out.println("<tr><th>ID</th><th>TITLE</th><th>AUTHOR</th><th>PRICE</th></tr>"); //header row
            Book myBook;
            for (int count = 0; count < bkManager.getArray().size(); count++) {
                myBook = (Book) bkManager.getArray().get(count);
                out.println("<tr><td>" + myBook.getBookId() + "</td><td>" + myBook.getTitle() + "</td><td>" + myBook.getAuthor() + "</td><td>" + myBook.getPrice()+ "</td></tr>");
            }
            out.println("</table><table> GET LIST TABLE: <br>");
            out.println("<tr><th>ID</th><th>TITLE</th><th>AUTHOR</th><th>PRICE</th></tr>");
            out.println(bkManager.getList());
            out.println("</table");
            out.println("<tr><th>GET DETAILS: </tr></th>");
            out.println(bkManager.getDetail(3));
            out.println("<br></table>");
            //bkManager.addBook("To Kill A Mockingbird", "Harper Lee", 5.99);
            //bkManager.updateBook("Lord of the Rings","JR Tolkein", 7.99); 
            out.println("</body>");
            out.println("</html>");
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
