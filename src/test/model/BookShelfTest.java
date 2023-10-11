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
        book1 = new Book("Fundamental Astronomy", 2000, "scientific", 10201010);
        book2 = new Book("I hope I can fly", 2010, "fiction", 21421282);
        book3 = new Book("World War 2 Explained", 1990, "history", 13348593);
        book4 = new Book("Elementary C++", 1992, "scientific", 23172564);
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
        book2.setBorrowed(true);
        book4.setBorrowed(true);
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book4);

        List<Book> testBooks = new ArrayList<>();
        testBooks.add(book2);
        testBooks.add(book4);

        assertEquals(testBooks, testBookShelf.getBorrowedBooks());
        book2.setBorrowed(false);
        testBooks.remove(0);

        assertEquals(testBooks, testBookShelf.getBorrowedBooks());
    }

    @Test
    void testFindBookIsbn() {
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book4);

        assertEquals(book1, testBookShelf.findBookIsbn(10201010));
        assertEquals(book4, testBookShelf.findBookIsbn(23172564));
        assertEquals(null, testBookShelf.findBookIsbn(23172));
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
        assertFalse(testBookShelf.isBookBorrowed(92819182));
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);
        assertTrue(testBookShelf.isBookBorrowed(21421282));
        assertTrue(testBookShelf.isBookBorrowed(10201010));
        assertTrue(testBookShelf.isBookBorrowed(13348593));
        book3.setBorrowed(true);
        assertFalse(testBookShelf.isBookBorrowed(13348593));
        assertFalse(testBookShelf.isBookBorrowed(100302101));
    }

    @Test
    void testBorrowAndReturnBook() {
        testBookShelf.addBook(book1);
        testBookShelf.addBook(book2);
        testBookShelf.addBook(book3);

        testBookShelf.borrowBook(user1, 13348593);
        assertEquals(book3, user1.getBookborrowed());
        assertTrue(book3.getIsBorrowed());

        testBookShelf.borrowBook(user2, 10201010);
        assertEquals(book1, user2.getBookborrowed());
        assertTrue(book1.getIsBorrowed());
        assertFalse(book2.getIsBorrowed());

        testBookShelf.returnBook(user1);
        assertNull(user1.getBookborrowed());
        assertFalse(book3.getIsBorrowed());

        testBookShelf.returnBook(user2);
        assertNull(user2.getBookborrowed());
        assertFalse(book1.getIsBorrowed());
        assertFalse(book2.getIsBorrowed());
    }

}
