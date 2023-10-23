package persistence;

import model.Book;
import model.BookShelf;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterBookShelfTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriterBookShelf writer = new JsonWriterBookShelf("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookShelf() {
        try {
            BookShelf bookShelf = new BookShelf();
            JsonWriterBookShelf writer = new JsonWriterBookShelf("./data/testWriterBookShelfEmpty.json");
            writer.open();
            writer.write(bookShelf);
            writer.close();

            JsonReaderBookShelf reader = new JsonReaderBookShelf("./data/testWriterBookShelfEmpty.json");
            BookShelf testBookShelf = reader.read();
            assertEquals(testBookShelf.getBookList().size(), bookShelf.getBookList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookShelf() {
        try {
            BookShelf bookShelf = new BookShelf();
            bookShelf.addBook(new Book("Fundamental Astronomy", 2000, "scientific",
                    "10201010"));
            bookShelf.addBook(new Book("I hope I can fly", 2010, "fiction",
                    "21421282"));
            JsonWriterBookShelf writer = new JsonWriterBookShelf("./data/testWriterBookShelfGeneral.json");
            writer.open();
            writer.write(bookShelf);
            writer.close();

            JsonReaderBookShelf reader = new JsonReaderBookShelf("./data/testWriterBookShelfGeneral.json");
            BookShelf testBookShelf = reader.read();
            assertEquals(2 , testBookShelf.getBookList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
