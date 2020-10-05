package edu.spring.xml.app;

public class Category {
    private Long id;
    private String name;

    private Book book;

    public Category(Long id, String name, Book book) {
        this.id = id;
        this.name = name;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Category{" +
                "book=" + getBook() +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
