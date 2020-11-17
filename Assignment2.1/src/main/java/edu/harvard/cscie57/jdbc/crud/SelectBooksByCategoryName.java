package edu.harvard.cscie57.jdbc.crud;

import edu.harvard.cscie57.jdbc.domain.Book;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectBooksByCategoryName {
    private static final String sql = "SELECT b.id, b.category_id, b.isbn, b.title, b.price FROM book b, category c WHERE b.category_id = c.id and c.name = :name";
    private DataSource dataSource;

    public SelectBooksByCategoryName(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Book> getBooks(String categoryName) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("name", categoryName);
        return namedParameterJdbcTemplate.query(sql, namedParameters, rs->{
            Map<Integer, Book> map = new HashMap<>();
            Book book;
            while (rs.next()) {
                Integer id = rs.getInt("id");
                book = map.get(id);
                if (book==null) {
                    book = new Book();
                    book.setId(id);
                    book.setCategory_id(rs.getInt("category_id"));
                    book.setIsbn(rs.getString("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setPrice(rs.getFloat("price"));
                    map.put(id, book);
                }
            }
            return new ArrayList<>(map.values());
        });
    }
}
