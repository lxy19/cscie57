package edu.harvard.cscie57.jdbc.App;

import edu.harvard.cscie57.jdbc.config.NamedJdbcCfg;
import edu.harvard.cscie57.jdbc.dao.BookCategoryDao;
import edu.harvard.cscie57.jdbc.dao.BookCategoryDaoImpl;
import edu.harvard.cscie57.jdbc.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class SelectBooksByCategoryNameApp {
    private static Logger logger = LoggerFactory.getLogger(NamedJdbcCfg.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        BookCategoryDao bookCategoryDao = ctx.getBean("bookCategoryDao", BookCategoryDaoImpl.class);
        logger.info("============== Start of Select Book By Category Name ============== ");
        List<Book> bookList = bookCategoryDao.findBooksByCategoryName("Java");
        bookList.forEach(book -> logger.info(book.toString()));
        logger.info("============== End of Select Book By Category Name ============== ");
    }
}
