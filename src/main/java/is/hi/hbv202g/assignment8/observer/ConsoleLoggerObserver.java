package is.hi.hbv202g.assignment8.observer;

public class ConsoleLoggerObserver implements LibraryObserver {

    /**
     * Library Change Observer.
     */
    @Override
    public void onLibraryChanged(String message) {
        System.out.println("[Library Event]" + message);
    }
}
