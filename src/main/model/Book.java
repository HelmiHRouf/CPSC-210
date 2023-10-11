package model;

public class Book {
    private String title;
    private int yearPublished;
    private String category;
    private int numIsbn;
    private boolean isBorrowed;
    private User borrower;

    // EFFECTS: construct a new Book that is not borrowed yet.
    public Book(String title, int yearPublished, String category, int numIsbn) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.category = category;
        this.numIsbn = numIsbn;
        this.isBorrowed = false;
        this.borrower = null;
    }

    // Setter
    public void setBorrowed(Boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    public void setBorrower(User user) {
        this.borrower = user;
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

    public int getNumIsbn() {
        return numIsbn;
    }

    public Boolean getIsBorrowed() {
        return isBorrowed;
    }

    public User getBorrower() {
        return borrower;
    }
}
