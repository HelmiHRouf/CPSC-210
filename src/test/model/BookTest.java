package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book testBook;

    // test constructor and setter getter only

    @Test
    void testConstructor() {
        testBook = new Book("Fundamental Astronomy", 2000, "scientific", 10201010);

        assertEquals("Fundamental Astronomy", testBook.getTitle());
        assertEquals(2000, testBook.getYearPublished());
        assertEquals("scientific", testBook.getCategory());
        assertEquals(10201010, testBook.getNumIsbn());
        assertFalse(testBook.getIsBorrowed());
        assertNull(testBook.getBorrower());

        testBook.setBorrowed(true);
        assertTrue(testBook.getIsBorrowed());
    }
}
