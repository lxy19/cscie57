package edu.harvard.cscie57.jdbc.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class InsertBook extends SqlUpdate {
    private final static String SQL_INSERT_BOOK = "INSERT INTO Book(category_id, isbn, price, title) VALUES (:category_id, :isbn, :price, :title)";

    public InsertBook(DataSource ds) {
        super(ds, SQL_INSERT_BOOK );
        declareParameter(new SqlParameter("category_id", Types.INTEGER));
        declareParameter(new SqlParameter("isbn", Types.VARCHAR));
        declareParameter(new SqlParameter("price", Types.FLOAT));
        declareParameter(new SqlParameter("title", Types.VARCHAR));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);
    }

}
