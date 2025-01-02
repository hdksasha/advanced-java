package org.example.entities;

import org.example.annotations.*;

/**
 * Represents a book with validation annotations.
 */
public class Book {

    @NotNull
    private String title;

    @NotNull
    private String author;

    @MinValue(10)
    private Integer pageCount;

    public Book(String title, String author, Integer pageCount) {
        this.title = title;
        this.author = author;
        this.pageCount = pageCount;
    }
}

