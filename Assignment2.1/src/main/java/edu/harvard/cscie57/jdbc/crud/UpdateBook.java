package edu.harvard.cscie57.jdbc.crud;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class UpdateBook extends SqlUpdate {
    private final static String SQL_UPDATE_BOOK = "Update Book SET price=:price WHERE id=:id";

    public UpdateBook(DataSource ds) {
        super(ds, SQL_UPDATE_BOOK);
        super.declareParameter(new SqlParameter("price", Types.FLOAT));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

}
