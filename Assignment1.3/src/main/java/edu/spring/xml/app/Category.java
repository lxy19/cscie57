package edu.spring.xml.app;

public class Category {
    private Long id;
    private String name;

    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Category{" +
                "book=" +book +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
