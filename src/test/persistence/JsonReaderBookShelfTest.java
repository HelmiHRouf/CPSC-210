package persistence;

import model.BookShelf;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderBookShelfTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderBookShelf reader = new JsonReaderBookShelf("./data/noSuchFile.json");
        try {
            BookShelf bookShelf = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookShelf() {
        JsonReaderBookShelf reader = new JsonReaderBookShelf("./data/testReaderBookShelfEmpty.json");
        BookShelf testBookShelf = new BookShelf();
        try {
            BookShelf bookShelf = reader.read();
            assertEquals(testBookShelf.getBookList(), bookShelf.getBookList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookShelf() {
        JsonReaderBookShelf reader = new JsonReaderBookShelf("./data/testReaderBookShelfGeneral.json");
        BookShelf testBookShelf = new BookShelf();
        try {
            BookShelf bookShelf = reader.read();
            assertTrue(testBookShelf.getBookList().size() != bookShelf.getBookList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
