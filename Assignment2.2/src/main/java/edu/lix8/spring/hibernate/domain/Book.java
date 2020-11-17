package edu.lix8.spring.hibernate.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name=Book.FIND_BOOK_WITH_AUTHORS_AND_CATEGORIES,
                query = "select distinct b from Book b "+
                    "left join fetch b.category c " +
                    "left join fetch b.authors a"),
        @NamedQuery(name=Book.FIND_BOOK_WITH_AUTHORS_AND_CATEGORY_BY_ID,
                query = "select distinct b from Book b "+
                        "left join fetch b.category c " +
                        "left join fetch b.authors a "+
                        "where b.id = :id"),
        @NamedQuery(name = Book.FIND_ALL_BOOKS_WITH_CATEGORY_AND_AUTHOR,
                query = "select distinct b from Book b "+
                        "left join fetch b.category c " +
                        "left join fetch b.authors a "+
                        "where a.id = :id")
})

public class Book extends AbstractEntity{
    public static final String FIND_BOOK_WITH_AUTHORS_AND_CATEGORIES =
            "Book.findBookWithAuthorsAndCategories";
    public static final String FIND_BOOK_WITH_AUTHORS_AND_CATEGORY_BY_ID =
            "Book.findBookWithAuthorsAndCategoryById";
    public static final String FIND_ALL_BOOKS_WITH_CATEGORY_AND_AUTHOR =
            "Book.findAllBooksWithCategoryAndAuthor";

    @Column(name="ISBN")
    private String isbn;

    @Column(name="TITLE")
    private String title;

    @Column(name="PRICE")
    private Float price;


    @ManyToMany(cascade=CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "author_book",
        joinColumns = @JoinColumn(name = "BOOK_ID"),
        inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private Set<Author> authors = new HashSet<>();

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}');
        return sb.toString();
    }
}
