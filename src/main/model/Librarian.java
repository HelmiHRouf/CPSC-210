package model;

import org.json.JSONObject;
import persistence.Writable;

// represent librarian that has username and password
public class Librarian implements Writable {
    private String username;
    private String password;

    // REQUIRES: username and password need to be different string
    // EFFECT: construct a new librarian
    public Librarian(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getter
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // EFFECTS: turn Librarian object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonLibrarian = new JSONObject();
        jsonLibrarian.put("username", username);
        jsonLibrarian.put("password", password);
        return jsonLibrarian;
    }
}
