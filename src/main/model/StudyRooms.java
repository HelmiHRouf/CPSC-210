package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represent a list of study room. As a user, they can book and cancel book of the study room. As a librarian, they can
// get all the study room information and get all the study room that is booked and who booked it.
public class StudyRooms implements Writable {
    private List<StudyRoom> listStudyRoom;


    // EFFECTS: construct a StudyRooms
    public StudyRooms() {
        listStudyRoom = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a study room in a study rooms;
    public void addStudyRoom(StudyRoom studyRoom) {
        this.listStudyRoom.add(studyRoom);
    }

    // EFFECTS: decide whether the room can be booked
    public boolean canTheRoomBooked(int roomId) {
        StudyRoom room = listStudyRoom.get(roomId);
        return (room.getBooked().equals(""));
    }

    // MODIFIES: this, User, StudyRoom
    // EFFECTS: book a study room, set the booked information, booker information, and tell who booked the room
    public void bookStudyRoom(User user, int roomId) {
        StudyRoom studyRoom = listStudyRoom.get(roomId);
        studyRoom.setBooker(user.getUsername());
        user.setRoomBooked(studyRoom);
    }

    // MODIFIES: this, User
    // EFFECTS: cancel book a study room, remove the booked information, booker information, and who booked the room
    public void cancelBookStudyRoom(User user) {
        String studyRoom = user.getRoomBooked();
        studyRoom.setBooked(false);
        studyRoom.setBooker(null);
        user.setRoomBooked(null);
    }

    // EFFECTS: return all the study room that is booked
    public List<StudyRoom> listBookedRooms() {
        List<StudyRoom> listBookedRooms = new ArrayList<>();
        for (StudyRoom room : listStudyRoom) {
            if (room.getIsBooked()) {
                listBookedRooms.add(room);
            }
        }
        return listBookedRooms;
    }

    // EFFECTS: pass all the StudyRoom
    public List<StudyRoom> getListStudyRoom() {
        return listStudyRoom;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonStudyRooms = new JSONObject();
        jsonStudyRooms.put("study rooms", studyRoomToJson());
        return jsonStudyRooms;
    }

    private JSONArray studyRoomToJson() {
        JSONArray jsonArray = new JSONArray();
        for (StudyRoom studyRoom : listStudyRoom) {
            jsonArray.put(studyRoom.toJson());
        }

        return jsonArray;
    }
}
