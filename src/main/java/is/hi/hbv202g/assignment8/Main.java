package is.hi.hbv202g.assignment8;

import is.hi.hbv202g.assignment8.service.LibrarySystem;
import is.hi.hbv202g.assignment8.observer.ConsoleLoggerObserver;
import is.hi.hbv202g.assignment8.ui.LibraryTextUI;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addObserver(new ConsoleLoggerObserver());

        LibraryTextUI textUI = new LibraryTextUI(librarySystem);
        textUI.start();
    }
}
