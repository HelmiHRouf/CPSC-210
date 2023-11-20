package ui;


import model.*;
import persistence.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// represent a library kiosk app
public class Library {
    private static final String JSON_STORE_ACCOUNTS = "./data/accounts.json";
    private static final String JSON_STORE_BOOK_SHELF = "./data/bookShelf.json";
    private static final String JSON_STORE_STUDY_ROOMS = "./data/studyRooms.json";
    private JsonWriterAccounts jsonWriterAccounts;
    private JsonWriterBookShelf jsonWriterBookShelf;
    private JsonWriterStudyRooms jsonWriterStudyRooms;
    private JsonReaderAccounts jsonReaderAccounts;
    private JsonReaderBookShelf jsonReaderBookShelf;
    private JsonReaderStudyRooms jsonReaderStudyRooms;
    private Scanner input;

    private Accounts accounts;
    private BookShelf bookShelf;
    private StudyRooms studyRooms;

    // MODIFIES: this
    // EFFECT: construct a Library kiosk app
    public Library() {
        jsonWriterAccounts = new JsonWriterAccounts(JSON_STORE_ACCOUNTS);
        jsonReaderAccounts = new JsonReaderAccounts(JSON_STORE_ACCOUNTS);
        jsonWriterBookShelf = new JsonWriterBookShelf(JSON_STORE_BOOK_SHELF);
        jsonReaderBookShelf = new JsonReaderBookShelf(JSON_STORE_BOOK_SHELF);
        jsonWriterStudyRooms = new JsonWriterStudyRooms(JSON_STORE_STUDY_ROOMS);
        jsonReaderStudyRooms = new JsonReaderStudyRooms(JSON_STORE_STUDY_ROOMS);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibrary() {
        String command = null;
        System.out.println("Do you want to load existing Library?");
        System.out.println("\ty -> yes, get the existing library");
        System.out.println("\tn -> no, go to the blank library");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            loadAll();
            runLibraryHelper();
        } else if (command.equals("n")) {
            init();
            runLibraryHelper();
        }

    }

