package ui;

import model.*;
import persistence.*;

//import com.apple.eawt.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// represent a library kiosk app presented with GUI
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
    private User user;

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
    private JPanel doBorrowBookPanel;
    private JPanel doBookStudyRoomPanel;
    private JPanel doFindBookCategoryPanel;
    private JPanel doFindBookYearPublishedPanel;
    private JPanel doFindBookIsbnPanel;
    private JPanel doAddBookPanel;
    private JPanel doRemoveBookPanel;

    private JTextField userLoginUsernameText;
    private JTextField userLoginPasswordText;
    private JTextField librarianLoginUsernameText;
    private JTextField librarianLoginPasswordText;
    private JTextField userRegisterUsernameText;
    private JTextField userRegisterPasswordText;
    private JTextField librarianRegisterUsernameText;
    private JTextField librarianRegisterPasswordText;
    private JTextField findBookCategoryText;
    private JTextField findBookYearPublishedText;
    private JTextField findBookIsbnText;
    private JTextField borrowBookText;
    private JTextField bookStudyRoomText;
    private JTextField addBookTitle;
    private JTextField addBookYearPublished;
    private JTextField addBookCategory;
    private JTextField addBookIsbn;
    private JTextField removeBookIndex;




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
        frame.setIconImage(IMAGE_ICON.getImage());
        //Application.getApplication().setDockIconImage(IMAGE_ICON.getImage());
        frame.setTitle("Library Kiosk Software");
        frame.setSize(1000, 800);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        runLibrary();
    }

    // EFFECTS: display load or no load option
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

    // EFFECTS: display the login or register options
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

    // EFFECTS: a helper to display the login or register options
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

    // EFFECTS: display login user
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

    // EFFECTS: a helper to display login user
    private void userUIHelper(JPanel panel) {
        userLoginPasswordText = new JTextField(20);
        userLoginPasswordText.setBounds(160,275,165,25);
        panel.add(userLoginPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginUserLogin");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: display login librarian
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

    // EFFECTS: a helper to display login librarian
    private void librarianUIHelper(JPanel panel) {
        librarianLoginPasswordText = new JTextField(20);
        librarianLoginPasswordText.setBounds(160,275,165,25);
        panel.add(librarianLoginPasswordText);

        JButton button = new JButton("Login");
        button.setActionCommand("loginLibrarianLogin");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: display register user
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

    // EFFECTS: a helper to display register user
    private void registerUserUIHelper(JPanel panel) {
        userRegisterPasswordText = new JTextField(20);
        userRegisterPasswordText.setBounds(160,275,165,25);
        panel.add(userRegisterPasswordText);

        JButton button = new JButton("Register");
        button.setActionCommand("loginUserRegister");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: display register librarian
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

    // EFFECTS: a helper to display register librarian
    private void librarianRegisterUIHelper(JPanel panel) {
        librarianRegisterPasswordText = new JTextField(20);
        librarianRegisterPasswordText.setBounds(160,275,165,25);
        panel.add(librarianRegisterPasswordText);

        JButton button = new JButton("Register");
        button.setActionCommand("loginLibrarianRegister");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: display the userUI command
    private void userUICommand(String id, String pw) {
        int loginIndex = accounts.loginUserIndex(id, pw);
        user = accounts.getUser(loginIndex);

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

        userUICommandHelper();
        userUICommandSecondHelper();
    }

    // EFFECTS: a helper to display the userUI command
    private void userUICommandHelper() {
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
    }

    // EFFECTS: a second helper to display the userUI command
    private void userUICommandSecondHelper() {
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

    // EFFECTS: display the librarianUI command
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
        addBook.setActionCommand("addBook");
        addBook.addActionListener(this);
        addBook.setBounds(100,450,375,50);
        welcomeLibrarianPanel.add(addBook);

        librarianUICommandHelper();
        librarianUICommandSecondHelper();
    }

    // EFFECTS: a helper to display the librarianUI command
    private void librarianUICommandHelper() {
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
    }

    // EFFECTS: a second helper to display the librarianUI command
    private void librarianUICommandSecondHelper() {
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

        JButton logOutLibrarian = new JButton("LogOut");
        logOutLibrarian.setActionCommand("logOutLibrarian");
        logOutLibrarian.addActionListener(this);
        logOutLibrarian.setBounds(312,660,375,50);
        welcomeLibrarianPanel.add(logOutLibrarian);
    }

    // EFFECTS: display al books category
    private void findBookCategory() {
        doFindBookCategoryPanel = new JPanel();
        frame.add(doFindBookCategoryPanel);
        doFindBookCategoryPanel.setLayout(null);
        doFindBookCategoryPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doFindBookCategoryPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Find Book Category");
        title.setBounds(140,30,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Type to select Category:");
        subTitle.setBounds(130,65,220,50);
        panel.add(subTitle);

        JPanel categoryPanel = showCategory();
        categoryPanel.setLocation(50,130);
        panel.add(categoryPanel);

        JLabel bookTitle = new JLabel("Category");
        bookTitle.setBounds(50,365,80,25);
        panel.add(bookTitle);

        findBookCategoryHelper(panel);
    }

    // EFFECTS: a helper to display al books category
    private void findBookCategoryHelper(JPanel panel) {
        findBookCategoryText = new JTextField(20);
        findBookCategoryText.setBounds(160,365,165,25);
        panel.add(findBookCategoryText);

        JButton button = new JButton("Find");
        button.setActionCommand("findBookCategoryFind");
        button.addActionListener(this);
        button.setBounds(125,425,150,50);
        panel.add(button);
    }

    // EFFECT: chow the category menu
    private JPanel showCategory() {
        List<String> booksCategory = bookShelf.getBooksCategory();
        JPanel panel = new JPanel(new GridLayout((booksCategory.size() + 1), 0));
        int index = 0;
        for (String category : booksCategory) {
            JLabel label = new JLabel("Index of " + index + ": " + category);
            label.setSize(new Dimension(100, 25));
            label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            label.setOpaque(true);
            panel.add(label);
            index++;
        }

        panel.add(new JLabel("Found " + index + " Categories!", SwingConstants.CENTER));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel panelOuter = new JPanel();
        panelOuter.setLayout(new GridLayout(1,0));
        panelOuter.setSize(new Dimension(300,200));
        panelOuter.add(scrollPane);
        return panelOuter;
    }

    // EFFECTS: Display the find book year menu
    private void findBookYear() {
        doFindBookYearPublishedPanel = new JPanel();
        frame.add(doFindBookYearPublishedPanel);
        doFindBookYearPublishedPanel.setLayout(null);
        doFindBookYearPublishedPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doFindBookYearPublishedPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Find Book Year");
        title.setBounds(150,60,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Insert the year published");
        subTitle.setBounds(100,125,220,50);
        panel.add(subTitle);

        findBookYearHelper(panel);
    }

    // EFFECTS: a helper to display find book year menu
    private void findBookYearHelper(JPanel panel) {
        JLabel bookTitle = new JLabel("Year");
        bookTitle.setBounds(60,250,80,25);
        panel.add(bookTitle);

        findBookYearPublishedText = new JTextField(20);
        findBookYearPublishedText.setBounds(160,250,165,25);
        panel.add(findBookYearPublishedText);

        JButton button = new JButton("Find");
        button.setActionCommand("findBookYearFind");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: Display the find book ISBN menu
    private void findBookIsbn() {
        doFindBookIsbnPanel = new JPanel();
        frame.add(doFindBookIsbnPanel);
        doFindBookIsbnPanel.setLayout(null);
        doFindBookIsbnPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doFindBookIsbnPanel.add(panel);
        panel.setBounds(300, 150, 400, 500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Find Book ISBN");
        title.setBounds(150, 60, 290, 50);
        panel.add(title);

        findBookIsbnHelper(panel);
    }

    // EFFECTS: a helper to display the find book ISBN menu
    private void findBookIsbnHelper(JPanel panel) {
        JLabel subTitle = new JLabel("Insert the ISBN Number:");
        subTitle.setBounds(100,125,220,50);
        panel.add(subTitle);

        JLabel bookTitle = new JLabel("ISBN");
        bookTitle.setBounds(60,250,80,25);
        panel.add(bookTitle);

        findBookIsbnText = new JTextField(20);
        findBookIsbnText.setBounds(160,250,165,25);
        panel.add(findBookIsbnText);

        JButton button = new JButton("Find");
        button.setActionCommand("findBookIsbnFind");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // EFFECTS: display the borrow book menu
    private void doBorrowBook() {
        doBorrowBookPanel = new JPanel();
        frame.add(doBorrowBookPanel);
        doBorrowBookPanel.setLayout(null);
        doBorrowBookPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doBorrowBookPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Borrow Book");
        title.setBounds(150,60,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Insert the ISBN Number:");
        subTitle.setBounds(100,125,220,50);
        panel.add(subTitle);

        doBorrowBookHelper(panel);
    }

    // EFFECTS: a helper to display the borrow book menu
    private void doBorrowBookHelper(JPanel panel) {
        JLabel bookTitle = new JLabel("ISBN");
        bookTitle.setBounds(60,250,80,25);
        panel.add(bookTitle);

        borrowBookText = new JTextField(20);
        borrowBookText.setBounds(160,250,165,25);
        panel.add(borrowBookText);

        JButton button = new JButton("Borrow");
        button.setActionCommand("borrowBookBorrowBook");
        button.addActionListener(this);
        button.setBounds(125,375,150,50);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: return a book
    private void doReturnBook() {
        if ((user.getBookborrowed().equals(""))) {
            JOptionPane.showMessageDialog(frame, "You have no book borrowed, cannot return book!");
        } else {
            bookShelf.returnBook(user);
            JOptionPane.showMessageDialog(frame, "User " + user.getUsername() + " has Returned the book!");
        }
    }

    // MODIFIES: this
    // EFFECTS: book a study room
    private void doBookStudyRoom() {
        doBookStudyRoomPanel = new JPanel();
        frame.add(doBookStudyRoomPanel);
        doBookStudyRoomPanel.setLayout(null);
        doBookStudyRoomPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doBookStudyRoomPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Book Study Room");
        title.setBounds(150,60,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Insert the room ID:");
        subTitle.setBounds(100,125,220,50);
        panel.add(subTitle);

        doBookStudyRoomHelper(panel);
    }

    // EFFECTS: a helper to book a study room
    private void doBookStudyRoomHelper(JPanel panel) {
        JLabel bookTitle = new JLabel("Room ID");
        bookTitle.setBounds(60, 250, 80, 25);
        panel.add(bookTitle);

        bookStudyRoomText = new JTextField(20);
        bookStudyRoomText.setBounds(160, 250, 165, 25);
        panel.add(bookStudyRoomText);

        JButton button = new JButton("Book");
        button.setActionCommand("bookStudyRoomBookStudyRoom");
        button.addActionListener(this);
        button.setBounds(125, 375, 150, 50);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: cancel book a study room
    private void doUnBookStudyRoom() {
        if ((user.getRoomBooked() == -1)) {
            JOptionPane.showMessageDialog(frame, "You have no study room booked,"
                    + " please book a room in advance to cancel the book!");
        } else {
            studyRooms.cancelBookStudyRoom(user);
            JOptionPane.showMessageDialog(frame, "User " + user.getUsername()
                    + " has Cancelled Room booking!");
        }
    }

    // MODIFIES: this
    // EFFECTS: add book to the BookShelf
    private void doAddBook() {
        doAddBookPanel = new JPanel();
        frame.add(doAddBookPanel);
        doAddBookPanel.setLayout(null);
        doAddBookPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doAddBookPanel.add(panel);
        panel.setBounds(250,60,500,650);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Add Book");
        title.setBounds(200,30,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Insert the Book Information:");
        subTitle.setBounds(150,90,220,50);
        panel.add(subTitle);

        JLabel bookTitle = new JLabel("title");
        bookTitle.setBounds(60,200,80,25);
        panel.add(bookTitle);

        addBookTitle = new JTextField(20);
        addBookTitle.setBounds(260,200,165,25);
        panel.add(addBookTitle);

        doAddBookHelper(panel);
    }

    // EFFECTS: a helper to add book to the BookShelf
    private void doAddBookHelper(JPanel panel) {
        JLabel bookYearPublished = new JLabel("year published");
        bookYearPublished.setBounds(60,275,200,25);
        panel.add(bookYearPublished);

        addBookYearPublished = new JTextField(20);
        addBookYearPublished.setBounds(260,275,165,25);
        panel.add(addBookYearPublished);

        JLabel bookCategory = new JLabel("category");
        bookCategory.setBounds(60,350,80,25);
        panel.add(bookCategory);

        addBookCategory = new JTextField(20);
        addBookCategory.setBounds(260,350,165,25);
        panel.add(addBookCategory);

        JLabel bookIsbn = new JLabel("ISBN");
        bookIsbn.setBounds(60,425,80,25);
        panel.add(bookIsbn);

        addBookIsbn = new JTextField(20);
        addBookIsbn.setBounds(260,425,165,25);
        panel.add(addBookIsbn);

        JButton button = new JButton("Add");
        button.setActionCommand("addBookAddBook");
        button.addActionListener(this);
        button.setBounds(175,525,150,50);
        panel.add(button);
    }

    // MODIFIES: this
    // EFFECTS: remove book from the BookShelf, given the book index on the BookShelf
    private void doRemoveBook() {
        doRemoveBookPanel = new JPanel();
        frame.add(doRemoveBookPanel);
        doRemoveBookPanel.setLayout(null);
        doRemoveBookPanel.setSize(1000, 800);

        JPanel panel = new JPanel();
        doRemoveBookPanel.add(panel);
        panel.setBounds(300,150,400,500);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(null);

        JLabel title = new JLabel("Remove Book");
        title.setBounds(150,60,290,50);
        panel.add(title);

        JLabel subTitle = new JLabel("Insert the index of the book:");
        subTitle.setBounds(100,125,220,50);
        panel.add(subTitle);

        doRemoveBookHelper(panel);
    }

    // EFFECTS: a helper to remove book from the BookShelf
    private void doRemoveBookHelper(JPanel panel) {
        JLabel bookTitle = new JLabel("index");
        bookTitle.setBounds(60, 250, 80, 25);
        panel.add(bookTitle);

        removeBookIndex = new JTextField(20);
        removeBookIndex.setBounds(160, 250, 165, 25);
        panel.add(removeBookIndex);

        JButton button = new JButton("Remove");
        button.setActionCommand("removeBookRemoveBook");
        button.addActionListener(this);
        button.setBounds(125, 375, 150, 50);
        panel.add(button);
    }

    // EFFECTS: return all book that is borrowed
    private void doGetBorrowedBook() {
        List<Book> borrowedBooks = bookShelf.getBorrowedBooks();
        if (borrowedBooks.size() == 0) {
            JOptionPane.showMessageDialog(frame, "No Books Borrowed!");
        } else {
            JPanel panel = new JPanel(new GridLayout(borrowedBooks.size(), 0));
            int index = 0;
            for (Book book : borrowedBooks) {
                JLabel label = new JLabel("index of " + index + ": " + book.getTitle()
                        + " has been booked by " + book.getBorrower());
                label.setSize(new Dimension(100, 100));
                label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                label.setOpaque(true);
                panel.add(label);
                index++;
            }
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.add(scrollPane);
            frame.setVisible(true);
        }
    }

    // EFFECTS: return all StudyRoom that is booked
    private void doGetBookedStudyRoom() {
        List<StudyRoom> listBookedRooms = studyRooms.listBookedRooms();
        if (listBookedRooms.size() == 0) {
            JOptionPane.showMessageDialog(frame, "No Rooms Booked!");
        } else {
            JPanel panel = new JPanel(new GridLayout(listBookedRooms.size(), 0));
            int index = 0;
            for (StudyRoom studyRoom : listBookedRooms) {
                JLabel label = new JLabel("Index of " + index + ": " + "Room ID " + studyRoom.getRoomID()
                        + ", Borrowed by " + studyRoom.getBooked());
                label.setSize(new Dimension(100, 100));
                label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                label.setOpaque(true);
                panel.add(label);
                index++;
            }

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.add(scrollPane);
            frame.setVisible(true);
        }
    }

    // EFFECTS: return all book in the BookShelf
    private void doGetAllBooks() {
        List<Book> bookList = bookShelf.getBookList();
        JPanel panel = new JPanel(new GridLayout((bookList.size() + 1), 0));
        int index = 0;
        for (Book book : bookList) {
            JLabel label = new JLabel("Index of: " + index + ": With title " + book.getTitle() + ", Published at "
                    + book.getYearPublished() + ", category " + book.getCategory() + ", ISBN " + book.getNumIsbn()
                    + ", Borrowed by " + book.getBorrower());
            label.setSize(new Dimension(100, 100));
            label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            label.setOpaque(true);
            panel.add(label);
            index++;
        }

        panel.add(new JLabel("Found " + index + " Books!", SwingConstants.CENTER));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // EFFECTS: return all StudyRoom in the Library
    private void doGetAllStudyRoom() {
        List<StudyRoom> studyRoomList = studyRooms.getListStudyRoom();
        JPanel panel = new JPanel(new GridLayout((studyRoomList.size() + 1), 0));
        int index = 0;
        for (StudyRoom studyRoom : studyRoomList) {
            Integer id = studyRoom.getRoomID();
            String borrower = studyRoom.getBooked();
            JLabel label = new JLabel("Index of " + index + ": " + "Room ID " + id + ", Borrowed by " + borrower);
            label.setSize(new Dimension(100, 100));
            label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            label.setOpaque(true);
            panel.add(label);
            index++;
        }

        panel.add(new JLabel("Found " + index + " Study Rooms!", SwingConstants.CENTER));

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,300);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    // EFFECTS: login for the user
    private void doLoginUser() {
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

    // EFFECTS: login for the libarian
    private void doLoginLibrarian() {
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

    // EFFECTS: register for the user
    private void doRegisterUser() {
        String user = userRegisterUsernameText.getText();
        String password = userRegisterPasswordText.getText();
        accounts.addUser(user, password);
        JOptionPane.showMessageDialog(frame, "Congrats, your User account has been made!");
        registerUserPanel.setVisible(false);
        welcomePanel.setVisible(true);
    }

    // EFFECTS: register for the librarian
    private void doRegisterLibrarian() {
        String user = librarianRegisterUsernameText.getText();
        String password = librarianRegisterPasswordText.getText();
        accounts.addLibrarian(user, password);
        JOptionPane.showMessageDialog(frame, "Congrats, your Librarian account has been made!");
        registerLibrarianPanel.setVisible(false);
        welcomePanel.setVisible(true);
    }

    // EFFECTS: find book based based on category mechanism
    private void findBookCategoryFind() {
        String pick = findBookCategoryText.getText();
        List<Book> bookList = bookShelf.findBookCategory(pick);
        if (bookList.size() == 0) {
            doFindBookCategoryPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Books not found! make sure to "
                    + "Input the listed category");
            welcomeUserPanel.setVisible(true);
        } else {
            findBookCategoryFindHelper(bookList);
        }
    }

    // EFFECTS: a helper to find book based on category mechanism
    private void findBookCategoryFindHelper(List<Book> bookList) {
        doFindBookCategoryPanel.setVisible(false);
        JPanel panel = new JPanel(new GridLayout((bookList.size() + 1), 0));
        int index = 0;
        for (Book book : bookList) {
            JLabel label = new JLabel("Index of: " + index + ": With title " + book.getTitle() + ", Published at "
                    + book.getYearPublished() + ", ISBN " + book.getNumIsbn());
            label.setSize(new Dimension(100, 100));
            label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
            label.setOpaque(true);
            panel.add(label);
            index++;
        }

        panel.add(new JLabel("Found " + index + " Books!", SwingConstants.CENTER));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,400);
        frame.add(scrollPane);
        frame.setVisible(true);
        welcomeUserPanel.setVisible(true);
    }

    // EFFECTS: find book based on year published mechanism
    private void findBookYearFind() {
        Integer year = Integer.parseInt(findBookYearPublishedText.getText());
        List<Book> bookList = bookShelf.findBookYear(year);
        if (bookList.size() == 0) {
            doFindBookYearPublishedPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Cannot Found Books with Given Published Year!");
            welcomeUserPanel.setVisible(true);
        } else {
            findBookYearFindHelper(bookList);
        }
    }

    // EFFECTS: a helper to find book based on year published mechanism
    private void findBookYearFindHelper(List<Book> bookList) {
        doFindBookYearPublishedPanel.setVisible(false);
        JPanel panel = new JPanel(new GridLayout(bookList.size() + 1, 0));
        int found = 1;
        for (Book book : bookList) {
            JLabel label = new JLabel(found + ". " + book.getTitle() + ", Retrieved from " + book.getCategory()
                    + ", ISBN: " + book.getNumIsbn(), SwingConstants.CENTER);
            label.setBackground(found % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            label.setOpaque(true);
            panel.add(label);
            found++;
        }
        panel.add(new JLabel("Found" + (found - 1) + " Books!", SwingConstants.CENTER));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(scrollPane);
        frame.setVisible(true);
        welcomeUserPanel.setVisible(true);
    }

    // EFFECTS: find book based on ISBN mechanism
    private void findBookIsbnFind() {
        String isbn = findBookIsbnText.getText();
        Book book = bookShelf.findBookIsbn(isbn);
        if (book == null) {
            doFindBookIsbnPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Cannot Found Book with Given ISBN Number!");
            welcomeUserPanel.setVisible(true);
        } else {
            doFindBookIsbnPanel.setVisible(false);
            JPanel panel = new JPanel(new GridLayout(2, 0));
            panel.add(new JLabel(book.getTitle() + ", Published at "
                    + book.getYearPublished(), SwingConstants.CENTER));
            panel.add(new JLabel("Found Book!", SwingConstants.CENTER));
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400,200);
            frame.add(panel);
            frame.setVisible(true);
            welcomeUserPanel.setVisible(true);
        }
    }

    // EFFECTS: confirm whether the user can borrow the book, otherwise display borrow menu
    private void doBorrowBookGoTo() {
        if (user.canBorrowBook()) {
            welcomeUserPanel.setVisible(false);
            doBorrowBook();
        } else {
            JOptionPane.showMessageDialog(frame, "You already borrowed a book, please return it"
                    + "in advance to borrow another book!");
        }
    }

    // EFFECTS: borrow book mechanism
    private void doBorrowBookBorrow() {
        String pick = borrowBookText.getText();
        if (bookShelf.isBookBorrowed(pick)) {
            bookShelf.borrowBook(user, pick);
            doBorrowBookPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "User " + user.getUsername()
                    + " has Borrowed book " + user.getBookborrowed() + " !");
            welcomeUserPanel.setVisible(true);
        } else {
            doBorrowBookPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "This book has been borrowed/Wrong ISBN number"
                    + ", perhaps borrow an other book?");
            welcomeUserPanel.setVisible(true);
        }
    }

    // EFFECTS: confirm whether the user can book the study room, otherwise display the book menu
    private void doBookStudyRoomGoTo() {
        if (user.canBookARoom()) {
            welcomeUserPanel.setVisible(false);
            doBookStudyRoom();
        } else {
            JOptionPane.showMessageDialog(frame, "You already booked a study room, please "
                    + "cancel it in advance to book another room!");
        }
    }

    // EFFECTS: book study room mechanism
    private void doBookStudyRoomBook() {
        Integer roomId = Integer.parseInt(bookStudyRoomText.getText());
        if (studyRooms.canTheRoomBooked(roomId)) {
            studyRooms.bookStudyRoom(user, roomId);
            doBookStudyRoomPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "User " + user.getUsername()
                    + " has Borrowed Room " + roomId + "!");
            welcomeUserPanel.setVisible(true);
        } else {
            doBookStudyRoomPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "This Room was Already Booked, Perhaps Find Another Room?");
            welcomeUserPanel.setVisible(true);
        }
    }

    // EFFECTS: add book mechanism
    private void doAddBookAdd() {
        String title = addBookTitle.getText();
        Integer yearPublished = Integer.parseInt(addBookYearPublished.getText());
        String category = addBookCategory.getText();
        String isbn = addBookIsbn.getText();
        Book newBook = new Book(title, yearPublished, category, isbn);
        bookShelf.addBook(newBook);
        doAddBookPanel.setVisible(false);
        JOptionPane.showMessageDialog(frame, "Book Has been Added!");
        welcomeLibrarianPanel.setVisible(true);
    }

    // EFFECTS: remove book mechanism
    private void doRemoveBookRemove() {
        welcomeLibrarianPanel.setVisible(false);
        Integer index = Integer.parseInt(removeBookIndex.getText());
        if (index < bookShelf.getBookList().size()) {
            bookShelf.remove(index);
            doRemoveBookPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Book Has been Removed!");
        } else {
            doRemoveBookPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Index not valid");
        }
        welcomeLibrarianPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: proceed all button in GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("loadLibrary")) {
            loadPanel.setVisible(false);
            loadAll();
            runLibraryHelper();
        } else if (e.getActionCommand().equals("noLoadLibrary")) {
            loadPanel.setVisible(false);
            init();
            runLibraryHelper();
        } else if (e.getActionCommand().equals("loginUserButton")) {
            welcomePanel.setVisible(false);
            userUI();
        } else if (e.getActionCommand().equals("loginLibrarianButton")) {
            welcomePanel.setVisible(false);
            librarianUI();
        } else if (e.getActionCommand().equals("registerUserButton")) {
            welcomePanel.setVisible(false);
            registerUserUI();
        } else if (e.getActionCommand().equals("registerLibrarianButton")) {
            welcomePanel.setVisible(false);
            registerLibrarianUI();
        } else {
            actionPerformedHelper(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: a helper to proceed all button in GUI
    private void actionPerformedHelper(ActionEvent e) {
        if (e.getActionCommand().equals("saveALlButton")) {
            saveAll();
        } else if (e.getActionCommand().equals("loginUserLogin")) {
            doLoginUser();
        } else if (e.getActionCommand().equals("loginLibrarianLogin")) {
            doLoginLibrarian();
        } else if (e.getActionCommand().equals("loginUserRegister")) {
            doRegisterUser();
        } else if (e.getActionCommand().equals("loginLibrarianRegister")) {
            doRegisterLibrarian();
        } else if (e.getActionCommand().equals("findBookCategory")) {
            welcomeUserPanel.setVisible(false);
            findBookCategory();
        } else if (e.getActionCommand().equals("findBookCategoryFind")) {
            findBookCategoryFind();
        } else if (e.getActionCommand().equals("findBookYearPublished")) {
            welcomeUserPanel.setVisible(false);
            findBookYear();
        } else if (e.getActionCommand().equals("findBookYearFind")) {
            findBookYearFind();
        } else {
            actionPerformedSecondHelper(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: a second helper to proceed all button in GUI
    private void actionPerformedSecondHelper(ActionEvent e) {
        if (e.getActionCommand().equals("findBookIsbn")) {
            welcomeUserPanel.setVisible(false);
            findBookIsbn();
        } else if (e.getActionCommand().equals("findBookIsbnFind")) {
            findBookIsbnFind();
        } else if (e.getActionCommand().equals("borrowBook")) {
            doBorrowBookGoTo();
        } else if (e.getActionCommand().equals("borrowBookBorrowBook")) {
            doBorrowBookBorrow();
        } else if (e.getActionCommand().equals("returnBook")) {
            doReturnBook();
        } else if (e.getActionCommand().equals("bookStudyRoom")) {
            doBookStudyRoomGoTo();
        } else if (e.getActionCommand().equals("bookStudyRoomBookStudyRoom")) {
            doBookStudyRoomBook();
        } else if (e.getActionCommand().equals("unBookStudyRoom")) {
            doUnBookStudyRoom();
        } else {
            actionPerformedThirdHelper(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: a third helper to proceed all button in GUI
    private void actionPerformedThirdHelper(ActionEvent e) {
        if (e.getActionCommand().equals("logOutUser")) {
            welcomeUserPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Logged out!");
            welcomePanel.setVisible(true);
        } else if (e.getActionCommand().equals("addBook")) {
            welcomeLibrarianPanel.setVisible(false);
            doAddBook();
        } else if (e.getActionCommand().equals("addBookAddBook")) {
            doAddBookAdd();
        } else if (e.getActionCommand().equals("removeBook")) {
            welcomeLibrarianPanel.setVisible(false);
            doRemoveBook();
        } else if (e.getActionCommand().equals("removeBookRemoveBook")) {
            doRemoveBookRemove();
        } else if (e.getActionCommand().equals("logOutLibrarian")) {
            welcomeLibrarianPanel.setVisible(false);
            JOptionPane.showMessageDialog(frame, "Logged out!");
            welcomePanel.setVisible(true);
        } else if (e.getActionCommand().equals("getBorrowedBook")) {
            doGetBorrowedBook();
        } else {
            actionPerformedFourthHelper(e);
        }
    }

    // MODIFIES: this
    // EFFECTS: a fourth helper to proceed all button in GUI
    private void actionPerformedFourthHelper(ActionEvent e) {
        if (e.getActionCommand().equals("getBookedRoom")) {
            doGetBookedStudyRoom();
        } else if (e.getActionCommand().equals("getAllBooks")) {
            doGetAllBooks();
        } else if (e.getActionCommand().equals("getAllRooms")) {
            doGetAllStudyRoom();
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
