package model;

import org.json.JSONObject;
import persistence.Writable;

// repreesnt a study room that has room ID, also contain field that represent whether this study room is booked and who
// booked it.
public class StudyRoom implements Writable {
    private String roomID;
    private boolean isBooked;
    private User booker;

    // EFFECTS: construct a Study Room with roomID as an index, no one Booked and not booked yet
    public StudyRoom(String roomID) {
        this.roomID = roomID;
        this.isBooked = false;
        this.booker = null;
    }

    // setter

    // MODIFIES: this
    // EFFECTS: set book with the given boolean
    public void setBooked(boolean booked) {
        this.isBooked = booked;
    }

    // MODIFIES: this
    // EFFECTS: set booker with the given user
    public void setBooker(User booker) {
        this.booker = booker;
    }

    // getter

    public String getRoomID() {
        return this.roomID;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    public User getBooked() {
        return this.booker;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonStudyRoom = new JSONObject();
        jsonStudyRoom.put("room ID", roomID);
        jsonStudyRoom.put("isBooked", isBooked);
        jsonStudyRoom.put("booker", booker.toJson());
        return jsonStudyRoom;
    }
}
