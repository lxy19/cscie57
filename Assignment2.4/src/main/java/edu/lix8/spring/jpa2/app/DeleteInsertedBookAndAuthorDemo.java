package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class DeleteInsertedBookAndAuthorDemo {
    private static Logger logger = LoggerFactory.getLogger(DeleteInsertedBookAndAuthorDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Delete the saved book and authors from database ==============");
        int bookId = 12;
        Book bookDelete = publishingService.findBookWithAuthorsAndCategoryById(bookId);
        publishingService.deleteBook(bookDelete);
        listBooks(publishingService.findAllBooks());
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
