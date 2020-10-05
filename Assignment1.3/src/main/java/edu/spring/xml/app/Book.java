package edu.spring.xml.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Book {
    private Long id;
    private String isbn;
    private String title;
    private Float price;
    private static final Logger logger =
            LoggerFactory.getLogger(Category.class);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void reading() {
        logger.info("Reading " + title + " book");
    }

    public void throwException() {
        throw new RuntimeException("Book Bean Exception");
    }

    @Override
    public String toString() {
        return "Book - {" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                "} ";
    }
}
