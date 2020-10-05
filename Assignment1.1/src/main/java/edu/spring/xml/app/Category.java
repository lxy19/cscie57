package edu.spring.xml.app;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Category {
    private Long id;
    private String name;
    private Set<Book> booksSet;
    private List<Book> booksList;
    private Map<String, Book> booksMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List list) {
        this.booksList = list;
    }

    public void setSet(Set set) {
        this.booksSet = set;
    }

    public void setMap(Map<String, Book> map) { this.booksMap = map;}

    @Override
    public String toString() {
        String str = "Category{" +
                "id=" + id +
                ", name=" + name ;
        if (booksSet!=null) {
            str += ", booksSet=" + booksSet;
        }
        if (booksList!=null) {
            str +=  ", booksList=" + booksList;
        }
        if (booksMap!=null) {
            str += ", booksMap=" + booksMap;
        }
        str += '}';
        return str;
    }
}
