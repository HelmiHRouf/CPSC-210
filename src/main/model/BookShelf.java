package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// this class represent lost of all book in the library. as a user, they can find book in this class based on year
// published, numISBN, and category. This class can also display all the category available to the user. As a librarian
// this class allows them to add and remove book in the bookshelf, get all book information, and get list of all
// book that is borrowed and who borrowed it.
public class BookShelf implements Writable {
    private ArrayList<Book> bookList;

    // EFFECTS: construct a new BookShelf
    public BookShelf() {
        bookList = new ArrayList<>();
    }


    // EFFECTS: return all books based on the given category
    public List<Book> findBookCategory(String pick) {
        List<Book> bookListNew = new ArrayList<>();
        for (Book book : bookList) {
            String category = book.getCategory();
            if (category.equals(pick)) {
                bookListNew.add(book);
            }
        }
        return bookListNew;
    }

    // EFFECTS: return all books based on the given years
    public List<Book> findBookYear(int year) {
        List<Book> bookListNew = new ArrayList<>();
        for (Book book : bookList) {
            int yearPublished = book.getYearPublished();
            if (yearPublished == year) {
                bookListNew.add(book);
            }
        }
        return bookListNew;
    }

    // MODIFIES: this
    // EFFECTS: add book to the BookShelf
    public void addBook(Book book) {
        bookList.add(book);
    }

    // MODIFIES: this
    // EFFECTS: remove book to the BookShelf
    public void remove(int index) {
        bookList.remove(index);
    }

    // EFFECTS: pass back all book
    public List<Book> getBookList() {
        return bookList;
    }

    // EFFECTS: get all borrowed books
    public List<Book> getBorrowedBooks() {
        List<Book> listBorrowedBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (!book.getBorrower().equals("")) {
                listBorrowedBooks.add(book);
            }
        }
        return listBorrowedBooks;
    }

    // EFFECTS: find book given isbn
    public Book findBookIsbn(String isbn) {
        for (Book book : bookList) {
            if (isbn.equals(book.getNumIsbn())) {
                return book;
            }
        }
        return null;
    }

    // EFFECTS: get all the book category
    public List<String> getBooksCategory() {
        List<String> bookTitle = new ArrayList<>();
        for (Book book : bookList) {
            bookTitle.add(book.getCategory());
        }
        bookTitle =  new ArrayList<>(new HashSet<>(bookTitle));
        return bookTitle;
    }

    // EFFECT: Decide whether the book can be borrowed
    public boolean isBookBorrowed(String isbn) {
        for (Book book : bookList) {
            if (isbn.equals(book.getNumIsbn())) {
                return book.getBorrower().equals("");
            }
        }
        return false;
    }

    // MODIFIES: this, User, Book
    // EFFECTS: borrow book from the bookshelf
    public void borrowBook(User user, String isbn) {
        Book borrowedBook = findBookIsbn(isbn);
        user.setBookborrowed(borrowedBook.getTitle());
        user.setBookBorrowedIsbn(borrowedBook.getNumIsbn());
        borrowedBook.setBorrower(user.getUsername());
    }

    // MODIFIES: this, User, Book
    // EFFECTS: return book
    public void returnBook(User user) {
        String numIsbn = user.getBookBorrowedIsbn();
        user.setBookborrowed("");
        Book book = findBookIsbn(numIsbn);
        book.setBorrower("");
        user.setBookBorrowedIsbn("");
    }


    // EFFECTS: turn BookShelf object to JSON object
    public JSONObject toJson() {
        JSONObject jsonBookShelf = new JSONObject();
        jsonBookShelf.put("bookList", bookToJson());
        return jsonBookShelf;
    }

    // EFFECTS: returns books in this bookshelf as a JSON array
    private JSONArray bookToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Book book : bookList) {
            jsonArray.put(book.toJson());
        }

        return jsonArray;
    }
}
