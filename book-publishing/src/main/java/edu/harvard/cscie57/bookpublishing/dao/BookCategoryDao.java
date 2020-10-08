package edu.harvard.cscie57.bookpublishing.dao;

import edu.harvard.cscie57.bookpublishing.domain.Book;
import edu.harvard.cscie57.bookpublishing.domain.Category;

import java.util.List;

public interface BookCategoryDao {
    public List<Book> findBooksByCategoryName(String categoryName);
    public List<Category> findAllCategories();
    public List<Book> findAllBooks();
    public void addBook(Book book);
    public void updateInfo(Book book, Category category);
    public void deleteBook();
}
