package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudyRoomsTest {
    private StudyRooms testStudyRooms;
    private StudyRoom studyRoom1;
    private StudyRoom studyRoom2;
    private StudyRoom studyRoom3;
    private StudyRoom studyRoom4;
    private User user1;
    private User user2;

    @BeforeEach
    void runBefore() {
        testStudyRooms = new StudyRooms();
        studyRoom1 = new StudyRoom(01);
        studyRoom2 = new StudyRoom(02);
        studyRoom3 = new StudyRoom(03);
        studyRoom4 = new StudyRoom(04);
        user1 = new User("aaa", "bbb");
        user2 = new User("ccc", "ddd");
    }
    // make 7 test

    @Test
    void testAddStudyRoom() {
        assertEquals(0, testStudyRooms.getListStudyRoom().size());
        testStudyRooms.addStudyRoom(studyRoom1);
        testStudyRooms.addStudyRoom(studyRoom2);
        testStudyRooms.addStudyRoom(studyRoom3);
        assertEquals(studyRoom1, testStudyRooms.getListStudyRoom().get(0));
        assertEquals(studyRoom2, testStudyRooms.getListStudyRoom().get(1));
        assertEquals(studyRoom3, testStudyRooms.getListStudyRoom().get(2));
        assertEquals(3, testStudyRooms.getListStudyRoom().size());
    }

}
