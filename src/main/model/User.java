package model;

public class User {
    private String username;
    private String password;
    private Book bookBorrowed;
    private StudyRoom roomBooked;

    // REQUIRES: username and password need to be different string
    // EFFECT: construct a new user with no book borrowed and no room booked
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookBorrowed = null;
        this.roomBooked = null;
    }

    // EFFECTS: determine whether the user can borrow the book
    public boolean canBorrowBook() {
        return (getBookborrowed() == null);
    }

    // EFFECTS: determine whether the user can book a study room
    public boolean canBookARoom() {
        return (getRoomBooked() == null);
    }


    // setter

    public void setBookborrowed(Book book) {
        this.bookBorrowed = book;
    }

    public void setRoomBooked(StudyRoom studyRoom) {
        this.roomBooked = studyRoom;
    }




    // getter
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Book getBookborrowed() {
        return this.bookBorrowed;
    }

    public StudyRoom getRoomBooked() {
        return this.roomBooked;
    }
}
