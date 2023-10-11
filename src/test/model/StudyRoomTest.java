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
        assertFalse(studyRoom.getIsBooked());
        assertEquals(null, studyRoom.getBooked());
    }

    @Test
    void testsetBookedAndBooker() {
        user = new User("aaa", "bbb");
        studyRoom = new StudyRoom(1);
        assertEquals(1, studyRoom.getRoomID());
        assertFalse(studyRoom.getIsBooked());
        assertEquals(null, studyRoom.getBooked());

        studyRoom.setBooked(true);
        assertTrue(studyRoom.getIsBooked());

        studyRoom.setBooker(user);
        assertEquals(user, studyRoom.getBooked());

        studyRoom.setBooker(null);
        assertEquals(null, studyRoom.getBooked());
    }
}
