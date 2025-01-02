package org.example;

import org.example.entities.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new Student object
            Student student = new Student("1", "Alice", 25);
            // Create a new Book object
            Book book = new Book("Harry Potter and the Chamber of Secrets", "J. K. Rowling", 384);
            // Create a new Car object
            Car car = new Car("Audi", 2003, 1900);

            // Validate Student, Car and Book objects using annotation-based validation
            AnnotationValidator.validate(student);
            AnnotationValidator.validate(car);
            AnnotationValidator.validate(book);

            // Confirm that all objects are valid
            System.out.println("All objects are valid.");
        } catch (Exception e) {
            // Handle validation errors and print the message
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}