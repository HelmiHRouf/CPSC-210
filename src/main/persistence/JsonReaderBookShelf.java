package persistence;

import model.Book;
import model.BookShelf;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads BookShelf from JSON data stored in file
// part of this code were cited from JsonSerializationDemo in CPSC 210 course
public class JsonReaderBookShelf {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBookShelf(String source) {
        this.source = source;
    }

    // EFFECTS: reads BookShelf from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookShelf read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookShelf(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses BookShelf from JSON object and returns it
    private BookShelf parseBookShelf(JSONObject jsonObject) {
        BookShelf bookShelf = new BookShelf();
        addBooks(bookShelf, jsonObject);
        return bookShelf;
    }

    // MODIFIES: bookShelf
    // EFFECTS: parses listStudyRoom from JSON object and adds them to StudyRooms
    private void addBooks(BookShelf bookShelf, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("bookList");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bookShelf, nextBook);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses StudyRoom from JSON object and adds it to StudyRooms
    private void addBook(BookShelf bookShelf, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int yearPublished = jsonObject.getInt("year published");
        String category = jsonObject.getString("category");
        String numIsbn = jsonObject.getString("num ISBN");
        String borrower = jsonObject.getString("borrower");
        Book book = new Book(title, yearPublished, category, numIsbn);
        book.setBorrower(borrower);
        bookShelf.addBook(book);
    }

//    public Book getBook(JSONObject jsonObject) {
//        String title = jsonObject.getString("title");
//        int yearPublished = jsonObject.getInt("year published");
//        String category = jsonObject.getString("category");
//        String numIsbn = jsonObject.getString("num ISBN");
//        String borrower = jsonObject.getString("borrower");
//        Book book = new Book(title, yearPublished, category, numIsbn);
//        book.setBorrower(borrower);
//        return book;
//    }
}
