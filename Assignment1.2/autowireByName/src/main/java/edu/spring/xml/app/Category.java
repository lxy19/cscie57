package edu.spring.xml.app;

public class Category {
    private Long id;
    private String name;

    private Book bookWhiteman;

    public void setBookWhiteman(Book bookWhiteman) {
        this.bookWhiteman = bookWhiteman;
    }

    public Book getBookWhiteman() {
        return bookWhiteman;
    }

    @Override
    public String toString() {
        return "Category{" +
                "book=" + getBookWhiteman() +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
