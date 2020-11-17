package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class FindBookAuthorsCategoryByBookIdDemo {
    private static Logger logger = LoggerFactory.getLogger(FindBookAuthorsCategoryByBookIdDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Listing book 10 with details: ==============");
        Book book= publishingService.findBookWithAuthorsAndCategoryById(10);
        listBook(book);
    }

    private static void listBook(Book book) {
            logger.info(book.toString()+"\n");
            if (book.getCategory()!=null) {
                logger.info("\t" +book.getCategory().toString());
            }
            book.getAuthors().forEach(author -> logger.info("\t" +author.toString()));
            logger.info("------------------------------------------");
    }
}