    // EFFECT: Processes user input, take role as a helper method
    private void runLibraryHelper() {
        boolean stillHere = true;
        String command = null;
        while (stillHere) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("u")) {
                userUI();
            } else if (command.equals("l")) {
                librarianUI();
            } else if (command.equals("q")) {
                stillHere = false;
            } else if (command.equals("rl")) {
                registerLibrarianUI();
            } else if (command.equals("ru")) {
                registerUserUI();
            }
        }
        saveBeforeQuit();
    }

    // EFFECTS: load all saved information to the main.
    private void loadAll() {
        loadAccounts();
        loadBookShelf();
        loadStudyRooms();
    }

    // EFFECTS: ask a save option before quitting the app.
    private void saveBeforeQuit() {
        String command = null;
        System.out.println("Do you want to save all changes in Library?");
        System.out.println("\ty -> yes, save the changes");
        System.out.println("\tn -> no, quit without saving");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("y")) {
            saveAll();
        } else if (command.equals("n")) {
            System.out.println("Quit without saving...");
        }
        System.out.println("\n Thank you for visiting our Library, Goodbye!");
    }

    // EFFECTS: save all information from the main.
    private void saveAll() {
        saveAccounts();
        saveBookShelf();
        saveStudyRooms();
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts, bookShelf, and studyRooms
    private void init() {
        accounts = new Accounts();
        bookShelf = new BookShelf();
        studyRooms = new StudyRooms();
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Welcome to the Library Kiosk Machine, Select Options Below to Continue:");
        System.out.println("\nSelect Account:");
        System.out.println("\tl -> librarian");
        System.out.println("\tu -> user");
        System.out.println("\tq -> quit");
        System.out.println("\nDon't have an account?");
        System.out.println("\trl -> register as librarian");
        System.out.println("\tru -> register as User");
    }


    // MODIFIES: this
    // EFFECTS: show sub-options for User branch
    private void userUI() {
        String command = null;
        System.out.println("Insert Username");
        String id = input.next();
        id = id.toLowerCase();
        System.out.println("Insert Password");
        String pw = input.next();
        pw = pw.toLowerCase();
        boolean keepGoing = accounts.loginUser(id, pw);
        if (keepGoing) {
            userUICommand(keepGoing, id, pw); // Helper for succed login case
        } else {
            System.out.println("Wrong username/password");
        }
    }

    // EFFECTS: proceed the userUI command
    private void userUICommand(Boolean keepGoing, String id, String pw) {
        int loginIndex = accounts.loginUserIndex(id, pw);
        User user = accounts.getUser(loginIndex);
        while (keepGoing) {
            displayUserMenu(user);
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("f")) {
                doFindBook();
            } else if (command.equals("b")) {
                doBorrowBook(user);
            } else if (command.equals("r")) {
                doReturnBook(user);
            } else if (command.equals("s")) {
                doBookStudyRoom(user);
            } else if (command.equals("u")) {
                doUnBookStudyRoom(user);
            }
        }
        System.out.println("\nLogging out...");
        System.out.println("\nLogged out!");
    }

    // MODIFIES: this
    // EFFECTS: show sub-options for Librarian branch
    private void librarianUI() {
        String command = null;
        System.out.println("Insert Username");
        String id = input.next();
        id = id.toLowerCase();
        System.out.println("Insert Password");
        String pw = input.next();
        pw = pw.toLowerCase();
        boolean keepGoing = accounts.loginLibrarian(id, pw);
        if (keepGoing) {
            librarianUICommand(keepGoing, id, pw); // Helper for succed login case
        } else {
            System.out.println("Wrong username/password");
        }
    }

    // EFFECTS: proceed the userUI command
    private void librarianUICommand(Boolean keepGoing, String id, String pw) {
        int loginIndex = accounts.loginLibrarianIndex(id, pw);
        Librarian librarian = accounts.getLibrarian(loginIndex);
        while (keepGoing) {
            displayLibrarianMenu(librarian);
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("a")) {
                doAddBook();
            } else if (command.equals("r")) {
                doRemoveBook();
            } else if (command.equals("gb")) {
                doGetBorrowedBook();
            } else if (command.equals("gr")) {
                doGetBookedStudyRoom();
            } else if (command.equals("gar")) {
                doGetAllStudyRoom();
            } else if (command.equals("gab")) {
                doGetAllBooks();
            }
        }
        System.out.println("\nLogging out... Logged out!");
    }

    // EFFECTS: display user menu
    private void displayUserMenu(User user) {
        System.out.println("\nWelcome User " + user.getUsername() + "! Select Options:");
        System.out.println("\tf -> find book");
        System.out.println("\tb -> borrow book");
        System.out.println("\tr -> return book");
        System.out.println("\ts -> book a study room");
        System.out.println("\tu -> unBook a study room");
        System.out.println("\tq -> Log out");
    }

    // EFFECTS: display librarian menu
    private void displayLibrarianMenu(Librarian librarian) {
        System.out.println("\nWelcome Librarian " + librarian.getUsername() + "! Select Options:");
        System.out.println("\ta -> add book");
        System.out.println("\tr -> remove book");
        System.out.println("\tgr -> get booked room information");
        System.out.println("\tgb -> get borrowed book information");
        System.out.println("\tgar -> get all room information");
        System.out.println("\tgab -> get all book information");
        System.out.println("\tq -> Log out");
    }

    //MODIFIES: Accounts
    //EFFECTS: Add new Credential of librarian to the accounts
    private void registerLibrarianUI() {
        System.out.println("Welcome! insert your username here:");
        String id = input.next();
        System.out.println("Next, insert your password here:");
        String pw = input.next();
        accounts.addLibrarian(id, pw);
        System.out.println("Congrats, your librarian account has been made!");
    }

    //MODIFIES: Accounts
    //EFFECTS: Add new Credential of user to the accounts
    private void registerUserUI() {
        System.out.println("Welcome! insert your username here:");
        String id = input.next();
        System.out.println("Next, insert your password here:");
        String pw = input.next();
        accounts.addUser(id, pw);
        System.out.println("Congrats, your User account has been made!");
    }

    ///////////////////////////// All subdirectory for the user ////////////////////////////////

    /////// Search Book Mechanism //////////

    // EFFECTS: make a find book mechanism
    private void doFindBook() {
        boolean keepGoing = true;
        String pick = null;
        while (keepGoing) {
            showSearchOptions();
            pick = input.next();
            pick = pick.toLowerCase();
            if (pick.equals("c")) {
                showCategory();
                pick = input.next();
                pick = pick.toLowerCase();
                doFindBookCategory(pick);
            } else if (pick.equals("y")) {
                System.out.println("Insert the year published");
                int year = input.nextInt();
                doFindBookYear(year);
            } else if (pick.equals("i")) {
                System.out.println("Insert the ISBN Number");
                String isbn = input.next();
                findBookIsbn(isbn);
            } else {
                keepGoing = false;
            }
        }
    }

    // EFFECTS: Display Search option entries
    private void showSearchOptions() {
        System.out.println("\nFind book based on:");
        System.out.println("\tc -> Category:");
        System.out.println("\ty -> Year Published:");
        System.out.println("\ti -> ISBN Number:");
        System.out.println("\tany key -> Back:");
    }

    // EFFECTS: Display the Books categories
    private void showCategory() {
        System.out.println("\nType to select Category:");
        List<String> booksCategory = bookShelf.getBooksCategory();
        for (String category : booksCategory) {
            System.out.println(category);
        }
    }

    // EFFECTS: Display all books based on the given category
    private void doFindBookCategory(String pick) {
        List<Book> bookList = bookShelf.findBookCategory(pick);
        int found = 1;
        for (Book book : bookList) {
            System.out.println(found + ". " + book.getTitle() + ", Published at " + book.getYearPublished()
                    + ", ISBN: " + book.getNumIsbn());
            found++;
        }
        if (found == 1) {
            System.out.println("Cannot Found Books with given Category!");
        } else {
            found--;
            System.out.println("Found " + found + " Books!");
        }
    }

    // EFFECTS: Display all books based on the given years
    private void doFindBookYear(int year) {
        List<Book> bookList = bookShelf.findBookYear(year);
        int found = 1;
        for (Book book : bookList) {
            System.out.println(found + ". " + book.getTitle() + ", Retrieved from" + book.getCategory()
                    + ", ISBN: " + book.getNumIsbn());
            found++;
        }
        if (found == 1) {
            System.out.println("Cannot Found Books with Given Published Year!");
        } else {
            found--;
            System.out.println("Found " + found + " Books!");
        }
    }

    // EFFECTS Display book based on the given ISBN
    private void findBookIsbn(String isbn) {
        Book book = bookShelf.findBookIsbn(isbn);
        if (book == null) {
            System.out.println("Cannot Found Book with Given ISBN Number!");
        } else {
            System.out.println(book.getTitle() + ", Published at " + book.getYearPublished());
            System.out.println("Found Book!");
        }
    }

    /////// end of Search Book Mechanism //////////

    // MODIFIES: this
    // EFFECTS: make a borrow book mechanism
    private void doBorrowBook(User user) {
        if (user.canBorrowBook()) {
            String pick;
            System.out.println("Make sure you already know the ISBN of the book or find it at the find book feature!");
            System.out.println("Insert the ISBN Number:");
            pick = input.next();
            if (bookShelf.isBookBorrowed(pick)) {
                bookShelf.borrowBook(user, pick);
                System.out.println("User " + user.getUsername()
                        + " has Borrowed book " + user.getBookborrowed() + " !");
            } else {
                System.out.println("This book has been borrowed/Wrong ISBN number, perhaps borrow an other book?");
            }
        } else {
            System.out.println("You already borrowed a book, please return it in advance to borrow another book!");
        }
    }

    // MODIFIES: this
    // EFFECTS: return a book
    private void doReturnBook(User user) {
        if ((user.getBookborrowed() == null)) {
            System.out.println("You have no book borrowed, cannot return book!");
        } else {
            bookShelf.returnBook(user);
            System.out.println("User " + user.getUsername() + " has Returned the book!");
        }
    }

    // MODIFIES: this
    // EFFECTS: book a study room
    private void doBookStudyRoom(User user) {
        if (user.canBookARoom()) {
            int roomId;
            System.out.println("Insert the room ID:");
            roomId = input.nextInt();
            if (studyRooms.canTheRoomBooked(roomId)) {
                studyRooms.bookStudyRoom(user, roomId);
                System.out.println("User " + user.getUsername() + " has Borrowed " + "Room " + roomId + "!");
            } else {
                System.out.println("This Room was Already Booked, Perhaps Find Another Room?");
            }
        } else {
            System.out.println("You already booked a study room, please cancel it in advance to book another room!");
        }
    }

    // MODIFIES: this
    // EFFECTS: cancel book a study room
    private void doUnBookStudyRoom(User user) {
        if ((user.getRoomBooked() == -1)) {
            System.out.println("You have no study room booked, please book a room in advance to cancel the book!");
        } else {
            studyRooms.cancelBookStudyRoom(user);
            System.out.println("User " + user.getUsername() + " has Cancelled " + "Room booking!");
        }
    }


    ///////////////////////////// All subdirectory for the librarian ////////////////////////////////

    // MODIFIES: this
    // EFFECTS: add book to the BookShelf
    private void doAddBook() {
        System.out.println("Insert the Book Title");
        String title = input.next();
        System.out.println("Insert the Release Year of the Book");
        int year = input.nextInt();
        System.out.println("Insert the Category of the Book");
        String category = input.next();
        System.out.println("Insert the ISBN of the Book");
        String isbn = input.next();
        Book newBook = new Book(title, year, category, isbn);
        bookShelf.addBook(newBook);
        System.out.println("Book Has been Added!");
    }

    // MODIFIES: this
    // EFFECTS: remove book to the BookShelf, given the book index on the BookShelf
    private void doRemoveBook() {
        System.out.println("Input the index of the book");
        int index = input.nextInt();
        if (index < bookShelf.getBookList().size()) {
            bookShelf.remove(index);
            System.out.println("Book Has been Removed!");
        } else {
            System.out.println("Index not valid");
        }
    }

    // EFFECTS: return all book that is borrowed
    private void doGetBorrowedBook() {
        List<Book> borrowedBooks = bookShelf.getBorrowedBooks();
        int index = 0;
        for (Book book : borrowedBooks) {
            System.out.println("index of " + index + ": " + book.getTitle()
                    + " has been booked by " + book.getBorrower());
            index++;
        }
        System.out.println("Found " + index + " Book(s)!");

    }

    // EFFECTS: return all StudyRoom that is booked
    private void doGetBookedStudyRoom() {
        List<StudyRoom> listBookedRooms = studyRooms.listBookedRooms();
        int index = 0;
        for (StudyRoom room : listBookedRooms) {
            System.out.println("index of " + index + ": room " + room.getRoomID()
                    + " has been booked by " + room.getBooked());
            index++;
        }
        System.out.println("Found " + index + " Study Room(s)!");
    }

    // EFFECTS: return all book in the BookShelf
    private void doGetAllBooks() {
        List<Book> bookList = bookShelf.getBookList();
        int index = 0;
        for (Book book : bookList) {
            System.out.println("index of " + index + ": " + book.getTitle() + ", " + book.getYearPublished() + ", "
                    + book.getCategory() + ", " + book.getNumIsbn() + ", " + book.getBorrower());
            index++;
        }
        System.out.println("Found " + index + " Book(s)!");
    }

    // EFFECTS: return all StudyRoom in the Library
    private void doGetAllStudyRoom() {
        List<StudyRoom> studyRoomList = studyRooms.getListStudyRoom();
        int index = 0;
        for (StudyRoom room : studyRoomList) {
            System.out.println("index of " + index + ": room " + room.getRoomID()
                    + ", is booked? " + room.getBooked());
            index++;
        }
        System.out.println("Found " + index + " Study Room(s)!");
    }


    // EFFECTS: saves the Accounts to file
    private void saveAccounts() {
        try {
            jsonWriterAccounts.open();
            jsonWriterAccounts.write(accounts);
            jsonWriterAccounts.close();
            System.out.println("Saved Accounts to " + JSON_STORE_ACCOUNTS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ACCOUNTS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Accounts from file
    private void loadAccounts() {
        try {
            accounts = jsonReaderAccounts.read();
            System.out.println("Loaded Accounts from " + JSON_STORE_ACCOUNTS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ACCOUNTS);
        }
    }


    // EFFECTS: saves the BookShelf to file
    private void saveBookShelf() {
        try {
            jsonWriterBookShelf.open();
            jsonWriterBookShelf.write(bookShelf);
            jsonWriterBookShelf.close();
            System.out.println("Saved Accounts to " + JSON_STORE_BOOK_SHELF);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BOOK_SHELF);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads BookShelf from file
    private void loadBookShelf() {
        try {
            bookShelf = jsonReaderBookShelf.read();
            System.out.println("Loaded Accounts from " + JSON_STORE_BOOK_SHELF);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BOOK_SHELF);
        }
    }

    // EFFECTS: saves the StudyRooms to file
    private void saveStudyRooms() {
        try {
            jsonWriterStudyRooms.open();
            jsonWriterStudyRooms.write(studyRooms);
            jsonWriterStudyRooms.close();
            System.out.println("Saved Accounts to " + JSON_STORE_STUDY_ROOMS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_STUDY_ROOMS);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads studyRooms from file
    private void loadStudyRooms() {
        try {
            studyRooms = jsonReaderStudyRooms.read();
            System.out.println("Loaded Accounts from " + JSON_STORE_STUDY_ROOMS);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_STUDY_ROOMS);
        }
    }
}



