package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class FindAllBooksDemo {
    private static Logger logger = LoggerFactory.getLogger(FindAllBooksDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Listing books without authors and categories ==============");
        List<Book> bookWithoutAuthorsAndCategories = publishingService.findAllBooks();
        listBooks(bookWithoutAuthorsAndCategories);
    }

    private static void listBooks(List<Book> bookList) {
        bookList.forEach(book->{
            logger.info(book.toString()+"\n");
            logger.info("------------------------------------------");
        });
    }
}
