package persistence;

import model.StudyRoom;
import model.StudyRooms;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterStudyRoomsTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriterStudyRooms writer = new JsonWriterStudyRooms("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStudyRooms() {
        try {
            StudyRooms studyRooms = new StudyRooms();
            JsonWriterStudyRooms writer = new JsonWriterStudyRooms("./data/testWriterStudyRoomsEmpty.json");
            writer.open();
            writer.write(studyRooms);
            writer.close();

            JsonReaderStudyRooms reader = new JsonReaderStudyRooms("./data/testWriterStudyRoomsEmpty.json");
            StudyRooms testStudyRooms = reader.read();
            assertEquals(testStudyRooms.getListStudyRoom().size(), studyRooms.getListStudyRoom().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudyRooms() {
        try {
            StudyRooms studyRooms = new StudyRooms();
            studyRooms.addStudyRoom(new StudyRoom(0));
            studyRooms.addStudyRoom(new StudyRoom(1));
            JsonWriterStudyRooms writer = new JsonWriterStudyRooms("./data/testWriterStudyRoomsGeneral.json");
            writer.open();
            writer.write(studyRooms);
            writer.close();

            JsonReaderStudyRooms reader = new JsonReaderStudyRooms("./data/testWriterStudyRoomsGeneral.json");
            StudyRooms testStudyRooms = reader.read();
            assertEquals(2 , testStudyRooms.getListStudyRoom().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
