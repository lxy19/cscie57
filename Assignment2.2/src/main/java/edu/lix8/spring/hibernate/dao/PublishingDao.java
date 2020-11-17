package edu.lix8.spring.hibernate.dao;

import edu.lix8.spring.hibernate.domain.Book;

import java.util.List;

public interface PublishingDao {
    List<Book> findAllBooksWithoutAuthorsAndCategories();
    List<Book> findAllBooksWithAuthorsAndCategories();
    Book findBookWithAuthorsAndCategoryById(int bookId);
    List<Book> findAllBooksWithCategoryAndAuthor(int authorId);
    Book createNewBook(Book book);
    void deleteBook(Book book);
}
