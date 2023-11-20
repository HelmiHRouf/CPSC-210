package ui;

import model.*;
import persistence.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LibraryGUI implements ActionListener {
    private static final String JSON_STORE_ACCOUNTS = "./data/accounts.json";
    private static final String JSON_STORE_BOOK_SHELF = "./data/bookShelf.json";
    private static final String JSON_STORE_STUDY_ROOMS = "./data/studyRooms.json";
    private static final ImageIcon IMAGE_ICON = new ImageIcon("src/main/ui/libraryImage.png");
    private JsonWriterAccounts jsonWriterAccounts;
    private JsonWriterBookShelf jsonWriterBookShelf;
    private JsonWriterStudyRooms jsonWriterStudyRooms;
    private JsonReaderAccounts jsonReaderAccounts;
    private JsonReaderBookShelf jsonReaderBookShelf;
    private JsonReaderStudyRooms jsonReaderStudyRooms;

    private Accounts accounts;
    private BookShelf bookShelf;
    private StudyRooms studyRooms;

    private JFrame frame;
    private JPanel loadPanel;
    private JPanel welcomePanel;
    private JPanel loginUserPanel;
    private JPanel loginLibrarianPanel;
    private JPanel registerUserPanel;
    private JPanel registerLibrarianPanel;
    private JPanel welcomeUserPanel;
    private JPanel welcomeLibrarianPanel;

    private JTextField userLoginUsernameText;
    private JTextField userLoginPasswordText;
    private JTextField librarianLoginUsernameText;
    private JTextField librarianLoginPasswordText;
    private JTextField userRegisterUsernameText;
    private JTextField userRegisterPasswordText;
    private JTextField librarianRegisterUsernameText;
    private JTextField librarianRegisterPasswordText;

//    private JLabel userLoginNotification;
//    private JLabel librarianLoginNotification;
//    private JLabel userRegisterNotification;
//    private JLabel librarianRegisterNotification;





    // MODIFIES: this
    // EFFECT: construct a Library kiosk app
    public LibraryGUI() {
        jsonWriterAccounts = new JsonWriterAccounts(JSON_STORE_ACCOUNTS);
        jsonReaderAccounts = new JsonReaderAccounts(JSON_STORE_ACCOUNTS);
        jsonWriterBookShelf = new JsonWriterBookShelf(JSON_STORE_BOOK_SHELF);
        jsonReaderBookShelf = new JsonReaderBookShelf(JSON_STORE_BOOK_SHELF);
        jsonWriterStudyRooms = new JsonWriterStudyRooms(JSON_STORE_STUDY_ROOMS);
        jsonReaderStudyRooms = new JsonReaderStudyRooms(JSON_STORE_STUDY_ROOMS);

        frame = new JFrame();
        frame.setTitle("Library Kiosk Software");
        frame.setSize(1000, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        runLibrary();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLibrary() {
        loadPanel = new JPanel();
        frame.add(loadPanel);
        loadPanel.setLayout(null);
        loadPanel.setSize(1000, 800);
        //loadPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel("Do you want to load existing Library?");
        label.setBounds(330,270,250,25);

        JButton yesLoadButton = new JButton("yes");
        yesLoadButton.setActionCommand("loadLibrary");
        yesLoadButton.addActionListener(this);
        yesLoadButton.setBounds(360,380,80,25);

        JButton noLoadButton = new JButton("no");
        noLoadButton.setActionCommand("noLoadLibrary");
        noLoadButton.addActionListener(this);
        noLoadButton.setBounds(460,380,80,25);

        loadPanel.add(label);
        loadPanel.add(yesLoadButton);
        loadPanel.add(noLoadButton);
    }

    private void runLibraryHelper() {
        welcomePanel = new JPanel();
        frame.add(welcomePanel);
        welcomePanel.setLayout(null);
        welcomePanel.setSize(1000, 800);

        JLabel label = new JLabel("Welcome to the Library Kiosk Machine, Select Options Below to Continue:");
        label.setIcon(IMAGE_ICON); // set the image on the label
        label.setHorizontalTextPosition(JLabel.CENTER); // set the text position horizontally
        label.setVerticalTextPosition(JLabel.TOP); // set the text position vertically
        label.setIconTextGap(20); // set the gap of the text from the image
        label.setBounds(270,10,500,400);

        JButton loginUserButton = new JButton("Login as a User");
        loginUserButton.setActionCommand("loginUserButton");
        loginUserButton.addActionListener(this);
        loginUserButton.setBounds(100,420,800,50);

        JButton loginLibrarianButton = new JButton("Login as a Librarian");
        loginLibrarianButton.setActionCommand("loginLibrarianButton");
        loginLibrarianButton.addActionListener(this);
        loginLibrarianButton.setBounds(100,490,800,50);
        welcomePanel.add(label);
        welcomePanel.add(loginUserButton);
        welcomePanel.add(loginLibrarianButton);

        runLibraryHelperHelper();
    }

    private void runLibraryHelperHelper() {
        JButton registerUserButton = new JButton("Register as a User");
        registerUserButton.setActionCommand("registerUserButton");
        registerUserButton.addActionListener(this);
        registerUserButton.setBounds(100,560,800,50);

        JButton registerLibrarianButton = new JButton("Register as a Librarian");
        registerLibrarianButton.setActionCommand("registerLibrarianButton");
        registerLibrarianButton.addActionListener(this);
        registerLibrarianButton.setBounds(100,630,800,50);

        JButton saveALlButton = new JButton("Save all Changes");
        saveALlButton.setActionCommand("saveALlButton");
        saveALlButton.addActionListener(this);
        saveALlButton.setBounds(100,700,800,50);

        welcomePanel.add(registerUserButton);
        welcomePanel.add(registerLibrarianButton);
        welcomePanel.add(saveALlButton);
    }

    private void userUI() {
        loginUserPanel = new JPanel();
        frame.add(loginUserPanel);
        loginUserPanel.setLayout(null);
        loginUserPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        loginUserPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("User Login");
        title.setBounds(160,75,200,50);
        panel.add(title);

        JLabel userLabel = new JLabel("username");
        userLabel.setBounds(60,200,80,25);
        panel.add(userLabel);

        userLoginUsernameText = new JTextField(20);
        userLoginUsernameText.setBounds(160,200,165,25);
        panel.add(userLoginUsernameText);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(60,275,80,25);
        panel.add(passwordLabel);

        userUIHelper(panel);
    }

    private void userUIHelper(JPanel panel) {
        userLoginPasswordText = new JTextField(20);
        userLoginPasswordText.setBounds(160,275,165,25);
        panel.add(userLoginPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginUserLogin");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);

//        userLoginNotification = new JLabel("");
//        userLoginNotification.setBounds(10,475,300,25);
//        panel.add(userLoginNotification);
    }

    private void librarianUI() {
        loginLibrarianPanel = new JPanel();
        frame.add(loginLibrarianPanel);
        loginLibrarianPanel.setLayout(null);
        loginLibrarianPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        loginLibrarianPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Librarian Login");
        title.setBounds(150,75,200,50);
        panel.add(title);

        JLabel userLabel = new JLabel("username");
        userLabel.setBounds(60,200,80,25);
        panel.add(userLabel);

        librarianLoginUsernameText = new JTextField(20);
        librarianLoginUsernameText.setBounds(160,200,165,25);
        panel.add(librarianLoginUsernameText);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(60,275,80,25);
        panel.add(passwordLabel);

        librarianUIHelper(panel);
    }

    private void librarianUIHelper(JPanel panel) {
        librarianLoginPasswordText = new JTextField(20);
        librarianLoginPasswordText.setBounds(160,275,165,25);
        panel.add(librarianLoginPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginLibrarianLogin");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);

//        librarianLoginNotification = new JLabel("");
//        librarianLoginNotification.setBounds(10,475,300,25);
//        panel.add(librarianLoginNotification);
    }

    private void registerUserUI() {
        registerUserPanel = new JPanel();
        frame.add(registerUserPanel);
        registerUserPanel.setLayout(null);
        registerUserPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        registerUserPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("User Register");
        title.setBounds(150,75,200,50);
        panel.add(title);

        JLabel userLabel = new JLabel("username");
        userLabel.setBounds(60,200,80,25);
        panel.add(userLabel);

        userRegisterUsernameText = new JTextField(20);
        userRegisterUsernameText.setBounds(160,200,165,25);
        panel.add(userRegisterUsernameText);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(60,275,80,25);
        panel.add(passwordLabel);

        registerUserUIHelper(panel);
    }

    private void registerUserUIHelper(JPanel panel) {
        userRegisterPasswordText = new JTextField(20);
        userRegisterPasswordText.setBounds(160,275,165,25);
        panel.add(userRegisterPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginUserRegister");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);

//        userRegisterNotification = new JLabel("");
//        userRegisterNotification.setBounds(10,475,300,25);
//        panel.add(userRegisterNotification);
    }

    private void registerLibrarianUI() {
        registerLibrarianPanel = new JPanel();
        frame.add(registerLibrarianPanel);
        registerLibrarianPanel.setLayout(null);
        registerLibrarianPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        registerLibrarianPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Librarian Register");
        title.setBounds(150,75,200,50);
        panel.add(title);

        JLabel userLabel = new JLabel("username");
        userLabel.setBounds(60,200,80,25);
        panel.add(userLabel);

        librarianRegisterUsernameText = new JTextField(20);
        librarianRegisterUsernameText.setBounds(160,200,165,25);
        panel.add(librarianRegisterUsernameText);

        JLabel passwordLabel = new JLabel("password");
        passwordLabel.setBounds(60,275,80,25);
        panel.add(passwordLabel);

        librarianRegisterUIHelper(panel);
    }

    private void librarianRegisterUIHelper(JPanel panel) {
        librarianRegisterPasswordText = new JTextField(20);
        librarianRegisterPasswordText.setBounds(160,275,165,25);
        panel.add(librarianRegisterPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginLibrarianRegister");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);

//        librarianRegisterNotification = new JLabel("");
//        librarianRegisterNotification.setBounds(10,475,300,25);
//        panel.add(librarianRegisterNotification);
    }

    //EFFECTS: proceed the userUI command
    private void userUICommand(String id, String pw) {
        int loginIndex = accounts.loginUserIndex(id, pw);
        User user = accounts.getUser(loginIndex);

        welcomeUserPanel = new JPanel();
        frame.add(welcomeUserPanel);
        welcomeUserPanel.setLayout(null);
        welcomeUserPanel.setSize(1000, 800);

        JLabel label = new JLabel("Welcome User " + user.getUsername() + ", Select Options:");
        label.setIcon(IMAGE_ICON); // set the image on the label
        label.setHorizontalTextPosition(JLabel.CENTER); // set the text position horizontally
        label.setVerticalTextPosition(JLabel.TOP); // set the text position vertically
        label.setIconTextGap(20); // set the gap of the text from the image
        label.setBounds(330,10,500,400);
        welcomeUserPanel.add(label);

        JButton findBookCategory = new JButton("Find Book Based on Category");
        findBookCategory.setActionCommand("findBookCategory");
        findBookCategory.addActionListener(this);
        findBookCategory.setBounds(100,450,375,50);
        welcomeUserPanel.add(findBookCategory);

        JButton findBookYearPublished = new JButton("Find Book Based on Year Published");
        findBookYearPublished.setActionCommand("findBookYearPublished");
        findBookYearPublished.addActionListener(this);
        findBookYearPublished.setBounds(100,520,375,50);
        welcomeUserPanel.add(findBookYearPublished);

        JButton findBookIsbn = new JButton("Find Book Based on ISBN");
        findBookIsbn.setActionCommand("findBookIsbn");
        findBookIsbn.addActionListener(this);
        findBookIsbn.setBounds(100,590,375,50);
        welcomeUserPanel.add(findBookIsbn);

        JButton borrowBook = new JButton("Borrow Book");
        borrowBook.setActionCommand("borrowBook");
        borrowBook.addActionListener(this);
        borrowBook.setBounds(100,660,375,50);
        welcomeUserPanel.add(borrowBook);

        JButton returnBook = new JButton("Return Book");
        returnBook.setActionCommand("returnBook");
        returnBook.addActionListener(this);
        returnBook.setBounds(525,450,375,50);
        welcomeUserPanel.add(returnBook);

        JButton bookStudyRoom = new JButton("Book Study Room");
        bookStudyRoom.setActionCommand("bookStudyRoom");
        bookStudyRoom.addActionListener(this);
        bookStudyRoom.setBounds(525,520,375,50);
        welcomeUserPanel.add(bookStudyRoom);

        JButton unBookStudyRoom = new JButton("UnBook Study Room");
        unBookStudyRoom.setActionCommand("unBookStudyRoom");
        unBookStudyRoom.addActionListener(this);
        unBookStudyRoom.setBounds(525,590,375,50);
        welcomeUserPanel.add(unBookStudyRoom);

        JButton logOutUser = new JButton("LogOut");
        logOutUser.setActionCommand("logOutUser");
        logOutUser.addActionListener(this);
        logOutUser.setBounds(525,660,375,50);
        welcomeUserPanel.add(logOutUser);
    }

    private void librarianUICommand(String id, String pw) {
        int loginIndex = accounts.loginLibrarianIndex(id, pw);
        Librarian librarian = accounts.getLibrarian(loginIndex);

        welcomeLibrarianPanel = new JPanel();
        frame.add(welcomeLibrarianPanel);
        welcomeLibrarianPanel.setLayout(null);
        welcomeLibrarianPanel.setSize(1000, 800);

        JLabel label = new JLabel("Welcome Librarian " + librarian.getUsername() + ", Select Options:");
        label.setIcon(IMAGE_ICON); // set the image on the label
        label.setHorizontalTextPosition(JLabel.CENTER); // set the text position horizontally
        label.setVerticalTextPosition(JLabel.TOP); // set the text position vertically
        label.setIconTextGap(20); // set the gap of the text from the image
        label.setBounds(330,10,500,400);
        welcomeLibrarianPanel.add(label);

        JButton addBook = new JButton("Add Book");
        addBook.setActionCommand("addBook ");
        addBook.addActionListener(this);
        addBook.setBounds(100,450,375,50);
        welcomeLibrarianPanel.add(addBook);

        JButton getBookedRoom = new JButton("Get Booked Room Information");
        getBookedRoom.setActionCommand("getBookedRoom");
        getBookedRoom.addActionListener(this);
        getBookedRoom.setBounds(100,520,375,50);
        welcomeLibrarianPanel.add(getBookedRoom);

        JButton getAllRooms = new JButton("Get All Rooms Information");
        getAllRooms.setActionCommand("getAllRooms");
        getAllRooms.addActionListener(this);
        getAllRooms.setBounds(100,590,375,50);
        welcomeLibrarianPanel.add(getAllRooms);

        JButton removeBook = new JButton("Remove Book");
        removeBook.setActionCommand("removeBook");
        removeBook.addActionListener(this);
        removeBook.setBounds(525,450,375,50);
        welcomeLibrarianPanel.add(removeBook);

        JButton getBorrowedBook = new JButton("Get Borrowed Book Information");
        getBorrowedBook.setActionCommand("getBorrowedBook");
        getBorrowedBook.addActionListener(this);
        getBorrowedBook.setBounds(525,520,375,50);
        welcomeLibrarianPanel.add(getBorrowedBook);

        JButton getAllBooks = new JButton("Get All Books Information");
        getAllBooks.setActionCommand("getAllBooks");
        getAllBooks.addActionListener(this);
        getAllBooks.setBounds(525,590,375,50);
        welcomeLibrarianPanel.add(getAllBooks);

        JButton logOutUser = new JButton("LogOut");
        logOutUser.setActionCommand("logOutUser");
        logOutUser.addActionListener(this);
        logOutUser.setBounds(312,660,375,50);
        welcomeLibrarianPanel.add(logOutUser);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("loadLibrary")) {
            loadPanel.setVisible(false);
            loadAll();
            runLibraryHelper();
        }

        if (e.getActionCommand().equals("noLoadLibrary")) {
            loadPanel.setVisible(false);
            init();
            runLibraryHelper();
        }

        if (e.getActionCommand().equals("loginUserButton")) {
            welcomePanel.setVisible(false);
            userUI();
        }

        if (e.getActionCommand().equals("loginLibrarianButton")) {
            welcomePanel.setVisible(false);
            librarianUI();
        }

        if (e.getActionCommand().equals("registerUserButton")) {
            welcomePanel.setVisible(false);
            registerUserUI();
        }

        actionPerformedHelper(e);
    }

    private void actionPerformedHelper(ActionEvent e) {
        if (e.getActionCommand().equals("registerLibrarianButton")) {
            welcomePanel.setVisible(false);
            registerLibrarianUI();
        }

        if (e.getActionCommand().equals("saveALlButton")) {
            saveAll();
        }

        if (e.getActionCommand().equals("loginUserLogin")) {
            String user = userLoginUsernameText.getText();
            String password = userLoginPasswordText.getText();
            boolean keepGoing = accounts.loginUser(user, password);
            if (keepGoing) {
                loginUserPanel.setVisible(false);
                userUICommand(user, password);
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong username/password.");
                loginUserPanel.setVisible(false);
                welcomePanel.setVisible(true);
            }
        }

        if (e.getActionCommand().equals("loginLibrarianLogin")) {
            String user = librarianLoginUsernameText.getText();
            String password = librarianLoginPasswordText.getText();
            boolean keepGoing = accounts.loginLibrarian(user, password);
            if (keepGoing) {
                loginLibrarianPanel.setVisible(false);
                librarianUICommand(user, password);
            } else {
                JOptionPane.showMessageDialog(frame, "Wrong username/password.");
                loginLibrarianPanel.setVisible(false);
                welcomePanel.setVisible(true);
            }
        }

        if (e.getActionCommand().equals("loginUserRegister")) {
            String user = userRegisterUsernameText.getText();
            String password = userRegisterPasswordText.getText();
            accounts.addUser(user, password);
            JOptionPane.showMessageDialog(frame, "Congrats, your User account has been made!");
            registerUserPanel.setVisible(false);
            welcomePanel.setVisible(true);
        }

        if (e.getActionCommand().equals("loginLibrarianRegister")) {
            String user = librarianRegisterUsernameText.getText();
            String password = librarianRegisterPasswordText.getText();
            accounts.addLibrarian(user, password);
            JOptionPane.showMessageDialog(frame, "Congrats, your Librarian account has been made!");
            registerLibrarianPanel.setVisible(false);
            welcomePanel.setVisible(true);
        }
    }

    // EFFECTS: load all saved information to the main.
    private void loadAll() {
        loadAccounts();
        loadBookShelf();
        loadStudyRooms();
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

    // MODIFIES: this
    // EFFECTS: initializes accounts, bookShelf, and studyRooms
    private void init() {
        accounts = new Accounts();
        bookShelf = new BookShelf();
        studyRooms = new StudyRooms();
    }

    // EFFECTS: save all information from the main.
    private void saveAll() {
        saveAccounts();
        saveBookShelf();
        saveStudyRooms();
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
}
