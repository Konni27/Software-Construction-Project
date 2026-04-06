package is.hi.hbv202g.assignment8.ui;

import is.hi.hbv202g.assignment8.exception.BookAlreadyBorrowedException;
import is.hi.hbv202g.assignment8.exception.UserOrBookDoesNotExistException;
import is.hi.hbv202g.assignment8.model.Book;
import is.hi.hbv202g.assignment8.model.Lending;
import is.hi.hbv202g.assignment8.model.User;
import is.hi.hbv202g.assignment8.service.LibrarySystem;

import java.util.List;
import java.util.Scanner;

public class LibraryTextUI {

    private final LibrarySystem librarySystem;
    private final Scanner scanner;


    public LibraryTextUI(LibrarySystem librarySystem) {
        this.librarySystem = librarySystem;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleAddStudent();
                    break;
                case "2":
                    handleAddFacultyMember();
                    break;
                case "3":
                    handleAddBook();
                    break;
                case "4":
                    handleFindBook();
                    break;
                case "5":
                    handleBorrowBook();
                    break;
                case "6":
                    handleReturnBook();
                    break;
                case "7":
                    showAllBooks();
                    break;
                case "8":
                    showAllUsers();
                    break;
                case "9":
                    showAllLendings();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting library system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Library System Menu ===");
        System.out.println("1. Add student user");
        System.out.println("2. Add faculty member user");
        System.out.println("3. Add book");
        System.out.println("4. Find book");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Show all books");
        System.out.println("8. Show all users");
        System.out.println("9. Show all lendings");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleAddStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Has the fee been paid? (true/false): ");
        boolean feePaid = Boolean.parseBoolean(scanner.nextLine());

        librarySystem.addStudentUser(name, feePaid);
    }

    private void handleAddFacultyMember() {
        System.out.print("Enter faculty member name: ");
        String name = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        librarySystem.addFacultyMemberUser(name, department);
    }

    private void handleAddBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();

        librarySystem.addBookWithTitleAndNameOfSingleAuthor(title, authorName);
    }


    private void handleFindBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        Book book = librarySystem.findBookByTitle(title);

        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Found book: " + book.getTitle());
        }
    }


    private void handleBorrowBook() {
        try {
            System.out.print("Enter user name: ");
            String userName = scanner.nextLine();

            System.out.print("Enter book title: ");
            String bookTitle = scanner.nextLine();

            User user = librarySystem.findUserByName(userName);
            Book book = librarySystem.findBookByTitle(bookTitle);

            librarySystem.borrowBook(user, book);
            System.out.println("Book borrowed successfully.");
        } catch (UserOrBookDoesNotExistException | BookAlreadyBorrowedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleReturnBook() {
        try {
            System.out.print("Enter user name: ");
            String userName = scanner.nextLine();

            System.out.print("Enter book title: ");
            String bookTitle = scanner.nextLine();

            User user = librarySystem.findUserByName(userName);
            Book book = librarySystem.findBookByTitle(bookTitle);

            librarySystem.returnBook(user, book);
            System.out.println("Book returned successfully.");
        } catch (UserOrBookDoesNotExistException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showAllBooks() {
        List<Book> books = librarySystem.getBooks();

        if (books.isEmpty()) {
            System.out.println("No books in the system.");
            return;
        }

        System.out.println("Books:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle());
        }
    }

    private void showAllUsers() {
        List<User> users = librarySystem.getUsers();

        if (users.isEmpty()) {
            System.out.println("No users in the system.");
            return;
        }

        System.out.println("Users:");
        for (User user : users) {
            System.out.println("- " + user.getName());
        }
    }

    private void showAllLendings() {
        List<Lending> lendings = librarySystem.getLendings();

        if (lendings.isEmpty()) {
            System.out.println("No active lendings.");
            return;
        }

        System.out.println("Active lendings:");
        for (Lending lending : lendings) {
            System.out.println("- " + lending.getBook().getTitle()
                    + " borrowed by "
                    + lending.getUser().getName()
                    + ", due: "
                    + lending.getDueDate());
        }
    }
}