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