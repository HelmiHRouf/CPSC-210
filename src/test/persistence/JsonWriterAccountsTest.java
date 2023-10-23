package persistence;

import model.Accounts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterAccountsTest {
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriterAccounts writer = new JsonWriterAccounts("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccounts() {
        try {
            Accounts accounts = new Accounts();
            JsonWriterAccounts writer = new JsonWriterAccounts("./data/testWriterAccountsEmpty.json");
            writer.open();
            writer.write(accounts);
            writer.close();

            JsonReaderAccounts reader = new JsonReaderAccounts("./data/testWriterAccountsEmpty.json");
            Accounts accountsTest = reader.read();
            assertEquals(accounts.getAllUser(), accountsTest.getAllUser());
            assertEquals(accounts.getAllLibrarian(), accountsTest.getAllLibrarian());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccounts() {
        try {
            Accounts accounts = new Accounts();
            accounts.addUser("sam", "222");
            accounts.addLibrarian("joe", "111");
            JsonWriterAccounts writer = new JsonWriterAccounts("./data/testWriterAccountsGeneral.json");
            writer.open();
            writer.write(accounts);
            writer.close();

            JsonReaderAccounts reader = new JsonReaderAccounts("./data/testWriterAccountsGeneral.json");
            Accounts accountsTest = reader.read();
            assertEquals(1 , accountsTest.getAllUser().size());
            assertEquals(1, accountsTest.getAllLibrarian().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
