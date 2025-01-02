import entities.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create a new Student object
            Student student = new Student("1", "Alice", 25);
            // Create a new Book object
            Book book = new Book("Harry Potter and the Chamber of Secrets", "J. K. Rowling", 384);
            // Create a new Car object
            Car car = new Car("Audi", 2003, 1900);

            // Validate Student and Car objects using annotation-based validation
            AnnotationValidator.validate(student);
            AnnotationValidator.validate(car);

            // Measure validation time for Book object using reflection
            long startReflection = System.nanoTime();
            AnnotationValidator.validate(book);
            long endReflection = System.nanoTime();

            // Output the time taken for validation using reflection
            System.out.println("Validation with reflection for 'book' took: " + (endReflection - startReflection) + " ns");

            // Measure validation time for Book object using manual validation
            long startManual = System.nanoTime();
            book.validate();
            long endManual = System.nanoTime();

            // Output the time taken for manual validation
            System.out.println("Validation without reflection for 'book' took: " + (endManual - startManual) + " ns");

            // Confirm that all objects are valid
            System.out.println("All objects are valid.");
        } catch (Exception e) {
            // Handle validation errors and print the message
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}
