package entities;

import annotations.*;

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

    // Validates the book by checking that the title and the author is not null and that the pageCount is more than 9.
    public void validate() {
        if (this.title == null || this.title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (this.author == null || this.author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (this.pageCount < 10) {
            throw new IllegalArgumentException("Page count cannot be less than 10");
        }
    }
}

