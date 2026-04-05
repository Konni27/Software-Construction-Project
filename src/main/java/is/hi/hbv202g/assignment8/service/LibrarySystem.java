package is.hi.hbv202g.assignment8.service;

import is.hi.hbv202g.assignment8.exception.BookAlreadyBorrowedException;
import is.hi.hbv202g.assignment8.exception.EmptyAuthorListException;
import is.hi.hbv202g.assignment8.exception.UserOrBookDoesNotExistException;
import is.hi.hbv202g.assignment8.model.*;
import is.hi.hbv202g.assignment8.observer.LibraryObserver;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class LibrarySystem {

    private List<is.hi.hbv202g.assignment8.model.Book> books;
    private List<is.hi.hbv202g.assignment8.model.Lending> lendings;
    private List<is.hi.hbv202g.assignment8.model.User> users;
    private List<LibraryObserver> observers;

    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.lendings = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (LibraryObserver observer : observers) {
            observer.onLibraryChanged(message);
        }
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName) {
        Book book = new Book(title, authorName);
        books.add(book);
        notifyObservers("Added book: " + title);
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        Book book = new Book(title, authors);
        books.add(book);
        notifyObservers("Added book: " + title);
    }

    public void addStudentUser(String name, boolean feePaid) {
        users.add(new is.hi.hbv202g.assignment8.model.Student(name, feePaid));
        notifyObservers("Added student user: " + name);

    }

    public void addFacultyMemberUser(String name, String department) {
        users.add(new is.hi.hbv202g.assignment8.model.FacultyMember(name, department));
        notifyObservers("Added faculty member user: " + name);

    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException, BookAlreadyBorrowedException {
        if (user == null || book == null || !users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }

        if (isBookBorrowed(book)) {
            throw new BookAlreadyBorrowedException("Book is already borrowed.");
        }

        lendings.add(new Lending(book, user));
        notifyObservers("Book borrowed: " + book.getTitle() + " by " + user.getName());
    }


    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
            throws UserOrBookDoesNotExistException {
        if (facultyMember == null || book == null || !users.contains(facultyMember) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }

        for (Lending lending : lendings) {
            if (lending.getUser().equals(facultyMember) && lending.getBook().equals(book)) {
                lending.setDueDate(newDueDate);
                notifyObservers("Lending extended for book: " + book.getTitle() + " until " + newDueDate);
                return;
            }
        }

        throw new UserOrBookDoesNotExistException("Lending does not exist.");
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
        if (user == null || book == null || !users.contains(user) || !books.contains(book)) {
            throw new UserOrBookDoesNotExistException("User or book does not exist.");
        }

        Lending lendingToRemove = null;

        for (Lending lending : lendings) {
            if (lending.getUser().equals(user) && lending.getBook().equals(book)) {
                lendingToRemove = lending;
                break;
            }
        }

        if (lendingToRemove == null) {
            throw new UserOrBookDoesNotExistException("Lending does not exist.");
        }

        lendings.remove(lendingToRemove);
        notifyObservers("Book returned: " + book.getTitle() + " by " + user.getName());
    }

    public boolean isBookBorrowed(Book book) {
        for (Lending lending : lendings) {
            if (lending.getBook().equals(book)) {
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Lending> getLendings() {
        return lendings;
    }

}

