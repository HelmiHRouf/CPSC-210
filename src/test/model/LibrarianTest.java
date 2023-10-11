package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarianTest {
    private String username1;
    private String password1;
    private String username2;
    private String password2;
    // test constructor and getter only

    @BeforeEach
    void runBefore() {
        username1 = "username1";
        password1 = "password1";
        username2 = "username1";
        password2 = "password1";
    }

    @Test
    void testConstructor() {
        Librarian librarian1 = new Librarian(username1, password1);
        assertEquals(username1, librarian1.getUsername());
        assertEquals(password1, librarian1.getPassword());

        Librarian librarian2 = new Librarian(username2, password2);
        assertEquals(username2, librarian2.getUsername());
        assertEquals(password2, librarian2.getPassword());
    }
}
