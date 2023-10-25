package model;

import org.json.JSONObject;
import persistence.Writable;

// represent a user with username and password. this class also represent whether the user borrow book and which book is
// that, and also whether the user booked a study room and which study room is that.
public class User implements Writable {
    private String username;
    private String password;
    private String bookBorrowed;
    private String bookBorrowedIsbn;
    private int roomBooked;

    // REQUIRES: username and password need to be different string
    // EFFECT: construct a new user with no book borrowed and no room booked
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookBorrowed = "";
        this.bookBorrowedIsbn = "";
        this.roomBooked = -1;
    }

    // EFFECTS: determine whether the user can borrow the book
    public boolean canBorrowBook() {
        return (getBookborrowed().equals(""));
    }

    // EFFECTS: determine whether the user can book a study room
    public boolean canBookARoom() {
        return (getRoomBooked() == -1);
    }


    // setter

    public void setBookborrowed(String bookTitle) {
        this.bookBorrowed = bookTitle;
    }

    public void setBookBorrowedIsbn(String numIsbn) {
        this.bookBorrowedIsbn = numIsbn;
    }

    public void setRoomBooked(int roomID) {
        this.roomBooked = roomID;
    }




    // getter
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBookborrowed() {
        return this.bookBorrowed;
    }

    public String getBookBorrowedIsbn() {
        return this.bookBorrowedIsbn;
    }

    public int getRoomBooked() {
        return this.roomBooked;
    }

    // EFFECTS: turn User object to JSON object
    public JSONObject toJson() {
        JSONObject jsonUser = new JSONObject();
        jsonUser.put("username", username);
        jsonUser.put("password", password);
        jsonUser.put("bookBorrowed", bookBorrowed);
        jsonUser.put("bookBorrowedIsbn", bookBorrowedIsbn);
        jsonUser.put("roomBooked", roomBooked);
        return jsonUser;
    }
}
