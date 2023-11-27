# Library Kiosk Software

### A Software that Allows User to Utilize Library's Features

A simplified version of Library Management Software operated in Java that allow the library visitor to access 
library features more efficiently. This software might be used in the public library or school library and the library
visitor might want to make an account to get full access to the kiosk machine. This project interest me because
I used to deal with library in manual manner, sometime it took me more time to que rather than actually study or
read book there.

User Stories:
- As a user:
    - To be able to **make an account** containing Username, Password, List of borrowed book, and study room booking 
  information.
    - to be able to search book in the library based on category, year published, and ISBN.
    - to be able to **borrow** book(s) from the library. (just 1 book for now)
    - to be able to **return** book(s) from the library. (just 1 book for now)
    - to be able to **book** the study room inside the library.
    - to be able to **cancel book** of the study room inside the library.
    - to be able to **log out** and back to the main interface.
    - to be able to **save** every change that the user made
    - to be able to **load** the existing library
  
- As a librarian:
    - To be able to **make an account** containing Username and Password.
    - To be able to **track** list borrowed books.
    - To be able to **track** list booked rooms.
    - To be able to **add** book collection from the library's inventory.
    - To be able to **remove** book collection from the library's inventory.
    - To be able to **track** list of books and its information.
    - To be able to **track** list of rooms and its information.
    - To be able to **log out** and back to the main interface. 
    - to be able to **save** every change that the librarian made
    - to be able to **load** the existing library

# Instructions for Grader

- You can add Book to a BookShelf by register->login as a librarian -> add Book. Input correct information of the book.
- You can remove Book in the BookShelf by login as a librarian -> remove Book. Make sure to know the book index.
- You can locate my visual component by see the app logo, go to welcome panel, or in user/librarian welcome panel.
- You can save the state of my application by click Save all changes button in welcome panel.
- You can reload the state of my application by run, and select yes to load an existing library.

# Phase 4: Task 2
Sun Nov 26 17:43:49 PST 2023
Event log cleared.

Sun Nov 26 17:44:06 PST 2023
Added User: naufal

Sun Nov 26 17:44:15 PST 2023
Login User naufal success

Sun Nov 26 17:44:17 PST 2023
Display books category

Sun Nov 26 17:44:21 PST 2023
Display all books with collosal category

Sun Nov 26 17:44:27 PST 2023
Display all books from 2020

Sun Nov 26 17:44:32 PST 2023
Display all books from 2000

Sun Nov 26 17:45:00 PST 2023
Display all books from 2000

Sun Nov 26 17:45:08 PST 2023
Book Fundamental Astronomy borrowed by naufal

Sun Nov 26 17:45:10 PST 2023
Book Fundamental Astronomy returned by naufal

Sun Nov 26 17:45:14 PST 2023
Study room 4 booked by naufal

Sun Nov 26 17:45:16 PST 2023
Study room 4 cancelled by naufal

Sun Nov 26 17:45:26 PST 2023
Login Librarian helmi success

Sun Nov 26 17:45:43 PST 2023
Book mars asia added to bookshelf

Sun Nov 26 17:45:48 PST 2023
Display all books

Sun Nov 26 17:45:48 PST 2023
Book dany removed to bookshelf

# Phase 4: Task 3

If I have more time, I would like to re-factor my LibraryGUI class into multiple classes to improve the cohesion and
to adhere the single responsibility principle. This may be done by splitting each panel (to load, to login for each 
user and librarian, and menu for user and librarian) to separated class.


