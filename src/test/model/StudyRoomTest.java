package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudyRoomTest {
    private StudyRoom studyRoom;
    private User user;
    // test constructor, setter and getter only.

    @Test
    void testConstructor() {
        studyRoom = new StudyRoom(0);
        assertEquals(0, studyRoom.getRoomID());
        assertEquals("", studyRoom.getBooked());
    }

    @Test
    void testsetBookedAndBooker() {
        user = new User("aaa", "bbb");
        String userUsername = user.getUsername();
        studyRoom = new StudyRoom(1);
        assertEquals(1, studyRoom.getRoomID());
        assertEquals("", studyRoom.getBooked());

        studyRoom.setBooker(user.getUsername());

        assertEquals(user.getUsername(), studyRoom.getBooked());

        studyRoom.setBooker("");
        assertEquals("", studyRoom.getBooked());
    }
}
