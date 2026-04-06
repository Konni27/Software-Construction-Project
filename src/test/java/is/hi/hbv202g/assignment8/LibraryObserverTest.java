package is.hi.hbv202g.assignment8;

import is.hi.hbv202g.assignment8.observer.LibraryObserver;
import org.junit.jupiter.api.Test;
import is.hi.hbv202g.assignment8.service.LibrarySystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryObserverTest {
    @Test
    public void observerShouldReceiveNotificationWhenStudentIsAdded() {
        LibrarySystem librarySystem = new LibrarySystem();
        TestObserver observer = new TestObserver();

        librarySystem.addObserver(observer);
        librarySystem.addStudentUser("Anna", true);

        assertEquals("Added student user: Anna", observer.getLastMessage());
    }

    private static class TestObserver implements LibraryObserver {
        private String lastMessage;

        @Override
        public void onLibraryChanged(String message) {
            lastMessage = message;
        }

        public String getLastMessage() {
            return lastMessage;
        }
    }
}
