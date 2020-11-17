package edu.lix8.spring.jpa2.service;

import edu.lix8.spring.jpa2.domain.Book;

import java.util.List;

public interface PublishingService {
    Book findBookWithAuthorsAndCategoryById(int bookId);
    List<Book> findAllBooks();
    Book createNewBook(Book book);
    void deleteBook(Book book);
}
