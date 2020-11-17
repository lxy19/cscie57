package edu.lix8.spring.jpa2.service;

import edu.lix8.spring.jpa2.domain.Book;
import edu.lix8.spring.jpa2.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.google.common.collect.Lists;

/**
 * Create by Xiangye Li on 11/14/2020
 */
@Service("PublishingService")
@Transactional
public class PublishingServiceImpl implements PublishingService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional(readOnly=true)
    public Book findBookWithAuthorsAndCategoryById(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Transactional(readOnly=true)
    public List<Book> findAllBooks() {
        return  Lists.newArrayList(bookRepository.findAll());
    }

    @Override
    public Book createNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}
