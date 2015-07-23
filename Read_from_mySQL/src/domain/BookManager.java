package domain;

import java.sql.*;
/**
 *
 * @author srhodes47
 */

public class BookManager {
    private final Connection conn;
    private final Statement stat;
    private ResultSet myResults;
   
    public BookManager (Connection connTemp, Statement statTemp) throws SQLException
    {
        conn = connTemp;
        stat = statTemp;
        myResults = this.stat.getResultSet(); //how the table is read in;
    }
    
    public void getList() throws SQLException
    { 
        myResults = this.stat.executeQuery("SELECT * FROM books");
        while (this.myResults.next())
            {   
                System.out.println(this.myResults.getString("id")); //column1
                System.out.println(this.myResults.getString("title")); //column2
                System.out.println(this.myResults.getString("author")); //column3
                System.out.println(this.myResults.getString("price")); //column4
            }
    }
    
    public void getDetail(int bookId) throws SQLException
    {
          myResults = this.stat.executeQuery("SELECT * FROM books WHERE id in ('" + bookId + "');");
          while (this.myResults.next())
          {
              System.out.println(this.myResults.getString("title"));
              System.out.println(this.myResults.getString("author"));
              System.out.println(this.myResults.getString("price"));
              
          }
    }
    
    public void addBook(String title, String author, double price) throws SQLException
    {
        String sql = "INSERT INTO books (title,author,price) VALUES ('" + title + "', '" + author + "', " + price + ");";
        System.out.println("Sql = " + sql);
        this.stat.executeUpdate(sql);
    }
    
    public void updateBook(String title, String author, double price) throws SQLException
    {
        String sql = "UPDATE books SET title ='"  + title + "', author= '" + author + "' WHERE price = " + price;
        System.out.println("Sql = " + sql);
        this.stat.executeUpdate(sql);
    }   
}
