package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class FindAllBooksAuthorsCategoriesDemo {
    private static Logger logger = LoggerFactory.getLogger(FindAllBooksAuthorsCategoriesDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Listing books with authors and category names ==============");
        List<Book> books= publishingService.findAllBooksWithAuthorsAndCategories();
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
