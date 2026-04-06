# Design Documentation

## Overview

The project is a text-based library management system with a clear separation between business logic and user interface.

## Main Components

- `model`: domain classes
- `service`: library operations and rules
- `ui`: console-based user interaction
- `observer`: event notification system
- `exception`: custom exceptions for invalid operations

## Business Logic

The main business logic is implemented in `LibrarySystem`.  
It stores books, users, lendings and observers and provides methods for borrowing, returning and extending lendings.

## User Interface

The `LibraryTextUI` class provides a textual menu for interacting with the system.
