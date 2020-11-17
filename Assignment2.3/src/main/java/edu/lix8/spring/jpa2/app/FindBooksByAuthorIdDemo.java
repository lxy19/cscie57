package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import java.util.List;

/**
 * Find all books for one author id who has more than one book in
 * your database using @NamedQuery.
 */
public class FindBooksByAuthorIdDemo {
    private static Logger logger = LoggerFactory.getLogger(FindBooksByAuthorIdDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Display all Boos by the selected Author: ==============");
        List<Book> books = publishingService.findAllBooksWithCategoryAndAuthor(4);
        listBooks(books);
    }

    private static void listBooks(List<Book> bookList) {
        bookList.forEach(book->{
            logger.info(book.toString()+"\n");
            if (book.getCategory()!=null) {
                logger.info("\t" +book.getCategory().toString());
            }
            book.getAuthors().forEach(author -> logger.info("\t" +author.toString()));
            logger.info("------------------------------------------");
        });
    }
}
