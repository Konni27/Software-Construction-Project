# HBV202GAssignment8
A Maven project skeleton. The provided Maven POM sets the Java version to 21.

All classes need to be located in Java package `is.hi.hbv202g.assignment8`.

Class and method names and parameter names and types of the submitted solution need to be **exactly** the same 
as in the UML class diagram in the assignment PDF. 

If you like, you can import from project directory `UML` the file 
`library_system.uxf` into the online UML editor https://www.umletino.com/ 
and copy/paste from there class and method names: click on a class and then, 
you can copy from the `properties` window on the right hand side class 
and method names.

Maven:

- `mvn compile` compiles all implementation classes.
- `mvn exec:java` executes the `main` method of the implementation, however that creates only an instance of the `LibrarySystem` class, but nothing more.
- `mvn test` runs test cases, however the that creates only an instance of the `LibrarySystem` class, but nothing more.
