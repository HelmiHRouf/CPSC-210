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

# UML diagram

<img src="UML%20diagram.png" width="700"/>

# Phase 4: Task 2
Tue Nov 28 10:45:10 PST 2023
Added User: joe

Tue Nov 28 10:45:10 PST 2023
Added User: usertest

Tue Nov 28 10:45:10 PST 2023
Added User: fdasfas

Tue Nov 28 10:45:10 PST 2023
Added User: bblasius

Tue Nov 28 10:45:10 PST 2023
Added Librarian: sam

Tue Nov 28 10:45:10 PST 2023
Added Librarian: helmi

Tue Nov 28 10:45:10 PST 2023
Added Librarian: asfasfas

Tue Nov 28 10:45:10 PST 2023
Added Librarian: mamang

Tue Nov 28 10:45:10 PST 2023
Book Fundamental Astronomy added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book I hope I can fly added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book World War 2 Explained added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book Elementary C++ added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book How Can I get Her added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book This is a Book added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book blasius added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book dany added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book seblak added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book local bussines added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Book winter jacket added to bookshelf

Tue Nov 28 10:45:10 PST 2023
Added Study Room 0

Tue Nov 28 10:45:10 PST 2023
Added Study Room 1

Tue Nov 28 10:45:10 PST 2023
Added Study Room 2

Tue Nov 28 10:45:10 PST 2023
Added Study Room 3

Tue Nov 28 10:45:10 PST 2023
Added Study Room 4

Tue Nov 28 10:45:10 PST 2023
Added Study Room 5

Tue Nov 28 10:45:10 PST 2023
Added Study Room 6

Tue Nov 28 10:45:17 PST 2023
Login User joe success

Tue Nov 28 10:45:18 PST 2023
Display books category

Tue Nov 28 10:45:21 PST 2023
Display all books with fiction category

Tue Nov 28 10:45:28 PST 2023
Display all books from 2000

Tue Nov 28 10:46:04 PST 2023
Display book with ISBN: 10201020

Tue Nov 28 10:46:25 PST 2023
Book dany returned by joe

Tue Nov 28 10:46:31 PST 2023
Study room 1 booked by joe

Tue Nov 28 10:46:34 PST 2023
Study room 1 cancelled by joe

Tue Nov 28 10:46:36 PST 2023
Display books category

Tue Nov 28 10:46:43 PST 2023
Display all books with gak mandi category

Tue Nov 28 10:47:16 PST 2023
Login Librarian sam success

Tue Nov 28 10:47:36 PST 2023
Book farrel added to bookshelf

Tue Nov 28 10:47:38 PST 2023
Display all books

Tue Nov 28 10:47:50 PST 2023
Display all study rooms that is booked

Tue Nov 28 10:47:53 PST 2023
Display all borrowed books

Tue Nov 28 10:47:58 PST 2023
Display all books

Tue Nov 28 10:47:58 PST 2023
Book winter jacket removed to bookshelf

# Phase 4: Task 3

If I have more time, I would like to re-factor my LibraryGUI class into multiple classes to improve the cohesion and
to adhere the single responsibility principle. This may be done by splitting each panel (to load, to login for each 
user and librarian, and menu for user and librarian) to separated class.


