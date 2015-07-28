package dao;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author srhodes47
 */
public class BookManager {

    private Connection conn;
    private Statement stat;
    private ResultSet myResults = null;
    private Boolean isConnected = false;
    private ArrayList myArray;

    public ArrayList getArray() { //method w/o HTML can be used by other classes
        myArray = new ArrayList();
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            myResults = this.stat.executeQuery("SELECT * FROM books");
            while (this.myResults.next()) {
                Book myBook = new Book(this.myResults.getString("id"), this.myResults.getString("title"), this.myResults.getString("author"), this.myResults.getString("price"));
                myArray.add(myBook);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return myArray;
    }

    public String getList() {
        StringBuilder sb = new StringBuilder(); //way to write to server output, can't use System.out.println
        try {
            if (isConnected == false) { //check Connection
                Connection("open");
            }
            myResults = this.stat.executeQuery("SELECT * FROM books");
            while (this.myResults.next()) { //scans database
                sb.append("<tr><td>").append(this.myResults.getString("id")).append("</td><td>").append(this.myResults.getString("title")).append("</td><td>").append(this.myResults.getString("author")).append("</td><td>").append(this.myResults.getString("price")).append("</tr>");
                //make a string of the row in the table
            }
        } catch (Exception ex) { //use try catch structure instead of throwing exceptions
            System.out.println(ex.getMessage());
            sb.append(("Error executing SQL"));
        }
        return sb.toString();
    }

    public String getDetail(int bookId) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isConnected == false) {
                Connection("open");
            }
            myResults = this.stat.executeQuery("SELECT * FROM books WHERE id in ('" + bookId + "');");

            while (this.myResults.next()) {
                sb.append("<tr><td>").append(this.myResults.getString("title")).append(", " + "</td><td>").append(this.myResults.getString("author")).append(", " + "</td><td>").append(this.myResults.getString("price")).append("." + "</tr>");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return sb.toString();
    }

    public void addBook(String title, String author, double price) {
        try {
            if (isConnected == false) {
                Connection("open");
            }
            String sql = "INSERT INTO books (title,author,price) VALUES ('" + title + "', '" + author + "', " + price + ");";
            this.stat.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateBook(String title, String author, double price) {
        try {
            if (isConnected == false) {
                Connection("open");
            }
            //syntax of sql must surround variables with 'x', good coding to print the string
            String sql = "UPDATE books SET title ='" + title + "', author= '" + author + "' WHERE price = " + price;
            this.stat.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void Connection(String doWhat) {
        try {
            if (doWhat.equalsIgnoreCase("open")) {

                Class.forName("com.mysql.jdbc.Driver").newInstance(); //always need this to access driver
                this.conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookstore?user=root"); //connects to database
                this.stat = this.conn.createStatement();
                isConnected = true;
            } else {
                this.conn.close();
                isConnected = false;
            }
        } catch (Exception ex) {
            System.out.println("Connection error:" + ex.getMessage());
        }
    }
}
