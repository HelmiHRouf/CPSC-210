package model;

import org.json.JSONObject;
import persistence.Writable;

// repreesnt a study room that has room ID, also contain field that represent whether this study room is booked and who
// booked it.
public class StudyRoom implements Writable {
    private int roomID;
    //private boolean isBooked;
    private String booker;

    // EFFECTS: construct a Study Room with roomID as an index, no one Booked and not booked yet
    public StudyRoom(int roomID) {
        this.roomID = roomID;
        this.booker = "";
    }

    // MODIFIES: this
    // EFFECTS: set booker with the given user
    public void setBooker(String username) {
        this.booker = username;
    }

    // getter

    public int getRoomID() {
        return this.roomID;
    }


    public String getBooked() {
        return this.booker;
    }

    // EFFECTS: turn StudyRoom object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonStudyRoom = new JSONObject();
        jsonStudyRoom.put("room ID", roomID);
        jsonStudyRoom.put("booker", booker);
        return jsonStudyRoom;
    }
}
