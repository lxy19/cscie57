package edu.harvard.cscie57.jdbc.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class DeleteBook extends SqlUpdate {
    private final static String SQL_DELETE_BOOK = "DELETE FROM book WHERE id = :id";

    public DeleteBook(DataSource ds) {
        super(ds, SQL_DELETE_BOOK);
        declareParameter(new SqlParameter("id", Types.INTEGER));
    }

}