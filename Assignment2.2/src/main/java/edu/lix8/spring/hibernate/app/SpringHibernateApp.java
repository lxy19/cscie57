package edu.lix8.spring.hibernate.app;

import edu.lix8.spring.hibernate.config.AppConfig;
import edu.lix8.spring.hibernate.dao.PublishingDao;
import edu.lix8.spring.hibernate.domain.Author;
import edu.lix8.spring.hibernate.domain.Book;
import edu.lix8.spring.hibernate.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.HashSet;
import java.util.List;

public class SpringHibernateApp {
    private static Logger logger = LoggerFactory.getLogger(
            SpringHibernateApp.class);
    public static void main(String... args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        PublishingDao publishingDao = ctx.getBean(PublishingDao.class);

        logger.info("============== Listing books without authors and categories ==============");
        List<Book> bookWithoutAuthorsAndCategories = publishingDao.findAllBooksWithoutAuthorsAndCategories();
        listBooks(bookWithoutAuthorsAndCategories);

        logger.info("============== Listing books with authors and categories ==============");
        List<Book> bookWithAuthorsAndCategories = publishingDao.findAllBooksWithAuthorsAndCategories();
        listBooksWithAuthorsAndCategories(bookWithAuthorsAndCategories);

        logger.info("============== Listing a book with authors and category by book id ==============");
        Book booksWithAuthorsAndCategoryById = publishingDao.findBookWithAuthorsAndCategoryById(10);
        listBooksWithAuthorsAndCategories(List.of(booksWithAuthorsAndCategoryById));
        logger.info("============== Listing all books with author id ==============");
        List<Book> booksWithAuthorId = publishingDao.findAllBooksWithCategoryAndAuthor(4);
        listBooksWithAuthorsAndCategories(booksWithAuthorId);

        logger.info("============== Create a new book with new authors not yet persisted ==============");
        Author author = new Author();
        author.setDescription("a PhD candidate in Computer Science at the University of Cambridge. He is co-author of the book Java 8 in Action");
        author.setFirst_name("Raoul-Gabriel");
        author.setLast_name("Urma");
        Category category = new Category();
        category.setName("Java");
        category.setId(1);
        Book book = new Book();
        book.setIsbn("0321349812");
        book.setPrice(48.18F);
        book.setTitle("Modern Java in Action");
        book.setAuthors(new HashSet<>(List.of(author)));
        book.setCategory(category);
        publishingDao.createNewBook(book);
        listBooksWithAuthorsAndCategories(publishingDao.findAllBooksWithoutAuthorsAndCategories());
        ctx.close();
    }
    private static void listBooks(List<Book> bookList) {
      bookList.forEach(book->{
            logger.info(book.toString()+"\n");
            logger.info("------------------------------------------");
        });
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
