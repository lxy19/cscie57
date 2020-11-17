package edu.lix8.spring.jpa2.service;

import edu.lix8.spring.jpa2.domain.Book;

import java.util.List;

public interface PublishingService {
    List<Book> findAllBooks();
    List<Book> findAllBooksWithAuthorsAndCategories();
    Book findBookWithAuthorsAndCategoryById(int bookId);
    List<Book> findAllBooksWithCategoryAndAuthor(int authorId);
    Book createNewBook(Book book);
    void deleteBook(Book book);
    List<Book> findAllBooksByNativeQuery();
}
