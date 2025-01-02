import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    // Entry point of the application
    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Prompt user to enter the number of persons to skip
        System.out.print("Enter number of skipped persons: ");
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Prompt user to enter the city to skip persons from
        System.out.print("Enter city to skip persons: ");
        String city = scanner.nextLine();

        // Generate a list of interviewed persons based on user input
        List<InterviewedPerson> persons = InterviewedPersonGenerator.generateInterviewedPersons()
                .gather(new InterviewedPersonGatherer(city, N, 500)) // Custom gather logic
                .toList();

        // Prompt user to specify the age range for filtering
        System.out.print("Enter start age to filter persons: ");
        int startAge = scanner.nextInt();
        System.out.print("Enter end age to filter persons: ");
        int endAge = scanner.nextInt();

        // Group persons by their first names, filtered by age
        Map<String, List<InterviewedPerson>> groupedByNameAndFilteredByAge = persons.stream()
                .filter(person -> (person.age() >= startAge) && (person.age() <= endAge))
                .collect(Collectors.groupingBy(InterviewedPerson::firstName));

        // Print the grouped and filtered results
        System.out.printf("Person in age between %d and %d and grouped by names:%n", startAge, endAge);
        System.out.println(groupedByNameAndFilteredByAge);

        // Collect salary statistics (min, max, average)
        SalaryStatistics stats = persons.stream()
                .collect(SalaryStatistics.collector());

        // Display salary statistics
        System.out.printf("Min Salary: %.2f\n", stats.getMin());
        System.out.printf("Max Salary: %.2f\n", stats.getMax());
        System.out.printf("Average Salary: %.2f\n", stats.getAverage());

        // Extract and sort salaries for percentile calculations
        List<Double> salaries = persons.stream()
                .map(InterviewedPerson::salary)
                .sorted()
                .collect(Collectors.toList());

        // Calculate IQR and identify outliers
        double q1 = getPercentile(salaries, 25); // 25th percentile
        double q3 = getPercentile(salaries, 75); // 75th percentile
        double iqr = q3 - q1;

        // Group persons into outliers and regular data based on salary thresholds
        Map<String, Long> outlierGroups = persons.stream()
                .collect(Collectors.groupingBy(
                        person -> (person.salary() < q1 * 0.75 || person.salary() > q3 * 1.25) ? "outliers" : "data",
                        Collectors.counting()
                ));

        // Display IQR and outlier analysis
        System.out.printf("IQR: %.2f\n", iqr);
        System.out.println("Outlier Analysis: " + outlierGroups);
    }

    // Helper method to calculate a specific percentile from a sorted list of values
    private static double getPercentile(List<Double> sortedValues, double percentile) {
        int index = (int) Math.ceil(percentile / 100.0 * sortedValues.size()) - 1; // Calculate index
        return sortedValues.get(index); // Return value at index
    }
}
