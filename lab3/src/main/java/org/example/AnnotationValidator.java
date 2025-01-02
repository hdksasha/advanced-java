package org.example;

import org.example.annotations.*;
import java.lang.reflect.*;

/**
 * This class uses reflection to validate fields based on annotations like @NotNull, @StringLength, @MinValue,
 * and @MaxValue. If any validation fails, an exception is thrown.
 */
public class AnnotationValidator {

    /**
     * Validates the fields of the provided object according to the annotations present.
     * Throws an exception if any validation fails.
     */
    public static void validate(Object obj) throws IllegalAccessException {
        // Get the class of the object
        Class<?> clazz = obj.getClass();

        // Iterate through each field
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // Access private fields

            Object value = field.get(obj); // Get field value

            // Check @NotNull annotation
            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                throw new IllegalArgumentException("Class: " + clazz.getSimpleName() + ". Field " + field.getName() + " cannot be null");
            }

            // Check @StringLength annotation
            if (field.isAnnotationPresent(StringLength.class)) {
                if (value instanceof String str) {
                    StringLength annotation = field.getAnnotation(StringLength.class);
                    if (str.length() < annotation.min() || str.length() > annotation.max()) {
                        throw new IllegalArgumentException("Class: " + clazz.getSimpleName() + ". Field " + field.getName() + " length must be between " + annotation.min() + " and " + annotation.max());
                    }
                } else {
                    throw new IllegalArgumentException("@StringLength is only valid for String fields. Field: " + field.getName() + " is not a String");
                }
            }

            // Check @MinValue annotation
            if (field.isAnnotationPresent(MinValue.class)) {
                if (value instanceof Integer intValue) {
                    MinValue annotation = field.getAnnotation(MinValue.class);
                    if (intValue < annotation.value()) {
                        throw new IllegalArgumentException("Class: " + clazz.getSimpleName() + ". Field " + field.getName() + " must be greater than or equal to " + annotation.value());
                    }
                } else {
                    throw new IllegalArgumentException("@MinValue is only valid for Integer fields. Field: " + field.getName() + " is not an Integer");
                }
            }

            // Check @MaxValue annotation
            if (field.isAnnotationPresent(MaxValue.class)) {
                if (value instanceof Integer intValue) {
                    MaxValue annotation = field.getAnnotation(MaxValue.class);
                    if (intValue > annotation.value()) {
                        throw new IllegalArgumentException("Class: " + clazz.getSimpleName() + ". Field " + field.getName() + " must be less than or equal to " + annotation.value());
                    }
                }
            }
        }
    }
}
