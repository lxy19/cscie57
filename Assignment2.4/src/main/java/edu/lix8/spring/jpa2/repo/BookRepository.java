package edu.lix8.spring.jpa2.repo;

import edu.lix8.spring.jpa2.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer>{
    Book findById(int bookId);
}
