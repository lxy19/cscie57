package edu.harvard.cscie57.jdbc.crud;

import edu.harvard.cscie57.jdbc.domain.Category;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllCategories extends MappingSqlQuery<Category> {
    private static final String SQL_SELECT_ALL_CATEGORY = "SELECT id, name FROM category";

    public FindAllCategories(DataSource ds) {
        super(ds, SQL_SELECT_ALL_CATEGORY);
    }

    @Override
    protected Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;
    }
}
