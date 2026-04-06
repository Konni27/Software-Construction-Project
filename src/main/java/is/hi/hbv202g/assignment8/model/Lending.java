package is.hi.hbv202g.assignment8.model;

import is.hi.hbv202g.assignment8.model.User;

import java.time.LocalDate;

public class Lending {
    private LocalDate dueDate;
    private Book book;
    private User user;

    /**
     * Lending.
     */
    public Lending(Book book, User user){
        this.book = book;
        this.user = user;
        this.dueDate = LocalDate.now().plusDays(30);
    }

    /**
     * Time of return date.
     */
    public LocalDate getDueDate(){
        return dueDate;
    }

    /**
     * Set return date.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Get book.
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set Book.
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get User for Lending.
     */
    public User getUser() {
        return user;
    }

    /**
     * Set User for Lending.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
