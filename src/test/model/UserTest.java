package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private String user1Id;
    private String user1Pw;
    private String user2Id;
    private String user2Pw;
    private Book book1;
    private Book book2;
    private StudyRoom studyRoom1;

    @BeforeEach
    void runBefore() {
        user1Id = "user1Id";
        user1Pw = "user1Pw";
        user2Id = "user2Id";
        user2Pw = "user2Pw";
        book1 = new Book("Fundamental Astronomy", 2000, "scientific", "10201010");
        book2 = new Book("I hope I can fly", 2010, "fiction", "21421282");
        studyRoom1 = new StudyRoom("1");
    }
    // 2 method and setter getter

    @Test
    void testConstructor() {
        User user1 = new User(user1Id, user1Pw);
        assertEquals(user1Id, user1.getUsername());
        assertEquals(user1Pw, user1.getPassword());
        assertEquals(null, user1.getBookborrowed());
        assertEquals(null, user1.getRoomBooked());
        assertTrue(user1.canBorrowBook());
        assertTrue(user1.canBookARoom());
    }

    @Test
    void testSetter() {
        User user2 = new User(user2Id, user2Pw);
        assertEquals(user2Id, user2.getUsername());
        assertEquals(user2Pw, user2.getPassword());
        assertEquals(null, user2.getBookborrowed());
        assertEquals(null, user2.getRoomBooked());
        assertTrue(user2.canBorrowBook());
        assertTrue(user2.canBookARoom());

        user2.setBookborrowed(book1);
        user2.setRoomBooked(studyRoom1);
        assertEquals(book1, user2.getBookborrowed());
        assertEquals(studyRoom1, user2.getRoomBooked());
        assertFalse(user2.canBorrowBook());
        assertFalse(user2.canBookARoom());

        user2.setBookborrowed(book2);
        user2.setRoomBooked(null);
        assertEquals(book2, user2.getBookborrowed());
        assertEquals(null, user2.getRoomBooked());
        assertFalse(user2.canBorrowBook());
        assertTrue(user2.canBookARoom());

        user2.setBookborrowed(null);
        assertEquals(null, user2.getBookborrowed());
        assertTrue(user2.canBorrowBook());
    }
}
