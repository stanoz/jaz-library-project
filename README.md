# JAZ Library Project

This project is a web API for a library system, developed using Java, Spring Boot, and Maven.

## Project Overview

This project interacts with a public API to retrieve data, which is then transferred to a PostgreSQL database. The project provides an API to modify this data. It also includes a user interface for easier interaction with the system. Additionally, the project generates logs for monitoring and debugging purposes.

## Features

The API provides the following features:

- Book Management: Add, edit, delete, and retrieve books.
- Author Management: Add, edit, delete, and retrieve authors.
- Subject Management: Add, edit, delete, and retrieve subjects.
- Language Management: Add, edit, delete, and retrieve languages.
- Bookshelves Management: Add, edit, delete, and retrieve bookshelves.

## API Endpoints

1. **Book Management**:
   - `GET /api/books/show-all`: Retrieve all books.
   - `GET /api/books/book-details/{id}`: Retrieve a specific book by its ID.
   - `PUT /api/books/edit-book/{id}`: Edit a specific book by its ID.
   - `POST /api/books/add-book`: Add a new book.
   - `DELETE /api/books/delete-book/{id}`: Delete a specific book by its ID.

2. **Author Management**:
   - `GET /api/books/authors`: Retrieve all authors.
   - `GET /api/books/author/{authorId}`: Retrieve a specific author by its ID.
   - `GET /api/books/authors-from-book/{bookId}`: Retrieve all authors from a specific book.
   - `PUT /api/books/edit-author/{authorId}/{bookId}`: Edit a specific author from a specific book.
   - `POST /api/books/add-author/{bookId}`: Add a new author to a specific book.
   - `DELETE /api/books/delete-author/{bookId}/{authorId}`: Delete a specific author from a specific book.

3. **Subject Management**:
   - `GET /api/books/subjects`: Retrieve all subjects.
   - `GET /api/books/subject/{subjectId}`: Retrieve a specific subject by its ID.
   - `GET /api/books/subjects-from-book/{bookId}`: Retrieve all subjects from a specific book.
   - `PUT /api/books/edit-subject/{bookId}`: Edit a specific subject from a specific book.
   - `POST /api/books/add-subject/{bookId}`: Add a new subject to a specific book.
   - `DELETE /api/books/delete-subject/{bookId}/{subjectId}`: Delete a specific subject from a specific book.

4. **Language Management**:
   - `GET /api/books/languages`: Retrieve all languages.
   - `GET /api/books/language/{languageId}`: Retrieve a specific language by its ID.
   - `GET /api/books/languages-from-book/{bookId}`: Retrieve all languages from a specific book.
   - `PUT /api/books/edit-language/{bookId}`: Edit a specific language from a specific book.
   - `POST /api/books/add-language/{bookId}`: Add a new language to a specific book.
   - `DELETE /api/books/delete-language/{bookId}/{languageId}`: Delete a specific language from a specific book.

5. **Bookshelves Management**:
   - `GET /api/books/bookshelves`: Retrieve all bookshelves.
   - `GET /api/books/bookshelves/{bookshelvesId}`: Retrieve a specific bookshelf by its ID.
   - `GET /api/books/bookshelves-from-book/{bookId}`: Retrieve all bookshelves from a specific book.
   - `PUT /api/books/edit-bookshelves/{bookId}`: Edit a specific bookshelf from a specific book.
   - `POST /api/books/add-bookshelves/{bookId}`: Add a new bookshelf to a specific book.
   - `DELETE /api/books/delete-bookshelves/{bookId}/{bookshelvesId}`: Delete a specific bookshelf from a specific book.

6. **Logs Management**:
   - `GET /service/logs/download`: Download the logs.

## Code Structure

The project is divided into several modules, each with a specific purpose and functionality.

1. `library_web_api`: This module contains the controllers and services for the web API. It handles the HTTP requests and responses, and contains the business logic for the application. The `BookApiController` and `BookService` classes are part of this module.

2. `tools`: This module provides utility services like logging. It includes the `logger` package, which contains the services and controllers for managing logs, such as the `LoggerController` class.

3. `library_client`: This module is responsible for interacting with the external public API to retrieve data. It includes classes like `BooksClient` and `BooksClientSettings`.

4. `library_data`: This module interacts with the database. It contains the JPA repositories for accessing and manipulating the data in the database. Classes like `AuthorRepository` and `BookRepository` are part of this module.

5. `library_updater`: This module is responsible for updating the data in the application. It includes classes like `BooksUpdater`, which updates the books data by interacting with the public API and the database.
