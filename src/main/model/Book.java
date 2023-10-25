package model;

import org.json.JSONObject;
import persistence.Writable;

// represent a book with parameter title, year published, category, and numISBN.
// the book also has field that represent is the book borrowed and who borrow the book
public class Book implements Writable {
    private String title;
    private int yearPublished;
    private String category;
    private String numIsbn;
    private String borrower;

    // EFFECTS: construct a new Book that is not borrowed yet.
    public Book(String title, int yearPublished, String category, String numIsbn) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.category = category;
        this.numIsbn = numIsbn;
        this.borrower = "";
    }

    /// Setter
    // MODIFIES: this
    // EFFECTS: set the borrowed book with given boolean.
//    public void setBorrowed(Boolean borrowed) {
//        this.isBorrowed = borrowed;
//    }

    // MODIFIES: this
    // EFFECTS: set the borrowed with given User.
    public void setBorrower(String username) {
        this.borrower = username;
    }


    // Getter
    public String getTitle() {
        return this.title;
    }

    public int getYearPublished() {
        return this.yearPublished;
    }

    public String getCategory() {
        return this.category;
    }

    public String getNumIsbn() {
        return numIsbn;
    }


    public String getBorrower() {
        return borrower;
    }

    // EFFECTS: return Book object to JSON object
    public JSONObject toJson() {
        JSONObject jsonBook = new JSONObject();
        jsonBook.put("title", title);
        jsonBook.put("year published", yearPublished);
        jsonBook.put("category", category);
        jsonBook.put("num ISBN", numIsbn);
        jsonBook.put("borrower", borrower);
        return jsonBook;
    }
}
