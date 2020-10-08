package edu.harvard.cscie57.bookpublishing.domain;

import java.io.Serializable;

public class Book implements Serializable {
    private Integer id;
    private Integer category_id;
    private String isbn;
    private String title;
    private Float price;
}
