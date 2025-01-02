import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class InterviewedPersonGatherer implements Gatherer<InterviewedPerson, AtomicInteger, InterviewedPerson> {

    // City whose residents should be skipped during gathering
    private final String cityToSkip;
    // Number of persons to skip from the specified city
    private final int skipCount;
    // Counter for the number of persons added to the downstream collector
    private int addedCount;
    // Maximum number of persons to add to the downstream collector
    private final int total;

    // Constructor initializes the gatherer with the skip logic and total count
    public InterviewedPersonGatherer(String cityToSkip, int skipCount, int total) {
        this.cityToSkip = cityToSkip;
        this.skipCount = skipCount;
        this.addedCount = 0; // Tracks the number of persons added
        this.total = total; // Maximum persons allowed in the downstream collection
    }

    // Supplies a new AtomicInteger state for tracking skipped persons
    @Override
    public Supplier<AtomicInteger> initializer() {
        return AtomicInteger::new;
    }

    // Defines the logic for processing elements and passing them to the downstream collector
    @Override
    public Integrator<AtomicInteger, InterviewedPerson, InterviewedPerson> integrator() {
        return (state, element, downstream) -> {
            // Skip the person if they are from the specified city and skipCount is not reached
            if (element.city().equalsIgnoreCase(cityToSkip) && state.get() < skipCount) {
                state.incrementAndGet(); // Increment the skip counter
                return true; // Continue processing
            }
            // Add the person to the downstream if total count is not exceeded
            else if (addedCount < total) {
                addedCount += 1; // Increment added counter
                return downstream.push(element); // Push the element to the downstream
            }
            // Stop further processing if conditions are not met
            return false;
        };
    }

    // Combines states from multiple threads (default implementation from Gatherer)
    @Override
    public BinaryOperator<AtomicInteger> combiner() {
        return Gatherer.super.combiner();
    }

    // Finalizes the result of the gather operation (default implementation from Gatherer)
    @Override
    public BiConsumer<AtomicInteger, Downstream<? super InterviewedPerson>> finisher() {
        return Gatherer.super.finisher();
    }
}
