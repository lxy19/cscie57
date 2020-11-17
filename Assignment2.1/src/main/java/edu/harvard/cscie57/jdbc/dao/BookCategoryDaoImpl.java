package edu.harvard.cscie57.jdbc.dao;

import edu.harvard.cscie57.jdbc.crud.*;
import edu.harvard.cscie57.jdbc.domain.Book;
import edu.harvard.cscie57.jdbc.domain.Category;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("BookCategoryDao")
public class BookCategoryDaoImpl implements BookCategoryDao {
    private static final Log logger = LogFactory.getLog(BookCategoryDaoImpl.class);
    private DataSource dataSource;
    private FindAllBooks findAllBooks;
    private FindAllCategories findAllCategories;
    private SelectBooksByCategoryName selectBooksByCategoryName;
    private InsertBook insertBook;
    private DeleteBook deleteBook;
    private UpdateBook updateBook;

    @Override
    public List<Book> findBooksByCategoryName(String categoryName) {
      return selectBooksByCategoryName.getBooks(categoryName);
    }

    /**
     * Using findAllCategories crud operation
     * @return List<Category>
     */
    @Override
    public List<Category> findAllCategories() {
        return findAllCategories.execute();
    }

    /**
     * Using findAllBooks crud operation
     * @return List<Book>
     */
    @Override
    public List<Book> findAllBooks() {
        return findAllBooks.execute();
    }

    @Override
    public void addBook(Book book) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("category_id", book.getCategory_id());
        paramMap.put("isbn", book.getIsbn());
        paramMap.put("price", book.getPrice());
        paramMap.put("title", book.getTitle());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertBook.updateByNamedParam(paramMap, keyHolder);
        book.setId(keyHolder.getKey().intValue());
        logger.info("Inserted a new book with id: " + book.getId());
    }


    @Override
    public void updateInfo(Book book) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("price", book.getPrice());
        paramMap.put("id", book.getId());
        updateBook.updateByNamedParam(paramMap);
        logger.info("Existing book updated with id: " + book.getId());
    }

    @Override
    public void deleteBook(int bookId) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("id", bookId);
        deleteBook.updateByNamedParam(paramMap);
        logger.info(String.format("Deleted book with id: ." + bookId));
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.findAllBooks = new FindAllBooks(dataSource);
        this.findAllCategories = new FindAllCategories(dataSource);
        this.insertBook = new InsertBook(dataSource);
        this.selectBooksByCategoryName = new SelectBooksByCategoryName(dataSource);
        this.deleteBook = new DeleteBook(dataSource);
        this.updateBook = new UpdateBook(dataSource);
    }

}
