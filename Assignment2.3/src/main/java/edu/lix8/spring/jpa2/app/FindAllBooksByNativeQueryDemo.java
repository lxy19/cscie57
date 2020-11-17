package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class FindAllBooksByNativeQueryDemo {
    private static Logger logger = LoggerFactory.getLogger( FindAllBooksByNativeQueryDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Books found by native query ==============");
        List<Book> books = publishingService.findAllBooksByNativeQuery();
        listBooks(books);
    }

    private static void listBooks(List<Book> bookList) {
        bookList.forEach(book->{
            logger.info(book.toString()+"\n");
            logger.info("------------------------------------------");
        });
    }
}
