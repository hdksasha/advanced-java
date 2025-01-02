package org.example.annotations;

import java.lang.annotation.*;


// Annotation to specify the minimum value allowed for a field.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinValue {
    // The minimum value allowed for the annotated field.
    int value();
}
