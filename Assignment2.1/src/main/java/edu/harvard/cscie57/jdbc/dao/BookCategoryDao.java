package edu.harvard.cscie57.jdbc.dao;

import edu.harvard.cscie57.jdbc.domain.Book;
import edu.harvard.cscie57.jdbc.domain.Category;

import java.util.List;

public interface BookCategoryDao {
    public List<Book> findBooksByCategoryName(String categoryName);
    public List<Category> findAllCategories();
    public List<Book> findAllBooks();
    public void addBook(Book book);
    public void updateInfo(Book book);
    public void deleteBook(int bookId);
}
