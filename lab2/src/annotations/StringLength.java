package annotations;

import java.lang.annotation.*;

// Annotation to specify the minimum and maximum length constraints for a string field.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringLength {
    // The minimum length of the string.
    int min() default 0;
    // The maximum length of the string.
    int max() default Integer.MAX_VALUE;
}
