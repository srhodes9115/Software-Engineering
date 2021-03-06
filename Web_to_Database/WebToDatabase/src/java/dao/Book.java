package dao;

/**
 *
 * @author srhodes47
 */
public class Book {
    private String bookId; //right click, refractor, encapsulate elements
    private String title; //generates automatic methods
    private String author;
    private String price;
    
    public Book (String bookID_temp, String title_temp, String author_temp, String price_temp)
    {
        bookId = bookID_temp;
        title = title_temp;
        author = author_temp;
        price = price_temp;
    }
    /**
     * @return the bookId
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * @param bookId the bookId to set
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }
    
}
