package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookShelfTest {
    private BookShelf testBookShelf;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private User user1;
    private User user2;


    @BeforeEach
    void runBefore() {
        testBookShelf = new BookShelf();
        book1 = new Book("Fundamental Astronomy", 2000, "scientific", "10201010");
        book2 = new Book("I hope I can fly", 2010, "fiction", "21421282");
        book3 = new Book("World War 2 Explained", 1990, "history", "13348593");
        book4 = new Book("Elementary C++", 1992, "scientific", "23172564");
        user1 = new User("joe", "111");
        user2 = new User("joa", "222");
    }
    // do 12 test


    @Test
    void testConstructor() {
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        assertEquals(2, testBookShelf.getBookList().size());
        testBookShelf.remove(0);
        assertEquals(1, testBookShelf.getBookList().size());
        testBookShelf.remove(0);
        assertEquals(0, testBookShelf.getBookList().size());
    }

    @Test
    void testFindBookCategory() {
        List<Book> testList = new ArrayList<>();
        assertEquals(testList, testBookShelf.findBookCategory("kakaka"));
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);
        testBookShelf.addBook(book4);

        testList.add(book1);
        testList.add(book4);
        assertEquals(testList, testBookShelf.findBookCategory("scientific"));
    }

    @Test
    void testFindBookYear() {
        List<Book> testList = new ArrayList<>();
        assertEquals(testList, testBookShelf.findBookYear(2020));
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);

        testList.add(book1);

        List<Book> testList2 = new ArrayList<>();
        assertEquals(testList, testBookShelf.findBookYear(2000));
        assertEquals(testList2, testBookShelf.findBookYear(1000));

    }

    @Test
    void testBookBorrowed() {
        book2.setBorrower(user1.getUsername());
        book4.setBorrower(user2.getUsername());
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);
        testBookShelf.addBook(book4);

        List<Book> testBooks = new ArrayList<>();
        testBooks.add(book2);
        testBooks.add(book4);

        assertEquals(testBooks, testBookShelf.getBorrowedBooks());
        book2.setBorrower("");
        testBooks.remove(0);

        assertEquals(testBooks, testBookShelf.getBorrowedBooks());
    }

    @Test
    void testFindBookIsbn() {
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book4);

        assertEquals(book1, testBookShelf.findBookIsbn("10201010"));
        assertEquals(book4, testBookShelf.findBookIsbn("23172564"));
        assertEquals(null, testBookShelf.findBookIsbn("23172"));
    }

    @Test
    void testGetBooksCategory() {
        List<Book> testlist = new ArrayList<>();
        assertEquals(testlist, testBookShelf.getBooksCategory());

        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);

        List<String> testStrings = new ArrayList<>();
        testStrings.add("fiction");
        testStrings.add("scientific");
        testStrings.add("history");

        assertEquals(testStrings, testBookShelf.getBooksCategory());

        testBookShelf.addBook(book4);
        assertEquals(testStrings, testBookShelf.getBooksCategory());
    }

    @Test
    void testIsBookBorrowed() {
        assertFalse(testBookShelf.isBookBorrowed("92819182"));
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);
        assertTrue(testBookShelf.isBookBorrowed("21421282"));
        assertTrue(testBookShelf.isBookBorrowed("10201010"));
        assertTrue(testBookShelf.isBookBorrowed("13348593"));
        book3.setBorrower(user1.getUsername());
        assertFalse(testBookShelf.isBookBorrowed("13348593"));
        assertFalse(testBookShelf.isBookBorrowed("100302101"));
    }

    @Test
    void testBorrowAndReturnBook() {
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);

        testBookShelf.borrowBook(user1, "13348593");
        assertEquals(book3.getTitle(), user1.getBookborrowed());
        assertEquals(book3.getBorrower(), user1.getUsername());
        assertEquals(book3.getNumIsbn(), user1.getBookBorrowedIsbn());


        testBookShelf.borrowBook(user2, "10201010");
        assertEquals(book1.getTitle(), user2.getBookborrowed());
        assertEquals(book1.getBorrower(), user2.getUsername());
        assertEquals(book1.getNumIsbn(), user2.getBookBorrowedIsbn());

        testBookShelf.returnBook(user1);
        assertEquals(user1.getBookborrowed(), "");
        assertEquals(user1.getBookBorrowedIsbn(), "");
        assertEquals(book3.getBorrower(), "");

        testBookShelf.returnBook(user2);
        assertEquals(user2.getBookborrowed(), "");
        assertEquals(user2.getBookBorrowedIsbn(), "");
        assertEquals(book1.getBorrower(), "");
    }

}
