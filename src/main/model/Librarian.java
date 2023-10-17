package model;

// represent librarian that has username and password
public class Librarian {
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
}
