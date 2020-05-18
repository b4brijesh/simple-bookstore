# A Simple Online BookStore
The purpose of this project is to build a simple online bookstore that users can use to search and buy books from. 

**Tech stack used**: SpringBoot application using Java 8 with H2 database (using Hibernate's JPA implementation), along with Spring validation libraries and Lombok annotation libraries, and Junit 5 for unit testing

## Schema
Every book has these mandatory attributes: ISBN, title, author, price and copies. There can be multiple copies of each book. For simplicity, it is assumed that all copies have the same price and other details.

## API endpoints
The functionality is limited to the back-end implementation of 4 basic APIs:
1. Search a book by ISBN/Author/Title: Separate endpoints have been implemented for ISBN, author and title based searches to keep things simple. Author and title searches can search for partial case-insensitive matches. ISBN being unique for each book, can return only 1 book entity at max, while author and title searches can return multiple books as JSON arrays.
2. Search media coverage about a book, given its ISBN: A public REST API located at https://jsonplaceholder.typicode.com/posts is consumed to search if the book's complete title is present in any of the posts' title or body. Any posts matching are returned as a JSON array.
3. Add a Book to the store - adds a book to the store if all mandatory attributes are provided. User can only add one copy of a book at a time.
4. Buy a book - If a valid book entity is provided, then the number of copies gets reduced by 1 and the user gets his book entity returned. While not all book attributes need to match, the ISBN is used to find the exact book from the store to return a copy of it. If no copies remain, a copy is added automatically.

## Steps to run
1. Clone the repository or download the ZIP and extract it.
2. **For development/debug/testing purposes**: The project can be imported into IntelliJ IDEA as a project and run from inside the IDE.
3. **Using Maven**: Build the Maven JAR and run it.
4. **Using Docker**: Build using the Dockerfile inside the repository and run it.
