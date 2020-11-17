package edu.harvard.cscie57.jdbc.crud;

import edu.harvard.cscie57.jdbc.domain.Book;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllBooks extends MappingSqlQuery<Book> {
    private static final String SQL_SELECT_ALL_BOOK = "SELECT id, category_id, isbn, price, title FROM book";

    public FindAllBooks(DataSource ds) {
        super(ds, SQL_SELECT_ALL_BOOK);
    }

    @Override
    protected Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setCategory_id(resultSet.getInt("category_id"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setPrice(resultSet.getFloat("price"));
        book.setTitle(resultSet.getString("title"));
        return book;
    }
}
