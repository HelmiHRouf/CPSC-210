package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReaderAccounts {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAccounts(String source) {
        this.source = source;
    }

    // EFFECTS: reads Accounts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Accounts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccounts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses StudyRooms from JSON object and returns it
    private Accounts parseAccounts(JSONObject jsonObject) {
        Accounts accounts = new Accounts();
        addAccounts(accounts, jsonObject);
        return accounts;
    }

    // MODIFIES: studyRooms
    // EFFECTS: parses listStudyRoom from JSON object and adds them to StudyRooms
    private void addAccounts(Accounts accounts, JSONObject jsonObject) {
        JSONArray jsonArrayUser = jsonObject.getJSONArray("user");
        JSONArray jsonArrayLibrarian = jsonObject.getJSONArray("librarian");
        for (Object json : jsonArrayUser) {
            JSONObject nextUser = (JSONObject) json;
            addUser(accounts, nextUser);
        }
        for (Object json : jsonArrayLibrarian) {
            JSONObject nextLibrarian = (JSONObject) json;
            addLibrarian(accounts, nextLibrarian);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses StudyRoom from JSON object and adds it to StudyRooms
    private void addUser(Accounts accounts, JSONObject jsonObject) {
        User user = getUser(jsonObject);
        accounts.addUser(user.getUsername(), user.getPassword());
        int userIndex = accounts.loginUserIndex(user.getUsername(), user.getPassword());
        User userPulled = accounts.getUser(userIndex);
        userPulled.setBookborrowed(user.getBookborrowed());
        userPulled.setRoomBooked((user.getRoomBooked()));
    }

    public User getUser(JSONObject jsonObject) {
        Book bookBorrowed;
        StudyRoom roomBooked;
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (jsonObject.isNull("bookBorrowed")) {
            bookBorrowed = null;
        } else {
            bookBorrowed = getBook(jsonObject.getJSONObject("bookBorrowed"));
        }
        if (jsonObject.isNull("roomBooked")) {
            roomBooked = null;
        } else {
            roomBooked = getStudyRoom(jsonObject.getJSONObject("roomBooked"));
        }
        User user = new User(username, password);
        user.setRoomBooked(roomBooked);
        user.setBookborrowed(bookBorrowed);
        return user;
    }

    // MODIFIES: wr
    // EFFECTS: parses StudyRoom from JSON object and adds it to StudyRooms
    private void addLibrarian(Accounts accounts, JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        accounts.addLibrarian(username, password);
    }
}
