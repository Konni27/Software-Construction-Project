package is.hi.hbv202g.assignment8.model;

import is.hi.hbv202g.assignment8.exception.EmptyAuthorListException;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;

    /**
     * Book and one Author.
     */
    public Book(String title, String  authorName){
        this.title = title;
        this.authors = new ArrayList<>();
        this.authors.add(new Author(authorName));
    }

    /**
     * Book and Authors.
     */
    public Book(String title, List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()){
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.title = title;
        this.authors = authors;

    }

    /**
     * List of Author.
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     *  Set Author.
     */
    public void setAuthors(List<Author> authors) throws EmptyAuthorListException {
        if (authors == null || authors.isEmpty()) {
            throw new EmptyAuthorListException("Author list cannot be empty");
        }
        this.authors = authors;
    }

    /**
     * Add Author.
     */
    public void addAuthors(Author author){
        authors.add(author);

    }

    /**
     * Get Book Title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set Book Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }


}
