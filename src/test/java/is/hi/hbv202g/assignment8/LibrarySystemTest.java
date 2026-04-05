package is.hi.hbv202g.assignment8;

import is.hi.hbv202g.assignment8.service.LibrarySystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibrarySystemTest {

    @Test
    public void addStudentUserShouldIncreaseUserCount() {
        LibrarySystem librarySystem = new LibrarySystem();

        librarySystem.addStudentUser("Anna", true);

        assertEquals(1, librarySystem.getUsers().size());
    }
}
