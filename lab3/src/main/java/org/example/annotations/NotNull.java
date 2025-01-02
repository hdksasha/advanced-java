package org.example.annotations;

import java.lang.annotation.*;

// Annotation to specify that a field cannot be null.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {

}