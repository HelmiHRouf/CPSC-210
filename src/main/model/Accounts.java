package model;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
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
    }

    // REQUIRES: the librarian cannot have the same username along each other
    // MODIFIES: This
    // EFFECTS: add new librarian credential to the userList
    public void addLibrarian(String username, String password) {
        Librarian librarian = new Librarian(username, password);
        librarianList.add(librarian);
    }

    // EFFECTS: give the login result to the user
    public boolean loginUser(String username, String password) {
        for (User user : userList) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return true;
            }
        }
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
                return true;
            }
        }
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
}
