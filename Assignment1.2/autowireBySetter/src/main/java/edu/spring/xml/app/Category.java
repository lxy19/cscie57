package edu.spring.xml.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Category {
    private Long id;
    private String name;
    private Book book;

    @Autowired
    @Qualifier("bookFrost")
    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Category{" +
                "book=" + book +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
