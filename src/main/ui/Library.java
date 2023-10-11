package ui;


import model.*;

import java.util.List;
import java.util.Scanner;

public class Library {
    private User user;
    private Librarian librarian;
    private Accounts accounts;
    private BookShelf bookShelf;
    private StudyRooms studyRooms;
    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;
    private Book book5;
    private StudyRoom studyRoom1;
    private StudyRoom studyRoom2;
    private StudyRoom studyRoom3;
    private StudyRoom studyRoom4;
    private StudyRoom studyRoom5;
    private StudyRoom studyRoom6;
    private Scanner input;


    public Library() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibrary() {
        boolean stillHere = true;
        String command = null;
        init();

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
        System.out.println("\n Thank you for visiting our Library, Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts, bookShelf, and studyRooms
    private void init() {
        book1 = new Book("Fundamental Astronomy", 2000, "scientific", 10201010);
        book2 = new Book("I hope I can fly", 2010, "fiction", 21421282);
        book3 = new Book("World War 2 Explained", 1990, "history", 13348593);
        book4 = new Book("Elementary C++", 1992, "scientific", 23172564);
        book5 = new Book("How Can I get Her", 2010, "fiction", 12460549);
        user = new User("joe", "111");
        librarian = new Librarian("sam", "222");
        accounts = new Accounts();
        accounts.addUser(user.getUsername(), user.getPassword());
        accounts.addLibrarian(librarian.getUsername(), librarian.getPassword());
        bookShelf = new BookShelf();
        bookShelf.addBook(book1);
        bookShelf.addBook(book2);
        bookShelf.addBook(book3);
        bookShelf.addBook(book4);
        bookShelf.addBook(book5);
        addStudyRooms(); //use Helper to reduce the total line of the code
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: declare a study Rooms with 6 study room inside
    private void addStudyRooms() {
        studyRoom1 = new StudyRoom(01);
        studyRoom2 = new StudyRoom(02);
        studyRoom3 = new StudyRoom(03);
        studyRoom4 = new StudyRoom(04);
        studyRoom5 = new StudyRoom(05);
        studyRoom6 = new StudyRoom(06);
        studyRooms = new StudyRooms();
        studyRooms.addStudyRoom(studyRoom1);
        studyRooms.addStudyRoom(studyRoom2);
        studyRooms.addStudyRoom(studyRoom3);
        studyRooms.addStudyRoom(studyRoom4);
        studyRooms.addStudyRoom(studyRoom5);
        studyRooms.addStudyRoom(studyRoom6);
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

    private void displayUserMenu(User user) {
        System.out.println("\nWelcome User " + user.getUsername() + "! Select Options:");
        System.out.println("\tf -> find book");
        System.out.println("\tb -> borrow book");
        System.out.println("\tr -> return book");
        System.out.println("\ts -> book a study room");
        System.out.println("\tu -> unBook a study room");
        System.out.println("\tq -> Log out");
    }

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

    /// all method for User

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
                int isbn = input.nextInt();
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
        List<String> bookCatergory = bookShelf.getBooksCategory();
        for (String category : bookCatergory) {
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
    private void findBookIsbn(int isbn) {
        Book book = bookShelf.findBookIsbn(isbn);
        System.out.println(book.getTitle() + ", Published at " + book.getYearPublished());
        if (book == null) {
            System.out.println("Cannot Found Book with Given ISBN Number!");
        } else {
            System.out.println("Found Book!");
        }
    }


    // MODIFIES: this
    // EFFECTS: make a borrow book mechanism
    private void doBorrowBook(User user) {
        if (user.canBorrowBook()) {
            int pick;
            System.out.println("Make sure you already know the ISBN of the book or find it at the find book feature!");
            System.out.println("Insert the ISBN Number:");
            pick = input.nextInt();
            if (bookShelf.isBookBorrowed(pick)) {
                bookShelf.borrowBook(user, pick);
                System.out.println("User " + user.getUsername() + " has Borrowed book with " + pick + " as the ISBN!");
            } else {
                System.out.println("This book has been borrowed, perhaps borrow an other book?");
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
        if ((user.getRoomBooked() == null)) {
            System.out.println("You have no study room booked, please book a room in advance to cancel the book!");
        } else {
            studyRooms.cancelBookStudyRoom(user);
            System.out.println("User " + user.getUsername() + " has Cancelled " + "Room booking!");
        }
    }


    /// All subdirectory for the librarian

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
        int isbn = input.nextInt();
        Book newBook = new Book(title, year, category, isbn);
        bookShelf.addBook(newBook);
        System.out.println("Book Has been Added!");
    }

    // MODIFIES: this
    // EFFECTS: remove book to the BookShelf, given the book index on the BookShelf
    private void doRemoveBook() {
        System.out.println("Input the index of the book");
        int index = input.nextInt();
        bookShelf.remove(index);
        System.out.println("Book Has been Removed!");
    }

    // EFFECTS: return all book that is borrowed
    private void doGetBorrowedBook() {
        List<Book> borrowedBooks = bookShelf.getBorrowedBooks();
        int index = 0;
        for (Book book : borrowedBooks) {
            User user = book.getBorrower();
            System.out.println("index of " + index + ": " + book.getTitle()
                    + " has been booked by " + user.getUsername());
            index++;
        }
        System.out.println("Found " + index + " Book(s)!");

    }

    // EFFECTS: return all StudyRoom that is booked
    private void doGetBookedStudyRoom() {
        List<StudyRoom> listBookedRooms = studyRooms.listBookedRooms();
        int index = 0;
        for (StudyRoom room : listBookedRooms) {
            User user = room.getBooked();
            System.out.println("index of " + index + ": room " + room.getRoomID()
                    + " has been booked by " + user.getUsername());
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
                    + book.getCategory() + ", " + book.getNumIsbn() + ", " + book.getIsBorrowed());
            index++;
        }
        System.out.println("Found " + index + " Book(s)!");
    }

    // EFFECTS: return all StudyRoom in the Library
    private void doGetAllStudyRoom() {
        List<StudyRoom> studyRoomList = studyRooms.getListStudyRoom();
        int index = 0;
        for (StudyRoom room : studyRoomList) {
            User user = room.getBooked();
            System.out.println("index of " + index + ": room " + room.getRoomID()
                    + ", is booked? " + room.getIsBooked());
            index++;
        }
        System.out.println("Found " + index + " Study Room(s)!");
    }
}



