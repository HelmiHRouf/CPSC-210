package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    private Accounts testAccounts;
    private String user1Id;
    private String user1Pw;
    private String user2Id;
    private String user2Pw;
    private String librarian1Id;
    private String librarian1Pw;
    private String librarian2Id;
    private String librarian2Pw;



    @BeforeEach
    void runBefore() {
        testAccounts = new Accounts();
        user1Id = "user1Id";
        user1Pw = "user1Pw";
        user2Id = "user2Id";
        user2Pw = "user2Pw";
        librarian1Id = "librarian1Id";
        librarian1Pw = "librarian1Pw";
        librarian2Id = "librarian2Id";
        librarian2Pw = "librarian2Pw";
    }
    // Do 9 test

    @Test
    void testConstructor() {
        testAccounts.addUser(user1Id, user1Pw);
        testAccounts.addLibrarian(librarian1Id, librarian1Pw);
        assertTrue(testAccounts.getUser(0).getUsername().equals(user1Id));
        assertTrue(testAccounts.getUser(0).getPassword().equals(user1Pw));
        assertTrue(testAccounts.getLibrarian(0).getUsername().equals(librarian1Id));
        assertTrue(testAccounts.getLibrarian(0).getPassword().equals(librarian1Pw));

        testAccounts.addUser(user2Id, user2Pw);
        testAccounts.addLibrarian(librarian2Id, librarian2Pw);
        assertTrue(testAccounts.getUser(1).getUsername().equals(user2Id));
        assertTrue(testAccounts.getUser(1).getPassword().equals(user2Pw));
        assertTrue(testAccounts.getLibrarian(1).getUsername().equals(librarian2Id));
        assertTrue(testAccounts.getLibrarian(1).getPassword().equals(librarian2Pw));
    }

    @Test
    void testLogin() {
        testAccounts.addUser(user2Id, user2Pw);
        testAccounts.addLibrarian(librarian2Id, librarian2Pw);
        assertTrue(testAccounts.loginUser(user2Id, user2Pw));
        assertFalse(testAccounts.loginUser(user2Id, user2Id));
        assertTrue(testAccounts.loginLibrarian(librarian2Id, librarian2Pw));
        assertFalse(testAccounts.loginLibrarian(librarian2Pw, librarian2Pw));

        testAccounts.addUser(user1Id, user1Pw);
        testAccounts.addLibrarian(librarian1Id, librarian1Pw);
        assertTrue(testAccounts.loginUser(user2Id, user2Pw));
        assertTrue(testAccounts.loginUser(user1Id, user1Pw));
        assertFalse(testAccounts.loginUser(user2Id, user1Pw));
        assertFalse(testAccounts.loginUser(user1Id, user2Pw));

        assertTrue(testAccounts.loginLibrarian(librarian2Id, librarian2Pw));
        assertTrue(testAccounts.loginLibrarian(librarian1Id, librarian1Pw));
        assertFalse(testAccounts.loginLibrarian(librarian2Id, librarian1Pw));
        assertFalse(testAccounts.loginLibrarian(librarian1Id, librarian2Pw));

    }

    @Test
    void testLoginIndex() {
        testAccounts.addUser(user1Id, user1Pw);
        testAccounts.addLibrarian(librarian1Id, librarian1Pw);
        assertEquals(0, testAccounts.loginUserIndex(user1Id, user1Pw));
        assertEquals(1, testAccounts.loginUserIndex(user1Id, user1Id)); // false login
        assertEquals(0, testAccounts.loginLibrarianIndex(librarian1Id, librarian1Pw));
        assertEquals(1, testAccounts.loginLibrarianIndex(librarian1Id, librarian1Id));
        // false login

        testAccounts.addUser(user2Id, user2Pw);
        testAccounts.addLibrarian(librarian2Id, librarian2Pw);
        assertEquals(1, testAccounts.loginUserIndex(user2Id, user2Pw));
        assertEquals(1, testAccounts.loginLibrarianIndex(librarian2Id, librarian2Pw));
        assertEquals(0, testAccounts.loginUserIndex(user1Id, user1Pw));
        assertEquals(0, testAccounts.loginLibrarianIndex(librarian1Id, librarian1Pw));

    }
}
