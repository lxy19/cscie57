package edu.harvard.cscie57.jdbc.App;

import edu.harvard.cscie57.jdbc.config.NamedJdbcCfg;
import edu.harvard.cscie57.jdbc.dao.BookCategoryDao;
import edu.harvard.cscie57.jdbc.dao.BookCategoryDaoImpl;
import edu.harvard.cscie57.jdbc.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class FindAllCategoriesApp {
    private static Logger logger = LoggerFactory.getLogger(NamedJdbcCfg.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(NamedJdbcCfg.class);
        BookCategoryDao bookCategoryDao = ctx.getBean("bookCategoryDao", BookCategoryDaoImpl.class);
        logger.info("============== Start of Find All Categories ============== ");
        List<Category> categories = bookCategoryDao.findAllCategories();
        categories.forEach(category -> logger.info(category.toString()));
        logger.info("============== End of Find All Categories ============== ");
    }
}