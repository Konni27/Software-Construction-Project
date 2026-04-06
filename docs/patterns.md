# Design Patterns

## Observer Pattern

This project uses the Observer pattern to notify interested objects about changes in the library system.

### Roles

- **Subject:** `LibrarySystem`
- **Observer interface:** `LibraryObserver`
- **Concrete observer:** `ConsoleLoggerObserver`

### Usage

Whenever an important event happens, such as adding a book or borrowing a book, `LibrarySystem` sends a message to all registered observers.

This keeps notification behavior separate from the core business logic.
