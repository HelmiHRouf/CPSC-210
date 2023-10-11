package model;

public class Book {
    private String title;
    private int yearPublished;
    private String category;
    private int numIsbn;
    private boolean isBorrowed;

    // EFFECTS: construct a new Book that is not borrowed yet.
    public Book(String title, int yearPublished, String category, int numIsbn) {
        this.title = title;
        this.yearPublished = yearPublished;
        this.category = category;
        this.numIsbn = numIsbn;
        this.isBorrowed = false;
    }

    // Setter
    public void setBorrowed(Boolean borrowed) {
        this.isBorrowed = borrowed;
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
}
