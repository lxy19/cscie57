package edu.lix8.spring.jpa2.app;

import edu.lix8.spring.jpa2.domain.Author;
import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.domain.Category;
import edu.lix8.spring.jpa2.service.PublishingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;
import java.util.HashSet;

public class InsertBookWithAuthorDemo {
    private static Logger logger = LoggerFactory.getLogger(InsertBookWithAuthorDemo.class);

    public static void main(String[]args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();
        PublishingService publishingService = ctx.getBean(PublishingService.class);

        logger.info("============== Create a new book with new authors not yet persisted ==============");
        Author author1 = new Author();
        author1.setDescription("a PhD candidate in Computer Science at the University of Cambridge");
        author1.setFirst_name("Raoul-Gabriel");
        author1.setLast_name("Urma");
        Author author2 = new Author();
        author2.setDescription("a senior software engineer at Red Hat");
        author2.setFirst_name("Mario");
        author2.setLast_name("Fusco");
        Category category = publishingService.findAllBooks().get(0).getCategory();
        Book book = new Book();
        book.setIsbn("0321349812");
        book.setPrice(48.18F);
        book.setTitle("Modern Java in Action");
        book.setAuthors(new HashSet<>(List.of(author1, author2)));
        book.setCategory(category);
        book = publishingService.createNewBook(book);
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
