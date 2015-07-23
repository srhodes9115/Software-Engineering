
package read_from_mysql;

import domain.Book; //import other object class
import domain.BookManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author srhodes47
 */
public class Read_from_mySQL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection conn = null;
        Statement stat;
        try 
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance(); //always need this to access driver
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore?user=root"); //connects to database
            stat = (Statement) conn.createStatement(); //statements is for sql
            
            Book myBook = new Book("Great Expectations","Charles Dickens",6.99); //object created
            BookManager mybookmanager = new BookManager(conn,stat);
         
            mybookmanager.getDetail(1);
            mybookmanager.getList();
            mybookmanager.addBook("To Kill A Mockingbird", "Harper Lee", 5.99);
            mybookmanager.updateBook("Lord of the Rings","JR Tolkein", 7.99); //changes the book by the price
 
        }
        
        catch (Exception e) {
            System.out.println("error = " + e.getMessage()); // prints error message
        }
        
        finally {
            try {
                conn.close();
                stat = null;
            } catch (SQLException ex) {
                Logger.getLogger(Read_from_mySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
