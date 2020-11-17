package edu.lix8.spring.hibernate.dao;

import edu.lix8.spring.hibernate.domain.Book;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by Xiangye Li on 10/30/2020
 */
@Transactional
@Repository("PublishingDao")
public class PublishingDaoImpl implements PublishingDao {

    private static final Log logger = LogFactory.getLog(PublishingDaoImpl .class);
    private SessionFactory sessionFactory;

    //Inject the SessionFactory
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooksWithoutAuthorsAndCategories() {
        return sessionFactory.getCurrentSession().createQuery("from Book b").list();
    }

    @Override
    public List findAllBooksWithAuthorsAndCategories() {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Book.findBookWithAuthorsAndCategories").list();
    }

    @Override
    public Book findBookWithAuthorsAndCategoryById(int bookId) {
        return (Book) sessionFactory.getCurrentSession()
                .getNamedQuery("Book.findBookWithAuthorsAndCategoryById")
                .setParameter("id",bookId).uniqueResult();
    }

    @Override
    public List<Book> findAllBooksWithCategoryAndAuthor(int authorId) {
        return sessionFactory.getCurrentSession()
                .getNamedQuery("Book.findAllBooksWithCategoryAndAuthor")
                .setParameter("id",authorId).list();
    }

    /**
     * Create a new book with a new author not persisted yet
     * @param book
     * @return
     */
    @Override
    public Book createNewBook(Book book) {
        sessionFactory.getCurrentSession().saveOrUpdate(book);
        logger.info("Book saved with id: " + book.getId());
        return book;
    }

    @Override
    public void deleteBook(Book book) {
        sessionFactory.getCurrentSession().delete(book);
        logger.info("Book deleted with id: "+book.getId());
    }

}
