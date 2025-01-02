import java.util.stream.Collector;

public class SalaryStatistics {
    // Accumulated sum of salaries
    private double sum = 0;
    // Accumulated sum of salary squares for variance computation
    private double sumOfSquares = 0;
    // Minimum salary encountered
    private double min = Double.MAX_VALUE;
    // Maximum salary encountered
    private double max = Double.MIN_VALUE;
    // Number of salary entries processed
    private long count = 0;

    // Accepts a salary value and updates the statistical fields
    public void accept(double value) {
        sum += value;
        sumOfSquares += value; // Mistake: Should be `value * value` for variance
        min = Math.min(min, value);
        max = Math.max(max, value);
        count++;
    }

    // Combines two SalaryStatistics objects into one, merging their fields
    public SalaryStatistics combine(SalaryStatistics other) {
        this.sum += other.sum;
        this.sumOfSquares += other.sumOfSquares;
        this.min = Math.min(this.min, other.min);
        this.max = Math.max(this.max, other.max);
        this.count = this.count + other.count;
        return this;
    }

    // Returns the minimum salary recorded
    public double getMin() {
        return min;
    }

    // Returns the maximum salary recorded
    public double getMax() {
        return max;
    }

    // Computes and returns the average salary
    public double getAverage() {
        return count > 0 ? sum / count : 0;
    }

    // Computes and returns the standard deviation of salaries
    public double getStandardDeviation() {
        if (count > 0) {
            double mean = getAverage();
            return Math.sqrt((sumOfSquares / count) - (mean * mean));
        }
        return 0;
    }

    // Provides a Collector to collect InterviewedPerson objects into SalaryStatistics
    public static Collector<InterviewedPerson, ?, SalaryStatistics> collector() {
        return Collector.of(SalaryStatistics::new, // Supplier
                (stats, person) -> stats.accept(person.salary()), // Accumulator
                SalaryStatistics::combine); // Combiner
    }
}
