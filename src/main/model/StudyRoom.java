package model;

public class StudyRoom {
    private int roomID;
    private boolean isBooked;
    private User booker;

    // EFFECTS: construct a Study Room with roomID as an index, no one Booked and not booked yet
    public StudyRoom(int roomID) {
        this.roomID = roomID;
        this.isBooked = false;
        this.booker = null;
    }

    // setter
    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    public void setBooker(User booker) {
        this.booker = booker;
    }

    // getter

    public int getRoomID() {
        return this.roomID;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public User getBooked() {
        return this.booker;
    }
}
