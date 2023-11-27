package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represent a list of user account and list of librarian account that is registered in this library.
// this class allows user and librarina to register theirs given their username and password.
// this class also allows user and librarian to login given that they already registered in.
public class Accounts implements Writable {
    private List<User> userList;
    private List<Librarian> librarianList;


    // MODIFIES: This
    // EFFECT: Construct Account with no ListofUser and ListofLibrarian yet:
    public Accounts() {
        this.userList = new ArrayList<>();
        this.librarianList = new ArrayList<>();
    }

    // REQUIRES: the user cannot have the same username along each other
    // MODIFIES: This
    // EFFECTS add new user credential to the userList
    public void addUser(String username, String password) {
        User user = new User(username, password);
        userList.add(user);
        EventLog.getInstance().logEvent(new Event("Added User: " + username));
    }

    // REQUIRES: the librarian cannot have the same username along each other
    // MODIFIES: This
    // EFFECTS: add new librarian credential to the userList
    public void addLibrarian(String username, String password) {
        Librarian librarian = new Librarian(username, password);
        librarianList.add(librarian);
        EventLog.getInstance().logEvent(new Event("Added Librarian: " + username));
    }

    // EFFECTS: give the login result to the user
    public boolean loginUser(String username, String password) {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                EventLog.getInstance().logEvent(new Event("Login User " + username + " success"));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Login User " + username + " failed"));
        return false;
    }

    // REQUIRES: must log in successfully
    // EFFECTS: give the credential index of the user
    public int loginUserIndex(String username, String password) {
        int index = 0;
        for (User user : userList) {
            if (!(username.equals(user.getUsername()) && password.equals(user.getPassword()))) {
                index++;
            } else {
                return index;
            }
        }
        return index;
    }

    // EFFECTS: give the login result to the librarian
    public boolean loginLibrarian(String username, String password) {
        for (Librarian librarian : librarianList) {
            if (username.equals(librarian.getUsername()) && password.equals(librarian.getPassword())) {
                EventLog.getInstance().logEvent(new Event("Login Librarian " + username + " success"));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Login Librarian " + username + " failed"));
        return false;
    }

    // REQUIRES: must log in successfully
    // EFFECTS: give the credential index of the librarian
    public int loginLibrarianIndex(String username, String password) {
        int index = 0;
        for (Librarian librarian : librarianList) {
            if (!(username.equals(librarian.getUsername()) && password.equals(librarian.getPassword()))) {
                index++;
            } else {
                return index;
            }
        }
        return index;
    }

    //getter

    // EFFECTS: Return user at given index
    public User getUser(int index) {
        return this.userList.get(index);
    }

    // EFFECTS: Return librarian at given index
    public Librarian getLibrarian(int index) {
        return this.librarianList.get(index);
    }

    // EFFECTS: Return all users
    public List<User> getAllUser() {
        return this.userList;
    }

    // EFFECTS: Return all librarians
    public List<Librarian> getAllLibrarian() {
        return this.librarianList;
    }

    // EFFECTS: return Accounts object to JSON object
    @Override
    public JSONObject toJson() {
        JSONObject jsonAccount = new JSONObject();
        jsonAccount.put("user", userToJson());
        jsonAccount.put("librarian", librarianToJson());
        return jsonAccount;
    }

    // EFFECTS: returns users in this account as a JSON array
    private JSONArray userToJson() {
        JSONArray jsonArray = new JSONArray();
        for (User user : userList) {
            jsonArray.put(user.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns libarians in this account as a JSON array
    private JSONArray librarianToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Librarian librarian : librarianList) {
            jsonArray.put(librarian.toJson());
        }

        return jsonArray;
    }
}
