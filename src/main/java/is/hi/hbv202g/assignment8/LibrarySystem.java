package is.hi.hbv202g.assignment8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class LibrarySystem {

    private List<Book> books;
    private List<Lending> lendings;
    private List<User> users;
    public LibrarySystem(){
        books = new ArrayList<>();
        lendings = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBookWithTitleAndNameOfSingleAuthor(String title, String authorName){
        books.add(new Book(title,authorName));
    }

    public void addBookWithTitleAndAuthorList(String title, List<Author> authors) throws EmptyAuthorListException {
        books.add(new Book(title,authors));

    }

    public void addStudentUser(String name, boolean feePaid){
        users.add(new Student(name,feePaid));

    }

    public void addFacultyMemberUser(String name, String department){
        users.add(new FacultyMember(name,department));

    }

    public Book findBookByTitle(String title){
        return null;
    }

    public User findUserByName(String name) {
        return null;
    }

    public void borrowBook(User user, Book book) throws UserOrBookDoesNotExistException {
    }

    public void extendLending(FacultyMember facultyMember, Book book, LocalDate newDueDate)
            throws UserOrBookDoesNotExistException {
    }

    public void returnBook(User user, Book book) throws UserOrBookDoesNotExistException {
    }
}
