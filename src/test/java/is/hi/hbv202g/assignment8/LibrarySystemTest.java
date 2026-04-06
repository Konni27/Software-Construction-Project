package is.hi.hbv202g.assignment8;

import is.hi.hbv202g.assignment8.exception.BookAlreadyBorrowedException;
import is.hi.hbv202g.assignment8.exception.UserOrBookDoesNotExistException;
import is.hi.hbv202g.assignment8.model.Book;
import is.hi.hbv202g.assignment8.model.User;
import is.hi.hbv202g.assignment8.service.LibrarySystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarySystemTest {

    private LibrarySystem librarySystem;

    /**
     * Set up Library System.
     */
    @BeforeEach
    public void setUp() {
        librarySystem = new LibrarySystem();
    }

    /**
     * Add Student User Should Increase User Count.
     */
    @Test
    public void addStudentUserShouldIncreaseUserCount() {
        librarySystem.addStudentUser("Anna", true);

        assertEquals(1, librarySystem.getUsers().size());
    }

    /**
     * Add Book Should Increase Book Count.
     */
    @Test
    public void addBookShouldIncreaseBookCount() {
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        assertEquals(1, librarySystem.getBooks().size());
    }

    /**
     * Find Book By Title Should Return Correct Book.
     */
    @Test
    public void findBookByTitleShouldReturnCorrectBook() {
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        Book book = librarySystem.findBookByTitle("Clean Code");

        assertNotNull(book);
        assertEquals("Clean Code", book.getTitle());
    }

    /**
     * Find Book By Title Should Return Null When Missing.
     */
    @Test
    public void findBookByTitleShouldReturnNullWhenMissing() {
        Book book = librarySystem.findBookByTitle("Unknown Book");

        assertNull(book);
    }

    /**
     * Find User By Name Should Return Correct User.
     */
    @Test
    public void findUserByNameShouldReturnCorrectUser() {
        librarySystem.addStudentUser("Anna", true);

        User user = librarySystem.findUserByName("Anna");

        assertNotNull(user);
        assertEquals("Anna", user.getName());
    }

    /**
     * Borrow Book Should Create Lending.
     */
    @Test
    public void borrowBookShouldCreateLending() throws UserOrBookDoesNotExistException, BookAlreadyBorrowedException {
        librarySystem.addStudentUser("Anna", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        User user = librarySystem.findUserByName("Anna");
        Book book = librarySystem.findBookByTitle("Clean Code");

        librarySystem.borrowBook(user, book);

        assertEquals(1, librarySystem.getLendings().size());
        assertTrue(librarySystem.isBookBorrowed(book));
    }

    /**
     * Borrowing Already Borrowed Book Should Throw Exception.
     */
    @Test
    public void borrowingAlreadyBorrowedBookShouldThrowException()
            throws UserOrBookDoesNotExistException, BookAlreadyBorrowedException {
        librarySystem.addStudentUser("Anna", true);
        librarySystem.addStudentUser("Jon", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        User firstUser = librarySystem.findUserByName("Anna");
        User secondUser = librarySystem.findUserByName("Jon");
        Book book = librarySystem.findBookByTitle("Clean Code");

        librarySystem.borrowBook(firstUser, book);

        assertThrows(BookAlreadyBorrowedException.class, () -> {
            librarySystem.borrowBook(secondUser, book);
        });
    }

    /**
     * Return Book Should Remove Lending.
     */
    @Test
    public void returnBookShouldRemoveLending() throws UserOrBookDoesNotExistException, BookAlreadyBorrowedException {
        librarySystem.addStudentUser("Anna", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        User user = librarySystem.findUserByName("Anna");
        Book book = librarySystem.findBookByTitle("Clean Code");

        librarySystem.borrowBook(user, book);
        librarySystem.returnBook(user, book);

        assertEquals(0, librarySystem.getLendings().size());
        assertFalse(librarySystem.isBookBorrowed(book));
    }

    /**
     * Return Book That Is Not Borrowed Should Throw Exception.
     */
    @Test
    public void returningBookThatIsNotBorrowedShouldThrowException() {
        librarySystem.addStudentUser("Anna", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Clean Code", "Robert Martin");

        User user = librarySystem.findUserByName("Anna");
        Book book = librarySystem.findBookByTitle("Clean Code");

        assertThrows(UserOrBookDoesNotExistException.class, () -> {
            librarySystem.returnBook(user, book);
        });
    }
}
