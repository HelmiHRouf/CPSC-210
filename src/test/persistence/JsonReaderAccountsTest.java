package persistence;

import model.Accounts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderAccountsTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/noSuchFile.json");
        try {
            Accounts accounts = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccounts() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/testReaderAccountsEmpty.json");
        Accounts testAccounts = new Accounts();
        try {
            Accounts accounts = reader.read();
            assertEquals(testAccounts.getAllUser(), accounts.getAllUser());
            assertEquals(testAccounts.getAllLibrarian(), accounts.getAllLibrarian());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccounts() {
        JsonReaderAccounts reader = new JsonReaderAccounts("./data/testReaderAccountsGeneral.json");
        Accounts testAccounts = new Accounts();
        try {
            Accounts accounts = reader.read();
            assertTrue(testAccounts.getAllLibrarian().size() != accounts.getAllLibrarian().size());
            assertTrue(testAccounts.getAllUser().size() != accounts.getAllUser().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
