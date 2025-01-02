package annotations;

import java.lang.annotation.*;


// Annotation to specify the maximum value allowed for a field.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MaxValue {
    // The maximum value allowed for the annotated field.
    int value();
}
