package persistence;

import model.StudyRooms;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderStudyRoomsTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderStudyRooms reader = new JsonReaderStudyRooms("./data/noSuchFile.json");
        try {
            StudyRooms studyRooms = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyStudyRooms() {
        JsonReaderStudyRooms reader = new JsonReaderStudyRooms("./data/testReaderStudyRoomsEmpty.json");
        StudyRooms studyRooms = new StudyRooms();
        try {
            StudyRooms testStudyRooms = reader.read();
            assertEquals(studyRooms.getListStudyRoom().size(), testStudyRooms.getListStudyRoom().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudyRooms() {
        JsonReaderStudyRooms reader = new JsonReaderStudyRooms("./data/testReaderStudyRoomsGeneral.json");
        StudyRooms studyRooms = new StudyRooms();
        try {
            StudyRooms testStudyRooms = reader.read();
            assertTrue(studyRooms.getListStudyRoom().size() != testStudyRooms.getListStudyRoom().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
