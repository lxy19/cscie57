package edu.lix8.spring.hibernate.app;

import edu.lix8.spring.hibernate.config.AppConfig;
import edu.lix8.spring.hibernate.dao.PublishingDao;
import edu.lix8.spring.hibernate.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateAppDeleteBook {
    private static Logger logger = LoggerFactory.getLogger(
            SpringHibernateApp.class);
    public static void main(String... args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        PublishingDao publishingDao = ctx.getBean(PublishingDao.class);
        logger.info("============== Delete the saved book and authors from database ==============");
        int bookId = 12;
        Book bookDelete = publishingDao.findBookWithAuthorsAndCategoryById(bookId);
        publishingDao.deleteBook(bookDelete);
        listBooksWithAuthorsAndCategories(publishingDao.findAllBooksWithoutAuthorsAndCategories());
        ctx.close();
    }

    private static void listBooksWithAuthorsAndCategories(List<Book> bookList) {
        bookList.forEach(book->{
            logger.info(book.toString()+"\n");
            if (book.getCategory()!=null) {
                logger.info(book.getCategory().toString());
            }
            book.getAuthors().forEach(author -> logger.info(author.toString()));
            logger.info("------------------------------------------");
        });
    }
}
