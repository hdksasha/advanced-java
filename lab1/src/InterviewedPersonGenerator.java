import java.util.Random;
import java.util.stream.Stream;

public class InterviewedPersonGenerator {
    private final static String[] CITIES = {"Kyiv", "Lviv", "Kharkiv", "Cherkasy"};
    private final static String[] FIRST_NAMES = {"Andrii", "Olena", "Dmytro", "Oksana", "Mykola", "Iryna"};
    private final static String[] LAST_NAMES = {"Shevchenko", "Kovalenko", "Bondarenko", "Tkachenko", "Kravchenko", "Melnyk"};
    private final static Random RANDOM = new Random();

    public static Stream<InterviewedPerson> generateInterviewedPersons() {
        return Stream.generate(() -> new InterviewedPerson(
            CITIES[RANDOM.nextInt(CITIES.length)], // Random city
            RANDOM.nextInt(56) + 20,              // Random age between 20 and 75
            FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)], // Random first name
            LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)],   // Random last name
            Math.round((20000 + (RANDOM.nextDouble() * 80000)) * 100.0) / 100.0  // Random salary between 20000 and 100000
        ));
    }
}
