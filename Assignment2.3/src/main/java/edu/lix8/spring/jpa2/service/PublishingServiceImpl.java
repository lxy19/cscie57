package edu.lix8.spring.jpa2.service;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.domain.Book_;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Create by Xiangye Li on 11/14/2020
 */
@Service("PublishingService")
@Repository
@Transactional
public class PublishingServiceImpl implements PublishingService {
    final static String ALL_BOOK_NATIVE_QUERY =
            "select id, category_id, isbn, title, price from book";
    private static final Log logger = LogFactory.getLog(PublishingServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Find all books without authors and categories
     */
    @Transactional(readOnly = true)
    @Override
    public List<Book> findAllBooks() {
        List<Book> books = em.createNamedQuery(Book.FIND_ALL, Book.class).getResultList();
        return books;
    }

    @Transactional(readOnly = true)
    @Override
    public List findAllBooksWithAuthorsAndCategories() {
        List<Book> books = em.createNamedQuery(Book.FIND_BOOK_WITH_AUTHORS_AND_CATEGORIES, Book.class).getResultList();
        return books;
    }

    @Override
    public Book findBookWithAuthorsAndCategoryById(int bookId) {
        logger.info("Find Book with Authors And Category By Book Id\n" + "using JPA 2 Criteria API");
        //retrieve an instance of criteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //create a typed query passing in book as the result type
        CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
        //The CriteriaQuery.from() method is invoked, passing in the entity class.
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        criteriaQuery.select(bookRoot).distinct(true);
        bookRoot.join(Book_.category, JoinType.LEFT);
        bookRoot.join(Book_.authors, JoinType.LEFT);
        //The CriteriaQuery.select() method is called and passes the root query object
        //as the result type. The distinct() method with true means that duplicate records
        //should be eliminated.
        criteriaQuery.select(bookRoot).distinct(true);
        //The CriteriaQuery.select() method is called and passes the root query object
        //as the result type. The distinct() method with true means that duplicate records
        //should be eliminated.
        Predicate criteria = cb.conjunction();
        Predicate p = cb.equal(bookRoot.get(Book_.id), bookId);
        criteria = cb.and(criteria, p);

        //The Predicate "criteria" is constructed with all the criteria and restrictions and passed as the
        //where clause to the query by calling the CriteriaQuery.where() method.
        criteriaQuery.where(criteria);

        return em.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Book> findAllBooksWithCategoryAndAuthor(int authorId) {
        TypedQuery<Book> query = em.createNamedQuery(
                Book.FIND_ALL_BOOKS_WITH_CATEGORY_AND_AUTHOR, Book.class);
        query.setParameter("id", authorId);

        return query.getResultList();
    }

    /**
     * Create and save in your database a new book with an author(s).
     * @param book
     * @return
     */
    @Override
    public Book createNewBook(Book book) {
        if (book.getId()==null) {
            logger.info("Insert a new book");
            em.persist(book);
        } else {
          logger.info("Update an existing book");
          em.merge(book);
        }
        logger.info("Book saved with id: "+book.getId());
        return book;
    }

    @Override
    public void deleteBook(Book book) {
        Book mergedBook = em.merge(book);
        em.flush();
        em.remove(mergedBook);
        logger.info("Book with id: " + mergedBook.getId()  + " deleted successfully");
    }

    @Override
    public List<Book> findAllBooksByNativeQuery() {
        return em.createNativeQuery(ALL_BOOK_NATIVE_QUERY,
                "bookResult").getResultList();
    }

}
